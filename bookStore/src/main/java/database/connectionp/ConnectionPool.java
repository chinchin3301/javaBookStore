package database.connectionp;

import entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class ConnectionPool {
    private final Logger logger = LogManager.getLogger(this.getClass().getName());
    private String url;
    private String user;
    private String password;
    private String driverDB;
    private ResourceBundle properties = ResourceBundle.getBundle("connectionPool");
    private final int maxConnection = Integer.parseInt(properties.getString("pool.maxConnection"));
    private BlockingQueue<Connection> connectionsQueue = new ArrayBlockingQueue<>(maxConnection);

    private static ConnectionPool instance = null;

    private ConnectionPool() {
        init();
    }
    private void init() {
        setDataForConnection();
        loadDrivers();
        createConnections();
    }
    private void setDataForConnection() {
        this.url = properties.getString("pool.url");
        this.user = properties.getString("pool.user");
        this.password = properties.getString("pool.password");
        this.driverDB = properties.getString("pool.driver");
    }
    private void loadDrivers() {
        try {
            Driver driver = (Driver) Class.forName(driverDB).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e){
            logger.warn(e);
            System.out.println(e);
        }
    }
    private void createConnections() {
        Connection connection = null;
        while(connectionsQueue.size() < maxConnection) {
            try {
                connection = DriverManager.getConnection(url, user, password);
                connectionsQueue.put(connection);
            } catch (InterruptedException e) {
                logger.warn(e);
                System.out.println(e);
            } catch (SQLException e){
                logger.warn(e);
                System.out.println(e);
            }
        }
    }
    public static synchronized ConnectionPool getInstance() {
        if(instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }
    public synchronized Connection getConnection() {
        Connection connection = null;
        try {
            connection = connectionsQueue.take();
        } catch (InterruptedException e) {
            logger.warn(e);
            System.out.println(e);
        }
        return connection;
    }
    public synchronized void returnConnection(Connection connection) {
        if(connection != null && connectionsQueue.size() <= maxConnection){
            try {
                connectionsQueue.put(connection);
            }catch (InterruptedException e) {
                logger.warn(e);
            }
        }
    }

    public static void main(String[] args) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM mydb.users")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            User user = new User();
            while(resultSet.next()){
                System.out.println(resultSet.getString("name"));
            }
        } catch (SQLException e){

        }finally {
            connectionPool.returnConnection(connection);
        }
        System.out.println("OK");
    }
}
