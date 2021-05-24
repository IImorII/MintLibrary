package service.impl;

import dao.AccountDao;
import dao.BookDao;
import dao.impl.ProxyDaoFactory;
import dto.AccountDto;
import dto.BookDto;
import entity.Account;
import entity.Book;
import entity.Role;
import exception.ConnectionException;
import exception.DaoException;
import exception.MapperException;
import mapper.AccountMapper;
import mapper.BookMapper;
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

    public AccountDto getOne(Integer id) {
        AccountDto dtoAccount= null;
        try {
            Account entity = (Account) ProxyDaoFactory.getDaoFor(Account.class).getById(id).get();
            dtoAccount = AccountMapper.getInstance().toDto(entity);
        } catch (DaoException | ConnectionException | MapperException ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
        return dtoAccount;
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

    public Optional<AccountDto> signUp(String login, String password, String name) {
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
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    public boolean OrderBook(Integer accountId, Integer bookId) {
        try {
            BookDao bookDao = (BookDao) ProxyDaoFactory.getDaoFor(Book.class);
            AccountDao accountDao = (AccountDao) ProxyDaoFactory.getDaoFor(Account.class);
            Book book = bookDao.getById(bookId).get();
            Account account = accountDao.getById(accountId).get();
            if (account.getBookAmountCurrent() >= account.getBookAmountMax() || book.getCount() <= 0) {
                return false;
            }
            System.out.println(accountId);
            System.out.println(bookId);
            List<Account> accounts = new ArrayList<>(accountDao.getAllByBookId(bookId));
            accounts.add(account);
            book.setCount(book.getCount() - 1);
            account.setBookAmountCurrent(account.getBookAmountCurrent() + 1);
            book.setAccounts(accounts);
            bookDao.create(book);
            accountDao.create(account);
        } catch (DaoException | ConnectionException ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
        return true;
    }

    public void ConfirmOrder(Integer userId, Integer bookId) {
        try {
            AccountDao accountDao = (AccountDao) ProxyDaoFactory.getDaoFor(Account.class);
            accountDao.changeBookAccountConfirm(true, bookId, userId);
        } catch (DaoException | ConnectionException ex) {
            log.error(ex);
            ex.printStackTrace();
        }
    }

    public String getHash(String password) throws NoSuchAlgorithmException {
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        byte[] bytes = sha256.digest(password.getBytes());
        String hash = Integer.toHexString(Arrays.hashCode(bytes));
        return hash;
    }
}
