package dao.mapper;

import entity.Book;
import entity.Genre;
import entity.Language;
import exception.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements Mapper<Book> {

    @Override
    public Book toEntity(ResultSet rs) throws MapperException {
        try {
            final Integer id = rs.getInt("id");
            final String name = rs.getString("genre");
            final Integer yearOfRelease = rs.getInt("year_of_release");
            final Language language =
            final Double rate = rs.getDouble("rate");
            final Integer count = rs.getInt("count");
            return new Book(
                    id,
                    name,
                    yearOfRelease,

            );
        } catch (SQLException ex) {
            throw new MapperException(ex.getMessage());
        }
    }
}
