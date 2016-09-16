package model.mainObjects;

import model.exceptions.InvalidEmailException;
import model.exceptions.InvalidUserNameException;

public class Patient extends Person {

	public Patient(String email, String password) throws InvalidEmailException, InvalidUserNameException{
		super(email, password);
		// TODO Auto-generated constructor stub
	}

}
