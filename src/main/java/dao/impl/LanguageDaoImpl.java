package dao.impl;

import dao.AbstractDao;
import dao.LanguageDao;
import dto.LanguageDto;
import mapper.LanguageMapper;
import mapper.Mapper;
import entity.Language;
import exception.ConnectionException;
import exception.DaoException;
import mapper.factory.MapperInstance;

import java.util.*;

public class LanguageDaoImpl extends AbstractDao<Language> implements LanguageDao {

    private static LanguageDaoImpl INSTANCE;

    private LanguageDaoImpl() {
        super("language");
    }

    public static LanguageDaoImpl getInstance() {
        try {
            LOCK.lock();
            if (INSTANCE == null) {
                INSTANCE = new LanguageDaoImpl();
            }
            return INSTANCE;
        } finally {
            LOCK.unlock();
        }
    }

    private static final String GET_ALL_BY_AUTHOR_ID = "select language.* from language " +
            "join author_language on author_language.language_id = language.id " +
            "join author on author.id = author_language.id where author.id = ?";

    @Override
    public List<Language> retrieveAllByAuthorId(Integer id) throws DaoException {
        return getManyQuery(GET_ALL_BY_AUTHOR_ID, Collections.singletonList(id));
    }

    @Override
    public Mapper<Language, LanguageDto> getMapper() {
        return Mapper.of(Language.class);
    }
}
