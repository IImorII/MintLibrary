package service.impl;

import dao.BookDao;
import dao.impl.ProxyDaoFactory;
import entity.Author;
import entity.Genre;
import entity.Language;
import exception.ConnectionException;
import exception.DaoException;
import mapper.BookMapper;
import entity.Book;
import dto.BookDto;
import exception.MapperException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.EntityRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BookServiceImpl {
    private static Logger log = LogManager.getLogger(BookServiceImpl.class);
    private static BookServiceImpl INSTANCE;

    private BookServiceImpl() {
    }

    public static BookServiceImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BookServiceImpl();
        }
        return INSTANCE;
    }

    public List<BookDto> getAll() {
        List<BookDto> dtoBooks = new ArrayList<>();
        try {
            List<Book> entityBooks = (List<Book>) ProxyDaoFactory.getDaoFor(Book.class).getAll();
            for (Book e : entityBooks) {
                dtoBooks.add(BookMapper.getInstance().toDto(e));
            }
        } catch (DaoException | ConnectionException | MapperException ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
        return dtoBooks;
    }

    public BookDto getOne(Integer id) {
        BookDto dtoBook = null;
        try {
            Book entity = (Book) ProxyDaoFactory.getDaoFor(Book.class).getById(id).get();
            dtoBook = BookMapper.getInstance().toDto(entity);
        } catch (DaoException | ConnectionException | MapperException ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
        return dtoBook;
    }

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
        ProxyDaoFactory.getDaoFor(Book.class).create(book);
    }

    public List<BookDto> getUnconfirmedBooks(Integer accountId) {
        List<BookDto> dtoBooks = new ArrayList<>();
        try {
            List<Book> entityBooks = ((BookDao) ProxyDaoFactory.getDaoFor(Book.class)).getAllByAccountId(accountId);
            for (Book e : entityBooks) {
                dtoBooks.add(BookMapper.getInstance().toDto(e));
            }
        } catch (DaoException | ConnectionException | MapperException ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
        return dtoBooks;
    }

    public void deleteBook(Integer id) throws ConnectionException, DaoException {
        ProxyDaoFactory.getDaoFor(Book.class).delete(id);
    }
}
