package repository;

import dao.BaseDao;
import dao.impl.ProxyDaoFactory;
import entity.Account;
import entity.Author;
import entity.BaseEntity;
import entity.Book;
import entity.Genre;
import entity.Language;
import entity.Role;
import exception.ConnectionException;
import exception.DaoException;
import listener.UpdateDBEvent;
import listener.UpdateDBListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.ref.SoftReference;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class EntityCache {
    private static EntityCache INSTANCE;
    private static final Logger log = LogManager.getLogger(EntityCache.class);

    private EntityCache() {
        TimerTask cacheRefresher = CacheRefresher.getInstance();
        Timer updateTimer = new Timer(true);
        updateTimer.scheduleAtFixedRate(cacheRefresher, 0, 5_000);
    }

    public static EntityCache getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EntityCache();
        }
        return INSTANCE;
    }

    private SoftReference<List<Book>> books;
    private SoftReference<List<Author>> authors;
    private SoftReference<List<Genre>> genres;
    private SoftReference<List<Language>> languages;
    private SoftReference<List<Account>> accounts;
    private SoftReference<List<Role>> roles;

    public void eraseCache(String name) {
        switch (name) {
            case "Book" -> books = null;
            case "Author" -> authors = null;
            case "Genre" -> genres = null;
            case "Language" -> languages = null;
            case "Account" -> accounts = null;
            case "Role" -> roles = null;
        }
    }

    public void initCache() {

    }
    public List<? extends BaseEntity> retrieveCollection(Class<? extends BaseEntity> tClass) {
        BaseDao dao = ProxyDaoFactory.getDaoFor(tClass);
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
            ex.printStackTrace();
        }
        return (List) reference.get();
    }
}
