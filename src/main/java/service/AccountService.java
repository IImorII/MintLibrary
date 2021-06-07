package service;

import dto.AccountDto;
import entity.Account;
import entity.Role;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    List<AccountDto> getAll();

    AccountDto getOne(Integer id);

    Optional<AccountDto> login(String login, String password);

    Optional<AccountDto> signUp(String login, String password, String name);

    String orderBook(Integer accountId, Integer bookId);

    void confirmOrder(Integer accountId, Integer bookId);

    void releaseOrder(Integer accountId, Integer bookId);

    void deleteAccount(Integer accountId);

    void changeAccountRole(Integer accountId, String role);

    List<AccountDto> removeAdmins(List<AccountDto> accounts);
}
