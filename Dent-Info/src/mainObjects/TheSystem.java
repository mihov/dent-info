package mainObjects;

import java.util.HashMap;
import java.util.HashSet;

import exceptions.InvalidEmailException;
import exceptions.InvalidUserNameException;
import inTheLab.DentalLaboratory;
import inTheLab.Manager;

public class TheSystem {
	
	private HashMap<String, Administrator> adminList;// <AdminUserName,
														// Administrator>
	private HashMap<String, Manager> managerList;// <ManagerUserName,
													// LaboratoriesBulstat>
	private HashMap<String, DentalLaboratory> laboratoryList;// <Bulstat,
																// DentalLaboratory>
	private HashMap<String, People> userList;

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

	public void createNewManager(String username, String email, String password) {
		try {
			Manager tempManager = new Manager(username, email, password, this);

			if (addUser(tempManager)) {
				this.managerList.put(username, tempManager);
				return;
			}

		} catch (InvalidEmailException e) {
			System.out.println(e.getMessage());
			// e.printStackTrace();
			return;
		} catch (InvalidUserNameException e) {
			System.out.println(e.getMessage());
			// e.printStackTrace();
			return;
		}

	}

	
	/**
	 * Verifies that the user already exists, if the user does not exist, add it
	 * to the list of users and returns true, otherwise returns false.
	 * 
	 * @param people
	 * @return Can you add this user to the list of users?
	 */
	public Boolean addUser(People people) {
		if (!doesThisUserExist(people.getUsername())) {
			this.userList.put(people.getUsername(), people);
			return true;
		} else {
			System.out.println("This user already exists!");
			return false;
		}
	}

	public Manager getManager(String username) {
		return this.managerList.get(username);
	}

	public Boolean doesThisUserExist(String username) {
		return this.userList.containsKey(username);
	}

	public Boolean doesThisLabExist(String labBulstat) {
		return this.laboratoryList.containsKey(labBulstat);
	}

	public DentalLaboratory getLab(String bulstat) {
		if (doesThisLabExist(bulstat)) {
			return this.laboratoryList.get(bulstat);
		} else {
			return null;
		}

	}

	public Boolean addNewLab(DentalLaboratory dentLab) {
		if (!doesThisLabExist(dentLab.getBulstat())) {
			this.laboratoryList.put(dentLab.getBulstat(), dentLab);
			return true;
		}
		return false;
	}

	public Boolean logInCheck(String userName, String userPassword) {
		if (doesThisUserExist(userName)) {
			return this.userList.get(userName).comparePassword(userPassword);
		} else {
			return false;
		}
	}

	public People logIn(String userName, String userPassword) {
		if (logInCheck(userName, userPassword)) {
			return this.userList.get(userName);
		} else {
			return null;
		}
	}
}
