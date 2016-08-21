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

	public Boolean createNewManager(String username, String email, String password) {
		if (!isThisUserExist(username)) {
			try {
				this.managerList.put(username, new Manager(username, email, password, this));
				this.userList.put(username, this.managerList.get(username));
				return true;
			} catch (InvalidEmailException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
				return false;
			} catch (InvalidUserNameException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
				return false;
			}
		} else {
			System.err.println("This username Exist!");
			return false;
		}

	}

	public Manager getManager(String username) {
		return this.managerList.get(username);
	}

	public Boolean isThisUserExist(String username) {
		return this.userList.containsKey(username);
	}

	public Boolean isThisLabExist(String labBulstat) {
		return this.laboratoryList.containsKey(labBulstat);
	}

	public DentalLaboratory getLab(String bulstat) {
		if (isThisLabExist(bulstat)) {
			return this.laboratoryList.get(bulstat);
		} else {
			return null;
		}

	}

	public Boolean addNewLab(DentalLaboratory dentLab) {
		if (!isThisLabExist(dentLab.getBulstat())) {
			this.laboratoryList.put(dentLab.getBulstat(), dentLab);
			return true;
		}
		return false;
	}

	public Boolean logInCheck(String userName, String userPassword) {
		if (isThisUserExist(userName)) {
			return this.userList.get(userName).comparePassword(userPassword);
		} else {
			return false;
		}
	}
}
