package CryptoManagement.src.main.java.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import CryptoManagement.src.main.java.models.Crypto;

public interface CryptoDao {
	void createCrypto(Crypto crypto) throws SQLException;
	Crypto getCryptoById(int id) throws SQLException;
	Crypto getCryptoBySymbol(String symbol) throws SQLException;
	List<Crypto> getAllCryptos() throws SQLException;
	void updateCrypto(Crypto crypto) throws SQLException;
	void deleteCrypto(int id) throws SQLException;
}
