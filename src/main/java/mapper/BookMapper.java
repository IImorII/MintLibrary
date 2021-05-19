package mapper;

//import dao.impl.AuthorDaoImpl;

import dao.AccountDao;
import dao.AuthorDao;
import dao.GenreDao;
import dao.LanguageDao;
import dao.impl.*;
//import dao.impl.LanguageDaoImpl;
import entity.*;
import dto.BookDto;
import exception.ConnectionException;
import exception.DaoException;
import exception.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookMapper implements Mapper<Book> {

    private static BookMapper INSTANCE;

    private BookMapper() {
    }

    public static BookMapper getInstance() {
        try {
            LOCK.lock();
            if (INSTANCE == null) {
                INSTANCE = new BookMapper();
            }
            return INSTANCE;
        } finally {
            LOCK.unlock();
        }
    }

    @Override
    public Book toEntity(ResultSet rs) throws MapperException {
        try {
            final Integer id = rs.getInt("id");
            final String name = rs.getString("name");
            final String description = rs.getString("description");
            final String photoUrl = rs.getString("photo_url");
            final Integer yearOfRelease = rs.getInt("year_of_release");
            final Double rate = rs.getDouble("rate");
            final Integer count = rs.getInt("count");
            final Language language = getLanguageById(rs.getInt("language_id_fk"));
            final List<Genre> genres = getGenresByBookId(id);
            final List<Author> authors = getAuthorsByBookId(id);
            final List<Account> accounts = getAccountsByBookId(id);
            return new Book(
                    id,
                    name,
                    description,
                    photoUrl,
                    yearOfRelease,
                    rate,
                    count,
                    language,
                    genres,
                    authors,
                    accounts
            );
        } catch (SQLException ex) {
            throw new MapperException(ex.getMessage());
        }
    }

    public BookDto toDto(Book entity) throws MapperException {
        String name = entity.getName();
        String description = entity.getDescription();
        String photoUrl = entity.getPhotoUrl();
        String language = entity.getLanguage().toString();
        List<String> authors = new ArrayList<>();
        for (Author e : entity.getAuthors()) {
            authors.add(e.getName());
        }
        List<String> genres = new ArrayList<>();
        for (Genre e : entity.getGenres()) {
            genres.add(e.getName());
        }
        Integer yearOfRelease = entity.getYearOfRelease();
        return new BookDto(
                name,
                description,
                photoUrl,
                language,
                authors,
                genres,
                yearOfRelease
        );
    }

    private Language getLanguageById(Integer id) throws MapperException {
        Language language;
        try {
            language = ((LanguageDao) ProxyDaoFactory.getDaoFor(Language.class)).getById(id).get();
        } catch (DaoException | ConnectionException ex) {
            throw new MapperException(ex.getMessage());
        }
        return language;
    }

    private List<Author> getAuthorsByBookId(Integer id) throws MapperException {
        List<Author> authors;
        try {
            authors = ((AuthorDao) ProxyDaoFactory.getDaoFor(Author.class)).getAllByBookId(id);
        } catch (DaoException | ConnectionException ex) {
            throw new MapperException(ex.getMessage());
        }
        return authors;
    }

    private List<Genre> getGenresByBookId(Integer id) throws MapperException {
        List<Genre> genres;
        try {
            genres = ((GenreDao) ProxyDaoFactory.getDaoFor(Genre.class)).getAllByBookId(id);
        } catch (DaoException | ConnectionException ex) {
            throw new MapperException(ex.getMessage());
        }
        return genres;
    }

    private List<Account> getAccountsByBookId(Integer id) throws MapperException {
        List<Account> accounts;
        try {
            accounts = ((AccountDao) ProxyDaoFactory.getDaoFor(Account.class)).getAllByBookId(id);
        } catch (DaoException | ConnectionException ex) {
            throw new MapperException(ex.getMessage());
        }
        return accounts;
    }
}
