package mainObjects;

public class Service {
	static Integer id = 1;
	private String serialNumber;// This is custom unique ID
	private String longName;
	private String shortName;
	private Double price;

	public Service(String longName, String shortName, Double price, String serialNumber) {
		if (serialNumber.length() < 1) {
			this.serialNumber = String.valueOf(id++);
		} else {
			this.serialNumber = serialNumber;
		}
		this.setLongName(longName);
		this.setShortName(shortName);
		this.setPrice(price);
	}
	
	
	

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		if (price >= 0) {
			this.price = price;
			// this.price = BigDecimal.valueOf(price);
		} else {
			System.err.println("Price can not be negative!");
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
		builder.append(" lv.\n");
		return builder.toString();
	}

}
