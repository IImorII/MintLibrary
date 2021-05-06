package dao.impl;

import dao.AuthorDao;
import dao.BaseDao;
import dao.mapper.AuthorMapper;
import dao.mapper.Mapper;
import entity.Author;
import exception.ConnectionException;
import exception.DaoException;

import java.util.*;

public class AuthorDaoImpl implements AuthorDao {

    private static AuthorDaoImpl INSTANCE;

    private AuthorDaoImpl() {

    }

    public static AuthorDaoImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AuthorDaoImpl();
        }
        return INSTANCE;
    }

    private static final String GET_ONE_BY_ID = "select * from author where id = ?";
    private static final String GET_ONE_BY_NAME = "select * from author where name = ?";
    private static final String GET_ALL = "select * from author";
    private static final String CREATE = "insert into author (name, birth) values (?, ?)";
    private static final String UPDATE = "update author set name = ?, birth = ? where id = ?";
    private static final String DELETE = "delete from author where id = ?";

    private static final String GET_ALL_BY_BOOK_ID = "select author.* from author " +
            "join book_author on book_author.author_id = author.id " +
            "join book on book.id = book_author.id where book.id = ?";
    private static final String GET_ALL_BY_LANGUAGE_ID = "select author.* from author " +
            "join author_language on author_language.id = author.id " +
            "join language on language.id = author_language.language_id where language.id = ?";

    @Override
    public List<Author> getAll() throws DaoException, ConnectionException {
        return getManyQuery(GET_ALL, null);
    }

    @Override
    public Optional<Author> getById(Integer id) throws DaoException, ConnectionException {
        return getOneQuery(GET_ONE_BY_ID, Collections.singletonList(id));
    }

    @Override
    public Optional<Author> getByName(String name) throws DaoException, ConnectionException {
        return getOneQuery(GET_ONE_BY_NAME, Collections.singletonList(name));
    }

    @Override
    public void create(Author entity) throws DaoException, ConnectionException {
        updateQuery(CREATE, Arrays.asList(entity.getName(), entity.getBirth()));
    }

    @Override
    public void update(Author entity) throws DaoException, ConnectionException {
        updateQuery(UPDATE, Arrays.asList(entity.getName(), entity.getBirth(), entity.getId()));
    }

    @Override
    public void delete(Integer id) throws DaoException, ConnectionException {
        updateQuery(DELETE, Collections.singletonList(id));
    }

    @Override
    public List<Author> getAllByBookId(Integer id) throws DaoException, ConnectionException {
        return getManyQuery(GET_ALL_BY_BOOK_ID, Collections.singletonList(id));
    }

    @Override
    public List<Author> getAllByLanguageId(Integer id) throws DaoException, ConnectionException {
        return getManyQuery(GET_ALL_BY_LANGUAGE_ID, Collections.singletonList(id));
    }

    @Override
    public Mapper<Author> getMapper() {
        return new AuthorMapper();
    }
}
