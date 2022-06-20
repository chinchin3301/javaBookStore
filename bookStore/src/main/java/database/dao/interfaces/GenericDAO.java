package database.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO <T>{
    void create(T object) throws SQLException;
    void update(Long id, T object) throws SQLException;
    void update(Long id, T object, Long localsId) throws SQLException;
    List<T> getAll() throws SQLException;
    List<T> getAll(Long localsId) throws SQLException;
    T getById(Long id) throws SQLException;
    T getById(Long id, Long localsId) throws SQLException;
}
