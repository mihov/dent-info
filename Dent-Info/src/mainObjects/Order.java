package mainObjects;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Order {
	private static int idCreator;
	private Integer id;
	private Patient patient;
	private Dentist dentist;
	private HashMap<DateReason, Date> dateList;
	private Double price;
	private Boolean isPayed;
	private Boolean isReady;
	private BufferedImage image;
	private HashMap<ToothPosition, ArrayList<Service>> servicesList;

	public Order(Patient patient, Dentist dentist) {
		this.id = idCreator++;
		this.setPatient(patient);
		this.setDentist(dentist);
		this.price = 0.0;
		this.isPayed = false;
		this.isReady = false;
		this.servicesList = new HashMap<>();
		this.dateList = new HashMap<>();
	}

	public Integer getId() {
		return id;
	}

	public Patient getPatient() {
		return patient;
	}

	private void setPatient(Patient patient) {
		if (patient != null) {
			this.patient = patient;
		}
	}

	public Dentist getDentist() {
		return dentist;
	}

	private void setDentist(Dentist dentist) {
		if (dentist != null) {
			this.dentist = dentist;
		}
	}

	public Double getPrice() {
		return price;
	}

	private void setPrice(Double price) {
		this.price = price;
	}

	public Boolean getIsPayed() {
		return isPayed;
	}

	public Boolean getIsReady() {
		return isReady;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public void addService(ToothPosition position, Service service) {
		if (!this.servicesList.containsKey(position)) {
			this.servicesList.put(position, new ArrayList<>());
		}
		this.servicesList.get(position).add(service);
		this.calculatePrice();
	}

	public void calculatePrice() {
		Double total = 0.00;
		for (Entry<ToothPosition, ArrayList<Service>> element : this.servicesList.entrySet()) {
			for (Service service_element : element.getValue()) {
				total += service_element.getPrice();
			}
		}
		this.setPrice(total);
	}

	public String getServiceList() {
		StringBuilder builder = new StringBuilder();
		builder.append("=== Service List ===\n");

		for (Entry<ToothPosition, ArrayList<Service>> element : this.servicesList.entrySet()) {
			builder.append("   ");
			builder.append(element.getKey());

			for (Service service_element : element.getValue()) {
				builder.append("\t");
				builder.append(service_element);
			}
		}
		builder.append("=== Total ");
		builder.append(this.getPrice());
		builder.append(" lv. ===\n");
		builder.append("=== End Of Service List ===\n");

		return builder.toString();
	}

	public void addDate(DateReason reason, Date date) {
		this.dateList.put(reason, date);
	}

	public Date getDate(DateReason reason) {
		return this.dateList.get(reason);
	}

	public String getDateList() {
		StringBuilder builder = new StringBuilder();
		for (Entry<DateReason, Date> element : this.dateList.entrySet()) {
			builder.append("\t");
			builder.append(element.getKey());
			builder.append("\t|\t");
			builder.append(element.getValue());
			builder.append("\n");
		}
		return builder.toString();
	}

}
/*
 * Уникален номер Списък с услуги по зъбен статус Пациент Зъболекар Дата на
 * приемане на поръчката Дата на завършване на поръчката (срок) Цена Дата за
 * проба Статус готовност Статус платена Снимка
 */