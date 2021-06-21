package mapper;

//import dao.impl.AuthorDaoImpl;

import dao.*;
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

public class BookMapper implements Mapper<Book, BookDto> {

    private static BookMapper INSTANCE;
    private GenreDao genreDao;
    private LanguageDao languageDao;
    private AuthorDao authorDao;

    private BookMapper() {
        genreDao = (GenreDao) Dao.of(Genre.class);
        languageDao = (LanguageDao) Dao.of(Language.class);
        authorDao = (AuthorDao) Dao.of(Author.class);
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
                    authors
            );
        } catch (SQLException ex) {
            throw new MapperException(ex.getMessage());
        }
    }

    public BookDto toDto(Book entity) throws MapperException {
        final Integer id = entity.getId();
        final Integer count = entity.getCount();
        final String name = entity.getName();
        final String description = entity.getDescription();
        final String photoUrl = entity.getPhotoUrl();
        final String language = entity.getLanguage().toString();
        final List<String> authors = new ArrayList<>();
        for (Author e : entity.getAuthors()) {
            authors.add(e.getName());
        }
        final List<String> genres = new ArrayList<>();
        for (Genre e : entity.getGenres()) {
            genres.add(e.getName());
        }
        Integer yearOfRelease = entity.getYearOfRelease();
        return new BookDto(
                id,
                count,
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
            language = languageDao.retrieveById(id).get();
        } catch (DaoException | ConnectionException ex) {
            throw new MapperException(ex.getMessage());
        }
        return language;
    }

    private List<Author> getAuthorsByBookId(Integer id) throws MapperException {
        List<Author> authors;
        try {
            authors = authorDao.retrieveAllByBookId(id);
        } catch (DaoException | ConnectionException ex) {
            throw new MapperException(ex.getMessage());
        }
        return authors;
    }

    private List<Genre> getGenresByBookId(Integer id) throws MapperException {
        List<Genre> genres;
        try {
            genres = genreDao.retrieveAllByBookId(id);
        } catch (DaoException | ConnectionException ex) {
            throw new MapperException(ex.getMessage());
        }
        return genres;
    }
}
