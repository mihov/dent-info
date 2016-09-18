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
			System.out.println("Managers added");
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
			System.out.println("Hello " + registerredManagers.get(username).getEmail());
			return registerredManagers.get(username).getPassword().equals(password);
		}
		System.out.println("could not find manager");
		return false;
	}
	
	public Manager getManager(String email){
		Manager m = registerredManagers.get(email);
		System.out.println(m.getEmail());
		System.out.println(m.getUser_id());
		return m;
	}

}
