package service.impl;

import cache.EntityCache;
import dao.AuthorDao;
import dao.Dao;
import dao.LanguageDao;
import dao.factory.ProxyDaoInstance;
import dto.AuthorDto;
import entity.Author;
import entity.Book;
import entity.Genre;
import entity.Language;
import exception.DaoException;
import exception.MapperException;
import exception.ServiceException;
import mapper.AuthorMapper;
import mapper.Mapper;
import mapper.factory.MapperInstance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.AuthorService;

import java.util.ArrayList;
import java.util.List;

public class AuthorServiceImpl implements AuthorService {
    private static Logger log = LogManager.getLogger(LanguageServiceImpl.class);
    private static AuthorServiceImpl INSTANCE;
    private AuthorDao authorDao;
    private AuthorMapper authorMapper;
    private LanguageDao languageDao;
    private EntityCache cache;

    private AuthorServiceImpl() {
        authorDao = (AuthorDao) Dao.of(Author.class);
        languageDao = (LanguageDao) Dao.of(Language.class);
        authorMapper = (AuthorMapper) Mapper.of(Author.class);
        cache = EntityCache.getInstance();
    }

    public static AuthorServiceImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AuthorServiceImpl();
        }
        return INSTANCE;
    }

    @Override
    public List<AuthorDto> getAll() {
        List<AuthorDto> dtoAuthors = new ArrayList<>();
        try {
            List<Author> entityAuthors = (List<Author>) cache.retrieveCollection(Author.class);
            for (Author a : entityAuthors) {
                dtoAuthors.add(authorMapper.toDto(a));
            }
        } catch (MapperException ex) {
            log.error(ex.getMessage());
        }
        return dtoAuthors;
    }

    @Override
    public void createAuthor(String name, Integer birth, String[] languagesId) throws ServiceException {
        try {
            Author author = new Author();
            List<Language> languages = new ArrayList<>();
            author.setName(name);
            author.setYearOfBirth(birth);
            for (String id : languagesId) {
                Language language = languageDao.retrieveById(Integer.parseInt(id)).get();
                languages.add(language);
            }
            author.getLanguages();
            authorDao.create(author);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
}
