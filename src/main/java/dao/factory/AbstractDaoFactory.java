package dao.factory;

import dao.BaseDao;
import dao.impl.AuthorDaoImpl;
import dao.impl.BookDaoImpl;
import dao.impl.GenreDaoImpl;
import dao.impl.LanguageDaoImpl;
import dao.impl.RoleDaoImpl;
import dao.impl.AccountDaoImpl;


public abstract class AbstractDaoFactory {
    public static BaseDao getDaoFor(Class entity) {
        return switch (entity.getSimpleName().toLowerCase()) {
            case "account" -> AccountDaoImpl.getInstance();
            case "author" -> AuthorDaoImpl.getInstance();
            case "book" -> BookDaoImpl.getInstance();
            case "genre" -> GenreDaoImpl.getInstance();
            case "language" -> LanguageDaoImpl.getInstance();
            case "role" -> RoleDaoImpl.getInstance();
            default -> null;
        };
    }
}
