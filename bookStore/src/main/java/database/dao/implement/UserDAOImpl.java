package database.dao.implement;

import database.connectionp.ConnectionPool;
import database.dao.interfaces.UserDAO;
import entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String ADD_USER = "INSERT INTO users(email," +
            "password,name,surname,birthday,phonenumber,address," +
            "is_banned,is_admin) VALUES(?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE_USER = "UPDATE users SET " +
            "email = ?,password = ?,name = ?,surname = ?," +
            "birthday = ?,phonenumber = ?,address = ?," +
            "is_banned = ?,is_admin = ? WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM users";
    private static final String SELECT_BY_ID = "SELECT * FROM users " +
            "WHERE id = ?";

    private static final String SELECT_BY_EMAIL_PASSWORD = "SELECT * FROM users " +
            "WHERE email = ? AND password = ?";
    private static final String SELECT_ID = "SELECT id FROM users WHERE " +
            "email = ? AND password = ?";
    private static final String SELECT_EMAIL = "SELECT email FROM users " +
            "WHERE email = ?";
    private static final String UPDATE_PASSWORD = "UPDATE users SET " +
            "password = ? WHERE id = ?";
    private static final String UPDATE_BAN = "UPDATE users SET " +
            "is_banned = ? WHERE id = ?";
    private static final String UPDATE_ADM = "UPDATE users SET " +
            "is_admin = ? WHERE id = ?";
    @Override
    public void create(User object) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER)) {
            preparedStatement.setString(1, object.getEmail());
            preparedStatement.setString(2, object.getPassword());
            preparedStatement.setString(3, object.getName());
            preparedStatement.setString(4, object.getSurname());
            preparedStatement.setDate(5, new java.sql.Date(object.getBirthday().getTime()));
            preparedStatement.setString(6, object.getPhonenumber());
            preparedStatement.setString(7, object.getAddress());
            preparedStatement.setBoolean(8, object.isIs_banned());
            preparedStatement.setBoolean(9, object.isIs_admin());
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void update(Long id, User object) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            preparedStatement.setString(1, object.getEmail());
            preparedStatement.setString(2, object.getPassword());
            preparedStatement.setString(3, object.getName());
            preparedStatement.setString(4, object.getSurname());
            preparedStatement.setDate(5, new java.sql.Date(object.getBirthday().getTime()));
            preparedStatement.setString(6, object.getPhonenumber());
            preparedStatement.setString(7, object.getAddress());
            preparedStatement.setBoolean(8, object.isIs_banned());
            preparedStatement.setBoolean(9, object.isIs_admin());
            preparedStatement.setLong(10, id);
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void update(Long id, User object, Long localsId) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> getAll() throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        List<User> users = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                setFields(user, resultSet);
                users.add(user);
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return users;
    }

    @Override
    public List<User> getAll(Long localsId) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public User getById(Long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        User user = new User();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                setFields(user, resultSet);
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return user;
    }

    @Override
    public User getById(Long id, Long localsId) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public User getByEmailPassword(String email, String password) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        User user = new User();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_EMAIL_PASSWORD)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                setFields(user, resultSet);
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return user;
    }

    @Override
    public Long getId(String email, String password) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        Long userId = (long)0;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userId = resultSet.getLong("id");
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return userId;
    }

    @Override
    public Boolean isEmailRegistered(String email) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        Boolean isRegistered = false;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                isRegistered = true;
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return isRegistered;
    }

    @Override
    public void changePassword(Long id, String newPassword) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSWORD)) {
            preparedStatement.setString(1, newPassword);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();

        }finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void changeBan(Long id, Boolean newBan) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BAN)) {
            preparedStatement.setBoolean(1, newBan);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();

        }finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void changeAdm(Long id, Boolean newAdm) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ADM)) {
            preparedStatement.setBoolean(1, newAdm);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();

        }finally {
            connectionPool.returnConnection(connection);
        }
    }

    private void setFields(User user, ResultSet resultSet) throws SQLException{
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setSurname(resultSet.getString("surname"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));
        user.setPhonenumber(resultSet.getString("phonenumber"));
        user.setAddress(resultSet.getString("address"));
        user.setBirthday(new Date(resultSet.getDate("birthday").getTime()));
        user.setIs_admin(resultSet.getBoolean("is_admin"));
        user.setIs_banned(resultSet.getBoolean("is_banned"));
    }
}
