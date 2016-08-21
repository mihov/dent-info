package mainObjects;

import java.util.HashMap;
import java.util.HashSet;

import exceptions.InvalidUserNameException;
import inTheLab.DentalLaboratory;
import inTheLab.Manager;

public class TheSystem {
	private HashMap<String, Administrator> adminList;// <AdminUserName,
														// Administrator>
	private HashMap<String, HashSet<String>> managerLabsList;// <ManagerUserName,
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
		this.managerLabsList = new HashMap<>();
		this.laboratoryList = new HashMap<>();
		this.userList = new HashMap<>();
	}

	public Boolean isThisUserExist(String userName) {
		return this.userList.containsKey(userName);
	}

	public Boolean isThisLabExist(String labBulstat) {
		return this.laboratoryList.containsKey(labBulstat);
	}

	public DentalLaboratory getLab(String bulstat) {
		return this.laboratoryList.get(bulstat);
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
