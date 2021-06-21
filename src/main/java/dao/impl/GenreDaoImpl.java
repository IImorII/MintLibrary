package dao.impl;

import dao.AbstractDao;
import dao.GenreDao;
import dto.GenreDto;
import mapper.GenreMapper;
import mapper.Mapper;
import entity.Genre;
import exception.ConnectionException;
import exception.DaoException;
import mapper.factory.MapperInstance;

import java.util.*;

public class GenreDaoImpl extends AbstractDao<Genre> implements GenreDao {

    private static GenreDaoImpl INSTANCE;

    private GenreDaoImpl() {
        super("genre");
    }

    public static GenreDaoImpl getInstance() {
        try {
            LOCK.lock();
            if (INSTANCE == null) {
                INSTANCE = new GenreDaoImpl();
            }
            return INSTANCE;
        } finally {
            LOCK.unlock();
        }
    }

    private static final String GET_ALL_BY_BOOK_ID = "select genre.* from genre " +
            "join book_genre on book_genre.genre_id = genre.id " +
            "join book on book.id = book_genre.id where book.id = ?";

    @Override
    public List<Genre> retrieveAllByBookId(Integer id) throws DaoException {
        return getManyQuery(GET_ALL_BY_BOOK_ID, Collections.singletonList(id));
    }

    @Override
    public Mapper<Genre, GenreDto> getMapper() {
        return Mapper.of(Genre.class);
    }
}
