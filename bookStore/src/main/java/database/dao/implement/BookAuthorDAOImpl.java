package database.dao.implement;

import database.connectionp.ConnectionPool;
import database.dao.interfaces.BookAuthorDAO;
import entity.BookAuthor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookAuthorDAOImpl implements BookAuthorDAO {
    private ConnectionPool connectionPool;
    private Connection connection;
    private static final String SELECT_BY_BOOK_ID = "SELECT * FROM books_authors " +
            "WHERE books_id = ?";
    private static final String ADD_BOOK_AUTHOR = "INSERT INTO books_authors(authors_id," +
            "books_id) VALUES(?, ?)";
    private static final String UPDATE_BOOK_AUTHOR = "UPDATE books_authors SET authors_id = ?, " +
            "books_id = ? WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM books_authors";
    private static final String SELECT_BY_ID = "SELECT * FROM books_authors WHERE id = ?";
    @Override
    public List<BookAuthor> getByBookId(Long bookId) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        List<BookAuthor> bookAuthors = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_BOOK_ID)) {
            preparedStatement.setLong(1, bookId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                BookAuthor bookAuthor = new BookAuthor();
                setFields(bookAuthor, resultSet);
                bookAuthors.add(bookAuthor);
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return bookAuthors;
    }

    @Override
    public void create(BookAuthor object) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(ADD_BOOK_AUTHOR)) {
            preparedStatement.setLong(1, object.getAuthorsId());
            preparedStatement.setLong(2, object.getBooksId());
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void update(Long id, BookAuthor object) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOK_AUTHOR)) {
            preparedStatement.setLong(1, object.getAuthorsId());
            preparedStatement.setLong(2, object.getBooksId());
            preparedStatement.setLong(3, id);
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void update(Long id, BookAuthor object, Long localsId) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<BookAuthor> getAll() throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        List<BookAuthor> bookAuthors = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                BookAuthor bookAuthor = new BookAuthor();
                setFields(bookAuthor, resultSet);
                bookAuthors.add(bookAuthor);
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return bookAuthors;
    }

    @Override
    public List<BookAuthor> getAll(Long localsId) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public BookAuthor getById(Long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        BookAuthor bookAuthor = new BookAuthor();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                setFields(bookAuthor, resultSet);
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return bookAuthor;
    }

    @Override
    public BookAuthor getById(Long id, Long localsId) throws SQLException {
        throw new UnsupportedOperationException();
    }

    private void setFields(BookAuthor bookAuthor, ResultSet resultSet) throws SQLException{
        bookAuthor.setId(resultSet.getLong("id"));
        bookAuthor.setAuthorsId(resultSet.getLong("authors_id"));
        bookAuthor.setBooksId(resultSet.getLong("books_id"));
    }
}
