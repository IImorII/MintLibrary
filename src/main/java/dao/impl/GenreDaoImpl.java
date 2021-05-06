package dao.impl;

import dao.BaseDao;
import dao.GenreDao;
import dao.mapper.GenreMapper;
import dao.mapper.Mapper;
import entity.Genre;
import exception.ConnectionException;
import exception.DaoException;

import java.util.*;

public class GenreDaoImpl implements GenreDao {

    private static GenreDaoImpl INSTANCE;

    private GenreDaoImpl() {

    }

    public static GenreDaoImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GenreDaoImpl();
        }
        return INSTANCE;
    }

    private static final String GET_ONE_BY_ID = "select * from genre where id = ?";
    private static final String GET_ONE_BY_NAME = "select * from genre where genre = ?";
    private static final String GET_ALL = "select * from genre";
    private static final String CREATE = "insert into genre (genre) values (?)";
    private static final String UPDATE = "update genre set genre = ? where id = ?";
    private static final String DELETE = "delete from genre where id = ?";
    private static final String GET_ALL_BY_BOOK_ID = "select genre.* from genre " +
            "join book_genre on book_genre.genre_id = genre.id " +
            "join book on book.id = book_genre.id where book.id = ?";

    @Override
    public List<Genre> getAll() throws DaoException, ConnectionException {
        return getManyQuery(GET_ALL, null);
    }

    @Override
    public Optional<Genre> getById(Integer id) throws DaoException, ConnectionException {
        return getOneQuery(GET_ONE_BY_ID, Arrays.asList(id));
    }

    @Override
    public Optional<Genre> getByName(String name) throws DaoException, ConnectionException {
        return getOneQuery(GET_ONE_BY_NAME, Arrays.asList(name));
    }

    @Override
    public void create(Genre entity) throws DaoException, ConnectionException {
        updateQuery(CREATE, Arrays.asList(entity.getName()));
    }

    @Override
    public void update(Genre entity) throws DaoException, ConnectionException {
        updateQuery(UPDATE, Arrays.asList(entity.getName(), entity.getId()));
    }

    @Override
    public void delete(Integer id) throws DaoException, ConnectionException {
        updateQuery(DELETE, Collections.singletonList(id));
    }

    @Override
    public List<Genre> getAllByBookId(Integer id) throws DaoException, ConnectionException {
        return getManyQuery(GET_ALL_BY_BOOK_ID, Collections.singletonList(id));
    }

    @Override
    public Mapper<Genre> getMapper() {
        return new GenreMapper();
    }
}
