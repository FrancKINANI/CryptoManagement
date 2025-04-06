package CryptoManagement.src.main.java.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import CryptoManagement.src.main.java.models.Exchange;

public interface ExchangeDao {
		// Define methods for exchange management
	void createExchange(Exchange exchange) throws SQLException;
	Exchange getExchangeById(int id) throws SQLException;
	List<Exchange> getAllExchanges() throws SQLException;
	void updateExchange(Exchange exchange) throws SQLException;
	void deleteExchange(int id) throws SQLException;
}
