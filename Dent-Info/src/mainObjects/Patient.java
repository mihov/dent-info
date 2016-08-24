package mainObjects;

import exceptions.InvalidEgnException;
import exceptions.InvalidEmailException;
import exceptions.InvalidUserNameException;

public class Patient extends Person {

	public Patient(String username, String email, String password) throws InvalidEmailException, InvalidUserNameException{
		super(username, email, password);
		// TODO Auto-generated constructor stub
	}

}
