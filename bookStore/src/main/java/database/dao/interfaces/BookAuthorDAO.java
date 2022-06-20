package database.dao.interfaces;

import entity.BookAuthor;

import java.sql.SQLException;
import java.util.List;

public interface BookAuthorDAO extends GenericDAO<BookAuthor> {
    List<BookAuthor> getByBookId(Long bookId) throws SQLException;
}
