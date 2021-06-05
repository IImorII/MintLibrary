package dao.impl;

import dao.BaseDao;
import entity.BaseEntity;
import java.lang.reflect.Proxy;


public abstract class ProxyDaoFactory {
    public static BaseDao getDaoFor(Class<? extends BaseEntity> entity) {
        BaseDao dao = switch (entity.getSimpleName().toLowerCase()) {
            case "account" -> AccountDaoImpl.getInstance();
            case "author" -> AuthorDaoImpl.getInstance();
            case "book" -> BookDaoImpl.getInstance();
            case "genre" -> GenreDaoImpl.getInstance();
            case "language" -> LanguageDaoImpl.getInstance();
            case "role" -> RoleDaoImpl.getInstance();
            default -> null;
        };
        return (BaseDao) Proxy.newProxyInstance(
                dao.getClass().getClassLoader(),
                dao.getClass().getInterfaces(),
                (handler, method, args) -> {
                    String methodName = method.getName();
                    if (methodName.equals("create") || methodName.equals("delete") || methodName.equals("update")) {
                        BaseDao.doUpdateDBEvent(dao, entity.getSimpleName());
                    }
                    return method.invoke(dao, args);
                }
        );
    }
}
