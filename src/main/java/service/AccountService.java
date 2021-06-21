package service;

import dto.AccountDto;
import entity.Account;
import entity.Role;

import java.util.List;
import java.util.Optional;

public interface AccountService extends Service<Account, AccountDto> {

    AccountDto getOne(Integer id);

    Optional<AccountDto> login(String login, String password);

    Optional<AccountDto> signUp(String login, String password, String name);

    String orderBook(Integer accountId, Integer bookId);

    void confirmOrder(Integer accountId, Integer bookId);

    void releaseOrder(Integer accountId, Integer bookId);

    void deleteAccount(Integer accountId);

    void changeAccountRole(Integer accountId, Integer roleId);

    List<AccountDto> removeAdmins(List<AccountDto> accounts);
}
