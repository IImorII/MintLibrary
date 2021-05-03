package dao;

import criteria.Criteria;
import dbcp.ConnectionPool;
import entity.BaseEntity;
import exception.ConnectionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BaseDao <T extends BaseEntity> {

    List<T> getAll();
    List<T> getByCriteria(Criteria criteria);
    Optional<T> getById(Integer id);
    Optional<T> getByName(String name);
    void update(T entity);
    void delete(Integer id);
    void create(T entity);
}
