package dao;

import entity.User;
import exception.ConnectionException;
import exception.DaoException;

import java.util.List;

public interface UserDao extends BaseDao<User> {
    List<User> getAllByRoleId(Integer id) throws DaoException, ConnectionException;
}
