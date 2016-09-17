package model.mainObjects;

import java.util.concurrent.ConcurrentHashMap;

import model.db.DentistDAO;
import model.exceptions.InvalidEmailException;
import model.exceptions.InvalidUserNameException;

public class DentistManager {

	private ConcurrentHashMap<String, Dentist> registerredDentists;
	
	private static DentistManager instance;
	
	private DentistManager(){
		registerredDentists = new ConcurrentHashMap<>();
		try {
			for(Dentist d : DentistDAO.getInstance().getAllDentists()){
				registerredDentists.put(d.getEmail(), d);
			}
		} catch (InvalidEmailException e) {
			System.out.println("An error with adding the managers in DentistManager");
		} catch (InvalidUserNameException e) {
			System.out.println("An error with adding the managers in DentistManager");
		}
	}
	
	public synchronized static DentistManager getInstance(){
		if(instance == null){
			instance = new DentistManager();
		}
		return instance;
	}
	
	
	public boolean validLogIn(String username,String password){
		if(registerredDentists.containsKey(username)){
			System.out.println("dentist found");
			System.out.println("Hello " + registerredDentists.get(username).getEmail());
			return registerredDentists.get(username).getPassword().equals(password);
		}
		System.out.println("could not find dentist");
		return false;
	}
	
	public void registerDentist(String password,String email) throws InvalidEmailException, InvalidUserNameException{
		Dentist m = new Dentist(email, password,0);
		System.out.println("manager made");
		registerredDentists.put(email, m);
		DentistDAO.getInstance().saveDentist(m);
	}
	
	public Dentist getDentist(String email){
		Dentist d = registerredDentists.get(email);
		System.out.println(d.getEmail());
		System.out.println(d.getUser_id());
		return d;
	}
	
}
