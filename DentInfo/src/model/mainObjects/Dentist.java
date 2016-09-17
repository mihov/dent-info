package model.mainObjects;

import java.util.HashMap;

import model.exceptions.InvalidEmailException;
import model.exceptions.InvalidUserNameException;

public class Dentist extends Person {
	private HashMap<String, Patient> patientlist;
	private String bulstat;
	
	public Dentist(String email, String password,int user_id)
			throws InvalidEmailException, InvalidUserNameException{
		super(email, password,user_id);
		this.patientlist = new HashMap<>();
	}
	
	public Dentist(int user_id, String email, String password, String firstName, String lastName, String egn,
			String address, String phone, Integer fk_user_type_id, Integer fk_dentist_id, Integer lab_id) {
		super( user_id,  email,  password,  firstName,  lastName,  egn,
				 address,  phone,  fk_user_type_id,  fk_dentist_id,  lab_id);
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
			if (!this.patientlist.containsKey(patient.getEmail())) {
				this.patientlist.put(patient.getEmail(), patient);
				return true;
			} else {
				System.err.println("Patient " + patient.getEmail() + " already exist!");
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
