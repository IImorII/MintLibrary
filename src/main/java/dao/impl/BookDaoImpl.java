package dao.impl;

import dao.AbstractDao;
import dao.BookDao;
import dao.Dao;
import dao.LanguageDao;
import dto.BookDto;
import entity.Language;
import mapper.Mapper;
import entity.Book;
import exception.DaoException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BookDaoImpl extends AbstractDao<Book> implements BookDao {

    private static BookDaoImpl INSTANCE;

    private BookDaoImpl() {
        super("book");
        setCreateQuery("insert into book (name, description, photo_url, year_of_release, rate, count, language_id_fk) values (?, ?, ?, ?, ?, ?, ?)");
        setUpdateQuary("update book set name = ?, description = ?, photo_url = ?, year_of_release = ?, rate = ?, count = ?, language_id_fk = ? where id = ?");
    }

    public static BookDaoImpl getInstance() {
        try {
            LOCK.lock();
            if (INSTANCE == null) {
                INSTANCE = new BookDaoImpl();
            }
            return INSTANCE;
        } finally {
            LOCK.unlock();
        }
    }

    private final String GET_ALL_BY_AUTHOR_ID = "select book.* from book " +
            "join book_author on book_author.id = book.id " +
            "join author on author.id = book_author.author_id where author.id = ?";
    private final String GET_ALL_BY_LANGUAGE_ID = "select * from book where language_id_fk = ?";
    private final String GET_ALL_BY_GENRE_ID = "select book.* from book " +
            "join book_genre on book_genre.id = book.id " +
            "join genre on genre.id = book_genre.genre_id where genre.id = ?";

    private final String GET_ALL_BY_ACCOUNT_ID = "select book.* from book " +
            "join book_account on book_account.id = book.id " +
            "join account on account.id = book_account.account_id where account.id = ?";
    private final String GET_ALL_CONFIRMED_BY_ACCOUNT_ID = "select book.* from book " +
            "join book_account on book_account.id = book.id " +
            "join account on account.id = book_account.account_id where account.id = ? and book_account.confirmed = true";
    private final String GET_ALL_UNCONFIRMED_BY_ACCOUNT_ID = "select book.* from book " +
            "join book_account on book_account.id = book.id " +
            "join account on account.id = book_account.account_id where account.id = ? and book_account.confirmed = false";
    private final String SET_BOOK_TO_GENRE = "insert into book_genre (id, genre_id) values (?, ?)";
    private final String SET_BOOK_TO_AUTHOR = "insert into book_author (id, author_id) values (?, ?)";
    private final String SET_BOOK_TO_ACCOUNT = "insert into book_account (id, account_id) values (?, ?)";

    @Override
    public void create(Book book) throws DaoException {
        if (book.getLanguage().getId() == null) {
            LanguageDao languageDao = (LanguageDao) Dao.of(Language.class);
            languageDao.create(book.getLanguage());
            book.getLanguage().setId(languageDao.retrieveByName(book.getLanguage().getName()).get().getId());
        }
        if (retrieveByName(book.getName()).isPresent()) {
            if (book.getId() == null) {
                book.setId(retrieveByName(book.getName()).get().getId());
            }
            update(book);
        } else {
            updateQuery(CREATE, Arrays.asList(
                    book.getName(),
                    book.getDescription(),
                    book.getPhotoUrl(),
                    book.getYearOfRelease(),
                    book.getRate(),
                    book.getCount(),
                    book.getLanguage().getId()));
            book.setId(retrieveByName(book.getName()).get().getId());
        }
        List<String> queries = Arrays.asList(SET_BOOK_TO_GENRE, SET_BOOK_TO_AUTHOR);
        createDependencies(book, queries, book.getGenres(), book.getAuthors());
    }

    @Override
    public void update(Book book) throws DaoException {
        updateQuery(UPDATE, Arrays.asList(
                book.getName(),
                book.getDescription(),
                book.getPhotoUrl(),
                book.getYearOfRelease(),
                book.getRate(),
                book.getCount(),
                book.getLanguage().getId(),
                book.getId()));
    }

    @Override
    public List<Book> retrieveAllByAuthorId(Integer id) throws DaoException {
        return getManyQuery(GET_ALL_BY_AUTHOR_ID, Collections.singletonList(id));
    }

    @Override
    public List<Book> retrieveAllByGenreId(Integer id) throws DaoException {
        return getManyQuery(GET_ALL_BY_GENRE_ID, Collections.singletonList(id));
    }

    @Override
    public List<Book> retrieveAllByLanguageId(Integer id) throws DaoException {
        return getManyQuery(GET_ALL_BY_LANGUAGE_ID, Collections.singletonList(id));
    }

    @Override
    public List<Book> retrieveAllByAccountId(Integer id) throws DaoException {
        return getManyQuery(GET_ALL_BY_ACCOUNT_ID, Collections.singletonList(id));
    }

    public List<Book> retrieveAllConfirmedByAccountId(Integer id) throws DaoException {
        return getManyQuery(GET_ALL_CONFIRMED_BY_ACCOUNT_ID, Collections.singletonList(id));
    }

    public List<Book> retrieveAllUnconfirmedByAccountId(Integer id) throws DaoException {
        return getManyQuery(GET_ALL_UNCONFIRMED_BY_ACCOUNT_ID, Collections.singletonList(id));
    }

    @Override
    public Mapper<Book, BookDto> getMapper() {
        return Mapper.of(Book.class);
    }
}
