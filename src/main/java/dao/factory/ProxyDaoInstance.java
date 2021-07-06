package dao.factory;

import dao.Dao;
import dao.impl.AccountDaoImpl;
import dao.impl.AuthorDaoImpl;
import dao.impl.BookDaoImpl;
import dao.impl.GenreDaoImpl;
import dao.impl.LanguageDaoImpl;
import dao.impl.RoleDaoImpl;

import java.lang.reflect.Proxy;


public enum ProxyDaoInstance {
    ROLE(RoleDaoImpl.getInstance()),
    GENRE(GenreDaoImpl.getInstance()),
    LANGUAGE(LanguageDaoImpl.getInstance()),
    AUTHOR(AuthorDaoImpl.getInstance()),
    BOOK(BookDaoImpl.getInstance()),
    ACCOUNT(AccountDaoImpl.getInstance());

    private final Dao dao;

    ProxyDaoInstance(Dao dao) {
        this.dao = dao;
    }

    public static Dao of(String name) {
        for (ProxyDaoInstance d: values()) {
            if (d.name().equalsIgnoreCase(name)) {
                return addUpdateInvocationHandler(d.dao, name);
            }
        }
        return null;
    }


    private static Dao addUpdateInvocationHandler(Dao dao, String entityName) {
        return (Dao) Proxy.newProxyInstance(
                dao.getClass().getClassLoader(),
                dao.getClass().getInterfaces(),
                (handler, method, args) -> {
                    String methodName = method.getName();
                    if (methodName.equals("create") || methodName.equals("delete") || methodName.equals("update")) {
                        Dao.doUpdateDBEvent(dao, entityName);
                    }
                    return method.invoke(dao, args);
                }
        );
    }
}
