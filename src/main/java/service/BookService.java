package service;

import entity.Book;
import exception.ConnectionException;
import exception.DaoException;
import dto.BookDto;
import exception.ServiceException;

import java.util.List;

public interface BookService extends Service<Book, BookDto> {

    BookDto getOne(Integer id);

    void createBook(String name,
                    String description,
                    String[] authorsId,
                    String[] genresId,
                    String language,
                    String photoUrl,
                    Integer count,
                    Integer year) throws ServiceException;

    List<BookDto> getUnconfirmedBooks(Integer accountId);

    List<BookDto> getConfirmedBooks(Integer accountId);

    void deleteBook(Integer id) throws ServiceException;
}
