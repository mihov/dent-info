package model.mainObjects;

import model.exceptions.EmptyNameException;
import model.exceptions.InvalidEgnException;
import model.exceptions.InvalidEmailException;
import model.exceptions.InvalidPhoneException;
import model.exceptions.InvalidUserNameException;
import model.inTheLab.DentalLaboratory;
import model.inTheLab.LaboratoryManager;
import model.tools.EGNValidator;
import model.tools.EmailValidator;
import model.tools.PassMD5;
import model.tools.PhoneValidator;

public abstract class Person {

	/*
	 * user_id password fk_user_type_id fk_dentist_id email address phone egn
	 * first_name last_name lab_id
	 */

	private int user_id;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String egn;
	private String address;
	private String phone;
	private Integer fk_user_type_id;
	private Integer fk_dentist_id;
	private Integer lab_id;

	private DentalLaboratory currentLab;

	public Person(String email, String password, int user_id) throws InvalidEmailException, InvalidUserNameException {
		this.setEmail(email);
		this.setPassword(password);
		this.setUserId(user_id);
		this.user_id = user_id;
	}

	public Person(int user_id, String email, String password, String firstName, String lastName, String egn,
			String address, String phone, Integer fk_user_type_id, Integer fk_dentist_id, Integer lab_id) {
		this.user_id = user_id;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.egn = egn;
		this.address = address;
		this.phone = phone;
		this.fk_user_type_id = fk_user_type_id;
		this.fk_dentist_id = fk_dentist_id;
		this.lab_id = lab_id;
	}

	public void setUserId(int user_id) {
		this.user_id = user_id;
	}

	public int getUser_id() {
		return user_id;
	}

	/**
	 * Sets the persons egn
	 * 
	 * @param egn
	 * @throws InvalidEgnException
	 */
	public void setEgn(String egn) {
		this.egn = egn;
	}

	/**
	 * Sets the email if it meets the requirements for an email
	 * 
	 * @param email
	 * @throws InvalidEmailException
	 */
	public void setEmail(String email) throws InvalidEmailException {
		if (new EmailValidator().validate(email)) {
			this.email = email;
		} else {
			throw new InvalidEmailException(email);
		}
	}

	/**
	 * Sets current password
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Returns the email address
	 * 
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Returns the first phone number
	 * 
	 * @return the phone1
	 */
	public String getPhone() {
		if (this.phone != null) {
			return phone;
		} else {
			System.out.println("There is no given phone number!");
			return null;
		}
	}

	/**
	 * Sets first phone number
	 * 
	 * @param phone1
	 */
	public void setPhone(String phone) {
		if (new PhoneValidator().validate(phone)) {
			this.phone = phone;
		} else {
			try {
				throw new InvalidPhoneException(phone);
			} catch (InvalidPhoneException e) {
				this.phone = null;
				e.getMessage();
			}
		}
	}

	/**
	 * Returns the adress
	 * 
	 * @return
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Changes the current adress with the new one
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		if (address == null) {
			System.out.println("Invalid adress!");
			return;
		}
		this.address = address;
	}

	/**
	 * Checks if the given password is the same as the persons password
	 * 
	 * @param password
	 * @return
	 */
	public boolean comparePassword(String password) {
		return this.password.equals(PassMD5.convert(password));
	}

	public String getPassword() {
		return this.password;
	}

	/**
	 * Returns the persons EGN number
	 * 
	 * @return
	 */
	public String getEgn() {
		return egn;
	}

	public String getFirstName() {
		if (this.firstName == null) {
			System.out.println("You have not entered a first name!");
		}
		return firstName;
	}

	public void setFirstName(String firstName) {
		if (firstName != null && !firstName.isEmpty()) {
			this.firstName = firstName;
		} else {
			this.firstName = "";
		}
	}

	public String getLastName() {
		if (this.lastName == null) {
			System.out.println("You have not entered a last name!");
		}
		return lastName;
	}

	@SuppressWarnings("unused")
	public void setLastName(String lastName) {
		if (lastName != null && !lastName.isEmpty()) {
			this.lastName = lastName;
		} else {
			this.lastName = "";
		}
	}

	/**
	 * Overrided toString to show username,email,name,Egn,Adress
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (getEmail() != null)
			builder.append(getEmail()).append("\t|\t");
		if (getPassword() != null)
			builder.append(getPassword()).append("\t|\t");
		if (getLab_id() != null)
			builder.append(getLab_id()).append("\t|\t");
		if (currentLab != null)
			builder.append(currentLab.getName()).append("\t|\t");
		if (getEgn() != null)
			builder.append(getEgn()).append("\t|\t");
		if (getAddress() != null)
			builder.append(getAddress());
		builder.append("\n");
		return builder.toString();
	}

	/**
	 * Returns the current dental laboratory
	 * 
	 * @return the currentLab
	 */
	public DentalLaboratory getCurrentLab() {
		return currentLab;
	}

	/**
	 * Sets the current lab
	 * 
	 * @param currentLab
	 *            the currentLab to set
	 */
	public void setCurrentLab(DentalLaboratory currentLab) {
		if (currentLab == null) {
			System.out.println("Error with the lab you have added!");
			return;
		}
		this.currentLab = currentLab;
	}

	public Integer getFk_user_type_id() {
		return fk_user_type_id;
	}

	public Integer getFk_dentist_id() {
		return fk_dentist_id;
	}

	public Integer getLab_id() {
		return lab_id;
	}

}
