package service.impl;

import cache.EntityCache;
import dao.AuthorDao;
import dao.LanguageDao;
import dao.factory.ProxyDaoFactory;
import dto.AuthorDto;
import dto.LanguageDto;
import entity.Author;
import entity.Language;
import exception.MapperException;
import mapper.AuthorMapper;
import mapper.LanguageMapper;
import mapper.factory.MapperFactory;
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
    private EntityCache cache;

    private AuthorServiceImpl() {
        authorDao = (AuthorDao) ProxyDaoFactory.get(Author.class);
        authorMapper = (AuthorMapper) MapperFactory.get(Author.class);
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
    public void createAuthor(String name, Integer birth, String[] languagesId) {

    }
}
