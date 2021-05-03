package dao.impl;

import criteria.Criteria;
import dao.BaseDao;
import dbcp.ConnectionPool;
import entity.User;
import exception.ConnectionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements BaseDao<User> {

    private static UserDao INSTANCE;

    private UserDao() {

    }

    public static UserDao getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserDao();
        }
        return INSTANCE;
    }

    @Override
    public List<User> getByCriteria(Criteria criteria) {
        return null;
    }

    @Override
    public List<User> getAll() {
        Connection connection = ConnectionPool.getInstance().getConnection();
        String sql = "select * from user;";
        List<User> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setRole(RoleDao.getInstance().getById(rs.getInt("role_fk")).get());
                list.add(user);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (ConnectionException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return list;
    }

    @Override
    public Optional<User> getById(Integer id) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        String sql = "select * from user where id = ?;";
        Optional<User> optional = Optional.empty();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            User user = new User();
            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setRole(RoleDao.getInstance().getById(rs.getInt("role_fk")).get());
            }
            optional = Optional.of(user);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (ConnectionException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return optional;
    }

    @Override
    public Optional<User> getByName(String name) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        String sql = "select * from user where name = ?;";
        Optional<User> optional = Optional.empty();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            User user = new User();
            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setRole(RoleDao.getInstance().getById(rs.getInt("role_fk")).get());
            }
            optional = Optional.of(user);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (ConnectionException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return optional;
    }

    @Override
    public void create(User entity) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        String sql = "insert into user (name, login, password, role_fk) values (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getLogin());
            ps.setString(3, entity.getPassword());
            ps.setInt(4, entity.getRole().getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (ConnectionException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public void update(User entity) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        String sql = "update user set name = ?, year_of_release = ?, rate = ?, count = ? where id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getLogin());
            ps.setString(3, entity.getPassword());
            ps.setInt(4, entity.getRole().getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (ConnectionException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public void delete(Integer id) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        String sql = "delete from user where id = ?;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (ConnectionException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
