package model.inTheLab;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.db.DBManager;

public class Service {
	static Integer id = 1;
	private Integer serialNumber;// This is custom unique ID
	private String longName;
	private String shortName;
	private Double price;
	private Integer labId;
	private Integer dbID;

	public Service(String longName, String shortName, Double price, DentalLaboratory dl) {
		this.serialNumber = id++;
		this.setLongName(longName);
		this.setShortName(shortName);
		this.setPrice(price);
	}
	
	
	public Integer getDbId(){
		return this.dbID;
	}
	
	public void setDbId(int id){
		this.dbID = id;
	}
	
	public void setLabId(Integer labId) {
		this.labId = labId;
	}
	
	public Integer getLabId() {
		return labId;
	}

	public Integer getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(Integer serialNumber) {
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
