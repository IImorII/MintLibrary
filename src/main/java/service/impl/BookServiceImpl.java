package service.impl;

import dao.BookDao;
import dao.impl.ProxyDaoFactory;
import dto.BookDto;
import entity.Author;
import entity.Book;
import entity.Genre;
import entity.Language;
import exception.ConnectionException;
import exception.DaoException;
import exception.MapperException;
import mapper.BookMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import cache.EntityCache;
import service.BookService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookServiceImpl implements BookService {
    private static Logger log = LogManager.getLogger(BookServiceImpl.class);
    private static BookServiceImpl INSTANCE;
    private BookDao bookDao;
    private BookMapper bookMapper;

    private BookServiceImpl() {
        bookDao = (BookDao) ProxyDaoFactory.getDaoFor(Book.class);
        bookMapper = BookMapper.getInstance();
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
            List<Book> entityBooks = (List<Book>) EntityCache.getInstance().retrieveCollection(Book.class);
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
    public void createBook(String name, String description, String author, String genre, String language, String photoUrl, Integer count, Integer year) throws ConnectionException, DaoException {
        Book book = new Book();
        book.setName(name);
        book.setDescription(description);
        book.setAuthors(Collections.singletonList(new Author(author)));
        book.setGenres(Collections.singletonList(new Genre(genre)));
        book.setLanguage(new Language(language));
        book.setPhotoUrl(photoUrl);
        book.setCount(count);
        book.setYearOfRelease(year);
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
        ProxyDaoFactory.getDaoFor(Book.class).delete(id);
    }
}
