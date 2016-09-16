package model.inTheLab;

import java.util.concurrent.ConcurrentHashMap;

import model.db.ManagerDAO;
import model.exceptions.InvalidEmailException;
import model.exceptions.InvalidUserNameException;

public class ManagersManager {
	
	private ConcurrentHashMap<String, Manager> registerredManagers;
	
	private static ManagersManager instance;
	
	private ManagersManager(){
		registerredManagers = new ConcurrentHashMap<>();
		try {
			for(Manager m : ManagerDAO.getInstance().getAllManagers()){
				registerredManagers.put(m.getEmail(), m);
			}
		} catch (InvalidEmailException e) {
			System.out.println("An error with adding the managers in ManagersManager");
		} catch (InvalidUserNameException e) {
			System.out.println("An error with adding the managers in ManagersManager");
		}
	}
	
	public synchronized static ManagersManager getInstance(){
		if(instance == null){
			instance = new ManagersManager();
		}
		return instance;
	}
	
	
	public boolean validLogIn(String username,String password){
		if(registerredManagers.containsKey(username)){
			System.out.println("manager found");
			System.out.println("Hello" + registerredManagers.get(username).getEmail());
			return registerredManagers.get(username).getPassword().equals(password);
		}
		System.out.println("could not find manager");
		return false;
	}
	
	public void registerManager(String password,String email) throws InvalidEmailException, InvalidUserNameException{
		Manager m = new Manager(email, password);
		System.out.println("manager made");
		ManagerDAO.getInstance().saveUser(m);
		registerredManagers.put(email, m);
	}

}
