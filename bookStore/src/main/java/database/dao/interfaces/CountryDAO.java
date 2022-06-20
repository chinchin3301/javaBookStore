package database.dao.interfaces;

import entity.Country;

import java.sql.SQLException;

public interface CountryDAO extends GenericDAO<Country> {
    Long getCountryId(String name, Long localId) throws SQLException;

}
