package model.inTheLab;

import java.util.HashMap;
import java.util.HashSet;

import model.exceptions.InvalidUserNameException;
import model.mainObjects.Dentist;
import model.mainObjects.Patient;

public class DentalLaboratory {
	private String bulstat;
	private String name;
	private String address;
	private Manager manager;
	private String website;
	private HashMap<String, Manager> managerList;
	private HashMap<String, Dentist> dentistList;
	private HashMap<String, Service> serviceList;
	private HashMap<Dentist, HashMap<Patient, HashSet<Order>>> orderList;
	private HashMap<Integer, Order> ordersById;

	/**
	 * 
	 * @param bulstat
	 * @param name
	 * @param address
	 * @throws InvalidUserNameException 
	 */
	public DentalLaboratory(String bulstat, String name,String address, Manager manager,String website) throws InvalidUserNameException {
		this.setBulstat(bulstat);
		this.setName(name);
		this.setAddress(address);
		this.setManager(manager);
		this.setWebsite(website);
		this.managerList = new HashMap<>();
		this.dentistList = new HashMap<>();
		this.serviceList = new HashMap<>();
		this.orderList = new HashMap<>();
		this.ordersById = new HashMap<>();
	}
	
	public void setWebsite(String wbsite){
		this.website = website;
	}
	
	public String getWebsite(){
		return this.website;
	}

	public String getBulstat() {
		return this.bulstat;
	}

	private void setBulstat(String bulstat) {
		this.bulstat = bulstat;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) throws InvalidUserNameException {
		if (name.length() > 1) {
			this.name = name;
		} else {
			throw new InvalidUserNameException(name);
		}
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		if (address != null) {
			this.address = address;
		} else {
		}
	}

	public Manager getManager(String managerUserName) {
		return this.managerList.get(managerUserName);
	}

	public Boolean setNewManager(Manager manager) {
		if (manager != null) {
			if (!this.managerList.containsKey(manager.getEmail())) {
				this.managerList.put(manager.getEmail(), manager);
				return true;
			} else {
				return false; // manager exist
			}
		} else {
			return false;// manage is null
		}
	}

	/**
	 * @return the manager
	 */
	public Manager getManager() {
		return manager;
	}

	/**
	 * @param manager the manager to set
	 */
	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Dentist getDentist(String dentistUserName) {
		return this.dentistList.get(dentistUserName);
	}

	public Boolean setNewDentist(Dentist dentist) {
		if (dentist != null) {
			if (!this.dentistList.containsKey(dentist.getEmail())) {
				this.dentistList.put(dentist.getEmail(), dentist);
			
				return true;
			} else {
				return false; // dentist exist
			}
		} else {
			return false;// dentist is null
		}
	}

	public Service getService(String serviceID) {
		return this.serviceList.get(serviceID);
	}

	public Boolean addServiceToList(Service service) {
		if (service != null) {
			if (!this.serviceList.containsKey(service.getSerialNumber())) {
				this.serviceList.put(service.getSerialNumber(), service);
				return true;
			} else {
				return null; // service exist
			}
		} else {
			return null; // service is null
		}
	}
	
	public Boolean changeService(Service service) {
		if (service != null) {
			if (this.serviceList.containsKey(service.getSerialNumber())) {
				this.serviceList.put(service.getSerialNumber(), service);
				return true;
			} else {
				return false; // service do not exist
			}
		} else {
			return null; // service is null
		}
	}	

	public Order getOrderByID(Integer id) {
		return this.ordersById.get(id);
	}

	public Boolean setOrderToOrderList(Order order) {
		if (order != null) {
			if (!this.orderList.containsKey(order.getDentist())) {
				/* If dentist is not in the list put them */
				this.orderList.put(order.getDentist(), new HashMap<>());
			}
			if (!this.orderList.get(order.getDentist()).containsKey(order.getPatient())) {
				// If patient is not in the list put them
				this.orderList.get(order.getDentist()).put(order.getPatient(), new HashSet<>());
			}
			// put current order to ordersById list
			this.ordersById.put(order.getId(), order);
			// put current order to orderList
			return this.orderList.get(order.getDentist()).get(order.getPatient()).add(order);
		} else {
			return false;
		}
	}
}

/*
 * Име Булстат Седалище Управител - МОЛ Контакти Списък със зъболекари, с които
 * работи Списък с услуги, които предлага. Списък с поръчки Списък
 * администратори-Управители
 */
