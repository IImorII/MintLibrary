package mapper;


import dao.BookDao;
import dao.LanguageDao;
import dao.impl.ProxyDaoFactory;
import entity.Author;
import entity.Book;
import entity.Language;
import exception.ConnectionException;
import exception.DaoException;
import exception.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AuthorMapper implements Mapper<Author> {

    private static AuthorMapper INSTANCE;

    private AuthorMapper() {
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
            final List<Book> books = getBooksByAuthorId(id);
            final List<Language> languages = getLanguagesByAuthorId(id);
            return new Author(
                    id,
                    name,
                    yearOfBirth,
                    languages,
                    books
            );
        } catch (SQLException ex) {
            throw new MapperException(ex.getMessage());
        }
    }

    private List<Book> getBooksByAuthorId(Integer id) throws MapperException {
        List<Book> books;
        try {
            books = ((BookDao) ProxyDaoFactory.getDaoFor(Book.class)).getAllByAuthorId(id);
        } catch (DaoException | ConnectionException ex) {
            throw new MapperException(ex.getMessage());
        }
        return books;
    }

    private List<Language> getLanguagesByAuthorId(Integer id) throws MapperException {
        List<Language> languages;
        try {
            languages = ((LanguageDao) ProxyDaoFactory.getDaoFor(Language.class)).getAllByAuthorId(id);
        } catch (DaoException | ConnectionException ex) {
            throw new MapperException(ex.getMessage());
        }
        return languages;
    }
}
