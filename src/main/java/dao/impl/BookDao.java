package dao.impl;

import criteria.Criteria;
import dao.BaseDao;
import dbcp.ConnectionPool;
import entity.Author;
import entity.Book;
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

public class BookDao implements BaseDao<Book> {

    private static BookDao INSTANCE;

    private BookDao() {

    }

    public static BookDao getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BookDao();
        }
        return INSTANCE;
    }

    @Override
    public List<Book> getByCriteria(Criteria criteria) {
        return null;
    }

    @Override
    public List<Book> getAll() {
        Connection connection = ConnectionPool.getInstance().getConnection();
        String sql = "select * from book;";
        List<Book> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setYearOfRelease(rs.getInt("year_of_release"));
                book.setRate(rs.getDouble("rate"));
                book.setCount(rs.getInt("count"));
                book.setLanguage(LanguageDao.getInstance().getById(rs.getInt("language_id_fk")).get());
                list.add(book);
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
    public Optional<Book> getById(Integer id) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        String sql = "select * from book where id = ?;";
        Optional<Book> optional = Optional.empty();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Book book = new Book();
            while (rs.next()) {
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setLanguage(LanguageDao.getInstance().getById(rs.getInt("language_id_fk")).get());
            }
            optional = Optional.of(book);
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
    public Optional<Book> getByName(String name) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        String sql = "select * from book where name = ?;";
        Optional<Book> optional = Optional.empty();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            Book book = new Book();
            while (rs.next()) {
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setLanguage(LanguageDao.getInstance().getById(rs.getInt("language_id_fk")).get());
            }
            optional = Optional.of(book);
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
    public void create(Book entity) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        String sql = "insert into book (name, year_of_release, rate, count, language_id_fk) values (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getYearOfRelease());
            ps.setDouble(3, entity.getRate());
            ps.setInt(4, entity.getLanguage().getId());
            ps.setInt(5, entity.getCount());
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
    public void update(Book entity) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        String sql = "update book set name = ?, year_of_release = ?, rate = ?, count = ? where id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getYearOfRelease());
            ps.setDouble(3, entity.getRate());
            ps.setInt(4, entity.getCount());
            ps.setInt(5, entity.getId());
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
        String sql = "delete from book where id = ?;";
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

    public List<Book> getBooksByAuthorId(Integer id) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        String sql = "select * from book_author where author_id = ?";
        List<Book> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer book_id = rs.getInt("id");
                Book book = getById(book_id).get();
                list.add(book);
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

    public void setBooksToAuthorId(Integer id, List<Book> books) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        String sql = "insert into book_author (id, author_id) values (?, " + id + ")";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (Book b : books) {
                ps.setInt(1, b.getId());
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
