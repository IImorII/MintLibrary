package service;

import dto.AccountDto;
import entity.Account;
import entity.Role;
import exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface AccountService extends Service<Account, AccountDto> {

    AccountDto getOne(Integer id) throws ServiceException;

    Optional<AccountDto> login(String login, String password) throws ServiceException;

    Optional<AccountDto> signUp(String login, String password, String name) throws ServiceException;

    String orderBook(Integer accountId, Integer bookId) throws ServiceException;

    void confirmOrder(Integer accountId, Integer bookId) throws ServiceException;

    void releaseOrder(Integer accountId, Integer bookId) throws ServiceException;

    void deleteAccount(Integer accountId) throws ServiceException;

    void changeAccountRole(Integer accountId, Integer roleId) throws ServiceException;

    List<AccountDto> removeAdmins(List<AccountDto> accounts) throws ServiceException;
}
