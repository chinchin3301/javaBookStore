package database.dao.implement;

import com.sun.org.apache.xpath.internal.operations.Or;
import database.connectionp.ConnectionPool;
import database.dao.interfaces.OrderDAO;
import entity.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String ADD_ORDER = "INSERT INTO orders(" +
            "create_time, finish_time, total_cost, status_id," +
            "users_id) VALUES(?,?,?,?,?)";
    private static final String UPDATE_ORDER = "UPDATE orders SET " +
            "create_time = ?, finish_time = ?, total_cost = ?, status_id = ?," +
            "users_id = ? WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM orders";
    private static final String SELECT_BY_ID = "SELECT * FROM orders WHERE " +
            "id = ?";
    private static final String SELECT_BY_USER_ID = "SELECT * FROM orders " +
            "WHERE users_id = ?";
    private static final String UPDATE_STATUS = "UPDATE orders SET " +
            "status_id = ? WHERE id = ?";
    private static final String UPDATE_FINISH_TIME = "UPDATE orders SET " +
            "finish_time = ? WHERE id = ?";

    @Override
    public void create(Order object) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORDER)) {
            preparedStatement.setDate(1, new java.sql.Date(object.getCreateTime().getTime()));
            preparedStatement.setDate(2, new java.sql.Date(object.getFinishTime().getTime()));
            preparedStatement.setLong(3, object.getTotalCost());
            preparedStatement.setLong(4, object.getStatusId());
            preparedStatement.setLong(5, object.getUsersId());
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void update(Long id, Order object) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER)) {
            preparedStatement.setDate(1, new java.sql.Date(object.getCreateTime().getTime()));
            preparedStatement.setDate(2, new java.sql.Date(object.getFinishTime().getTime()));
            preparedStatement.setLong(3, object.getTotalCost());
            preparedStatement.setLong(4, object.getStatusId());
            preparedStatement.setLong(5, object.getUsersId());
            preparedStatement.setLong(6, id);
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void update(Long id, Order object, Long localsId) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Order> getAll() throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        List<Order> orders = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                setFields(order, resultSet);
                orders.add(order);
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return orders;
    }

    @Override
    public List<Order> getAll(Long localsId) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Order getById(Long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        Order order = new Order();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                setFields(order, resultSet);
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return order;
    }

    @Override
    public Order getById(Long id, Long localsId) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Order> getByUserId(Long userId) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        List<Order> orders = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_USER_ID)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                setFields(order, resultSet);
                orders.add(order);
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return orders;
    }

    @Override
    public void changeStatus(Long id, Long newStatusId) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STATUS)) {
            preparedStatement.setLong(1, newStatusId);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void changeFinishTime(Long id, Date newFinishDate) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_FINISH_TIME)) {
            preparedStatement.setDate(1, new java.sql.Date(newFinishDate.getTime()));
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.returnConnection(connection);
        }
    }
    private void setFields(Order order, ResultSet resultSet) throws SQLException{
        order.setId(resultSet.getLong("id"));
        order.setCreateTime(new Date(resultSet.getDate("create_time").getTime()));
        order.setFinishTime(new Date(resultSet.getDate("finish_time").getTime()));
        order.setTotalCost(resultSet.getLong("total_cost"));
        order.setStatusId(resultSet.getLong("status_id"));
        order.setUsersId(resultSet.getLong("users_id"));
    }
}
