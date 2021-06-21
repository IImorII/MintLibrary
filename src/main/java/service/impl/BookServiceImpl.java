package service.impl;

import dao.*;
import dto.BookDto;
import entity.Author;
import entity.Book;
import entity.Genre;
import entity.Language;
import exception.ConnectionException;
import exception.DaoException;
import exception.MapperException;
import exception.ServiceException;
import mapper.BookMapper;
import mapper.Mapper;
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
        bookDao = (BookDao) Dao.of(Book.class);
        languageDao = (LanguageDao) Dao.of(Language.class);
        genreDao = (GenreDao) Dao.of(Genre.class);
        authorDao = (AuthorDao) Dao.of(Author.class);
        bookMapper = (BookMapper) Mapper.of(Book.class);
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
            Book entity = bookDao.retrieveById(id).get();
            dtoBook = bookMapper.toDto(entity);
        } catch (DaoException | MapperException ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
        return dtoBook;
    }

    @Override
    public void createBook(String name, String description, String[] authorsId, String[] genresId, String languageId, String photoUrl, Integer count, Integer year) throws ServiceException {
        try {
            Book book = new Book();
            List<Author> authors = new ArrayList<>();
            List<Genre> genres = new ArrayList<>();
            System.out.println(languageId);
            book.setName(name);
            book.setDescription(description);
            for (String id : authorsId) {
                Author author = authorDao.retrieveById(Integer.parseInt(id)).get();
                authors.add(author);
            }
            for (String id : genresId) {
                Genre genre = genreDao.retrieveById(Integer.parseInt(id)).get();
                genres.add(genre);
            }
            Language languageEntity = languageDao.retrieveById(Integer.parseInt(languageId)).get();
            book.setLanguage(languageEntity);
            book.setPhotoUrl(photoUrl);
            book.setCount(count);
            book.setYearOfRelease(year);
            book.setGenres(genres);
            book.setAuthors(authors);
            bookDao.create(book);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    @Override
    public List<BookDto> getUnconfirmedBooks(Integer accountId) {
        List<BookDto> unconfirmedBooks = new ArrayList<>();
        try {
            List<Book> entityBooks = bookDao.retrieveAllUnconfirmedByAccountId(accountId);
            for (Book e : entityBooks) {
                unconfirmedBooks.add(bookMapper.toDto(e));
            }
        } catch (DaoException | MapperException ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
        return unconfirmedBooks;
    }

    @Override
    public List<BookDto> getConfirmedBooks(Integer accountId) {
        List<BookDto> confirmedBooks = new ArrayList<>();
        try {
            List<Book> entityBooks = bookDao.retrieveAllConfirmedByAccountId(accountId);
            for (Book e : entityBooks) {
                confirmedBooks.add(bookMapper.toDto(e));
            }
        } catch (DaoException | MapperException ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
        return confirmedBooks;
    }

    @Override
    public void deleteBook(Integer id) throws ServiceException {
        try {
            bookDao.delete(id);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
}
