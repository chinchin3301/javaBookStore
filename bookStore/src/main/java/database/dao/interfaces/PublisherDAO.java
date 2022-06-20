package database.dao.interfaces;

import entity.Publisher;

import java.sql.SQLException;
import java.util.List;

public interface PublisherDAO extends GenericDAO<Publisher> {
    List<Publisher> getByCountryId(Long countryId) throws SQLException;
}
