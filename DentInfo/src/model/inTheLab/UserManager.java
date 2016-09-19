package model.inTheLab;

import java.util.concurrent.ConcurrentHashMap;

import model.db.DentistDAO;
import model.db.ManagerDAO;
import model.db.UserDAO;
import model.exceptions.InvalidEmailException;
import model.exceptions.InvalidUserNameException;
import model.mainObjects.Dentist;
import model.mainObjects.Person;

public class UserManager {
	
	private ConcurrentHashMap<String, Person> registerredUsers;
	private static UserManager instance;
	
	private UserManager(){
		registerredUsers = new ConcurrentHashMap<>();
		try {
			for(Person user : UserDAO.getInstance().getAllUsers()){
				registerredUsers.put(user.getEmail(), user);
			}
		} catch (InvalidEmailException e) {
			System.out.println("An error with adding the managers in ManagersManager");
		} catch (InvalidUserNameException e) {
			System.out.println("An error with adding the managers in ManagersManager");
		}
	}
	
	public synchronized static UserManager getInstance(){
		if(instance == null){
			instance = new UserManager();
		}
		return instance;
	}
	
	public boolean validLogIn(String username,String password){
		if(registerredUsers.containsKey(username)){
			return registerredUsers.get(username).getPassword().equals(password);
		}
		return false;
	}
	
	public synchronized Person getUser(String email){
		return registerredUsers.get(email);
	}
	
	public void registerManager(String password,String email) throws InvalidEmailException, InvalidUserNameException{
		Manager m = new Manager(email, password,0);
		System.out.println("manager made");
		registerredUsers.put(email, m);
		m.setUserTypeFk(1);
		ManagerDAO.getInstance().saveUser(m);
	}
	
	public void registerDentist(String password,String email,DentalLaboratory dl) throws InvalidEmailException, InvalidUserNameException{
		Dentist m = new Dentist(email, password,0,dl);
		System.out.println("manager made");
		registerredUsers.put(email, m);
		m.setUserTypeFk(2);
		DentistDAO.getInstance().saveDentist(m);
	}
	
	public Person getUserById(int id){
		for(Person p : registerredUsers.values()){
			if(p.getUser_id() == id){
				return p;
			}
		}
		System.out.println("OOPS didn't find user with that id !!!!");
		return null;
	}
}
