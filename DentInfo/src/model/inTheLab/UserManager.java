package model.inTheLab;

import java.util.concurrent.ConcurrentHashMap;

import model.db.UserDAO;
import model.exceptions.InvalidEmailException;
import model.exceptions.InvalidUserNameException;
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
}
