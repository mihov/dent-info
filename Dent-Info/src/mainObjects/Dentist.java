package mainObjects;

import exceptions.InvalidEmailException;
import exceptions.InvalidUserNameException;

public class Dentist extends People {

	public Dentist(String username, String email, String password)
			throws InvalidEmailException, InvalidUserNameException {
		super(username, email, password);
		// TODO Auto-generated constructor stub
	}

}
