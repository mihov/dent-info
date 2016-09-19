package model.inTheLab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Basket {
	private HashMap<Integer, ArrayList<Service>> content;

	public Basket() {
		this.content = new HashMap<>();

	}

	public void putService(Integer position, Service service) {
		if (!this.content.containsKey(position)) {
			this.content.put(position, new ArrayList<Service>());
		}
		this.content.get(position).add(service);
	}

	public void removeService(Integer position, Service service) {
		this.content.get(position).remove(service);
	}

	public Double getTotal() {
		Double sum = 0.0;
		for (Entry<Integer, ArrayList<Service>> element : this.content.entrySet()) {
			for (Service service_element : element.getValue()) {
				sum+= service_element.getPrice();
			}
		}
		return sum;
	}
}
