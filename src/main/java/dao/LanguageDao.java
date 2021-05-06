package dao;

import entity.Language;
import exception.ConnectionException;
import exception.DaoException;

import java.util.List;

public interface LanguageDao extends BaseDao<Language> {
    List<Language> getAllByAuthorId(Integer id) throws DaoException, ConnectionException;
}
