package CryptoManagement.src.main.java.views.UserViews;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CryptoManagement.src.main.java.config.DatabaseConfig;
import CryptoManagement.src.main.java.dao.impl.UserDAOImpl;
import CryptoManagement.src.main.java.models.User;
import CryptoManagement.src.main.java.utils.PasswordHasher;
import CryptoManagement.src.main.java.views.GestionCrytos;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.UIManager;

public class UserLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	protected static final String JoptionPane = null;
	private JPanel contentPane;
	private JTextField NameField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserLogin frame = new UserLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserLogin()  throws IOException, SQLException {
		setTitle("Login");
		Connection connection = DatabaseConfig.getConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 670, 499);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\fkina\\eclipse-workspace\\EFM\\src\\CryptoManagement\\src\\main\\java\\icons\\Artdesigner-Emoticons-2-Welcome.128.png"));
		lblNewLabel.setBounds(514, 119, 132, 235);
		contentPane.add(lblNewLabel);
		
		JLabel lblWelcome = new JLabel("Welecome back");
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 42));
		lblWelcome.setIcon(new ImageIcon("C:\\Users\\fkina\\eclipse-workspace\\EFM\\src\\CryptoManagement\\src\\main\\java\\icons\\Turbomilk-Livejournal-10-User-male.32.png"));
		lblWelcome.setBounds(121, 32, 388, 73);
		contentPane.add(lblWelcome);
		
		JLabel UsernameLabel = new JLabel("Username or Email");
		UsernameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		UsernameLabel.setIcon(new ImageIcon("C:\\Users\\fkina\\eclipse-workspace\\EFM\\src\\CryptoManagement\\src\\main\\java\\icons\\Hopstarter-Soft-Scraps-User-Administrator-Blue.24.png"));
		UsernameLabel.setBounds(10, 178, 176, 32);
		contentPane.add(UsernameLabel);
		
		JLabel PasswordLabel = new JLabel("Password");
		PasswordLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		PasswordLabel.setIcon(new ImageIcon("C:\\Users\\fkina\\eclipse-workspace\\EFM\\src\\CryptoManagement\\src\\main\\java\\icons\\Oxygen-Icons.org-Oxygen-Apps-preferences-desktop-user-password.24.png"));
		PasswordLabel.setBounds(10, 261, 176, 32);
		contentPane.add(PasswordLabel);
		
		NameField = new JTextField();
		NameField.setBounds(196, 178, 273, 35);
		contentPane.add(NameField);
		NameField.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBackground(new Color(255, 255, 255));
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = NameField.getText();
				String password = new String(passwordField.getPassword());
				UserDAOImpl userDao = new UserDAOImpl(connection);
				User user = null;
				try {
					user = userDao.getUserByUsername(username);
					System.out.println("User retrieved: " + user);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				try {
					if (user != null && userDao.validateCredentials(user.getUsername(), password)) {
						System.out.println("Login successful for user: " + user.getUsername());
						JOptionPane.showMessageDialog(null, "Login successful", "Success", JOptionPane.INFORMATION_MESSAGE);
						// Open the main application window
						GestionCrytos gestionCrytos = null;
						try {
							gestionCrytos = new GestionCrytos();
						} catch (IOException | SQLException e1) {
							e1.printStackTrace();
						}
						gestionCrytos.setVisible(true);
						dispose(); // Close the login window
					} else {
						JOptionPane.showMessageDialog(null, "Invalid username or password", "Login Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}});
		btnLogin.setBounds(269, 348, 123, 21);
		contentPane.add(btnLogin);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(196, 260, 273, 35);
		contentPane.add(passwordField);
		
		JButton btnResetPassword = new JButton("Forget Password?");
		btnResetPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnResetPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = NameField.getText();
				String password = new String(passwordField.getPassword());
				
				User user = new User();
				UserDAOImpl userDao = new UserDAOImpl(connection);
				try {
					user = userDao.getUserByUsername(username);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				if (user != null) {
					System.out.println("Reset password link sent to: " + user.getEmail());
					// Here you would typically send a reset password email
				} else {
					System.out.println("User not found.");
					// Show an error message
				}
			}
		});
		btnResetPassword.setBounds(363, 398, 153, 21);
		contentPane.add(btnResetPassword);
		
		JButton btnCreateAccount = new JButton("Create account");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Open the registration form
				UserRegistration userRegistration = null;
				try {
					userRegistration = new UserRegistration();
				} catch (IOException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				userRegistration.setVisible(true);
				dispose(); // Close the login window
			}
		});
		btnCreateAccount.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCreateAccount.setBounds(141, 399, 153, 21);
		contentPane.add(btnCreateAccount);
	}
}
