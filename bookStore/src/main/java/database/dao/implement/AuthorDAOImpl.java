package database.dao.implement;

import database.connectionp.ConnectionPool;
import database.dao.interfaces.AuthorDAO;
import entity.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAOImpl implements AuthorDAO {
    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String ADD_AUTHOR = "INSERT INTO authors(full_name) VALUES(?)";
    private static final String UPDATE_AUTHOR = "UPDATE authors SET full_name = ? WHERE id = ?";
    private static final String SELECT_ALL_AUTHORS = "SELECT * FROM authors";
    private static final String SELECT_BY_ID = "SELECT * FROM authors WHERE id = ?";
    @Override
    public void create(Author object) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(ADD_AUTHOR)){
            preparedStatement.setString(1, object.getFullName());
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void update(Long id, Author object) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_AUTHOR)){
            preparedStatement.setString(1, object.getFullName());
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void update(Long id, Author object, Long localsId) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Author> getAll() throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        List<Author> authors = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_AUTHORS)){

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Author author = new Author();
                setFields(author, resultSet);
                authors.add(author);
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return authors;
    }

    @Override
    public List<Author> getAll(Long localsId) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Author getById(Long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        Author author = new Author();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)){
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                setFields(author, resultSet);
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return author;
    }

    @Override
    public Author getById(Long id, Long localsId) throws SQLException {
        throw new UnsupportedOperationException();
    }
    private void setFields(Author author, ResultSet resultSet) throws SQLException{
        author.setId(resultSet.getLong("id"));
        author.setFullName(resultSet.getString("full_name"));
    }
}
