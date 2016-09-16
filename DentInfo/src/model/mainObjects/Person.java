package model.mainObjects;

import model.exceptions.EmptyNameException;
import model.exceptions.InvalidEgnException;
import model.exceptions.InvalidEmailException;
import model.exceptions.InvalidPhoneException;
import model.exceptions.InvalidUserNameException;
import model.inTheLab.DentalLaboratory;
import model.tools.EGNValidator;
import model.tools.EmailValidator;
import model.tools.PassMD5;
import model.tools.PhoneValidator;

public abstract class Person {
	private String username;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String egn;
	private String address;
	private String phone1;
	private DentalLaboratory currentLab;

	public Person(String username, String email, String password)
			throws InvalidEmailException, InvalidUserNameException {
		this.setUsername(username);
		this.setEmail(email);
		this.setPassword(password);
	}

	/**
	 * Sets the persons egn
	 * 
	 * @param egn
	 * @throws InvalidEgnException
	 */
	public void setEgn(String egn){
		this.egn = egn;
	}

	/**
	 * Sets the persons username if it has the needed length
	 * 
	 * @param username
	 * @throws InvalidUserNameException
	 */
	private void setUsername(String username) throws InvalidUserNameException {
		if (username.length() >= 1) {
			this.username = username;
		} else {
			throw new InvalidUserNameException(username);
		}
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
		if (this.phone1 != null) {
			return phone1;
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
			this.phone1 = phone;
		} else {
			try {
				throw new InvalidPhoneException(phone);
			} catch (InvalidPhoneException e) {
				this.phone1 = null;
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
	 * returns the current persons username
	 * 
	 * @return
	 */
	public String getUsername() {
		return username;
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
		if (getUsername() != null)
			builder.append(getUsername()).append("\t|\t");
		if (getEmail() != null)
			builder.append(getEmail()).append("\t|\t");
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
}
