package database.dao.implement;

import database.connectionp.ConnectionPool;
import database.dao.interfaces.CountryDAO;
import entity.BookGenre;
import entity.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryDAOImpl implements CountryDAO {
    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String SELECT_ID = "SELECT id FROM countries " +
            "WHERE name = ? AND locals_id = ?";
    private static final String ADD_COUNTRY = "INSERT INTO countries(" +
            "name, locals_id) VALUES(?, ?)";
    private static final String UPDATE_COUNTRY = "UPDATE countries " +
            "SET name = ? WHERE locals_id = ? AND id = ?";
    private static final String SELECT_ALL_BY_LOCAL = "SELECT * FROM countries" +
            " WHERE locals_id = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM countries" +
            " WHERE id = ?";
    @Override
    public Long getCountryId(String name, Long localId) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        Long countryID = (long)0;

        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID)) {
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, localId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                countryID = resultSet.getLong("id");
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return countryID;
    }

    @Override
    public void create(Country object) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(ADD_COUNTRY)) {
            preparedStatement.setString(1, object.getName());
            preparedStatement.setLong(2, object.getLocalsId());
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void update(Long id, Country object) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Long id, Country object, Long localsId) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COUNTRY)) {
            preparedStatement.setString(1, object.getName());
            preparedStatement.setLong(2, localsId);
            preparedStatement.setLong(3, object.getId());
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public List<Country> getAll() throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Country> getAll(Long localsId) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        List<Country> countries = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_LOCAL)) {
            preparedStatement.setLong(1, localsId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Country country = new Country();
                setFields(country, resultSet);
                countries.add(country);
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return countries;
    }

    @Override
    public Country getById(Long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        Country country = new Country();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                setFields(country, resultSet);
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return country;
    }

    @Override
    public Country getById(Long id, Long localsId) throws SQLException {
        throw new UnsupportedOperationException();
    }

    private void setFields(Country country, ResultSet resultSet) throws SQLException{
        country.setId(resultSet.getLong("id"));
        country.setLocalsId(resultSet.getLong("locals_id"));
        country.setName(resultSet.getString("name"));
    }
}
