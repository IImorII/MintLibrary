package repository;

import dao.BaseDao;
import dao.factory.AbstractDaoFactory;
import dbcp.ConnectionPool;
import entity.*;
import exception.ConnectionException;
import exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class EntityRepository {
    private static EntityRepository INSTANCE;
    private static final Logger log = LogManager.getLogger(EntityRepository.class);

    private EntityRepository() {
    }

    public static EntityRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EntityRepository();
        }
        return INSTANCE;
    }

    private SoftReference<List<Book>> books;
    private SoftReference<List<Author>> authors;
    private SoftReference<List<Genre>> genres;
    private SoftReference<List<Language>> languages;
    private SoftReference<List<Account>> accounts;
    private SoftReference<List<Role>> roles;


    public List<? extends BaseEntity> retrieveCollection(Class<? extends BaseEntity> tClass) {
        BaseDao dao = AbstractDaoFactory.getDaoFor(tClass);
        SoftReference reference = null;
        try {
            reference = switch (tClass.getSimpleName()) {
                case "Book" -> (books == null) ? books = new SoftReference<List<Book>>(dao.getAll()) : books;
                case "Author" -> (authors == null) ? authors = new SoftReference<List<Author>>(dao.getAll()) : authors;
                case "Genre" -> (genres == null) ? genres = new SoftReference<List<Genre>>(dao.getAll()) : genres;
                case "Language" -> (languages == null) ? languages = new SoftReference<List<Language>>(dao.getAll()) : languages;
                case "Account" -> (accounts == null) ? accounts = new SoftReference<List<Account>>(dao.getAll()) : accounts;
                case "Role" -> (roles == null) ? roles = new SoftReference<List<Role>>(dao.getAll()) : roles;
                default -> null;
            };
        } catch (DaoException | ConnectionException ex) {
            log.error(ex.getMessage());
        }
        return (List) reference.get();
    }
}
