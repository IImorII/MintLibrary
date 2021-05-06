package dao.impl;

import dao.BaseDao;
import dao.BookDao;
import dao.mapper.BookMapper;
import dao.mapper.Mapper;
import entity.Book;
import exception.ConnectionException;
import exception.DaoException;

import java.util.*;

public class BookDaoImpl implements BookDao {

    private static BookDaoImpl INSTANCE;

    private BookDaoImpl() {

    }

    public static BookDaoImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BookDaoImpl();
        }
        return INSTANCE;
    }

    private static final String GET_ONE_BY_ID = "select * from book where id = ?";
    private static final String GET_ONE_BY_NAME = "select * from book where name = ?";
    private static final String GET_ALL = "select * from book";
    private static final String CREATE = "insert into book (name, year_of_release, rate, count, language_fk) values (?, ?, ?, ?, ?)";
    private static final String UPDATE = "update book set name = ?, year_of_release = ?, rate = ?, count = ?, language_fk = ?, where id = ?";
    private static final String DELETE = "delete from book where id = ?";

    private static final String GET_ALL_BY_AUTHOR_ID = "select book.* from book " +
            "join book_author on book_author.id = book.id " +
            "join author on author.id = book_author.author_id where author.id = ?";
    private static final String GET_ALL_BY_LANGUAGE_ID = "select * from book where language_id_fk = ?";
    private static final String GET_ALL_BY_GENRE_ID = "select book.* from book " +
            "join book_genre on book_genre.id = book.id " +
            "join genre on genre.id = book_genre.genre_id where genre.id = ?";
    private static final String GET_ALL_BY_TICKET_ID = "select book.* from book " +
            "join ticket_book on ticket_book.book_id = book.id " +
            "join ticket on ticket.id = ticket_book.id where ticket.id = ?";

    @Override
    public List<Book> getAll() throws DaoException, ConnectionException {
        return getManyQuery(GET_ALL, null);
    }

    @Override
    public Optional<Book> getById(Integer id) throws DaoException, ConnectionException {
        return getOneQuery(GET_ONE_BY_ID, Collections.singletonList(id));
    }

    @Override
    public Optional<Book> getByName(String name) throws DaoException, ConnectionException {
        return getOneQuery(GET_ONE_BY_NAME, Collections.singletonList(name));
    }

    @Override
    public void create(Book entity) throws DaoException, ConnectionException {
        updateQuery(CREATE, Arrays.asList(
                entity.getName(),
                entity.getYearOfRelease(),
                entity.getRate(),
                entity.getCount(),
                entity.getLanguage().getId()));
    }

    @Override
    public void update(Book entity) throws DaoException, ConnectionException {
        updateQuery(UPDATE, Arrays.asList(
                entity.getName(),
                entity.getYearOfRelease(),
                entity.getRate(),
                entity.getCount(),
                entity.getLanguage().getId(),
                entity.getId()));
    }

    @Override
    public void delete(Integer id) throws DaoException, ConnectionException {
        updateQuery(DELETE, Collections.singletonList(id));
    }

    @Override
    public List<Book> getAllByAuthorId(Integer id) throws DaoException, ConnectionException {
        return getManyQuery(GET_ALL_BY_AUTHOR_ID, Collections.singletonList(id));
    }

    @Override
    public List<Book> getAllByGenreId(Integer id) throws DaoException, ConnectionException {
        return getManyQuery(GET_ALL_BY_GENRE_ID, Collections.singletonList(id));
    }

    @Override
    public List<Book> getAllByLanguageId(Integer id) throws DaoException, ConnectionException {
        return getManyQuery(GET_ALL_BY_LANGUAGE_ID, Collections.singletonList(id));
    }

    @Override
    public List<Book> getAllByTicketId(Integer id) throws DaoException, ConnectionException {
        return getManyQuery(GET_ALL_BY_TICKET_ID, Collections.singletonList(id));
    }

    @Override
    public Mapper<Book> getMapper() {
        return new BookMapper();
    }
}
