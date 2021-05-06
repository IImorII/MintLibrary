package dao.impl;

import dao.BaseDao;
import dao.UserDao;
import dao.mapper.Mapper;
import dao.mapper.UserMapper;
import entity.User;
import exception.ConnectionException;
import exception.DaoException;

import java.util.*;

public class UserDaoImpl implements UserDao {

    private static UserDaoImpl INSTANCE;

    private UserDaoImpl() {

    }

    public static UserDaoImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserDaoImpl();
        }
        return INSTANCE;
    }

    private static final String GET_ONE_BY_ID = "select * from user where id = ?";
    private static final String GET_ONE_BY_NAME = "select * from user where name = ?";
    private static final String GET_ALL = "select * from user";
    private static final String CREATE = "insert into user (name, login, password, role_id_fk) values (?, ?, ?, ?)";
    private static final String UPDATE = "update user set name = ?, login = ?, password = ?, role_id_fk = ? where id = ?";
    private static final String DELETE = "delete from user where id = ?";

    private static final String GET_ALL_BY_ROLE_ID = "select * from user where role_id_fk = ?";

    @Override
    public List<User> getAll() throws DaoException, ConnectionException {
        return getManyQuery(GET_ALL, null);
    }

    @Override
    public Optional<User> getById(Integer id) throws DaoException, ConnectionException {
        return getOneQuery(GET_ONE_BY_ID, Collections.singletonList(id));
    }

    @Override
    public Optional<User> getByName(String name) throws DaoException, ConnectionException {
        return getOneQuery(GET_ONE_BY_NAME, Collections.singletonList(name));
    }

    @Override
    public void update(User entity) throws DaoException, ConnectionException {
        updateQuery(UPDATE, Arrays.asList(
                entity.getName(),
                entity.getLogin(),
                entity.getPassword(),
                entity.getRole().getId(),
                entity.getId()));
    }

    @Override
    public void delete(Integer id) throws DaoException, ConnectionException {
        updateQuery(DELETE, Collections.singletonList(id));
    }

    @Override
    public void create(User entity) throws DaoException, ConnectionException {
        updateQuery(CREATE, Arrays.asList(
                entity.getName(),
                entity.getLogin(),
                entity.getPassword(),
                entity.getRole().getId()));
    }

    @Override
    public List<User> getAllByRoleId(Integer id) throws DaoException, ConnectionException {
        return getManyQuery(GET_ALL_BY_ROLE_ID, Collections.singletonList(id));
    }

    @Override
    public Mapper<User> getMapper() {
        return new UserMapper();
    }
}
