package dao.impl;

import criteria.Criteria;
import dao.BaseDao;
import dao.mapper.LanguageMapper;
import dao.mapper.Mapper;
import dbcp.ConnectionPool;
import entity.Genre;
import entity.Language;
import exception.ConnectionException;
import exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class LanguageDao implements BaseDao<Language> {

    private static LanguageDao INSTANCE;

    private LanguageDao() {
    }

    private static final String GET_ONE_BY_ID = "select * from language where id = ?";
    private static final String GET_ONE_BY_NAME = "select * from language where language = ?";
    private static final String GET_ALL = "select * from language";
    private static final String CREATE = "insert into language (language) values (?)";
    private static final String UPDATE = "update language set language = ? where id = ?";
    private static final String DELETE = "select * from language where id = ?";

    public static LanguageDao getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LanguageDao();
        }
        return INSTANCE;
    }

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
    public Mapper<Language> getModelMapper() {
        return new LanguageMapper();
    }
}
