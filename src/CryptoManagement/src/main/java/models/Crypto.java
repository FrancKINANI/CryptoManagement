package CryptoManagement.src.main.java.models;

public class Crypto {
    private int id;
    private String symbol;
    private String name;
    private int quantity;
    private double price;
    private java.sql.Date datePrice;

    public Crypto() {}

    public Crypto(String symbol, String name, int quantity, double price) {
        this.symbol = symbol;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public java.sql.Date getDatePrice() {
		return datePrice;
	}
	
	public void setDatePrice(java.sql.Date datePrice) {
		this.datePrice = datePrice;
	}
	
	@Override
	public String toString() {
		return "Crypto [id=" + id + ", symbol=" + symbol + ", name=" + name + ", quantity=" + quantity + ", price=" + price + "]";
	}
}