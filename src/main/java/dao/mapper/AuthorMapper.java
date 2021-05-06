package dao.mapper;

import dao.impl.BookDaoImpl;
import dao.impl.LanguageDaoImpl;
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

    @Override
    public Author toEntity(ResultSet rs) throws MapperException {
        try {
            final Integer id = rs.getInt("id");
            final String name = rs.getString("genre");
            final Integer birth = rs.getInt("birth");
            final List<Book> books = getBooksByAuthorId(id);
            final List<Language> languages = getLanguagesByAuthorId(id);
            return new Author(
                    id,
                    name,
                    birth,
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
            books = BookDaoImpl.getInstance().getAllByAuthorId(id);
        } catch (DaoException | ConnectionException ex) {
            throw new MapperException(ex.getMessage());
        }
        return books;
    }

    private List<Language> getLanguagesByAuthorId(Integer id) throws MapperException {
        List<Language> languages;
        try {
            languages = LanguageDaoImpl.getInstance().getAllByAuthorId(id);
        } catch (DaoException | ConnectionException ex) {
            throw new MapperException(ex.getMessage());
        }
        return languages;
    }
}
