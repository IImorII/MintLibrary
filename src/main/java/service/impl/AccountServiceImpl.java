package service.impl;

import dao.AccountDao;
import dao.impl.ProxyDaoFactory;
import dto.AccountDto;
import entity.Account;
import entity.Role;
import exception.ConnectionException;
import exception.DaoException;
import exception.MapperException;
import mapper.AccountMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.EntityRepository;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AccountServiceImpl {

    private static Logger log = LogManager.getLogger(AccountServiceImpl.class);
    private static AccountServiceImpl INSTANCE;

    private AccountServiceImpl() {
    }

    public static AccountServiceImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AccountServiceImpl();
        }
        return INSTANCE;
    }

    public List<AccountDto> getAll() {
        List<AccountDto> dtoAccounts = new ArrayList<>();
        try {
            List<Account> entityAccounts = (List<Account>) EntityRepository.getInstance().retrieveCollection(Account.class);
            for (Account e : entityAccounts) {
                dtoAccounts.add(AccountMapper.getInstance().toDto(e));
            }
        } catch (MapperException ex) {
            log.error(ex.getMessage());
        }
        return dtoAccounts;
    }

    public Optional<AccountDto> login(String login, String password) {
        try {
            AccountDao dao = (AccountDao) ProxyDaoFactory.getDaoFor(Account.class);
            final Optional<Account> accountOptional = dao.getByLogin(login);
            if (accountOptional.isPresent()) {
                String hash = getHash(password);
                if (hash.equals(accountOptional.get().getPassword())) {
                    return Optional.of(AccountMapper.getInstance().toDto(accountOptional.get()));
                }
            }
        } catch (NoSuchAlgorithmException | DaoException | ConnectionException | MapperException ex) {
            log.error(ex.getMessage());
        }
        return Optional.empty();
    }

    public Optional<AccountDto> singUp(String login, String password, String name) {
        try {
            AccountDao accountDao = (AccountDao) ProxyDaoFactory.getDaoFor(Account.class);
            final Optional<Account> accountOptional = accountDao.getByLogin(login);
            if (!accountOptional.isPresent()) {
                String hash = getHash(password);
                Account account = new Account();
                account.setName(name);
                account.setLogin(login);
                account.setPassword(hash);
                account.setRole((Role) EntityRepository.getInstance().retrieveCollection(Role.class).stream().
                        filter(s -> s.getName().equalsIgnoreCase("User")).
                        findFirst().
                        orElse(null));
                accountDao.create(account);
                return Optional.of(AccountMapper.getInstance().toDto(account));
            }
        } catch (NoSuchAlgorithmException | DaoException | ConnectionException | MapperException ex) {
            log.error(ex.getMessage());
        }
        return Optional.empty();
    }

    public String getHash(String password) throws NoSuchAlgorithmException {
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        byte[] bytes = sha256.digest(password.getBytes());
        String hash = Integer.toHexString(Arrays.hashCode(bytes));
        return hash;
    }
}
