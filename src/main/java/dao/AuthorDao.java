package dao;

import entity.Author;
import exception.DaoException;

import java.util.List;

public interface AuthorDao extends Dao<Author> {
    List<Author> retrieveAllByBookId(Integer id) throws DaoException;

    List<Author> retrieveAllByLanguageId(Integer id) throws DaoException;
}
