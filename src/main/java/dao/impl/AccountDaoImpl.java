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
        setUpdateQuary("update user set name = ?, login = ?, password = ?, book_amount_current = ?, book_amount_max = ?, role_id_fk = ? where id = ?");
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
    private static final String GET_ALL_BY_BOOK_ID = "select * from book_account where id = ?";

    @Override
    public void create(Account account) throws DaoException, ConnectionException {
        if (getByName(account.getName()).isPresent()) {
            throw new DaoException(account.getName() + " is duplicate!");
        } else if (account.getRole().getId() == null) {
            RoleDaoImpl.getInstance().create(account.getRole());
            account.getRole().setId(RoleDaoImpl.getInstance().getByName(account.getRole().getName()).get().getId());
        }
        updateQuery(CREATE, Arrays.asList(
                account.getName(),
                account.getLogin(),
                account.getPassword(),
                account.getBookAmountMax(),
                account.getRole().getId()));
    }

    @Override
    public void update(Account account) throws DaoException, ConnectionException {
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
    public Mapper<Account> getMapper() {
        return AccountMapper.getInstance();
    }
}
