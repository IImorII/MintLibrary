package dao;

import entity.Account;
import exception.ConnectionException;
import exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface AccountDao extends Dao<Account> {
    Optional<Account> retrieveByLogin(String login) throws DaoException;
    List<Account> retrieveAllByRoleId(Integer id) throws DaoException;
    List<Account> retrieveAllByBookId(Integer id) throws DaoException;
    void setBookAccountConfirmState(Boolean isConfirm, Integer bookId, Integer accountId) throws DaoException;
    void deleteBookFromAccount(Integer bookId, Integer accountId) throws DaoException;
}
