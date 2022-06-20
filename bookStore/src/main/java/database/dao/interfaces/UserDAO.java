package database.dao.interfaces;

import entity.User;

import java.sql.SQLException;

public interface UserDAO extends GenericDAO<User> {
    User getByEmailPassword(String email, String password) throws SQLException;
    Long getId(String email, String password) throws SQLException;
    Boolean isEmailRegistered(String email) throws SQLException;
    void changePassword(Long id, String newPassword) throws SQLException;
    void changeBan(Long id, Boolean newBan) throws SQLException;
    void changeAdm(Long id, Boolean newAdm) throws SQLException;
}
