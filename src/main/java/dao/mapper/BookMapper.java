package dao.mapper;

import dao.impl.AuthorDaoImpl;
import dao.impl.GenreDaoImpl;
import dao.impl.LanguageDaoImpl;
import entity.Author;
import entity.Book;
import entity.Genre;
import entity.Language;
import exception.ConnectionException;
import exception.DaoException;
import exception.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BookMapper implements Mapper<Book> {

    @Override
    public Book toEntity(ResultSet rs) throws MapperException {
        try {
            final Integer id = rs.getInt("id");
            final String name = rs.getString("genre");
            final Integer yearOfRelease = rs.getInt("year_of_release");
            final Language language = getLanguageById(rs.getInt("language_id_fk"));
            final Double rate = rs.getDouble("rate");
            final Integer count = rs.getInt("count");
            return new Book(
                    id,
                    name,
                    yearOfRelease,
                    language,
                    rate,
                    count
            );
        } catch (SQLException ex) {
            throw new MapperException(ex.getMessage());
        }
    }

    private Language getLanguageById(Integer id) throws MapperException {
        Language language;
        try {
            language = LanguageDaoImpl.getInstance().getById(id).get();
        } catch (DaoException | ConnectionException ex) {
            throw new MapperException(ex.getMessage());
        }
        return language;
    }

    private List<Author> getAuthorsByBookId(Integer id) throws MapperException {
        List<Author> authors;
        try {
            authors = AuthorDaoImpl.getInstance().getAllByBookId(id);
        } catch (DaoException | ConnectionException ex) {
            throw new MapperException(ex.getMessage());
        }
        return authors;
    }

    private List<Genre> getGenresByBookId(Integer id) throws MapperException {
        List<Genre> genres;
        try {
            genres = GenreDaoImpl.getInstance().getAllByBookId(id);
        } catch (DaoException | ConnectionException ex) {
            throw new MapperException(ex.getMessage());
        }
        return genres;
    }
}
