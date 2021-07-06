package dao;

import entity.Book;
import exception.DaoException;

import java.util.List;

public interface BookDao extends Dao<Book> {
    List<Book> retrieveAllByAuthorId(Integer id) throws DaoException;
    List<Book> retrieveAllByGenreId(Integer id) throws DaoException;
    List<Book> retrieveAllByLanguageId(Integer id) throws DaoException;
    List<Book> retrieveAllByAccountId(Integer id) throws DaoException;
    List<Book> retrieveAllConfirmedByAccountId(Integer id) throws DaoException;
    List<Book> retrieveAllUnconfirmedByAccountId(Integer id) throws DaoException;
}
