package dao;

import listener.UpdateDBEvent;
import listener.UpdateDBListener;
import mapper.Mapper;
import dbcp.ConnectionPool;
import entity.BaseEntity;
import exception.ConnectionException;
import exception.DaoException;
import exception.MapperException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public interface BaseDao<T extends BaseEntity> {

    ArrayList<UpdateDBListener> listeners = new ArrayList<UpdateDBListener>();

    Lock LOCK = new ReentrantLock();

    Logger log = LogManager.getLogger(BaseDao.class);

    void create(T entity) throws DaoException, ConnectionException;

    void update(T entity) throws DaoException, ConnectionException;

    List<T> getAll() throws DaoException, ConnectionException;

    Optional<T> getById(Integer id) throws DaoException, ConnectionException;

    Optional<T> getByName(String name) throws DaoException, ConnectionException;

    void delete(Integer id) throws DaoException, ConnectionException;


    default Optional<T> getOneQuery(String querySQL, List<Object> params) throws DaoException, ConnectionException {
        List<T> result = getManyQuery(querySQL, params);
        if (result.isEmpty()) {
            return Optional.empty();
        }
        if (result.size() == 1) {
            T value = result.get(0);
            return Optional.of(value);
        }
        return Optional.empty();
    }

    default List<T> getManyQuery(String querySQL, List<Object> parameters) throws DaoException, ConnectionException {
        try {
            List<T> objects = new ArrayList<>();
            PreparedStatement preparedStatement = getPreparedStatement(querySQL, parameters);
            ResultSet resultSet = preparedStatement.executeQuery();
            Mapper<T> mapper = getMapper();
            while (resultSet.next()) {
                T item = mapper.toEntity(resultSet);
                objects.add(item);
            }
            if (objects.isEmpty()) {
                return Collections.emptyList();
            }
            return objects;
        } catch (SQLException | MapperException ex) {
            ex.printStackTrace();
            throw new DaoException(ex.getMessage());
        }
    }

    default boolean updateQuery(String querySQL, List<Object> parameters) throws DaoException, ConnectionException {
        try {
            PreparedStatement preparedStatement = getPreparedStatement(querySQL, parameters);
            final int affectedRows = preparedStatement.executeUpdate();
            return affectedRows != 0;
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    default PreparedStatement getPreparedStatement(String querySQL, List<Object> parameters)
            throws SQLException, ConnectionException {
        final Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(querySQL);
            if (parameters != null) {
                int length = parameters.size();
                for (int i = 0; i < length; i++) {
                    Object value = parameters.get(i);
                    if (value == null) {
                        preparedStatement.setString(i + 1, null);
                        continue;
                    }
                    preparedStatement.setString(i + 1, String.valueOf(value));
                }
            }
            return preparedStatement;
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    Mapper<T> getMapper();

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
