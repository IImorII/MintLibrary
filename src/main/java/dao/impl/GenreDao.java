package dao.impl;

import criteria.Criteria;
import dao.BaseDao;
import dbcp.ConnectionPool;
import entity.Genre;
import entity.Language;
import exception.ConnectionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenreDao implements BaseDao<Genre> {

    private static GenreDao INSTANCE;

    private GenreDao() {

    }

    public static GenreDao getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GenreDao();
        }
        return INSTANCE;
    }

    @Override
    public List<Genre> getByCriteria(Criteria criteria) {
        return null;
    }

    @Override
    public List<Genre> getAll() {
        Connection connection = ConnectionPool.getInstance().getConnection();
        String sql = "select * from genre;";
        List<Genre> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Genre genre = new Genre();
                genre.setId(rs.getInt("id"));
                genre.setName(rs.getString("genre"));
                list.add(genre);
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
    public Optional<Genre> getById(Integer id) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        String sql = "select * from genre where id = ?;";
        Optional<Genre> optional = Optional.empty();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Genre genre = new Genre();
            while (rs.next()) {
                genre.setId(rs.getInt("id"));
                genre.setName(rs.getString("genre"));
            }
            optional = Optional.of(genre);
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
    public Optional<Genre> getByName(String name) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        String sql = "select * from genre where genre = ?;";
        Optional<Genre> optional = Optional.empty();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            Genre genre = new Genre();
            while (rs.next()) {
                genre.setId(rs.getInt("id"));
                genre.setName(rs.getString("genre"));
            }
            optional = Optional.of(genre);
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
    public void create(Genre entity) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        String sql = "insert into genre (genre) values (?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, entity.getName());
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

    public void update(Genre entity) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        String sql = "update genre set genre = ? where id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getId());
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
        String sql = "delete from genre where id = ?;";
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

    public List<Genre> getAllGenresByBookId(Integer id) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        String sql = "select * from book_genre where id = ?";
        List<Genre> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer genre_id = rs.getInt("genre_id");
                Genre genre = getById(genre_id).get();
                list.add(genre);
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

    public void setGenresToBookId(Integer id, List<Genre> genres) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        String sql = "insert into book_genre (id, genre_id) values (" + id + ", ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (Genre g : genres) {
                ps.setInt(1, g.getId());
                try {
                    ps.executeUpdate();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
