package dao;

import entity.Book;
import exception.ConnectionException;
import exception.DaoException;

import java.util.List;

public interface BookDao extends BaseDao<Book> {
    List<Book> getAllByAuthorId(Integer id) throws DaoException, ConnectionException;
    List<Book> getAllByGenreId(Integer id) throws DaoException, ConnectionException;
    List<Book> getAllByLanguageId(Integer id) throws DaoException, ConnectionException;
    List<Book> getAllByTicketId(Integer id) throws DaoException, ConnectionException;
}
