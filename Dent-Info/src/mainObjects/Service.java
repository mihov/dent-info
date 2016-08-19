package mainObjects;

import java.math.BigDecimal;

public class Service {
	static int id = 1;
	private int serialNumber;
	private String longName;
	private String shortName;
	private Double price;
	// private BigDecimal price;

	public Service(String longName, String shortName, Double price) {
		this.serialNumber = id++;
		this.setLongName(longName);
		this.setShortName(shortName);
		this.setPrice(price);
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	// public BigDecimal getPrice() {
	// return price;
	// }

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		if (price >= 0) {
			this.price = price;
			// this.price = BigDecimal.valueOf(price);
		} else {
			System.err.println("Price can not be negative");
		}
	}

	public String showFull() {
		StringBuilder builder = new StringBuilder();
		builder.append(shortName);
		builder.append("\t| ");
		builder.append(getPrice());
		builder.append(" lv.\t| ");
		builder.append(longName);
		return builder.toString();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(shortName);
		builder.append("\t| ");
		builder.append(getPrice());
		builder.append(" lv.");
		return builder.toString();
	}

}
