package mainObjects;

import exceptions.InvalidEgnException;
import exceptions.InvalidEmailException;
import exceptions.InvalidUserNameException;

public class Patient extends Person {

	public Patient(String username, String email, String password,String egn) throws InvalidEmailException, InvalidUserNameException, InvalidEgnException {
		super(username, email, password,egn);
		// TODO Auto-generated constructor stub
	}

}
