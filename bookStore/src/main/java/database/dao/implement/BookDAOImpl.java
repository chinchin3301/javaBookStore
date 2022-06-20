package database.dao.implement;

import database.connectionp.ConnectionPool;
import database.dao.interfaces.BookDAO;
import entity.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {
    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String SELECT_ID = "SELECT id FROM books WHERE title = ? AND locals_id = ?";
    private static final String SELECT_BY_GENRE_ID = "SELECT * FROM books WHERE genre_id = ? AND " +
            "locals_id = ?";
    private static final String SELECT_BY_PUBLISHER_ID = "SELECT * FROM books WHERE publishers_id = ? ";
    private static final String DELETE_BOOK = "DELETE FROM books WHERE id = ? AND locals_id = ?";
    private static final String ADD_BOOK = "INSERT INTO books(title, description, price, count," +
            "languages_id, genre_id, publishers_id, locals_id) VALUES " +
            "(?,?,?,?,?,?,?,?)";
    private static final String UPDATE_BOOK = "UPDATE books SET title = ?," +
            "description = ?, price = ?, count = ?, languages_id = ?," +
            "genre_id = ?, publishers_id = ? WHERE locals_id = ? AND id = ?";
    private static final String SELECT_ALL_BY_LOCAL = "SELECT * FROM books WHERE locals_id = ?";
    private static final String SELECT_BY_ID= "SELECT * FROM books WHERE id = ?";

    @Override
    public List<Long> getBookId(String title, Long localId) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        List<Long> bookIds = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID)) {
            preparedStatement.setString(1, title);
            preparedStatement.setLong(2, localId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                Long bookId = resultSet.getLong("id");
                bookIds.add(bookId);
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return bookIds;
    }

    @Override
    public List<Book> getByBookGenreId(Long bookGenreId, Long localId) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        List<Book> books = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_GENRE_ID)) {
            preparedStatement.setLong(1, bookGenreId);
            preparedStatement.setLong(2, localId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                setFields(book, resultSet);
                books.add(book);
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return books;
    }

    @Override
    public List<Book> getByPublisherId(Long publisherId) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        List<Book> books = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_PUBLISHER_ID)) {
            preparedStatement.setLong(1, publisherId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                setFields(book, resultSet);
                books.add(book);
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return books;
    }

    @Override
    public void delete(Long id, Long localId) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOK)) {
            preparedStatement.setLong(1, id);
            preparedStatement.setLong(2, localId);
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void create(Book object) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(ADD_BOOK)) {
            preparedStatement.setString(1, object.getTitle());
            preparedStatement.setString(2, object.getDescription());
            preparedStatement.setLong(3, object.getPrice());
            preparedStatement.setLong(4, object.getCount());
            preparedStatement.setLong(5, object.getLanguagesId());
            preparedStatement.setLong(6, object.getGenreId());
            preparedStatement.setLong(7, object.getPublishersId());
            preparedStatement.setLong(8, object.getLocalsId());
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void update(Long id, Book object) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Long id, Book object, Long localsId) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOK)) {
            preparedStatement.setString(1, object.getTitle());
            preparedStatement.setString(2, object.getDescription());
            preparedStatement.setLong(3, object.getPrice());
            preparedStatement.setLong(4, object.getCount());
            preparedStatement.setLong(5, object.getLanguagesId());
            preparedStatement.setLong(6, object.getGenreId());
            preparedStatement.setLong(7, object.getPublishersId());
            preparedStatement.setLong(8, localsId);
            preparedStatement.setLong(9, id);
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public List<Book> getAll() throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Book> getAll(Long localsId) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        List<Book> books = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_LOCAL)) {
            preparedStatement.setLong(1, localsId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                setFields(book, resultSet);
                books.add(book);
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return books;
    }

    @Override
    public Book getById(Long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        Book book = new Book();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                setFields(book, resultSet);
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return book;
    }

    @Override
    public Book getById(Long id, Long localsId) throws SQLException {
        throw new UnsupportedOperationException();
    }

    private void setFields(Book book, ResultSet resultSet) throws SQLException{
        book.setId(resultSet.getLong("id"));
        book.setTitle(resultSet.getString("title"));
        book.setDescription(resultSet.getString("description"));
        book.setPrice(resultSet.getLong("price"));
        book.setCount(resultSet.getLong("count"));
        book.setLanguagesId(resultSet.getLong("languages_id"));
        book.setGenreId(resultSet.getLong("genre_id"));
        book.setPublishersId(resultSet.getLong("publishers_id"));
        book.setLocalsId(resultSet.getLong("locals_id"));
    }
}
