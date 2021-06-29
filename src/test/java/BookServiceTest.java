import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import cache.EntityCache;
import dao.*;
import dto.AccountDto;
import dto.BookDto;
import entity.*;
import exception.DaoException;
import exception.MapperException;
import mapper.AccountMapper;
import mapper.BookMapper;
import mapper.Mapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    BookDao bookDao;

    @Mock
    EntityCache cache;

    List<Book> booksTest;

    int booksCount = 20;

    public void initEntities(int size) {
        booksTest = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            Book testBook = new Book();
            testBook.setId(i);
            testBook.setName("Account-" + i);
            Genre genre = new Genre("Genre-" + i);
            Author author = new Author("Author-" + i);
            testBook.setGenres(Collections.singletonList(genre));
            testBook.setAuthors(Collections.singletonList(author));
            testBook.setCount(i*10);
            testBook.setYearOfRelease(2000 + i);
            testBook.setDescription("Description-" + i);
            testBook.setPhotoUrl("URL-" + i);
            testBook.setRate((double) i);
            booksTest.add(testBook);
        }
    }

    @Before
    public void initialize() throws DaoException {
        initEntities(booksCount);
        bookDao = (BookDao) Dao.of(Book.class);
        cache = EntityCache.getInstance();
        when(bookDao.retrieveAll()).thenReturn(booksTest);
    }

    @Test
    public void testCache() throws DaoException {
        List<Book> booksFromDao = bookDao.retrieveAll();
        List<Book> booksFromCache = (List<Book>) cache.retrieveCollection(Book.class);
        assertNotNull(booksFromCache);
        assertNotNull(booksFromDao);
        assertEquals(booksFromDao.size(), booksFromCache.size());
        assertEquals(booksFromCache, booksFromDao);
    }


    @Test
    public void getAllMapperTest() throws DaoException, MapperException {
        List<Book> entityBooks = bookDao.retrieveAll();
        List<BookDto> dtoBooks = new ArrayList<>();
        BookMapper bookMapper = (BookMapper) Mapper.of(Book.class);
        for (Book e : entityBooks) {
            dtoBooks.add(bookMapper.toDto(e));
        }

        assertNotNull(dtoBooks);
        assertEquals(dtoBooks.size(), entityBooks.size());
        for (int i = 0; i < entityBooks.size(); i++) {
            Book entityBook = entityBooks.get(i);
            BookDto bookDto = dtoBooks.get(i);
            assertEquals(entityBook.getId(), bookDto.getId());
            assertEquals(entityBook.getName(), bookDto.getName());
            assertEquals(bookDto.getCount(), entityBook.getCount());
            assertEquals(bookDto.getDescription(), entityBook.getDescription());
            assertEquals(bookDto.getPhotoUrl(), entityBook.getPhotoUrl());
            assertEquals(bookDto.getYearOfRelease(), entityBook.getYearOfRelease());
            assertEquals(bookDto.getLanguage(), entityBook.getLanguage().getName());
            assertEquals(bookDto.getAuthorsNames().get(0), entityBook.getAuthors().get(0).getName());
            assertEquals(bookDto.getGenresNames().get(0), entityBook.getGenres().get(0).getName());
        }
    }
}