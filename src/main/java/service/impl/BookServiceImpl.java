package service.impl;

import dao.AuthorDao;
import dao.BookDao;
import dao.GenreDao;
import dao.LanguageDao;
import dao.factory.ProxyDaoFactory;
import dto.BookDto;
import entity.Author;
import entity.Book;
import entity.Genre;
import entity.Language;
import exception.ConnectionException;
import exception.DaoException;
import exception.MapperException;
import mapper.BookMapper;
import mapper.factory.MapperFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import cache.EntityCache;
import service.BookService;

import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {
    private static Logger log = LogManager.getLogger(BookServiceImpl.class);
    private static BookServiceImpl INSTANCE;
    private BookDao bookDao;
    private LanguageDao languageDao;
    private GenreDao genreDao;
    private AuthorDao authorDao;
    private BookMapper bookMapper;
    private EntityCache cache;

    private BookServiceImpl() {
        bookDao = (BookDao) ProxyDaoFactory.get(Book.class);
        languageDao = (LanguageDao) ProxyDaoFactory.get(Language.class);
        genreDao = (GenreDao) ProxyDaoFactory.get(Genre.class);
        authorDao = (AuthorDao) ProxyDaoFactory.get(Author.class);
        bookMapper = (BookMapper) MapperFactory.get(Book.class);
        cache = EntityCache.getInstance();
    }

    public static BookServiceImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BookServiceImpl();
        }
        return INSTANCE;
    }

    @Override
    public List<BookDto> getAll() {
        List<BookDto> dtoBooks = new ArrayList<>();
        try {
            List<Book> entityBooks = (List<Book>) cache.retrieveCollection(Book.class);
            for (Book e : entityBooks) {
                dtoBooks.add(bookMapper.toDto(e));
            }
        } catch (MapperException ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
        return dtoBooks;
    }

    @Override
    public BookDto getOne(Integer id) {
        BookDto dtoBook = null;
        try {
            Book entity = bookDao.getById(id).get();
            dtoBook = bookMapper.toDto(entity);
        } catch (DaoException | ConnectionException | MapperException ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
        return dtoBook;
    }

    @Override
    public void createBook(String name, String description, String[] authorsId, String[] genresId, String languageId, String photoUrl, Integer count, Integer year) throws ConnectionException, DaoException {
        Book book = new Book();
        List<Author> authors = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        book.setName(name);
        book.setDescription(description);
        for (String id : authorsId) {
            Author author = authorDao.getById(Integer.parseInt(id)).get();
            authors.add(author);
        }
        for (String id : genresId) {
            Genre genre = genreDao.getById(Integer.parseInt(id)).get();
            genres.add(genre);
        }
        Language languageEntity = languageDao.getById(Integer.parseInt(languageId)).get();
        book.setLanguage(languageEntity);
        book.setPhotoUrl(photoUrl);
        book.setCount(count);
        book.setYearOfRelease(year);
        book.setGenres(genres);
        book.setAuthors(authors);
        bookDao.create(book);
    }

    @Override
    public List<BookDto> getUnconfirmedBooks(Integer accountId) {
        List<BookDto> unconfirmedBooks = new ArrayList<>();
        try {
            List<Book> entityBooks = bookDao.getAllUnconfirmedByAccountId(accountId);
            for (Book e : entityBooks) {
                unconfirmedBooks.add(bookMapper.toDto(e));
            }
        } catch (DaoException | ConnectionException | MapperException ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
        return unconfirmedBooks;
    }

    @Override
    public List<BookDto> getConfirmedBooks(Integer accountId) {
        List<BookDto> confirmedBooks = new ArrayList<>();
        try {
            List<Book> entityBooks = bookDao.getAllConfirmedByAccountId(accountId);
            for (Book e : entityBooks) {
                confirmedBooks.add(bookMapper.toDto(e));
            }
        } catch (DaoException | ConnectionException | MapperException ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
        return confirmedBooks;
    }

    @Override
    public void deleteBook(Integer id) throws ConnectionException, DaoException {
        ProxyDaoFactory.get(Book.class).delete(id);
    }
}
