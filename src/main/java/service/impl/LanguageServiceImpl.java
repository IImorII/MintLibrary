package service.impl;

import cache.EntityCache;
import dao.LanguageDao;
import dao.factory.ProxyDaoFactory;
import dto.LanguageDto;
import entity.*;
import exception.MapperException;
import mapper.LanguageMapper;
import mapper.factory.MapperFactory;
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
        languageDao = (LanguageDao) ProxyDaoFactory.get(Language.class);
        languageMapper = (LanguageMapper) MapperFactory.get(Language.class);
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
    public void createLanguage(String name) {

    }
}
