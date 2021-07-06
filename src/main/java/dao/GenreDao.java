package dao;

import entity.Genre;
import exception.DaoException;

import java.util.List;

public interface GenreDao extends Dao<Genre> {
    List<Genre> retrieveAllByBookId(Integer id) throws DaoException;
}
