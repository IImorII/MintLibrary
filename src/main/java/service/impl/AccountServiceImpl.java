package service.impl;

import controller.command.CommandRoles;
import dao.AccountDao;
import dao.BookDao;
import dao.Dao;
import dao.RoleDao;
import dto.AccountDto;
import entity.Account;
import entity.Book;
import entity.Role;
import exception.DaoException;
import exception.MapperException;
import exception.ServiceException;
import mapper.AccountMapper;
import mapper.Mapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import cache.EntityCache;
import service.AccountService;
import util.SecureUtil;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AccountServiceImpl implements AccountService {
    private static final Logger log = LogManager.getLogger(AccountServiceImpl.class);
    private static AccountServiceImpl INSTANCE;

    private static final String ADMIN = "Admin";

    private final AccountDao accountDao;
    private final BookDao bookDao;
    private final RoleDao roleDao;
    private final AccountMapper accountMapper;
    private final EntityCache cache;

    private final static String HAVE_BOOK_ERROR = "You already have this book!";
    private final static String SUCCESS_ORDER = "Success order!";
    private final static String FAIL_ORDER = "Your ticket is full or no books left!";

    private AccountServiceImpl() {
        accountDao = (AccountDao) Dao.of(Account.class);
        bookDao = (BookDao) Dao.of(Book.class);
        roleDao = (RoleDao) Dao.of(Role.class);
        accountMapper = (AccountMapper) Mapper.of(Account.class);
        cache = EntityCache.getInstance();
    }

    public static AccountServiceImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AccountServiceImpl();
        }
        return INSTANCE;
    }

    @Override
    public List<AccountDto> getAll() throws ServiceException {
        List<AccountDto> dtoAccounts = new ArrayList<>();
        try {
            List<Account> entityAccounts = (List<Account>) cache.retrieveCollection(Account.class);
            for (Account e : entityAccounts) {
                dtoAccounts.add(accountMapper.toDto(e));
            }
        } catch (MapperException ex) {
            log.error(ex.getMessage());
            throw new ServiceException(ex.getMessage());
        }
        Collections.reverse(dtoAccounts);
        return dtoAccounts;
    }

    @Override
    public List<AccountDto> getAllWithoutAdmin() throws ServiceException {
        List<AccountDto> dtoAccounts = new ArrayList<>();
        try {
            List<Account> entityAccounts = (List<Account>) cache.retrieveCollection(Account.class);
            for (Account e : entityAccounts) {
                if (e.getRole().getName().equalsIgnoreCase(ADMIN)) {
                    continue;
                }
                dtoAccounts.add(accountMapper.toDto(e));
            }
        } catch (MapperException ex) {
            log.error(ex.getMessage());
            throw new ServiceException(ex.getMessage());
        }
        return dtoAccounts;
    }

    public List<AccountDto> removeAdmins(List<AccountDto> accounts) {
        accounts.removeIf(account -> account.getRole().equals(CommandRoles.ADMIN.getRole()));
        return accounts;
    }

    @Override
    public AccountDto getOne(Integer id) throws ServiceException {
        AccountDto dtoAccount = null;
        try {
            Account entity = accountDao.retrieveById(id).orElse(null);
            dtoAccount = accountMapper.toDto(entity);
        } catch (DaoException | MapperException ex) {
            log.error(ex.getMessage());
            throw new ServiceException(ex.getMessage());
        }
        return dtoAccount;
    }

    @Override
    public Optional<AccountDto> login(String login, String password) throws ServiceException{
        try {
            final Optional<Account> accountOptional = accountDao.retrieveByLogin(login);
            if (accountOptional.isPresent()) {
                String hash = accountOptional.get().getPassword();
                String psw = hash.substring(0, hash.indexOf("&salt="));
                String slt = hash.substring(hash.indexOf("&salt=") + 6);
                if (psw.equals(SecureUtil.getSecurePassword(password, SecureUtil.hexToBytes(slt)))) {
                    return Optional.of(accountMapper.toDto(accountOptional.get()));
                }
            }
        } catch (DaoException | MapperException ex) {
            log.error(ex.getMessage());
            throw new ServiceException(ex.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<AccountDto> signUp(String login, String password, String name) throws ServiceException {
        try {
            final Optional<Account> accountOptional = accountDao.retrieveByLogin(login);
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
                account.setId(accountDao.retrieveByLogin(login).get().getId());
                return Optional.of(accountMapper.toDto(account));
            }
        } catch (NoSuchAlgorithmException | DaoException | MapperException ex) {
            log.error(ex.getMessage());
            throw new ServiceException(ex.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public String orderBook(Integer accountId, Integer bookId) throws ServiceException {
        try {
            Book book = bookDao.retrieveById(bookId).get();
            Account account = accountDao.retrieveById(accountId).get();
            if (account.getBookAmountCurrent() >= account.getBookAmountMax() || book.getCount() <= 0) {
                return FAIL_ORDER;
            }
            List<Book> books = new ArrayList<>(bookDao.retrieveAllByAccountId(accountId));
            if (books.contains(book)) {
                return HAVE_BOOK_ERROR;
            }
            books.add(book);
            book.setCount(book.getCount() - 1);
            account.setBookAmountCurrent(account.getBookAmountCurrent() + 1);
            account.setBooks(books);
            bookDao.create(book);
            accountDao.create(account);
        } catch (DaoException ex) {
            log.error(ex.getMessage());
            throw new ServiceException(ex.getMessage());
        }
        return SUCCESS_ORDER;
    }

    @Override
    public void confirmOrder(Integer accountId, Integer bookId) throws ServiceException {
        try {
            accountDao.setBookAccountConfirmState(true, bookId, accountId);
        } catch (DaoException ex) {
            log.error(ex);
            throw new ServiceException(ex.getMessage());
        }
    }

    @Override
    public void releaseOrder(Integer accountId, Integer bookId) throws ServiceException {
        try {
            Account account = accountDao.retrieveById(accountId).get();
            Book book = bookDao.retrieveById(bookId).get();
            book.setCount(book.getCount() + 1);
            account.setBookAmountCurrent(account.getBookAmountCurrent() - 1);
            accountDao.deleteBookFromAccount(bookId, accountId);
            bookDao.update(book);
            accountDao.update(account);
        } catch (DaoException ex) {
            log.error(ex.getMessage());
            throw new ServiceException(ex.getMessage());
        }
    }


    @Override
    public void deleteAccount(Integer accountId) throws ServiceException {
        try {
            releaseAllUnconfirmedBooks(accountId);
            accountDao.delete(accountId);
        } catch (DaoException ex) {
            log.error(ex.getMessage());
            throw new ServiceException(ex.getMessage());
        }
    }

    public void releaseAllUnconfirmedBooks(Integer accountId) throws ServiceException {
        try {
            List<Book> unconfirmedBooks = bookDao.retrieveAllUnconfirmedByAccountId(accountId);
            for (Book b : unconfirmedBooks) {
                b.setCount(b.getCount() + 1);
                bookDao.update(b);
                accountDao.deleteBookFromAccount(b.getId(), accountId);
            }
        } catch (DaoException ex) {
            log.error(ex.getMessage());
            throw new ServiceException(ex.getMessage());
        }
    }

    @Override
    public void changeAccountRole(Integer accountId, Integer roleId) throws ServiceException {
        try {
            Account account = accountDao.retrieveById(accountId).get();
            account.setRole(roleDao.retrieveById(roleId).orElse(new Role("User")));
            accountDao.update(account);
        } catch (DaoException ex) {
            log.error(ex.getMessage());
            throw new ServiceException(ex.getMessage());
        }
    }
}
