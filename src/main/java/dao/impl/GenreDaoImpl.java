package dao.impl;

import dao.AbstractBaseDao;
import dao.GenreDao;
import dao.mapper.GenreMapper;
import dao.mapper.Mapper;
import entity.Genre;
import exception.ConnectionException;
import exception.DaoException;

import java.util.*;

public class GenreDaoImpl extends AbstractBaseDao<Genre> implements GenreDao {

    private static GenreDaoImpl INSTANCE;

    private GenreDaoImpl() {
        super("genre");
    }

    public static GenreDaoImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GenreDaoImpl();
        }
        return INSTANCE;
    }

    private static final String GET_ALL_BY_BOOK_ID = "select genre.* from genre " +
            "join book_genre on book_genre.genre_id = genre.id " +
            "join book on book.id = book_genre.id where book.id = ?";

    @Override
    public List<Genre> getAllByBookId(Integer id) throws DaoException, ConnectionException {
        return getManyQuery(GET_ALL_BY_BOOK_ID, Collections.singletonList(id));
    }

    @Override
    public Mapper<Genre> getMapper() {
        return new GenreMapper();
    }
}
