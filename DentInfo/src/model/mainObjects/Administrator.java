package model.mainObjects;

import model.exceptions.InvalidEmailException;
import model.exceptions.InvalidUserNameException;

public class Administrator extends Person {

	public Administrator(String email, String password)throws InvalidEmailException, InvalidUserNameException{
		super(email, password);
		// TODO Auto-generated constructor stub
	}

}
