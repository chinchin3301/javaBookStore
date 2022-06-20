package database.dao.implement;

import database.connectionp.ConnectionPool;
import database.dao.interfaces.PublisherDAO;
import entity.Publisher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PublisherDAOImpl implements PublisherDAO {
    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String ADD_PUBLISHER = "INSERT INTO publishers(" +
            "name, countries_id) VALUES(?, ?)";
    private static final String UPDATE_PUBLISHER = "UPDATE publishers SET " +
            "name = ?, countries_id = ? WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM publishers";
    private static final String SELECT_BY_ID = "SELECT * FROM publishers " +
            "WHERE id = ?";
    private static final String SELECT_BY_COUNTRY_ID = "SELECT * FROM publishers " +
            "WHERE countries_id = ?";
    @Override
    public void create(Publisher object) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(ADD_PUBLISHER)) {
            preparedStatement.setString(1, object.getName());
            preparedStatement.setLong(2, object.getCountriesId());
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void update(Long id, Publisher object) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PUBLISHER)) {
            preparedStatement.setString(1, object.getName());
            preparedStatement.setLong(2, object.getCountriesId());
            preparedStatement.setLong(3, id);
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void update(Long id, Publisher object, Long localsId) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Publisher> getAll() throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        List<Publisher> publishers = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Publisher publisher = new Publisher();
                setFields(publisher, resultSet);
                publishers.add(publisher);
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return publishers;
    }

    @Override
    public List<Publisher> getAll(Long localsId) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Publisher getById(Long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        Publisher publisher = new Publisher();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                setFields(publisher, resultSet);
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return publisher;
    }

    @Override
    public Publisher getById(Long id, Long localsId) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Publisher> getByCountryId(Long countryId) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        List<Publisher> publishers = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_COUNTRY_ID)) {
            preparedStatement.setLong(1, countryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Publisher publisher = new Publisher();
                setFields(publisher, resultSet);
                publishers.add(publisher);
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return publishers;
    }
    private void setFields(Publisher publisher, ResultSet resultSet) throws SQLException{
        publisher.setId(resultSet.getLong("id"));
        publisher.setName(resultSet.getString("name"));
        publisher.setCountriesId(resultSet.getLong("countries_id"));
    }
}
