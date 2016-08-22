package mainObjects;

import java.util.HashMap;

import exceptions.InvalidEgnException;
import exceptions.InvalidEmailException;
import exceptions.InvalidUserNameException;

public class Dentist extends Person {
	private HashMap<String, Patient> patientlist;
	private String bulstat;

	public Dentist(String username, String email, String password,String egn)
			throws InvalidEmailException, InvalidUserNameException, InvalidEgnException {
		super(username, email, password,egn);
		this.patientlist = new HashMap<>();
	}

	/**
	 * Adds a new patient to patientList
	 * 
	 * @param patient
	 * @return true if new patient added, false if argument is null or this user
	 *         exist
	 */
	public Boolean addPatient(Patient patient) {
		if (patient != null) {
			if (!this.patientlist.containsKey(patient.getUsername())) {
				this.patientlist.put(patient.getUsername(), patient);
				return true;
			} else {
				System.err.println("Patient " + patient.getUsername() + " already exist!");
				return false;
			}
		} else {
			return false;
		}
	}

	public Patient getPatient(String username) {
		return this.patientlist.get(username);
	}
	

	/**
	 * @return the bulstat
	 */
	public String getBulstat() {
		return bulstat;
	}

	/**
	 * @param bulstat
	 *            the bulstat to set
	 */
	public void setBulstat(String bulstat) {
		this.bulstat = bulstat;
	}
}

/*
 * Име Потребителско име Парола Булстат ЕГН Контакти Списък с пациенти Списък с
 * индивидуални цени – ако са договорени
 */
