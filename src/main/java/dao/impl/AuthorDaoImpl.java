package dao.impl;

import dao.AbstractBaseDao;
import dao.AuthorDao;
import mapper.AuthorMapper;
import mapper.Mapper;
import entity.Author;

import exception.ConnectionException;
import exception.DaoException;

import java.util.*;

public class AuthorDaoImpl extends AbstractBaseDao<Author> implements AuthorDao {

    private static AuthorDaoImpl INSTANCE;

    private AuthorDaoImpl() {
        super("author");
        setCreateQuery("insert into author (name, year_of_birth) values (?, ?)");
        setUpdateQuary("update author set name = ?, year_of_birth = ? where id = ?");
    }

    protected static AuthorDaoImpl getInstance() {
        try {
            LOCK.lock();
            if (INSTANCE == null) {
                INSTANCE = new AuthorDaoImpl();
            }
            return INSTANCE;
        } finally {
            LOCK.unlock();
        }
    }

    private final String GET_ALL_BY_BOOK_ID = "select author.* from author " +
            "join book_author on book_author.author_id = author.id " +
            "join book on book.id = book_author.id where book.id = ?";
    private final String GET_ALL_BY_LANGUAGE_ID = "select author.* from author " +
            "join author_language on author_language.id = author.id " +
            "join language on language.id = author_language.language_id where language.id = ?";

    private final String SET_AUTHOR_TO_BOOK = "insert into book_author (author_id, id) values (?, ?)";
    private final String SET_AUTHOR_TO_LANGUAGE = "insert into author_language (id, language_id) values (?, ?)";


    @Override
    public void create(Author author) throws DaoException, ConnectionException {
        if (getByName(author.getName()).isPresent()) {
            throw new DaoException(author.getName() + " is duplicate!");
        }
        updateQuery(CREATE, Arrays.asList(author.getName(), author.getYearOfBirth()));
        author.setId(getByName(author.getName()).get().getId());
        List<String> queries = Arrays.asList(SET_AUTHOR_TO_BOOK, SET_AUTHOR_TO_LANGUAGE);
        updateDependencies(author, queries, author.getBooks(), author.getLanguages());
    }

    @Override
    public void update(Author author) throws DaoException, ConnectionException {
        updateQuery(UPDATE, Arrays.asList(author.getName(), author.getYearOfBirth(), author.getId()));
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
        return AuthorMapper.getInstance();
    }
}
