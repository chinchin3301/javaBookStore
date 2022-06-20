package database.dao.implement;

import database.connectionp.ConnectionPool;
import database.dao.interfaces.StatusDAO;
import entity.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatusDAOImpl implements StatusDAO {
    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String ADD_STATUS = "INSERT INTO status(title," +
            "locals_id) VALUES(?, ?)";
    private static final String UPDATE_STATUS = "UPDATE status SET " +
            "title = ? WHERE locals_id = ? AND id = ?";
    private static final String SELECT_ALL = "SELECT * FROM status WHERE " +
            "locals_id = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM status WHERE " +
            "id = ?";
    private static final String SELECT_ID = "SELECT id FROM status WHERE " +
            "locals_id = ? AND title = ?";
    @Override
    public void create(Status object) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(ADD_STATUS)) {
            preparedStatement.setString(1, object.getTitle());
            preparedStatement.setLong(2, object.getLocalsId());
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void update(Long id, Status object) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Long id, Status object, Long localsId) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STATUS)) {
            preparedStatement.setString(1, object.getTitle());
            preparedStatement.setLong(2, localsId);
            preparedStatement.setLong(3, id);
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public List<Status> getAll() throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Status> getAll(Long localsId) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        List<Status> statuses = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            preparedStatement.setLong(1, localsId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Status status = new Status();
                setFields(status, resultSet);
                statuses.add(status);
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return statuses;
    }

    @Override
    public Status getById(Long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        Status status = new Status();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                setFields(status, resultSet);
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return status;
    }

    @Override
    public Status getById(Long id, Long localsId) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Long getId(String title, Long localId) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        Long statusId = (long)0;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID)) {
            preparedStatement.setLong(1, localId);
            preparedStatement.setString(2, title);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                statusId = resultSet.getLong("id");
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return statusId;
    }
    private void setFields(Status status, ResultSet resultSet) throws SQLException{
        status.setId(resultSet.getLong("id"));
        status.setTitle(resultSet.getString("title"));
        status.setLocalsId(resultSet.getLong("locals_id"));
    }
}
