package mapper;


import dao.Dao;
import dao.LanguageDao;
import dto.AuthorDto;
import entity.Author;
import entity.Language;
import exception.ConnectionException;
import exception.DaoException;
import exception.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorMapper implements Mapper<Author, AuthorDto> {

    private static AuthorMapper INSTANCE;
    private LanguageDao languageDao;

    private AuthorMapper() {
        languageDao = (LanguageDao) Dao.of(Language.class);
    }

    public static AuthorMapper getInstance() {
        try {
            LOCK.lock();
            if (INSTANCE == null) {
                INSTANCE = new AuthorMapper();
            }
            return INSTANCE;
        } finally {
            LOCK.unlock();
        }
    }


    @Override
    public Author toEntity(ResultSet rs) throws MapperException {
        try {
            final Integer id = rs.getInt("id");
            final String name = rs.getString("name");
            final Integer yearOfBirth = rs.getInt("year_of_birth");
            final List<Language> languages = getLanguagesByAuthorId(id);
            return new Author(
                    id,
                    name,
                    yearOfBirth,
                    languages
            );
        } catch (SQLException ex) {
            throw new MapperException(ex.getMessage());
        }
    }

    @Override
    public AuthorDto toDto(Author entity) throws MapperException {
        final Integer id = entity.getId();
        final String name = entity.getName();
        final Integer yearOfBirth = entity.getYearOfBirth();
        final List<String> languages = new ArrayList<>();
        for (Language e : entity.getLanguages()) {
            languages.add(e.getName());
        }
        return new AuthorDto(id, name, yearOfBirth, languages);
    }

    private List<Language> getLanguagesByAuthorId(Integer id) throws MapperException {
        List<Language> languages;
        try {
            languages = languageDao.retrieveAllByAuthorId(id);
        } catch (DaoException | ConnectionException ex) {
            throw new MapperException(ex.getMessage());
        }
        return languages;
    }
}
