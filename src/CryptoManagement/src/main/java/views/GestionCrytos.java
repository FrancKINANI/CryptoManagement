package CryptoManagement.src.main.java.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CryptoManagement.src.main.java.config.DatabaseConfig;
import CryptoManagement.src.main.java.dao.impl.CryptoDAOImpl;
import CryptoManagement.src.main.java.models.Crypto;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;

public class GestionCrytos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel CryptoPanel;
	private JTextField SymbolField;
	private JTextField NomField;
	private JTable tableCrypto;
	private JTextField QuantiteField;
	private JTextField PrixField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionCrytos frame = new GestionCrytos();
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
	public GestionCrytos() throws IOException, SQLException {
		Connection connection = DatabaseConfig.getConnection();
		setTitle("Gestion Cryptos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 853, 613);
		CryptoPanel = new JPanel();
		CryptoPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(CryptoPanel);
		CryptoPanel.setLayout(null);
		
		JLabel symbolLabel = new JLabel("Symbole");
		symbolLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		symbolLabel.setIcon(new ImageIcon("C:\\Users\\fkina\\eclipse-workspace\\EFM\\src\\CryptoManagement\\src\\main\\java\\icons\\Google-Noto-Emoji-People-Clothing-Objects-12157-anger-symbol.24.png"));
		symbolLabel.setBounds(26, 32, 79, 33);
		CryptoPanel.add(symbolLabel);
		
		JLabel NomLabel = new JLabel("Nom");
		NomLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		NomLabel.setIcon(new ImageIcon("C:\\Users\\fkina\\eclipse-workspace\\EFM\\src\\CryptoManagement\\src\\main\\java\\icons\\Fatcow-Farm-Fresh-Define-name.24.png"));
		NomLabel.setBounds(26, 87, 65, 33);
		CryptoPanel.add(NomLabel);
		
		JLabel QuantiteLabel = new JLabel("Quantité");
		QuantiteLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		QuantiteLabel.setIcon(new ImageIcon("C:\\Users\\fkina\\eclipse-workspace\\EFM\\src\\CryptoManagement\\src\\main\\java\\icons\\Google-Noto-Emoji-People-Clothing-Objects-12157-anger-symbol.24.png"));
		QuantiteLabel.setBounds(26, 144, 79, 33);
		CryptoPanel.add(QuantiteLabel);
		
		JLabel PrixLabel = new JLabel("Prix");
		PrixLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		PrixLabel.setIcon(new ImageIcon("C:\\Users\\fkina\\eclipse-workspace\\EFM\\src\\CryptoManagement\\src\\main\\java\\icons\\Google-Noto-Emoji-People-Clothing-Objects-12157-anger-symbol.24.png"));
		PrixLabel.setBounds(26, 193, 79, 33);
		CryptoPanel.add(PrixLabel);
		
		JLabel PrincipalLabel = new JLabel("Entrer les informations et faites un choix");
		PrincipalLabel.setBounds(301, 10, 194, 13);
		CryptoPanel.add(PrincipalLabel);
		
		SymbolField = new JTextField();
		SymbolField.setBounds(138, 33, 243, 26);
		CryptoPanel.add(SymbolField);
		SymbolField.setColumns(10);
		
		NomField = new JTextField();
		NomField.setBounds(138, 87, 243, 26);
		CryptoPanel.add(NomField);
		NomField.setColumns(10);
		
		JButton Addbtn = new JButton("Add");
		Addbtn.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		Addbtn.setHorizontalAlignment(SwingConstants.LEFT);
		Addbtn.setIcon(new ImageIcon("C:\\Users\\fkina\\eclipse-workspace\\EFM\\src\\CryptoManagement\\src\\main\\java\\icons\\Custom-Icon-Design-Pretty-Office-2-Add-event.24.png"));
		Addbtn.setBounds(498, 32, 108, 33);
		Addbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Crypto crypto = new Crypto();
				crypto.setSymbol(SymbolField.getText());
				crypto.setName(NomField.getText());
				crypto.setQuantity(Integer.parseInt(QuantiteField.getText()));
				crypto.setPrice(Double.parseDouble(PrixField.getText()));
				CryptoDAOImpl cryptoDAO = new CryptoDAOImpl(connection);
				
				try {
					cryptoDAO.createCrypto(crypto);
					System.out.println("Crypto added successfully: " + crypto);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		CryptoPanel.add(Addbtn);
		
		JButton DeleteBtn = new JButton("Delete");
		DeleteBtn.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		DeleteBtn.setIcon(new ImageIcon("C:\\Users\\fkina\\eclipse-workspace\\EFM\\src\\CryptoManagement\\src\\main\\java\\icons\\Gakuseisean-Ivista-2-Misc-Delete-Database.24.png"));
		DeleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CryptoDAOImpl cryptoDAO = new CryptoDAOImpl(connection);
				Crypto crypto = new Crypto();
				try {
					crypto = cryptoDAO.getCryptoBySymbol(SymbolField.getText());
					if (crypto != null) {
						cryptoDAO.deleteCrypto(crypto.getId());
						System.out.println("Crypto deleted successfully: " + crypto);
					} else {
						System.out.println("Crypto not found");
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				if (crypto == null) {
					System.out.println("Crypto not found");
					return;
				}
			}
		});
		DeleteBtn.setBounds(498, 111, 108, 36);
		CryptoPanel.add(DeleteBtn);
		
		JButton UpdateBtn = new JButton("Update");
		UpdateBtn.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		UpdateBtn.setIcon(new ImageIcon("C:\\Users\\fkina\\eclipse-workspace\\EFM\\src\\CryptoManagement\\src\\main\\java\\icons\\Oxygen-Icons.org-Oxygen-Actions-edit-clear-history.24.png"));
		UpdateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CryptoDAOImpl cryptoDAO = new CryptoDAOImpl(connection);
				Crypto crypto = new Crypto();
				try {
					crypto = cryptoDAO.getCryptoBySymbol(SymbolField.getText());
					if (crypto != null) {
						crypto.setName(NomField.getText());
						crypto.setQuantity(Integer.parseInt(QuantiteField.getText()));
						crypto.setPrice(Double.parseDouble(PrixField.getText()));
						cryptoDAO.updateCrypto(crypto);
						System.out.println("Crypto updated successfully: " + crypto);
					} else {
						System.out.println("Crypto not found");
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		UpdateBtn.setBounds(498, 208, 108, 33);
		CryptoPanel.add(UpdateBtn);
		
		JButton RefreshBtn = new JButton("Refresh ");
		RefreshBtn.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		RefreshBtn.setIcon(new ImageIcon("C:\\Users\\fkina\\eclipse-workspace\\EFM\\src\\CryptoManagement\\src\\main\\java\\icons\\Oxygen-Icons.org-Oxygen-Actions-edit-clear-history.24.png"));
		RefreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CryptoDAOImpl cryptoDAO = new CryptoDAOImpl(connection);
				try {
					java.util.List<Crypto> ListCryptos = cryptoDAO.getAllCryptos();
					String[] columnNames = {"ID", "Symbol", "Name", "Quantity", "Price", "Date"};
					Object[][] data = new Object[ListCryptos.size()][6];
					for (int i = 0; i < ListCryptos.size(); i++) {
						Crypto crypto = ListCryptos.get(i);
						data[i][0] = crypto.getId();
						data[i][1] = crypto.getSymbol();
						data[i][2] = crypto.getName();
						data[i][3] = crypto.getQuantity();
						data[i][4] = crypto.getPrice();
						data[i][5] = crypto.getDatePrice();
					}
					tableCrypto.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
					tableCrypto.setFillsViewportHeight(true);
					System.out.println("Crypto list refreshed successfully");
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		RefreshBtn.setBounds(633, 454, 135, 33);
		CryptoPanel.add(RefreshBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(106, 385, 500, 158);
		CryptoPanel.add(scrollPane);
		
		tableCrypto = new JTable();
		scrollPane.setViewportView(tableCrypto);
		
		JLabel lblNewLabel = new JLabel("Liste des cryptos");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\fkina\\eclipse-workspace\\EFM\\src\\CryptoManagement\\src\\main\\java\\icons\\Gartoon-Team-Gartoon-Apps-Gtodo-todo-list.24.png"));
		lblNewLabel.setBounds(272, 329, 152, 33);
		CryptoPanel.add(lblNewLabel);
		
		QuantiteField = new JTextField();
		QuantiteField.setColumns(10);
		QuantiteField.setBounds(138, 151, 243, 26);
		CryptoPanel.add(QuantiteField);
		
		PrixField = new JTextField();
		PrixField.setColumns(10);
		PrixField.setBounds(138, 200, 243, 26);
		CryptoPanel.add(PrixField);
	}
}
