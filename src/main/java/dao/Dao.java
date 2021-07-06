package dao;

import dao.factory.ProxyDaoInstance;
import listener.UpdateDBEvent;
import listener.UpdateDBListener;
import mapper.Mapper;
import entity.BaseEntity;
import exception.DaoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public interface Dao<T extends BaseEntity> {

    ArrayList<UpdateDBListener> listeners = new ArrayList<>();

    Lock LOCK = new ReentrantLock();

    void create(T entity) throws DaoException;

    void update(T entity) throws DaoException;

    List<T> retrieveAll() throws DaoException;

    Optional<T> retrieveById(Integer id) throws DaoException;

    Optional<T> retrieveByName(String name) throws DaoException;

    void delete(Integer id) throws DaoException;

    static Dao of(Class<? extends BaseEntity> entity) {
        return ProxyDaoInstance.of(entity.getSimpleName());
    }

    Mapper getMapper();

    static void addUpdateDBEventListener(UpdateDBListener listener) {
        listeners.add(listener);
    }

    static UpdateDBListener[] getUpdateDBEventListeners() {
        return listeners.toArray(new UpdateDBListener[listeners.size()]);
    }

    static void removeUpdateDBEventListener(UpdateDBListener listener) {
        listeners.remove(listener);
    }

    static void doUpdateDBEvent(Object src, String message) {
        UpdateDBEvent ev = new UpdateDBEvent(src, message);
        for (UpdateDBListener listener : listeners) {
            listener.onDBUpdate(ev);
        }
    }
}
