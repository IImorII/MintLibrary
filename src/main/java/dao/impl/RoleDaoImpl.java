package dao.impl;

import dao.BaseDao;
import dao.RoleDao;
import dao.mapper.Mapper;
import dao.mapper.RoleMapper;
import entity.Role;
import exception.ConnectionException;
import exception.DaoException;

import java.util.*;

public class RoleDaoImpl implements RoleDao {

    private static RoleDaoImpl INSTANCE;

    private RoleDaoImpl() {

    }

    public static RoleDaoImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RoleDaoImpl();
        }
        return INSTANCE;
    }

    private static final String GET_ONE_BY_ID = "select * from role where id = ?";
    private static final String GET_ONE_BY_NAME = "select * from role where role = ?";
    private static final String GET_ALL = "select * from role";
    private static final String CREATE = "insert into role (role) values (?)";
    private static final String UPDATE = "update role set role = ? where id = ?";
    private static final String DELETE = "delete from role where id = ?";

    @Override
    public List<Role> getAll() throws DaoException, ConnectionException {
        return getManyQuery(GET_ALL, null);
    }

    @Override
    public Optional<Role> getById(Integer id) throws DaoException, ConnectionException {
        return getOneQuery(GET_ONE_BY_ID, Arrays.asList(id));
    }

    @Override
    public Optional<Role> getByName(String name) throws DaoException, ConnectionException {
        return getOneQuery(GET_ONE_BY_NAME, Arrays.asList(name));
    }

    @Override
    public void create(Role entity) throws DaoException, ConnectionException {
        updateQuery(CREATE, Arrays.asList(entity.getName()));
    }

    @Override
    public void update(Role entity) throws DaoException, ConnectionException {
        updateQuery(UPDATE, Arrays.asList(entity.getName(), entity.getId()));
    }

    @Override
    public void delete(Integer id) throws DaoException, ConnectionException {
        updateQuery(DELETE, Collections.singletonList(id));
    }

    @Override
    public Mapper<Role> getMapper() {
        return new RoleMapper();
    }
}
