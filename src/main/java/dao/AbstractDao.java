package dao;

import dbcp.ConnectionPool;
import entity.Account;
import entity.BaseEntity;
import exception.ConnectionException;
import exception.DaoException;
import exception.MapperException;
import mapper.Mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public abstract class AbstractDao<T extends BaseEntity> implements Dao<T> {

    protected final String TABLE_NAME;
    protected final String GET_ONE_BY_ID;
    protected final String GET_ONE_BY_NAME;
    protected final String GET_ALL;
    protected final String DELETE;
    protected String CREATE;
    protected String UPDATE;

    protected AbstractDao(String tableName) {
        this.TABLE_NAME = tableName;
        GET_ONE_BY_ID = "select * from " + tableName + " where id = ?";
        GET_ONE_BY_NAME = "select * from " + tableName + " where name = ?";
        GET_ALL = "select * from " + tableName;
        DELETE = "delete from " + tableName + " where id = ?";
        CREATE = "insert into " + tableName + " (name) values (?)";
        UPDATE = "update " + tableName + " set name = ? where id = ?";
    }

    protected Optional<T> getOneQuery(String querySQL, List<Object> params) throws DaoException {
        List<T> result = getManyQuery(querySQL, params);
        if (result.isEmpty()) {
            return Optional.empty();
        }
        if (result.size() == 1) {
            T value = result.get(0);
            return Optional.of(value);
        }
        return Optional.empty();
    }

    protected List<T> getManyQuery(String querySQL, List<Object> parameters) throws DaoException {
        try {
            List<T> objects = new ArrayList<>();
            PreparedStatement preparedStatement = getPreparedStatement(querySQL, parameters);
            ResultSet resultSet = preparedStatement.executeQuery();
            Mapper mapper = getMapper();
            while (resultSet.next()) {
                T item = (T) mapper.toEntity(resultSet);
                objects.add(item);
            }
            if (objects.isEmpty()) {
                return Collections.emptyList();
            }
            return objects;
        } catch (SQLException | MapperException | ConnectionException ex) {
            ex.printStackTrace();
            throw new DaoException(ex.getMessage());
        }
    }

    protected boolean updateQuery(String querySQL, List<Object> parameters) throws DaoException {
        try {
            PreparedStatement preparedStatement = getPreparedStatement(querySQL, parameters);
            final int affectedRows = preparedStatement.executeUpdate();
            return affectedRows != 0;
        } catch (SQLException | ConnectionException ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    protected PreparedStatement getPreparedStatement(String querySQL, List<Object> parameters)
            throws SQLException, ConnectionException {
        final Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(querySQL);
            if (parameters != null) {
                int length = parameters.size();
                for (int i = 0; i < length; i++) {
                    Object value = parameters.get(i);
                    if (value == null) {
                        preparedStatement.setString(i + 1, null);
                        continue;
                    }
                    preparedStatement.setString(i + 1, String.valueOf(value));
                }
            }
            return preparedStatement;
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public List<T> retrieveAll() throws DaoException {
        return getManyQuery(GET_ALL, null);
    }

    @Override
    public Optional<T> retrieveById(Integer id) throws DaoException {
        return getOneQuery(GET_ONE_BY_ID, Collections.singletonList(id));
    }

    @Override
    public Optional<T> retrieveByName(String name) throws DaoException {
        return getOneQuery(GET_ONE_BY_NAME, Collections.singletonList(name));
    }

    @Override
    public void delete(Integer id) throws DaoException {
        updateQuery(DELETE, Collections.singletonList(id));
    }

    @Override
    public void create(T entity) throws DaoException {
        if (retrieveByName(entity.getName()).isPresent()) {
            if (entity.getId() == null) {
                entity.setId(retrieveByName(entity.getName()).get().getId());
            }
            update(entity);
            return;
        }
        updateQuery(CREATE, Arrays.asList(entity.getName()));
    }

    @Override
    public void update(T entity) throws DaoException {
        updateQuery(UPDATE, Arrays.asList(entity.getName(), entity.getId()));
    }

    protected void setCreateQuery(String CREATE) {
        this.CREATE = CREATE;
    }

    protected void setUpdateQuary(String UPDATE) {
        this.UPDATE = UPDATE;
    }

    private <L extends BaseEntity, K extends BaseEntity> void setEntitiesRelations(L firstEntity, String query, K secondEntity) throws DaoException {
        updateQuery(query, Arrays.asList(firstEntity.getId(), secondEntity.getId()));
    }

    private <L extends BaseEntity, K extends BaseEntity> void createDependentEntities(K entity, String queryEntityToEntity, List<L> dependentEntities) throws DaoException {
        Class<L> dClass = (Class<L>) dependentEntities.stream().findAny().get().getClass();
        Dao<L> dao = Dao.of(dClass);
        for (L dependentEntity : dependentEntities) {

            dao.create(dependentEntity);
            if (dClass.getSimpleName().equals("Account")) {
                dependentEntity.setId(((AccountDao) dao).retrieveByLogin(
                        ((Account) dependentEntity).getLogin()).get().getId());
            } else {
                dependentEntity.setId(dao.retrieveByName(
                        dependentEntity.getName()).get().getId());
            }

            setEntitiesRelations(entity, queryEntityToEntity, dependentEntity);
        }
    }

    protected void createDependencies(T entity, List<String> queries, List<? extends BaseEntity>... lists) throws DaoException {
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] == null || lists[i].isEmpty()) {
                continue;
            }
            createDependentEntities(entity, queries.get(i), lists[i]);
        }
    }
}
