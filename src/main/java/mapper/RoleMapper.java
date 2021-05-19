package mapper;

import entity.Role;
import exception.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper implements Mapper<Role> {

    @Override
    public Role toEntity(ResultSet rs) throws MapperException {
        try {
            final Integer id = rs.getInt("id");
            final String name = rs.getString("name");
            return new Role(id, name);
        } catch (SQLException ex) {
            throw new MapperException(ex.getMessage());
        }
    }
}
