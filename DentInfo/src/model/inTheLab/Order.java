package model.inTheLab;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import model.mainObjects.Dentist;
import model.mainObjects.Patient;

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
	private HashMap<Integer, ArrayList<Service>> servicesList;

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

	public void addService(int position, Service service) {
		if (!this.servicesList.containsKey(position)) {
			this.servicesList.put(position, new ArrayList<>());
		}
		this.servicesList.get(position).add(service);
		this.calculatePrice();
	}

	public void calculatePrice() {
		Double total = 0.00;
		for (Entry<Integer, ArrayList<Service>> element : this.servicesList.entrySet()) {
			for (Service service_element : element.getValue()) {
				total += service_element.getPrice();
			}
		}
		this.setPrice(total);
	}

	public String getServiceList() {
		StringBuilder builder = new StringBuilder();
		builder.append("=== Service List ===\n");

		for (Entry<Integer, ArrayList<Service>> element : this.servicesList.entrySet()) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Order)) {
			return false;
		}
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

}
/*
 * Уникален номер Списък с услуги по зъбен статус Пациент Зъболекар Дата на
 * приемане на поръчката Дата на завършване на поръчката (срок) Цена Дата за
 * проба Статус готовност Статус платена Снимка
 */