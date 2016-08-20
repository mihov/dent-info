package mainObjects;

import java.util.ArrayList;
import java.util.HashMap;

import exceptions.InvalidEmailException;
import exceptions.InvalidUserNameException;

public class Manager extends People {
	private HashMap<String, Boolean> accessList;
	private ArrayList<String> laboratoriesBulstatList;

	public Manager(String username, String email, String password)
			throws InvalidEmailException, InvalidUserNameException {
		super(username, email, password);
		this.accessList = new HashMap<>();
		this.laboratoriesBulstatList = new ArrayList<>();
	}

	public Boolean getAccess(String function) {
		return this.accessList.get(function);
	}

	public void setAccess(String function, Boolean state) {
		this.accessList.put(function, state);
	}

}
