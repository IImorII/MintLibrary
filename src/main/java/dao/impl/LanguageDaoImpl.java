package dao.impl;

import dao.BaseDao;
import dao.LanguageDao;
import dao.mapper.LanguageMapper;
import dao.mapper.Mapper;
import entity.Language;
import exception.ConnectionException;
import exception.DaoException;

import java.util.*;

public class LanguageDaoImpl implements LanguageDao {

    private static LanguageDaoImpl INSTANCE;

    private LanguageDaoImpl() {
    }

    public static LanguageDaoImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LanguageDaoImpl();
        }
        return INSTANCE;
    }

    private static final String GET_ONE_BY_ID = "select * from language where id = ?";
    private static final String GET_ONE_BY_NAME = "select * from language where language = ?";
    private static final String GET_ALL = "select * from language";
    private static final String CREATE = "insert into language (language) values (?)";
    private static final String UPDATE = "update language set language = ? where id = ?";
    private static final String DELETE = "delete from language where id = ?";

    private static final String GET_ALL_BY_AUTHOR_ID = "select language.* from language " +
            "join author_language on author_language.language_id = language.id " +
            "join author on author.id = author_language.id where author.id = ?";

    @Override
    public List<Language> getAll() throws DaoException, ConnectionException {
        return getManyQuery(GET_ALL, null);
    }

    @Override
    public Optional<Language> getById(Integer id) throws DaoException, ConnectionException {
        return getOneQuery(GET_ONE_BY_ID, Arrays.asList(id));
    }

    @Override
    public Optional<Language> getByName(String name) throws DaoException, ConnectionException {
        return getOneQuery(GET_ONE_BY_NAME, Arrays.asList(name));
    }

    @Override
    public void create(Language entity) throws DaoException, ConnectionException {
        updateQuery(CREATE, Arrays.asList(entity.getName()));
    }

    @Override
    public void update(Language entity) throws DaoException, ConnectionException {
        updateQuery(UPDATE, Arrays.asList(entity.getName(), entity.getId()));
    }

    @Override
    public void delete(Integer id) throws DaoException, ConnectionException {
        updateQuery(DELETE, Collections.singletonList(id));
    }

    @Override
    public List<Language> getAllByAuthorId(Integer id) throws DaoException, ConnectionException {
        return getManyQuery(GET_ALL_BY_AUTHOR_ID, Collections.singletonList(id));
    }

    @Override
    public Mapper<Language> getMapper() {
        return new LanguageMapper();
    }
}
