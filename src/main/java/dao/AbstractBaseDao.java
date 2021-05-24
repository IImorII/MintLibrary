package dao;

import dao.impl.ProxyDaoFactory;
import entity.BaseEntity;
import exception.ConnectionException;
import exception.DaoException;

import java.util.*;

public abstract class AbstractBaseDao<T extends BaseEntity> implements BaseDao<T> {

    protected final String TABLE_NAME;
    protected final String GET_ONE_BY_ID;
    protected final String GET_ONE_BY_NAME;
    protected final String GET_ALL;
    protected final String DELETE;
    protected String CREATE;
    protected String UPDATE;

    protected AbstractBaseDao(String tableName) {
        this.TABLE_NAME = tableName;
        GET_ONE_BY_ID = "select * from " + tableName + " where id = ?";
        GET_ONE_BY_NAME = "select * from " + tableName + " where name = ?";
        GET_ALL = "select * from " + tableName;
        DELETE = "delete from " + tableName + " where id = ?";
        CREATE = "insert into " + tableName + " (name) values (?)";
        UPDATE = "update " + tableName + " set name = ? where id = ?";
    }

    @Override
    public List<T> getAll() throws DaoException, ConnectionException {
        return getManyQuery(GET_ALL, null);
    }

    @Override
    public Optional<T> getById(Integer id) throws DaoException, ConnectionException {
        return getOneQuery(GET_ONE_BY_ID, Collections.singletonList(id));
    }

    @Override
    public Optional<T> getByName(String name) throws DaoException, ConnectionException {
        return getOneQuery(GET_ONE_BY_NAME, Collections.singletonList(name));
    }

    @Override
    public void delete(Integer id) throws DaoException, ConnectionException {
        updateQuery(DELETE, Collections.singletonList(id));
    }

    @Override
    public void create(T entity) throws DaoException, ConnectionException {
        if (getByName(entity.getName()).isPresent()) {
            if (entity.getId() == null) {
                entity.setId(getByName(entity.getName()).get().getId());
            }
            update(entity);
            return;
        }
        updateQuery(CREATE, Arrays.asList(entity.getName()));
    }

    @Override
    public void update(T entity) throws DaoException, ConnectionException {
        updateQuery(UPDATE, Arrays.asList(entity.getName(), entity.getId()));
    }

    protected void setCreateQuery(String CREATE) {
        this.CREATE = CREATE;
    }

    protected void setUpdateQuary(String UPDATE) {
        this.UPDATE = UPDATE;
    }

    private <L extends BaseEntity, K extends BaseEntity> void setEntitiesRelations(L firstEntity, String query, K secondEnity) {
        try {
            updateQuery(query, Arrays.asList(firstEntity.getId(), secondEnity.getId()));
        } catch (DaoException | ConnectionException ex) {
            log.error(ex.getMessage());
        }
    }

    private <L extends BaseEntity, K extends BaseEntity> void createDependentEntities(K entity, String queryEntityToEntity, List<L> dependentEntities) {
        BaseDao<L> dao = ProxyDaoFactory.getDaoFor(dependentEntities.stream().findAny().get().getClass());
        for (L dependentEntity : dependentEntities) {
            try {
                dao.create(dependentEntity);
                dependentEntity.setId(dao.getByName(dependentEntity.getName()).get().getId());
            } catch (DaoException | ConnectionException ex) {
                log.error(ex.getMessage());
            }
            setEntitiesRelations(entity, queryEntityToEntity, dependentEntity);
        }
    }

    protected void createDependencies(T entity, List<String> queries, List<? extends BaseEntity>... lists) {
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] == null || lists[i].isEmpty()) {
                continue;
            }
            createDependentEntities(entity, queries.get(i), lists[i]);
        }
    }
}
