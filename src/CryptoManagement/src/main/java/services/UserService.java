package CryptoManagement.src.main.java.services;
import CryptoManagement.src.main.java.models.User;
import CryptoManagement.src.main.java.utils.PasswordHasher;

import java.sql.SQLException;
import java.util.List;

import CryptoManagement.src.main.java.dao.interfaces.UserDao;


public class UserService {
	private final UserDAO userDAO;

	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void createUser(User user) throws SQLException {
		userDAO.createUser(user);
	}

	public User getUserById(int id) throws SQLException {
		return userDAO.getUserById(id);
	}

	public User getUserByUsername(String username) throws SQLException {
		return userDAO.getUserByUsername(username);
	}

	public List<User> getAllUsers() throws SQLException {
		return userDAO.getAllUsers();
	}

	public void updateUser(User user) throws SQLException {
		userDAO.updateUser(user);
	}

	public void deleteUser(int id) throws SQLException {
		userDAO.deleteUser(id);
	}
	
	public boolean validateCredentials(String username, String password) throws SQLException {
		return userDAO.validateCredentials(username, password);
	}
	
	public void updateUserPassword(int userId, String newPassword) throws SQLException {
		userDAO.updateUserPassword(userId, newPassword);
	}
	
	public User loginUser(String username, String password) throws SQLException {
		User user = userDAO.getUserByUsername(username);
		if (user != null && PasswordHasher.verify(password, user.getPasswordHash())) {
			return user;
		}
		return null;
	}
}