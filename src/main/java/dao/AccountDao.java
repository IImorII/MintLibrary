package dao;

import entity.Account;
import exception.ConnectionException;
import exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface AccountDao extends BaseDao<Account> {
    Optional<Account> getByLogin(String login) throws DaoException, ConnectionException;
    List<Account> getAllByRoleId(Integer id) throws DaoException, ConnectionException;
    List<Account> getAllByBookId(Integer id) throws DaoException, ConnectionException;
}
