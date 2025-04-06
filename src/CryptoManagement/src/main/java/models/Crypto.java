package CryptoManagement.src.main.java.models;

public class Crypto {
    private int id;
    private String symbol;
    private String name;

    public Crypto() {}

    public Crypto(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
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

	@Override
	public String toString() {
		return "Crypto [id=" + id + ", symbol=" + symbol + ", name=" + name + ", getId()=" + getId() + ", getSymbol()="
				+ getSymbol() + ", getName()=" + getName() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}
}