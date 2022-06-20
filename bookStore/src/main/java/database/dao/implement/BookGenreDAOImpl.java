package database.dao.implement;

import database.connectionp.ConnectionPool;
import database.dao.interfaces.BookGenreDAO;
import entity.BookGenre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookGenreDAOImpl implements BookGenreDAO {
    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String SELECT_ID = "SELECT id FROM book_genres " +
            "WHERE title = ? AND locals_id = ?";
    private static final String ADD_BOOK_GENRE = "INSERT INTO book_genres(" +
            "title, locals_id) VALUES(?, ?)";
    private static final String UPDATE_BOOK_GENRE = "UPDATE book_genres " +
            "SET title = ? WHERE locals_id = ? AND id = ?";
    private static final String SELECT_ALL_BY_LOCAL = "SELECT * FROM book_genres" +
            " WHERE locals_id = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM book_genres" +
            " WHERE id = ?";
    @Override
    public Long getId(String title, Long localId) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        Long bookGenreId = (long)0;

        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID)) {
            preparedStatement.setString(1, title);
            preparedStatement.setLong(2, localId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bookGenreId = resultSet.getLong("id");
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return bookGenreId;
    }

    @Override
    public void create(BookGenre object) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(ADD_BOOK_GENRE)) {
            preparedStatement.setString(1, object.getTitle());
            preparedStatement.setLong(2, object.getLocalsId());
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void update(Long id, BookGenre object) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Long id, BookGenre object, Long localsId) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOK_GENRE)) {
            preparedStatement.setString(1, object.getTitle());
            preparedStatement.setLong(2, localsId);
            preparedStatement.setLong(3, id);
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public List<BookGenre> getAll() throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<BookGenre> getAll(Long localsId) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        List<BookGenre> bookGenres = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_LOCAL)) {
            preparedStatement.setLong(1, localsId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                BookGenre bookGenre = new BookGenre();
                setFields(bookGenre, resultSet);
                bookGenres.add(bookGenre);
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return bookGenres;
    }

    @Override
    public BookGenre getById(Long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        BookGenre bookGenre = new BookGenre();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                setFields(bookGenre, resultSet);
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return bookGenre;
    }

    @Override
    public BookGenre getById(Long id, Long localsId) throws SQLException {
        throw new UnsupportedOperationException();
    }

    private void setFields(BookGenre bookGenre, ResultSet resultSet) throws SQLException{
        bookGenre.setId(resultSet.getLong("id"));
        bookGenre.setLocalsId(resultSet.getLong("locals_id"));
        bookGenre.setTitle(resultSet.getString("title"));
    }
}
