package database.dao.interfaces;

import entity.Status;

import java.sql.SQLException;

public interface StatusDAO extends GenericDAO<Status> {
    Long getId(String title, Long localId) throws SQLException;
}
