package service;

import entity.Book;
import exception.ConnectionException;
import exception.DaoException;
import dto.BookDto;
import exception.ServiceException;

import java.util.List;

public interface BookService extends Service<Book, BookDto> {

    BookDto getOne(Integer id) throws ServiceException ;

    void createBook(String name,
                    String description,
                    String[] authorsId,
                    String[] genresId,
                    String language,
                    String photoUrl,
                    Integer count,
                    Integer year) throws ServiceException;

    List<BookDto> getAllByCriteria(String searchName, String[] genresId, String[] authorsId) throws ServiceException ;

    List<BookDto> getUnconfirmedBooks(Integer accountId) throws ServiceException;

    List<BookDto> getConfirmedBooks(Integer accountId) throws ServiceException;

    void deleteBook(Integer id) throws ServiceException;
}
