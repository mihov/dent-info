package mainObjects;

import exceptions.InvalidEmailException;
import exceptions.InvalidUserNameException;

public class Patient extends People {

	public Patient(String username, String email, String password) throws InvalidEmailException, InvalidUserNameException {
		super(username, email, password);
		// TODO Auto-generated constructor stub
	}

}
