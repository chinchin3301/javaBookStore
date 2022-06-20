package database.dao.interfaces;

import entity.Order;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface OrderDAO extends GenericDAO<Order> {
    List<Order> getByUserId(Long userId) throws SQLException;
    void changeStatus(Long id, Long newStatusId) throws SQLException;
    void changeFinishTime(Long id, Date newFinishDate) throws SQLException;
}
