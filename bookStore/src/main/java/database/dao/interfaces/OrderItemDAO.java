package database.dao.interfaces;

import entity.OrderItem;

import java.sql.SQLException;
import java.util.List;

public interface OrderItemDAO extends GenericDAO<OrderItem> {
    List<OrderItem> getByOrderId(Long orderId) throws SQLException;
    List<OrderItem> getByBookId(Long bookId) throws SQLException;
}
