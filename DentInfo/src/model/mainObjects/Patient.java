package model.mainObjects;

import model.exceptions.InvalidEmailException;
import model.exceptions.InvalidUserNameException;

public class Patient extends Person {

	public Patient(String email, String password, int user_id) throws InvalidEmailException, InvalidUserNameException {
		super(email, password, user_id);
		// TODO Auto-generated constructor stub
	}

	public Patient(int user_id, String email, String password, String firstName, String lastName, String egn,
			String address, String phone, Integer fk_user_type_id, Integer fk_dentist_id, Integer lab_id) {
		super(user_id, email, password, firstName, lastName, egn, address, phone, fk_user_type_id, fk_dentist_id,
				lab_id);
	}

}
