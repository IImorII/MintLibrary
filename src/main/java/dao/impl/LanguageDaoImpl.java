package dao.impl;

import dao.AbstractBaseDao;
import dao.LanguageDao;
import dao.mapper.LanguageMapper;
import dao.mapper.Mapper;
import entity.Language;
import exception.ConnectionException;
import exception.DaoException;

import java.util.*;

public class LanguageDaoImpl extends AbstractBaseDao<Language> implements LanguageDao {

    private static LanguageDaoImpl INSTANCE;

    private LanguageDaoImpl() {
        super("language");
    }

    public static LanguageDaoImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LanguageDaoImpl();
        }
        return INSTANCE;
    }

    private static final String GET_ALL_BY_AUTHOR_ID = "select language.* from language " +
            "join author_language on author_language.language_id = language.id " +
            "join author on author.id = author_language.id where author.id = ?";

    @Override
    public List<Language> getAllByAuthorId(Integer id) throws DaoException, ConnectionException {
        return getManyQuery(GET_ALL_BY_AUTHOR_ID, Collections.singletonList(id));
    }

    @Override
    public Mapper<Language> getMapper() {
        return new LanguageMapper();
    }
}
