package dao;

import entity.Author;
import exception.ConnectionException;
import exception.DaoException;

import java.util.List;

public interface AuthorDao extends BaseDao<Author> {
    List<Author> getAllByBookId(Integer id) throws DaoException, ConnectionException;
    List<Author> getAllByLanguageId(Integer id) throws DaoException, ConnectionException;
}
