package mapper;

import entity.Genre;
import exception.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreMapper implements Mapper<Genre> {

    @Override
    public Genre toEntity(ResultSet rs) throws MapperException {
        try {
            final Integer id = rs.getInt("id");
            final String name = rs.getString("name");
            return new Genre(id, name);
        } catch (SQLException ex) {
            throw new MapperException(ex.getMessage());
        }
    }
}
