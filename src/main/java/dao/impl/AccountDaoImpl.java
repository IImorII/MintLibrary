package dao.impl;

import dao.AbstractBaseDao;
import dao.AccountDao;
import mapper.Mapper;
import mapper.AccountMapper;
import entity.Account;
import exception.ConnectionException;
import exception.DaoException;

import java.util.*;

public class AccountDaoImpl extends AbstractBaseDao<Account> implements AccountDao {

    private static AccountDaoImpl INSTANCE;

    private AccountDaoImpl() {
        super("account");
        setCreateQuery("insert into account (name, login, password, book_amount_max, role_id_fk) values (?, ?, ?, ?, ?)");
        setUpdateQuary("update account set name = ?, login = ?, password = ?, book_amount_current = ?, book_amount_max = ?, role_id_fk = ? where id = ?");
    }

    protected static AccountDaoImpl getInstance() {
        try {
            LOCK.lock();
            if (INSTANCE == null) {
                INSTANCE = new AccountDaoImpl();
            }
            return INSTANCE;
        } finally {
            LOCK.unlock();
        }
    }

    private static final String GET_ONE_BY_LOGIN = "select * from account where login = ?";
    private static final String GET_ALL_BY_ROLE_ID = "select * from account where role_id_fk = ?";

    private static final String GET_ALL_BY_BOOK_ID = "select account.* from account " +
            " join book_account on book_account.account_id = account.id" +
            " join book on book.id = book_account.id where book.id = ?";
    private static final String BOOK_ACCOUNT_CONFIRM = "update book_account set confirmed = ? where id = ? and account_id = ?";
    private static final String DELETE_BOOK_FROM_ACCOUNT = "delete from book_account where id = ? and account_id = ?";

    @Override
    public void create(Account account) throws DaoException, ConnectionException {
        if (account.getRole().getId() == null) {
            RoleDaoImpl.getInstance().create(account.getRole());
            account.getRole().setId(RoleDaoImpl.getInstance().getByName(account.getRole().getName()).get().getId());
        }
        if (getByLogin(account.getLogin()).isPresent()) {
            if (account.getId() == null) {
                account.setId(getByLogin(account.getLogin()).get().getId());
            }
            update(account);
        } else {
            updateQuery(CREATE, Arrays.asList(
                    account.getName(),
                    account.getLogin(),
                    account.getPassword(),
                    account.getBookAmountMax(),
                    account.getRole().getId()));
        }
    }

    @Override
    public void update(Account account) throws DaoException, ConnectionException {
        if (account.getRole().getId() == null) {
            RoleDaoImpl.getInstance().create(account.getRole());
            account.getRole().setId(RoleDaoImpl.getInstance().getByName(account.getRole().getName()).get().getId());
        }
        updateQuery(UPDATE, Arrays.asList(
                account.getName(),
                account.getLogin(),
                account.getPassword(),
                account.getBookAmountCurrent(),
                account.getBookAmountMax(),
                account.getRole().getId(),
                account.getId()));
    }

    @Override
    public Optional<Account> getByLogin(String login) throws DaoException, ConnectionException {
        return getOneQuery(GET_ONE_BY_LOGIN, Collections.singletonList(login));
    }

    @Override
    public List<Account> getAllByRoleId(Integer id) throws DaoException, ConnectionException {
        return getManyQuery(GET_ALL_BY_ROLE_ID, Collections.singletonList(id));
    }

    @Override
    public List<Account> getAllByBookId(Integer id) throws DaoException, ConnectionException {
        return getManyQuery(GET_ALL_BY_BOOK_ID, Collections.singletonList(id));
    }

    @Override
    public void setBookAccountConfirmState(Boolean isConfirm, Integer bookId, Integer accountId) throws DaoException, ConnectionException {
        updateQuery(BOOK_ACCOUNT_CONFIRM, Arrays.asList((isConfirm) ? 1 : 0, bookId, accountId));
    }

    @Override
    public void deleteBookFromAccount(Integer bookId, Integer accountId) throws DaoException, ConnectionException {
        updateQuery(DELETE_BOOK_FROM_ACCOUNT, Arrays.asList(bookId, accountId));
    }

    @Override
    public Mapper<Account> getMapper() {
        return AccountMapper.getInstance();
    }
}
