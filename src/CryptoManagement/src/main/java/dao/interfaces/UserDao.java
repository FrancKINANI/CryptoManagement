package CryptoManagement.src.main.java.dao.interfaces;

import CryptoManagement.src.main.java.models.User;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    void createUser(User user) throws SQLException;
    User getUserById(int id) throws SQLException;
    User getUserByUsername(String username) throws SQLException;
    List<User> getAllUsers() throws SQLException;
    void updateUser(User user) throws SQLException;
    void deleteUser(int id) throws SQLException;
    boolean validateCredentials(String username, String password) throws SQLException;
    void updateUserPassword(int userId, String newPassword) throws SQLException;
    void deleteUserByUsername(String username) throws SQLException;
}