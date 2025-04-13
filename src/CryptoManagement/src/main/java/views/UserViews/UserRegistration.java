package CryptoManagement.src.main.java.views.UserViews;
import CryptoManagement.src.main.java.models.User;
import CryptoManagement.src.main.java.views.GestionCrytos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CryptoManagement.src.main.java.config.DatabaseConfig;
import CryptoManagement.src.main.java.dao.impl.UserDAOImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UserRegistration extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField UsernameField;
	private JTextField EmailField;
	private JPasswordField ConfirmpasswordField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserRegistration frame = new UserRegistration();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public UserRegistration() throws IOException, SQLException {
		setTitle("Registration");
		Connection connection = DatabaseConfig.getConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 663, 556);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 32));
		lblWelcome.setIcon(new ImageIcon("C:\\Users\\fkina\\eclipse-workspace\\EFM\\src\\CryptoManagement\\src\\main\\java\\icons\\Aha-Soft-Security-Login.32.png"));
		lblWelcome.setBounds(159, 49, 268, 61);
		contentPane.add(lblWelcome);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\fkina\\eclipse-workspace\\EFM\\src\\CryptoManagement\\src\\main\\java\\icons\\Artdesigner-Emoticons-2-Welcome.128.png"));
		lblNewLabel.setBounds(494, 120, 169, 229);
		contentPane.add(lblNewLabel);
		
		JLabel NameLabel = new JLabel("Name");
		NameLabel.setBounds(10, 182, 139, 13);
		contentPane.add(NameLabel);
		
		JLabel EmailLabel = new JLabel("Email");
		EmailLabel.setBounds(10, 243, 139, 13);
		contentPane.add(EmailLabel);
		
		UsernameField = new JTextField();
		UsernameField.setBounds(159, 164, 268, 34);
		contentPane.add(UsernameField);
		UsernameField.setColumns(10);
		
		EmailField = new JTextField();
		EmailField.setBounds(159, 225, 268, 34);
		contentPane.add(EmailField);
		EmailField.setColumns(10);
		
		JLabel PasswordConformLabel = new JLabel("Confirm Password");
		PasswordConformLabel.setBounds(10, 358, 139, 13);
		contentPane.add(PasswordConformLabel);
		
		JLabel PasswordLabel = new JLabel("Password");
		PasswordLabel.setBounds(10, 299, 139, 13);
		contentPane.add(PasswordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(159, 281, 268, 34);
		contentPane.add(passwordField);
		
		ConfirmpasswordField = new JPasswordField();
		ConfirmpasswordField.setBounds(159, 340, 268, 34);
		contentPane.add(ConfirmpasswordField);
		
		JButton btnSignIn = new JButton("Sign In");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = UsernameField.getText();
				String email = EmailField.getText();
				String password = new String(passwordField.getPassword());
				String confirmPassword = new String(ConfirmpasswordField.getPassword());

				if (password.equals(confirmPassword)) {
					UserDAOImpl userDao = new UserDAOImpl(connection);
					User user = new User(username, password, email);
					try {
						userDao.createUser(user);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println("User registered successfully!");
					
					JOptionPane.showMessageDialog(btnSignIn, "User registered successfully!");
					GestionCrytos gestionCrytos = null;
					try {
						gestionCrytos = new GestionCrytos();
					} catch (IOException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					gestionCrytos.setVisible(true);
					dispose();
				} else {
					System.out.println("Passwords do not match!");
				}
			}
		});
		btnSignIn.setBounds(112, 447, 85, 21);
		contentPane.add(btnSignIn);
		
		JButton btnLogin = new JButton("Login Instead");
		btnLogin.setBounds(342, 447, 106, 21);
		contentPane.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserLogin frame = null;
				try {
					frame = new UserLogin();
				} catch (IOException | SQLException e1) {
					e1.printStackTrace();
				}
				frame.setVisible(true);
				dispose();
			}
		});
	}

}
