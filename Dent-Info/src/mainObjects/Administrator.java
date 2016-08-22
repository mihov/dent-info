package mainObjects;

import exceptions.InvalidEgnException;
import exceptions.InvalidEmailException;
import exceptions.InvalidUserNameException;

public class Administrator extends Person {

	public Administrator(String username, String email, String password,String egn)
			throws InvalidEmailException, InvalidUserNameException, InvalidEgnException {
		super(username, email, password,egn);
		// TODO Auto-generated constructor stub
	}

}
