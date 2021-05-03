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

public class LanguageDao implements BaseDao<Language> {

    private static LanguageDao INSTANCE;

    private LanguageDao() {

    }

    public static LanguageDao getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LanguageDao();
        }
        return INSTANCE;
    }

    @Override
    public List<Language> getByCriteria(Criteria criteria) {
        return null;
    }

    @Override
    public List<Language> getAll() {
        Connection connection = ConnectionPool.getInstance().getConnection();
        String sql = "select * from language;";
        List<Language> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Language language = new Language();
                language.setId(rs.getInt("id"));
                language.setName(rs.getString("language"));
                list.add(language);
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
    public Optional<Language> getById(Integer id) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        String sql = "select * from language where id = ?;";
        Optional<Language> optional = Optional.empty();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Language language = new Language();
            while (rs.next()) {
                language.setId(rs.getInt("id"));
                language.setName(rs.getString("language"));
            }
            optional = Optional.of(language);
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
    public Optional<Language> getByName(String name) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        String sql = "select * from language where language = ?;";
        Optional<Language> optional = Optional.empty();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            Language language = new Language();
            while (rs.next()) {
                language.setId(rs.getInt("id"));
                language.setName(rs.getString("language"));
            }
            optional = Optional.of(language);
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
    public void create(Language entity) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        String sql = "insert into language (language) values (?)";
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

    @Override
    public void update(Language entity) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        String sql = "update language set language = ? where id = ?";
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
        String sql = "delete from language where id = ?;";
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

    public List<Language> getLanguagesByAuthorId(Integer id) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        String sql = "select * from author_language where id = ?";
        List<Language> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer language_id = rs.getInt("language_id");
                Language language = getById(language_id).get();
                list.add(language);
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

    public void setLanguagesToAuthorId(Integer id, List<Language> languages) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        String sql = "insert into author_language (id, language_id) values (" + id + ", ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (Language l : languages) {
                ps.setInt(1, l.getId());
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
