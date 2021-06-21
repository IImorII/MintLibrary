package mapper;

import dao.BookDao;
import dao.Dao;
import dao.RoleDao;
import dto.AccountDto;
import entity.Account;
import entity.Book;
import entity.Role;
import exception.ConnectionException;
import exception.DaoException;
import exception.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountMapper implements Mapper<Account, AccountDto> {

    private BookDao bookDao;

    private RoleDao roleDao;

    private static AccountMapper INSTANCE;

    private AccountMapper() {
        bookDao = (BookDao) Dao.of(Book.class);
        roleDao = (RoleDao) Dao.of(Role.class);
    }

    public static AccountMapper getInstance() {
        try {
            LOCK.lock();
            if (INSTANCE == null) {
                INSTANCE = new AccountMapper();
            }
            return INSTANCE;
        } finally {
            LOCK.unlock();
        }
    }

    @Override
    public Account toEntity(ResultSet rs) throws MapperException {
        try {
            final Integer id = rs.getInt("id");
            final String name = rs.getString("name");
            final String login = rs.getString("login");
            final Integer bookAmountCurrent = rs.getInt("book_amount_current");
            final Integer bookAmountMax = rs.getInt("book_amount_max");
            final String password = rs.getString("password");
            final Role role = getRoleById(rs.getInt("role_id_fk"));
            final List<Book> books = getBooksByAccountId(id);
            return new Account(id, name, login, password, bookAmountCurrent, bookAmountMax, role, books);
        } catch (SQLException ex) {
            throw new MapperException(ex.getMessage());
        }
    }

    public AccountDto toDto(Account entity) throws MapperException {
        final Integer id = entity.getId();
        final String login = entity.getLogin();
        final String name = entity.getName();
        final String role = entity.getRole().getName();
        final Integer amountCurrent = entity.getBookAmountCurrent();
        final Integer amountMax = entity.getBookAmountMax();
        return new AccountDto(
                id,
                login,
                name,
                role,
                amountCurrent,
                amountMax
        );
    }

    private Role getRoleById(Integer id) throws MapperException {
        Role role;
        try {
            role = roleDao.retrieveById(id).get();
        } catch (DaoException | ConnectionException ex) {
            throw new MapperException(ex.getMessage());
        }
        return role;
    }

    private List<Book> getBooksByAccountId(Integer accountId) throws MapperException {
        List<Book> books;
        try {
            books = bookDao.retrieveAllByAccountId(accountId);
        } catch (DaoException | ConnectionException ex) {
            throw new MapperException(ex.getMessage());
        }
        return books;
    }
}
