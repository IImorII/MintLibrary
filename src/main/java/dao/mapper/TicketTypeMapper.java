package dao.mapper;

import entity.Role;
import entity.TicketType;
import exception.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketTypeMapper implements Mapper<TicketType> {

    @Override
    public TicketType toEntity(ResultSet rs) throws MapperException {
        try {
            final Integer id = rs.getInt("id");
            final String name = rs.getString("name");
            final Integer amountMax = rs.getInt("amount_max");
            return new TicketType(id, name, amountMax);
        } catch (SQLException ex) {
            throw new MapperException(ex.getMessage());
        }
    }
}
