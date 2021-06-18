package dao.factory;

import dao.BaseDao;
import dao.impl.*;
import entity.BaseEntity;

import java.lang.reflect.Proxy;


public abstract class ProxyDaoFactory {

    public static BaseDao get(Class<? extends BaseEntity> entity) {
        return addUpdateInvocationHandler(switch (entity.getSimpleName().toLowerCase()) {
            case "account" -> AccountDaoImpl.getInstance();
            case "author" -> AuthorDaoImpl.getInstance();
            case "book" -> BookDaoImpl.getInstance();
            case "genre" -> GenreDaoImpl.getInstance();
            case "language" -> LanguageDaoImpl.getInstance();
            case "role" -> RoleDaoImpl.getInstance();
            default -> null;
        }, entity.getSimpleName());
    }

    private static BaseDao addUpdateInvocationHandler(BaseDao dao, String entityName) {
        return (BaseDao) Proxy.newProxyInstance(
                dao.getClass().getClassLoader(),
                dao.getClass().getInterfaces(),
                (handler, method, args) -> {
                    String methodName = method.getName();
                    if (methodName.equals("create") || methodName.equals("delete") || methodName.equals("update")) {
                        BaseDao.doUpdateDBEvent(dao, entityName);
                    }
                    return method.invoke(dao, args);
                }
        );
    }
}
