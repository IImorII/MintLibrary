package dao.impl;

import criteria.AuthorCriteria;
import criteria.Criteria;
import dao.BaseDao;
import dbcp.ConnectionPool;
import entity.Author;
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

public class AuthorDao implements BaseDao<Author> {

    private static AuthorDao INSTANCE;

    private AuthorDao() {

    }

    public static AuthorDao getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AuthorDao();
        }
        return INSTANCE;
    }

    @Override
    public List<Author> getByCriteria(Criteria criteria) {
        return null;
    }

    @Override
    public List<Author> getAll() {
        Connection connection = ConnectionPool.getInstance().getConnection();
        String sql = "select * from author;";
        List<Author> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Author author = new Author();
                author.setId(rs.getInt("id"));
                author.setName(rs.getString("language"));
                author.setBirth(rs.getInt("birth"));
                list.add(author);
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
    public Optional<Author> getById(Integer id) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        String sql = "select * from author where id = ?;";
        Optional<Author> optional = Optional.empty();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Author author = new Author();
            while (rs.next()) {
                author.setId(rs.getInt("id"));
                author.setName(rs.getString("name"));
                author.setBirth(rs.getInt("birth"));
            }
            optional = Optional.of(author);
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
    public Optional<Author> getByName(String name) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        String sql = "select * from author where name = ?;";
        Optional<Author> optional = Optional.empty();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            Author author = new Author();
            while (rs.next()) {
                author.setId(rs.getInt("id"));
                author.setName(rs.getString("name"));
                author.setBirth(rs.getInt("birth"));
            }
            optional = Optional.of(author);
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
    public void create(Author entity) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        String sql = "insert into author (name, birth) values (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(2, entity.getBirth());
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
    public void update(Author entity) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        String sql = "update author set name = ?, birth = ? where id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getBirth());
            ps.setInt(3, entity.getId());
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
        String sql = "delete from author where id = ?;";
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

    public List<Author> getAuthorsByBookId(Integer id) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        String sql = "select * from book_author where id = ?";
        List<Author> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer author_id = rs.getInt("author_id");
                Author author = getById(author_id).get();
                list.add(author);
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

    public void setAuthorsToBookId(Integer id, List<Author> authors) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        String sql = "insert into book_author (id, author_id) values (" + id + ", ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (Author a : authors) {
                ps.setInt(1, a.getId());
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

    public List<Author> getAuthorsByLanguageId(Integer id) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        String sql = "select * from author_language where language_id = ?";
        List<Author> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer author_id = rs.getInt("id");
                Author author = getById(author_id).get();
                list.add(author);
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

    public void setAuthorsToLanguageId(Integer id, List<Language> languages) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        String sql = "insert into author_language (id, language_id) values (?, " + id + ")";
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
