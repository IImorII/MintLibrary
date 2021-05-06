package dao;

import dao.mapper.Mapper;
import dbcp.ConnectionPool;
import entity.BaseEntity;
import exception.ConnectionException;
import exception.DaoException;
import exception.MapperException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface BaseDao <T extends BaseEntity> {

    List<T> getAll() throws DaoException, ConnectionException;
    Optional<T> getById(Integer id) throws DaoException, ConnectionException;
    Optional<T> getByName(String name) throws DaoException, ConnectionException;
    void update(T entity) throws DaoException, ConnectionException;
    void delete(Integer id) throws DaoException, ConnectionException;
    void create(T entity) throws DaoException, ConnectionException;

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

    default boolean updateQuery(String querySQL, List<Object> parameters) throws DaoException, ConnectionException {
        try {
            PreparedStatement preparedStatement = getPreparedStatement(querySQL, parameters);
            final int affectedRows = preparedStatement.executeUpdate();
            return affectedRows != 0;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
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
                    preparedStatement.setString(i + 1, String.valueOf(parameters.get(i)));
                }
            }
            return preparedStatement;
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    public Mapper<T> getMapper();
}
