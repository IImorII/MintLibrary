package service.impl;

import cache.EntityCache;
import dao.GenreDao;
import dao.factory.ProxyDaoFactory;
import dto.GenreDto;
import entity.Genre;
import exception.MapperException;
import mapper.GenreMapper;
import mapper.factory.MapperFactory;
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
        genreDao = (GenreDao) ProxyDaoFactory.get(Genre.class);
        genreMapper = (GenreMapper) MapperFactory.get(Genre.class);
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
    public void createGenre(String name) {

    }
}
