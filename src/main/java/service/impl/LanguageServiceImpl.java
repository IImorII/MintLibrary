package service.impl;

import cache.EntityCache;
import dao.Dao;
import dao.LanguageDao;
import dao.factory.ProxyDaoInstance;
import dto.LanguageDto;
import entity.*;
import exception.DaoException;
import exception.MapperException;
import exception.ServiceException;
import mapper.LanguageMapper;
import mapper.Mapper;
import mapper.factory.MapperInstance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.LanguageService;

import java.util.ArrayList;
import java.util.List;

public class LanguageServiceImpl implements LanguageService {
    private static Logger log = LogManager.getLogger(LanguageServiceImpl.class);
    private static LanguageServiceImpl INSTANCE;
    private LanguageDao languageDao;
    private LanguageMapper languageMapper;
    private EntityCache cache;

    private LanguageServiceImpl() {
        languageDao = (LanguageDao) Dao.of(Language.class);
        languageMapper = (LanguageMapper) Mapper.of(Language.class);
        cache = EntityCache.getInstance();
    }

    public static LanguageServiceImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LanguageServiceImpl();
        }
        return INSTANCE;
    }

    @Override
    public List<LanguageDto> getAll() {
        List<LanguageDto> dtoLanguages = new ArrayList<>();
        try {
            List<Language> entityLanguages = (List<Language>) cache.retrieveCollection(Language.class);
            for (Language l : entityLanguages) {
                dtoLanguages.add(languageMapper.toDto(l));
            }
        } catch (MapperException ex) {
            log.error(ex.getMessage());
        }
        return dtoLanguages;
    }

    @Override
    public void createLanguage(String name) throws ServiceException{
        try {
            Language language = new Language();
            language.setName(name);
            languageDao.create(language);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    @Override
    public void deleteLanguage(Integer languageId) throws ServiceException {
        try {
            languageDao.delete(languageId);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }

    }

    @Override
    public void updateLanguage(Integer languageId, String name) throws ServiceException {
        try {
            Language language = languageDao.retrieveById(languageId).get();
            if (name != null) {
                language.setName(name);
            }
            languageDao.update(language);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
}
