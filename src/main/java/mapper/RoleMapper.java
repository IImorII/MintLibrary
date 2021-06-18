package mapper;

import dto.AccountDto;
import dto.RoleDto;
import entity.Account;
import entity.Role;
import exception.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper implements Mapper<Role, RoleDto> {

    private static RoleMapper INSTANCE;

    private RoleMapper() {
    }

    public static RoleMapper getInstance() {
        try {
            LOCK.lock();
            if (INSTANCE == null) {
                INSTANCE = new RoleMapper();
            }
            return INSTANCE;
        } finally {
            LOCK.unlock();
        }
    }

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

    @Override
    public RoleDto toDto(Role entity) throws MapperException {
        final Integer id = entity.getId();
        final String name = entity.getName();
        return new RoleDto(id, name);
    }
}
