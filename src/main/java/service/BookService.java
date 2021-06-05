package service;

import exception.ConnectionException;
import exception.DaoException;
import dto.BookDto;

import java.util.List;

public interface BookService {
    List<BookDto> getAll();

    BookDto getOne(Integer id);

    void createBook(String name,
                    String description,
                    String author,
                    String genre,
                    String language,
                    String photoUrl,
                    Integer count,
                    Integer year) throws ConnectionException, DaoException;

    List<BookDto> getUnconfirmedBooks(Integer accountId);

    List<BookDto> getConfirmedBooks(Integer accountId);

    void deleteBook(Integer id) throws ConnectionException, DaoException;
}
