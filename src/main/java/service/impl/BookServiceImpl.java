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
import repository.EntityCache;
import service.BookService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookServiceImpl implements BookService {
    private static Logger log = LogManager.getLogger(BookService.class);
    private static BookServiceImpl INSTANCE;
    BookDao bookDao;

    private BookServiceImpl() {
        bookDao = (BookDao) ProxyDaoFactory.getDaoFor(Book.class);
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
                dtoBooks.add(BookMapper.getInstance().toDto(e));
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
            dtoBook = BookMapper.getInstance().toDto(entity);
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
        List<BookDto> dtoBooks = new ArrayList<>();
        try {
            List<Book> entityBooks = bookDao.getAllUnconfirmedByAccountId(accountId);
            for (Book e : entityBooks) {
                dtoBooks.add(BookMapper.getInstance().toDto(e));
            }
        } catch (DaoException | ConnectionException | MapperException ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
        return dtoBooks;
    }

    @Override
    public List<BookDto> getConfirmedBooks(Integer accountId) {
        List<BookDto> dtoBooks = new ArrayList<>();
        try {
            List<Book> entityBooks = bookDao.getAllConfirmedByAccountId(accountId);
            for (Book e : entityBooks) {
                dtoBooks.add(BookMapper.getInstance().toDto(e));
            }
        } catch (DaoException | ConnectionException | MapperException ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
        return dtoBooks;
    }

    @Override
    public void deleteBook(Integer id) throws ConnectionException, DaoException {
        ProxyDaoFactory.getDaoFor(Book.class).delete(id);
    }
}
