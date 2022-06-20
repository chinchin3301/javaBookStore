package database.dao.implement;

import database.connectionp.ConnectionPool;
import database.dao.interfaces.BasketDAO;
import entity.Basket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BasketDAOImpl implements BasketDAO {
    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String ADD_BASKET = "INSERT INTO baskets(count, books_id, users_id)" +
            " VALUES(?, ?, ?)";
    private static final String SELECT_BY_USER_ID = "SELECT * FROM baskets WHERE users_id = ?";
    private static final String UPDATE_BASKET = "UPDATE baskets SET count = ?, books_id = ?, " +
            "users_id = ? WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM baskets";
    private static final String SELECT_BY_ID = "SELECT * FROM baskets WHERE id = ?";
    private static final String DELETE_BASKET = "DELETE FROM baskets WHERE id = ?";
    @Override
    public List<Basket> getByUserId(Long userId) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        List<Basket> baskets = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_USER_ID)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Basket basket = new Basket();
                setFields(basket, resultSet);
                baskets.add(basket);
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return baskets;
    }

    @Override
    public void delete(Long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BASKET)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void create(Basket object) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(ADD_BASKET)){
            preparedStatement.setLong(1, object.getCount());
            preparedStatement.setLong(2, object.getBooksId());
            preparedStatement.setLong(3, object.getUsersId());
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void update(Long id, Basket object) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BASKET)){
            preparedStatement.setLong(1, object.getCount());
            preparedStatement.setLong(2, object.getBooksId());
            preparedStatement.setLong(3, object.getUsersId());
            preparedStatement.setLong(4, id);
            preparedStatement.executeUpdate();
        }finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void update(Long id, Basket object, Long localsId) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Basket> getAll() throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        List<Basket> baskets = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Basket basket = new Basket();
                setFields(basket, resultSet);
                baskets.add(basket);
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return baskets;
    }

    @Override
    public List<Basket> getAll(Long localsId) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Basket getById(Long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        Basket basket = new Basket();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                setFields(basket, resultSet);
            }
        }finally {
            connectionPool.returnConnection(connection);
        }
        return basket;
    }

    @Override
    public Basket getById(Long id, Long localsId) throws SQLException {
        throw new UnsupportedOperationException();
    }
    private void setFields(Basket basket, ResultSet resultSet) throws SQLException{
        basket.setId(resultSet.getLong("id"));
        basket.setCount(resultSet.getLong("count"));
        basket.setBooksId(resultSet.getLong("books_id"));
        basket.setUsersId(resultSet.getLong("users_id"));
    }
}
