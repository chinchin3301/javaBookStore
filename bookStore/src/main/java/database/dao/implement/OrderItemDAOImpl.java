package database.dao.implement;

import com.sun.org.apache.xpath.internal.operations.Or;
import database.connectionp.ConnectionPool;
import database.dao.interfaces.OrderItemDAO;
import entity.OrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAOImpl implements OrderItemDAO {
    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String ADD_ORDER_ITEM = "INSERT INTO order_items(" +
            "count, cost, orders_id, books_id) VALUES(?,?,?,?)";
    private static final String UPDATE_ORDER_ITEM = "UPDATE order_items SET " +
            "count = ?, cost = ?, orders_id = ?, books_id = ? WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM order_items";
    private static final String SELECT_BY_ID = "SELECT * FROM order_items " +
            "WHERE id = ?";
    private static final String SELECT_BY_ORDER_ID = "SELECT * FROM order_items " +
            "WHERE orders_id = ?";
    private static final String SELECT_BY_BOOK_ID = "SELECT * FROM order_items " +
            "WHERE books_id = ?";
    @Override
    public void create(OrderItem object) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORDER_ITEM)) {
            preparedStatement.setLong(1, object.getCount());
            preparedStatement.setLong(2, object.getCost());
            preparedStatement.setLong(3, object.getOrdersId());
            preparedStatement.setLong(4, object.getBooksId());
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void update(Long id, OrderItem object) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_ITEM)) {
            preparedStatement.setLong(1, object.getCount());
            preparedStatement.setLong(2, object.getCost());
            preparedStatement.setLong(3, object.getOrdersId());
            preparedStatement.setLong(4, object.getBooksId());
            preparedStatement.setLong(5, id);
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void update(Long id, OrderItem object, Long localsId) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<OrderItem> getAll() throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        List<OrderItem> orderItems = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                OrderItem orderItem = new OrderItem();
                setFields(orderItem, resultSet);
                orderItems.add(orderItem);
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return orderItems;
    }

    @Override
    public List<OrderItem> getAll(Long localsId) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public OrderItem getById(Long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        OrderItem orderItem = new OrderItem();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                setFields(orderItem, resultSet);
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return orderItem;
    }

    @Override
    public OrderItem getById(Long id, Long localsId) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<OrderItem> getByOrderId(Long orderId) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        List<OrderItem> orderItems = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ORDER_ID)) {
            preparedStatement.setLong(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                OrderItem orderItem = new OrderItem();
                setFields(orderItem, resultSet);
                orderItems.add(orderItem);
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return orderItems;
    }

    @Override
    public List<OrderItem> getByBookId(Long bookId) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        List<OrderItem> orderItems = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_BOOK_ID)) {
            preparedStatement.setLong(1, bookId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                OrderItem orderItem = new OrderItem();
                setFields(orderItem, resultSet);
                orderItems.add(orderItem);
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return orderItems;
    }
    private void setFields(OrderItem orderItem, ResultSet resultSet) throws SQLException{
        orderItem.setId(resultSet.getLong("id"));
        orderItem.setCount(resultSet.getLong("count"));
        orderItem.setCost(resultSet.getLong("cost"));
        orderItem.setOrdersId(resultSet.getLong("orders_id"));
        orderItem.setBooksId(resultSet.getLong("books_id"));
    }
}
