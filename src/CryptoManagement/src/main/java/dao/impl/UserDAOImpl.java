package CryptoManagement.src.main.java.dao.impl;

import CryptoManagement.src.main.java.dao.interfaces.UserDao;
import CryptoManagement.src.main.java.models.User;
import CryptoManagement.src.main.java.utils.PasswordHasher;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDao {
    private final Connection connection;
    
    // Constructor
    public UserDAOImpl(Connection connection) {
		this.connection = connection;
	}

    @Override
    public void createUser(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password_hash, email) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, PasswordHasher.hash(user.getPasswordHash()));
            stmt.setString(3, user.getEmail());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    user.setId(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public User getUserById(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToUser(rs);
                }
            }
        }
        return null;
    }

    @Override
    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToUser(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                users.add(mapResultSetToUser(rs));
            }
        }
        return users;
    }

    @Override
    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE users SET username = ?, password_hash = ?, email = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPasswordHash());
            stmt.setString(3, user.getEmail());
            stmt.setInt(4, user.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public boolean validateCredentials(String username, String password) throws SQLException {
        User user = getUserByUsername(username);
        return user != null && PasswordHasher.verify(password, user.getPasswordHash());
    }

    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPasswordHash(rs.getString("password_hash"));
        user.setEmail(rs.getString("email"));
        return user;
    }
    
    @Override
    public void updateUserPassword(int userId, String newPassword) throws SQLException {
		String sql = "UPDATE users SET password_hash = ? WHERE id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, PasswordHasher.hash(newPassword));
			stmt.setInt(2, userId);
			stmt.executeUpdate();
		}
	}
    @Override
    public void deleteUserByUsername(String username) throws SQLException {
    	String sql = "DELETE FROM users WHERE username = ?";
    	try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, username);
			stmt.executeUpdate();
		}
    }
}