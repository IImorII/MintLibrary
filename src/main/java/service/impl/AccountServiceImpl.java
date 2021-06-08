package service.impl;

import controller.command.CommandRoles;
import dao.AccountDao;
import dao.BookDao;
import dao.RoleDao;
import dao.impl.ProxyDaoFactory;
import dto.AccountDto;
import entity.Account;
import entity.Book;
import entity.Role;
import exception.ConnectionException;
import exception.DaoException;
import exception.MapperException;
import mapper.AccountMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import cache.EntityCache;
import service.AccountService;
import util.SecureUtil;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountServiceImpl implements AccountService {
    private static Logger log = LogManager.getLogger(AccountServiceImpl.class);
    private static AccountServiceImpl INSTANCE;

    private AccountDao accountDao;
    private BookDao bookDao;
    private RoleDao roleDao;
    private AccountMapper accountMapper;

    private final static String HAVE_BOOK_ERROR = "You already have this book!";
    private final static String SUCCESS_ORDER = "Success order!";
    private final static String FAIL_ORDER = "Your ticket is full or no books left!";

    private AccountServiceImpl() {
        accountDao = (AccountDao) ProxyDaoFactory.getDaoFor(Account.class);
        bookDao = (BookDao) ProxyDaoFactory.getDaoFor(Book.class);
        roleDao = (RoleDao) ProxyDaoFactory.getDaoFor(Role.class);
        accountMapper = AccountMapper.getInstance();
    }

    public static AccountServiceImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AccountServiceImpl();
        }
        return INSTANCE;
    }

    @Override
    public List<AccountDto> getAll() {
        List<AccountDto> dtoAccounts = new ArrayList<>();
        try {
            List<Account> entityAccounts = (List<Account>) EntityCache.getInstance().retrieveCollection(Account.class);
            for (Account e : entityAccounts) {
                dtoAccounts.add(accountMapper.toDto(e));
            }
        } catch (MapperException ex) {
            log.error(ex.getMessage());
        }
        return dtoAccounts;
    }

    public List<AccountDto> removeAdmins(List<AccountDto> accounts) {
        accounts.removeIf(account -> account.getRole().equals(CommandRoles.ADMIN.getRole()));
        return accounts;
    }

    @Override
    public AccountDto getOne(Integer id) {
        AccountDto dtoAccount = null;
        try {
            Account entity = accountDao.getById(id).orElse(null);
            dtoAccount = accountMapper.toDto(entity);
        } catch (DaoException | ConnectionException | MapperException ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
        return dtoAccount;
    }

    @Override
    public Optional<AccountDto> login(String login, String password) {
        try {
            final Optional<Account> accountOptional = accountDao.getByLogin(login);
            if (accountOptional.isPresent()) {
                String hash = accountOptional.get().getPassword();
                String psw = hash.substring(0, hash.indexOf("&salt="));
                String slt = hash.substring(hash.indexOf("&salt=") + 6);
                if (psw.equals(SecureUtil.getSecurePassword(password, SecureUtil.hexToBytes(slt)))) {
                    return Optional.of(accountMapper.toDto(accountOptional.get()));
                }
            }
        } catch (DaoException | ConnectionException | MapperException ex) {
            log.error(ex.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<AccountDto> signUp(String login, String password, String name) {
        try {
            final Optional<Account> accountOptional = accountDao.getByLogin(login);
            if (!accountOptional.isPresent()) {
                byte[] salt = SecureUtil.getSalt();
                String hash = SecureUtil.getSecurePassword(password, salt);
                hash += ("&salt=" + SecureUtil.bytesToHex(salt));
                Account account = new Account();
                account.setName(name);
                account.setLogin(login);
                account.setPassword(hash);
                account.setRole((Role) EntityCache.getInstance().retrieveCollection(Role.class).stream().
                        filter(s -> s.getName().equalsIgnoreCase("User")).
                        findFirst().
                        orElse(null));
                accountDao.create(account);
                account.setId(accountDao.getByLogin(login).get().getId());
                return Optional.of(accountMapper.toDto(account));
            }
        } catch (NoSuchAlgorithmException | DaoException | ConnectionException | MapperException ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public String orderBook(Integer accountId, Integer bookId) {
        try {
            Book book = bookDao.getById(bookId).get();
            Account account = accountDao.getById(accountId).get();
            if (account.getBookAmountCurrent() >= account.getBookAmountMax() || book.getCount() <= 0) {
                return FAIL_ORDER;
            }
            List<Account> accounts = new ArrayList<>(accountDao.getAllByBookId(bookId));
            if (accounts.contains(account)) {
                return HAVE_BOOK_ERROR;
            }
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
        return SUCCESS_ORDER;
    }

    @Override
    public void confirmOrder(Integer accountId, Integer bookId) {
        try {
            accountDao.setBookAccountConfirmState(true, bookId, accountId);
        } catch (DaoException | ConnectionException ex) {
            log.error(ex);
            ex.printStackTrace();
        }
    }

    @Override
    public void releaseOrder(Integer accountId, Integer bookId) {
        try {
            Account account = accountDao.getById(accountId).get();
            Book book = bookDao.getById(bookId).get();
            book.setCount(book.getCount() + 1);
            account.setBookAmountCurrent(account.getBookAmountCurrent() - 1);
            accountDao.deleteBookFromAccount(bookId, accountId);
            bookDao.update(book);
            accountDao.update(account);
        } catch (DaoException | ConnectionException ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
    }


    @Override
    public void deleteAccount(Integer accountId) {
        try {
            releaseAllBooks(accountId);
            accountDao.delete(accountId);
        } catch (DaoException | ConnectionException ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void releaseAllBooks(Integer accountId) {
        try {
            List<Book> books = bookDao.getAllByAccountId(accountId);
            for (Book b : books) {
                accountDao.deleteBookFromAccount(b.getId(), accountId);
            }
        } catch (DaoException | ConnectionException ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public void changeAccountRole(Integer accountId, Integer roleId) {
        try {
            Account account = accountDao.getById(accountId).get();
            account.setRole(roleDao.getById(roleId).orElse(new Role("User")));
            accountDao.update(account);
        } catch (DaoException | ConnectionException ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
