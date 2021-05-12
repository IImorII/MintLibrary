package dao.mapper;

import dao.impl.RoleDaoImpl;
import entity.Role;
import entity.Account;
import exception.ConnectionException;
import exception.DaoException;
import exception.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapper implements Mapper<Account> {

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
            return new Account(id, name, login, password, bookAmountCurrent, bookAmountMax, role);
        } catch (SQLException ex) {
            throw new MapperException(ex.getMessage());
        }
    }

    private Role getRoleById(Integer id) throws MapperException {
        Role role;
        try {
            role = RoleDaoImpl.getInstance().getById(id).get();
        } catch (DaoException | ConnectionException ex) {
            throw new MapperException(ex.getMessage());
        }
        return role;
    }
}
