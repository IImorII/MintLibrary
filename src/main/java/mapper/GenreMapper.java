package mapper;

import dao.Dao;
import dao.GenreDao;
import dto.GenreDto;
import dto.LanguageDto;
import entity.Genre;
import exception.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreMapper implements Mapper<Genre, GenreDto> {

    private static GenreMapper INSTANCE;


    private GenreMapper() {
    }

    public static GenreMapper getInstance() {
        try {
            LOCK.lock();
            if (INSTANCE == null) {
                INSTANCE = new GenreMapper();
            }
            return INSTANCE;
        } finally {
            LOCK.unlock();
        }
    }

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

    @Override
    public GenreDto toDto(Genre entity) throws MapperException {
        final Integer id = entity.getId();
        final String name = entity.getName();
        return new GenreDto(id, name);
    }
}
