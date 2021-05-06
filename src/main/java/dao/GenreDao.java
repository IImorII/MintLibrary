package dao;

import entity.Genre;
import exception.ConnectionException;
import exception.DaoException;

import java.util.List;

public interface GenreDao extends BaseDao<Genre> {
    List<Genre> getAllByBookId(Integer id) throws DaoException, ConnectionException;
}
