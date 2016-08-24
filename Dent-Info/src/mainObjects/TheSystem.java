package mainObjects;

import java.util.HashMap;
import java.util.HashSet;

import exceptions.InvalidEgnException;
import exceptions.InvalidEmailException;
import exceptions.InvalidUserNameException;
import inTheLab.DentalLaboratory;
import inTheLab.Manager;

public class TheSystem {
	
	private HashMap<String, Administrator> adminList;			// <AdminUserName,
																// Administrator>
	private HashMap<String, Manager> managerList;				// <ManagerUserName,
																// LaboratoriesBulstat>
	private HashMap<String, DentalLaboratory> laboratoryList;	// <Bulstat,
																// DentalLaboratory>
	private HashMap<String, Person> userList;					//AllUsers

	/**
	 * @param adminList
	 * @param managerLabsList
	 * @param laboratoryList
	 * @param userList
	 */
	public TheSystem() {
		this.adminList = new HashMap<>();
		this.managerList = new HashMap<>();
		this.laboratoryList = new HashMap<>();
		this.userList = new HashMap<>();
	}
	
	/**
	 * Creates new manager if some of the parameters are incorrect or user already exists shows message
	 * @param username
	 * @param email
	 * @param password
	 */
	public Boolean createNewManager(String username, String email, String password) {
		try {
			Manager tempManager = new Manager(username, email, password,this);

			if (addUser(tempManager)) {
				this.managerList.put(username, tempManager);
				return true;
			}

		} catch (InvalidEmailException e) {
			System.out.println(e.getMessage());
			return false;
		} catch (InvalidUserNameException e1) {
			System.out.println(e1.getMessage());
			return false;
		}
		return false;
	}

	
	/**
	 * Verifies that the user already exists, if the user does not exist, add it
	 * to the list of users and returns true, otherwise returns false.
	 * 
	 * @param people
	 * @return Can you add this user to the list of users?
	 */
	public Boolean addUser(Person people) {
		if (!doesThisUserExist(people.getUsername())) {
			this.userList.put(people.getUsername(), people);
			return true;
		} else {
			System.out.println("This user already exists!");
			return false;
		}
	}
	
	/**
	 * Returns an object from username
	 * @param username
	 * @return object of type manager
	 */
	public Manager getManager(String username) {
		return this.managerList.get(username);
	}
	
	/**
	 * Checks if the specified username exists from all the users and returns a boolean expression
	 * @param username
	 * @return boolean expression
	 */
	public Boolean doesThisUserExist(String username) {
		return this.userList.containsKey(username);
	}
	
	/**
	 * Checks if the specified lab exists and returns a boolean expression
	 * @param labBulstat
	 * @return boolean expression
	 */
	public Boolean doesThisLabExist(String labBulstat) {
		return this.laboratoryList.containsKey(labBulstat);
	}
	
	/**
	 * With given bulstat chechs if there is such a laboratory if so returns the laboratory else returns message
	 * @param bulstat
	 * @return Dental Laboratory
	 */
	public DentalLaboratory getLab(String bulstat) {
		if (doesThisLabExist(bulstat)) {
			return this.laboratoryList.get(bulstat);
		} else {
			System.out.println("No such laboratory found!");
			return null;
		}

	}
	
	
	/**
	 * Adds a new laboratory if such already exists returns message
	 * @param dentLab
	 * @return boolean expression
	 */
	public Boolean addNewLab(DentalLaboratory dentLab) {
		if (!doesThisLabExist(dentLab.getBulstat())) {
			this.laboratoryList.put(dentLab.getBulstat(), dentLab);
			return true;
		}
		System.out.println("Such a laboratory already exists");
		return false;
	}
	
	/**
	 * Checks if given username exists and compares the pasword for the found user under that username
	 * @param userName
	 * @param userPassword
	 * @return boolean expression
	 */
	public Boolean logInCheck(String userName, String userPassword) {
		if (doesThisUserExist(userName)) {
			return this.userList.get(userName).comparePassword(userPassword);
		} else {
			System.out.println("Wrong username!");
			return false;
		}
	}
	
	/**
	 * Uses login check method to see if the parameters are correct and returns a person.Else returns a message
	 * @param userName
	 * @param userPassword
	 * @return person
	 */
	public Person logIn(String userName, String userPassword) {
		if (logInCheck(userName, userPassword)) {
			return this.userList.get(userName);
		} else {
			System.out.println("Login failed!");
			return null;
		}
	}
}
