package database.dao.implement;

import database.connectionp.ConnectionPool;
import database.dao.interfaces.LocalDAO;
import entity.Local;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocalDAOImpl implements LocalDAO {
    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String ADD_LOCAL = "INSERT INTO locals(short_name, " +
            "name) VALUES (?, ?)";
    private static final String UPDATE_LOCAL = "UPDATE locals SET short_name = " +
            "?, name = ? WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM locals";
    private static final String SELECT_BY_ID = "SELECT * FROM locals WHERE " +
            "id = ?";
    private static final String SELECT_ID_BY_SHORT_NAME = "SELECT id FROM " +
            "locals WHERE short_name = ?";
    private static final String SELECT_ID_BY_NAME = "SELECT id FROM locals " +
            "WHERE name = ?";
    @Override
    public void create(Local object) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(ADD_LOCAL)) {
            preparedStatement.setString(1, object.getShortName());
            preparedStatement.setString(2, object.getName());
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void update(Long id, Local object) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_LOCAL)) {
            preparedStatement.setString(1, object.getShortName());
            preparedStatement.setString(2, object.getName());
            preparedStatement.setLong(3, id);
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void update(Long id, Local object, Long localsId) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Local> getAll() throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        List<Local> locals = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Local local = new Local();
                setFields(local, resultSet);
                locals.add(local);
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return locals;
    }

    @Override
    public List<Local> getAll(Long localsId) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Local getById(Long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        Local local = new Local();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                setFields(local, resultSet);
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return local;
    }

    @Override
    public Local getById(Long id, Long localsId) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Long getIdByShortName(String shortName) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        Long localId = (long)0;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID_BY_SHORT_NAME)) {
            preparedStatement.setString(1, shortName);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                localId = resultSet.getLong("id");
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return localId;
    }

    @Override
    public Long getIdByLongName(String LongName) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        Long localId = (long)0;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID_BY_NAME)) {
            preparedStatement.setString(1, LongName);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                localId = resultSet.getLong("id");
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return localId;
    }
    private void setFields(Local local, ResultSet resultSet) throws SQLException{
        local.setId(resultSet.getLong("id"));
        local.setShortName(resultSet.getString("short_name"));
        local.setName(resultSet.getString("name"));
    }
}
