package database.dao.interfaces;

import entity.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDAO extends GenericDAO<Book>{
    List<Long> getBookId(String title, Long localId) throws SQLException;
    List<Book> getByBookGenreId(Long bookGenreId, Long localId) throws SQLException;
    List<Book> getByPublisherId(Long publisherId) throws SQLException;
    void delete(Long id, Long localId) throws SQLException;
}
