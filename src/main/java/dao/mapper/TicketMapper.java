package dao.mapper;

import dao.impl.BookDaoImpl;
import dao.impl.TicketTypeDaoImpl;
import entity.*;
import exception.ConnectionException;
import exception.DaoException;
import exception.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TicketMapper implements Mapper<Ticket> {

    @Override
    public Ticket toEntity(ResultSet rs) throws MapperException {
        try {
            final Integer id = rs.getInt("id");
            final String name = rs.getString("name");
            final TicketType type = getTicketTypeById(id);
            final List<Book> books = getBooksByTicketId(id);
            final Integer amountCurrent = rs.getInt("amount_current");
            return new Ticket(id, name, type, amountCurrent, books);
        } catch (SQLException ex) {
            throw new MapperException(ex.getMessage());
        }
    }

    private TicketType getTicketTypeById(Integer id) throws MapperException {
        TicketType type;
        try {
            type = TicketTypeDaoImpl.getInstance().getById(id).get();
        } catch (DaoException | ConnectionException ex) {
            throw new MapperException(ex.getMessage());
        }
        return type;
    }

    private List<Book> getBooksByTicketId(Integer id) throws MapperException {
        List<Book> books;
        try {
            books = BookDaoImpl.getInstance().getAllByTicketId(id);
        } catch (DaoException | ConnectionException ex) {
            throw new MapperException(ex.getMessage());
        }
        return books;
    }
}
