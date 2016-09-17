package model.mainObjects;

import java.util.concurrent.ConcurrentHashMap;

import model.db.PatientDAO;
import model.exceptions.InvalidEmailException;
import model.exceptions.InvalidUserNameException;

public class PatientManager {

	private ConcurrentHashMap<String, Patient> registerredPatients;

	private static PatientManager instance;

	private PatientManager() {
		registerredPatients = new ConcurrentHashMap<>();
		try {
			for (Patient p : PatientDAO.getInstance().getAllPatients()) {
				registerredPatients.put(p.getEmail(), p);
			}
		} catch (InvalidEmailException e) {
			System.out.println("An error with adding the managers in PatientManager");
		} catch (InvalidUserNameException e) {
			System.out.println("An error with adding the managers in PatientManager");
		}
	}

	public synchronized static PatientManager getInstance() {
		if (instance == null) {
			instance = new PatientManager();
		}
		return instance;
	}

	public boolean validLogIn(String username, String password) {
		if (registerredPatients.containsKey(username)) {
			System.out.println("manager found");
			System.out.println("Hello " + registerredPatients.get(username).getEmail());
			return registerredPatients.get(username).getPassword().equals(password);
		}
		System.out.println("could not find patient");
		return false;
	}

	public void registerPatient(String password, String email) throws InvalidEmailException, InvalidUserNameException {
		Patient p = new Patient(email, password, 0);
		System.out.println("patient made");
		registerredPatients.put(email, p);
		PatientDAO.getInstance().savePatient(p);
	}

	public void registerPatient(String password, String email, String firstName, String lastName, Integer dentist_id,
			Integer lab_id) throws InvalidEmailException, InvalidUserNameException {
		Patient p = new Patient(0, email, password, firstName, lastName, "", "", "", 3, dentist_id, lab_id);

		System.out.println("patient made");
		registerredPatients.put(email, p);
		PatientDAO.getInstance().savePatient(p);
	}

	public Patient getPatient(String email) {
		Patient p = registerredPatients.get(email);
		System.out.println(p.getEmail());
		System.out.println(p.getUser_id());
		return p;
	}

}
