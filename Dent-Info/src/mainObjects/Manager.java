package mainObjects;

import exceptions.InvalidEmailException;
import exceptions.InvalidUserNameException;

public class Manager extends People {

	public Manager(String username, String email, String password)
			throws InvalidEmailException, InvalidUserNameException {
		super(username, email, password);
		// TODO Auto-generated constructor stub
	}

}
