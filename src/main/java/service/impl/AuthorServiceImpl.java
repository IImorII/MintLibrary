package service.impl;

import cache.EntityCache;
import dao.AuthorDao;
import dao.Dao;
import dao.LanguageDao;
import dto.AuthorDto;
import entity.Author;
import entity.Language;
import exception.DaoException;
import exception.MapperException;
import exception.ServiceException;
import mapper.AuthorMapper;
import mapper.Mapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.AuthorService;

import java.util.ArrayList;
import java.util.List;

public class AuthorServiceImpl implements AuthorService {
    private static final Logger log = LogManager.getLogger(LanguageServiceImpl.class);
    private static AuthorServiceImpl INSTANCE;
    private final AuthorDao authorDao;
    private final AuthorMapper authorMapper;
    private final LanguageDao languageDao;
    private final EntityCache cache;

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
            author.setLanguages(languages);
            authorDao.create(author);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    @Override
    public void deleteAuthor(Integer authorId) throws ServiceException {
        try {
            authorDao.delete(authorId);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }

    }

    @Override
    public void updateAuthor(Integer authorId, String name, Integer birth) throws ServiceException {
        try {
            Author author = authorDao.retrieveById(authorId).get();
            if (name != null) {
                author.setName(name);
            }
            if (birth != null) {
                author.setYearOfBirth(birth);
            }
            authorDao.update(author);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
}
