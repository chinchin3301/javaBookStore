package database.dao.interfaces;

import entity.Local;

import java.sql.SQLException;

public interface LocalDAO extends GenericDAO<Local> {
    Long getIdByShortName(String shortName) throws SQLException;
    Long getIdByLongName(String LongName) throws SQLException;
}
