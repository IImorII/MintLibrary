package dao.mapper;

import dao.impl.RoleDaoImpl;
import entity.Role;
import entity.User;
import exception.ConnectionException;
import exception.DaoException;
import exception.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements Mapper<User> {

    @Override
    public User toEntity(ResultSet rs) throws MapperException {
        try {
            final Integer id = rs.getInt("id");
            final String name = rs.getString("name");
            final String login = rs.getString("login");
            final String password = rs.getString("password");
            final Role role = getRoleById(rs.getInt("role_id_fk"));
            return new User(id, name, login, password, role);
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
