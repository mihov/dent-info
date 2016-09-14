package model.mainObjects;

import model.exceptions.InvalidEmailException;
import model.exceptions.InvalidUserNameException;

public class Administrator extends Person {

	public Administrator(String username, String email, String password)throws InvalidEmailException, InvalidUserNameException{
		super(username, email, password);
		// TODO Auto-generated constructor stub
	}

}
