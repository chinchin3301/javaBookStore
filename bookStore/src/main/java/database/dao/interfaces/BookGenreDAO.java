package database.dao.interfaces;

import entity.BookGenre;

import java.sql.SQLException;

public interface BookGenreDAO extends GenericDAO<BookGenre> {
    Long getId(String title, Long localId) throws SQLException;
}
