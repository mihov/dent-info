package mainObjects;

import exceptions.EmptyNameException;
import exceptions.InvalidEgnException;
import exceptions.InvalidEmailException;
import exceptions.InvalidPhoneException;
import exceptions.InvalidUserNameException;
import inTheLab.DentalLaboratory;
import tools.EGNValidator;
import tools.EmailValidator;
import tools.PhoneValidator;

public abstract class Person {
	private String username;
	private String email;
	private String password;
	private Name name;
	private String egn;
	private Address address;
	private String phone1;
	private String phone2;
	private DentalLaboratory currentLab;

	public Person(String username, String email, String password)throws InvalidEmailException, InvalidUserNameException{
		this.setUsername(username);
		this.setEmail(email);
		this.setPassword(password);
		this.name = new Name();
		this.address = new Address();
	}
	
	/**
	 *Sets the persons egn 
	 * @param egn
	 * @throws InvalidEgnException
	 */
	private void setEgn(String egn)throws InvalidEgnException{
		if(new EGNValidator().containsTenDigits(egn)){
			this.egn = egn;
		}
		else{
			throw new InvalidEgnException();
		}
	}
	
	
	/**
	 * Sets the persons username if it has the needed length
	 * @param username
	 * @throws InvalidUserNameException
	 */
	private void setUsername(String username) throws InvalidUserNameException {
		if (username.length() > 4) {
			this.username = username;
		} else {
			throw new InvalidUserNameException(username);
		}
	}
	
	/**
	 * Sets the email if it meets the requirements for an email
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
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Returns the email address
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Returns the first phone number
	 * @return the phone1
	 */
	public String getPhone1() {
		if(this.phone1 != null){
			return phone1;
		}
		else{
			System.out.println("There is no given phone number!");
			return null;
		}
	}

	/**
	 *Sets first phone number
	 * @param phone1
	 */
	public void setPhone1(String phone) {
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
	 * Returns second phone number
	 * @return the phone2
	 */
	public String getPhone2() {
		if(this.phone2 != null){
			return phone2;
		}
		else{
			System.out.println("There is no given phone number!");
			return null;
		}
	}

	/**
	 * Sets second phone number
	 * @param phone2
	 */
	public void setPhone2(String phone) {
		if (new PhoneValidator().validate(phone)) {
			this.phone2 = phone;
		} else {
			try {
				throw new InvalidPhoneException(phone);
			} catch (InvalidPhoneException e) {
				this.phone2 = null;
				e.getMessage();
			}
		}
	}
	
	/**
	 * Returns the adress
	 * @return
	 */
	public Address getAddress() {
		return address;
	}
	
	/**
	 * Changes the current adress with the new one
	 * @param address
	 */
	public void setAddress(Address address) {
		if(address == null){
			System.out.println("Invalid adress!");
			return;
		}
		this.address = address;
	}

	/**
	 * returns the current persons username
	 * @return
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * returns the persons name
	 * @return
	 */
	public Name getName() {
		return name;
	}

	/**
	 * Changes the name of the person if the name is not null
	 * @param name
	 */
	public void setName(Name name)throws EmptyNameException {
		if(name == null){
				throw new EmptyNameException();
		}
		this.name = name;
	}

	/**
	 * Checks if the given password is the same as the persons password
	 * @param password
	 * @return
	 */
	public boolean comparePassword(String password) {
		return this.password.equals(password);
	}

	/**
	 * Returns the persons EGN number
	 * @return
	 */
	public String getEgn() {
		return egn;
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
		if (getName() != null)
			builder.append(getName()).append("\t|\t");
		if (getEgn() != null)
			builder.append(getEgn()).append("\t|\t");
		if (getAddress() != null)
			builder.append(getAddress());
		builder.append("\n");
		return builder.toString();
	}

	/**
	 * Returns the current dental laboratory
	 * @return the currentLab
	 */
	public DentalLaboratory getCurrentLab() {
		return currentLab;
	}

	/**
	 * Sets the current lab
	 * @param currentLab the currentLab to set
	 */
	public void setCurrentLab(DentalLaboratory currentLab) {
		if(currentLab == null){
			System.out.println("Error with the lab you have added!");
			return;
		}
		this.currentLab = currentLab;
	}

	private class Name {
		private String firstName;
		private String middleName;
		private String lastName;

		public Name() {

		}

		public String getFirstName() {
			if(this.firstName == null){
				System.out.println("You have not entered a first name!");
			}
			return firstName;
		}

		@SuppressWarnings("unused")
		public void setFirstName(String firstName) {
			if (firstName != null && !firstName.isEmpty()) {
				this.firstName = firstName;
			} 
			else {
				this.firstName = "";
			}
		}

		public String getMiddleName() {
			if(this.middleName == null){
				System.out.println("You have not entered a middle name!");
			}
			return middleName;
		}

		@SuppressWarnings("unused")
		public void setMiddleName(String middleName) {
			if (middleName != null && !middleName.isEmpty()) {
				this.middleName = middleName;
			} 
			else {
				this.middleName = "";
			}
		}

		public String getLastName() {
			if(this.lastName == null){
				System.out.println("You have not entered a last name!");
			}
			return lastName;
		}

		@SuppressWarnings("unused")
		public void setLastName(String lastName) {
			if (lastName != null && !lastName.isEmpty()) {
				this.lastName = lastName;
			} 
			else {
				this.lastName = "";
			}
		}

		@SuppressWarnings("unused")
		public String getFullName() {
			StringBuilder builder = new StringBuilder();
			builder.append(getFirstName());
			builder.append(" ");
			builder.append(getMiddleName());
			builder.append(" ");
			builder.append(getLastName());
			return builder.toString();
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append(getFirstName());
			builder.append(" ");
			builder.append(getMiddleName());
			builder.append(" ");
			builder.append(getLastName());
			return builder.toString();
		}

	}
}
