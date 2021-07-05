package service.impl;

import cache.EntityCache;
import dao.Dao;
import dao.GenreDao;
import dao.factory.ProxyDaoInstance;
import dto.GenreDto;
import entity.Genre;
import entity.Language;
import exception.DaoException;
import exception.MapperException;
import exception.ServiceException;
import mapper.GenreMapper;
import mapper.Mapper;
import mapper.factory.MapperInstance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.GenreService;

import java.util.ArrayList;
import java.util.List;

public class GenreServiceImpl implements GenreService {
    private static Logger log = LogManager.getLogger(GenreServiceImpl.class);
    private static GenreServiceImpl INSTANCE;
    private GenreDao genreDao;
    private GenreMapper genreMapper;
    private EntityCache cache;

    private GenreServiceImpl() {
        genreDao = (GenreDao) Dao.of(Genre.class);
        genreMapper = (GenreMapper) Mapper.of(Genre.class);
        cache = EntityCache.getInstance();
    }

    public static GenreServiceImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GenreServiceImpl();
        }
        return INSTANCE;
    }

    @Override
    public List<GenreDto> getAll() {
        List<GenreDto> dtoGenres = new ArrayList<>();
        try {
            List<Genre> entityGenres = (List<Genre>) cache.retrieveCollection(Genre.class);
            for (Genre g : entityGenres) {
                dtoGenres.add(genreMapper.toDto(g));
            }
        } catch (MapperException ex) {
            log.error(ex.getMessage());
        }
        return dtoGenres;
    }

    @Override
    public void createGenre(String name) throws ServiceException{
        try {
            Genre genre = new Genre();
            genre.setName(name);
            genreDao.create(genre);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    @Override
    public void deleteGenre(Integer genreId) throws ServiceException {
        try {
            genreDao.delete(genreId);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }

    }

    @Override
    public void updateGenre(Integer genreId, String name) throws ServiceException {
        try {
            Genre genre = genreDao.retrieveById(genreId).get();
            if (name != null) {
                genre.setName(name);
            }
            genreDao.update(genre);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
}
