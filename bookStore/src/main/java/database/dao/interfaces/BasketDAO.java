package database.dao.interfaces;

import entity.Basket;

import java.sql.SQLException;
import java.util.List;

public interface BasketDAO extends GenericDAO<Basket> {
    List<Basket> getByUserId(Long userId) throws SQLException;
    void delete(Long id) throws SQLException;
}
