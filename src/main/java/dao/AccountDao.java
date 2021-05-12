package dao;

import entity.Account;
import exception.ConnectionException;
import exception.DaoException;

import java.util.List;

public interface AccountDao extends BaseDao<Account> {
    List<Account> getAllByRoleId(Integer id) throws DaoException, ConnectionException;
    List<Account> getAllByBookId(Integer id) throws DaoException, ConnectionException;
}
