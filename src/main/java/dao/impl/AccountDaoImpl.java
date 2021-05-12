package dao.impl;

import dao.AbstractBaseDao;
import dao.AccountDao;
import dao.mapper.Mapper;
import dao.mapper.AccountMapper;
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

    public static AccountDaoImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AccountDaoImpl();
        }
        return INSTANCE;
    }

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
    public List<Account> getAllByRoleId(Integer id) throws DaoException, ConnectionException {
        return getManyQuery(GET_ALL_BY_ROLE_ID, Collections.singletonList(id));
    }

    @Override
    public List<Account> getAllByBookId(Integer id) throws DaoException, ConnectionException {
        return getManyQuery(GET_ALL_BY_BOOK_ID, Collections.singletonList(id));
    }

    @Override
    public Mapper<Account> getMapper() {
        return new AccountMapper();
    }
}
