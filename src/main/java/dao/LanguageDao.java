package dao;

import entity.Language;
import exception.ConnectionException;
import exception.DaoException;

import java.util.List;

public interface LanguageDao extends Dao<Language> {
    List<Language> retrieveAllByAuthorId(Integer id) throws DaoException;
}
