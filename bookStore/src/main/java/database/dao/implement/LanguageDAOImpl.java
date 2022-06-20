package database.dao.implement;

import database.connectionp.ConnectionPool;
import database.dao.interfaces.LanguageDAO;
import entity.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LanguageDAOImpl implements LanguageDAO {
    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String ADD_LANGUAGE = "INSERT INTO languages(name) " +
            "VALUES (?)";
    private static final String UPDATE_LANGUAGE = "UPDATE languages SET name = ?" +
            " WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM languages";
    private static final String SELECT_BY_ID = "SELECT * FROM languages WHERE " +
            "ID = ?";
    @Override
    public void create(Language object) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(ADD_LANGUAGE)) {
            preparedStatement.setString(1, object.getName());
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void update(Long id, Language object) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_LANGUAGE)) {
            preparedStatement.setString(1, object.getName());
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void update(Long id, Language object, Long localsId) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Language> getAll() throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        List<Language> languages = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Language language = new Language();
                setFields(language, resultSet);
                languages.add(language);
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return languages;
    }

    @Override
    public List<Language> getAll(Long localsId) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Language getById(Long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        Language language = new Language();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                setFields(language, resultSet);
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return language;
    }

    @Override
    public Language getById(Long id, Long localsId) throws SQLException {
        throw new UnsupportedOperationException();
    }

    private void setFields(Language language, ResultSet resultSet) throws SQLException{
        language.setId(resultSet.getLong("id"));
        language.setName(resultSet.getString("name"));
    }
}
