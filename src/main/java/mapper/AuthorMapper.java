package mapper;


import dao.BookDao;
import dao.LanguageDao;
import dao.factory.ProxyDaoFactory;
import dto.AuthorDto;
import entity.Author;
import entity.Book;
import entity.Language;
import exception.ConnectionException;
import exception.DaoException;
import exception.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AuthorMapper implements Mapper<Author, AuthorDto> {

    private static AuthorMapper INSTANCE;
    private BookDao bookDao;
    private LanguageDao languageDao;

    private AuthorMapper() {
        bookDao = (BookDao) ProxyDaoFactory.get(Book.class);
        languageDao = (LanguageDao) ProxyDaoFactory.get(Language.class);
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
        return null;
    }

    private List<Language> getLanguagesByAuthorId(Integer id) throws MapperException {
        List<Language> languages;
        try {
            languages = languageDao.getAllByAuthorId(id);
        } catch (DaoException | ConnectionException ex) {
            throw new MapperException(ex.getMessage());
        }
        return languages;
    }
}
