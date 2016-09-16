package model.mainObjects;

import model.exceptions.InvalidEmailException;
import model.exceptions.InvalidUserNameException;

public class Patient extends Person {

	public Patient(String email, String password,int user_id) throws InvalidEmailException, InvalidUserNameException{
		super(email, password,user_id);
		// TODO Auto-generated constructor stub
	}

}
