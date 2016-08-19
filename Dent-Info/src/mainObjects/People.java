package mainObjects;

import exceptions.EmptyNameException;
import exceptions.InvalidEmailException;
import exceptions.InvalidUserNameException;
import tools.EmailValidator;

public abstract class People {
	private String username;
	private String email;
	private String password;
	private Name name;
	private String egn;
	private Address address;

	public People(String username, String email, String password) throws InvalidEmailException, InvalidUserNameException {
		this.setUsername(username);
		this.setEmail(email);
		this.setPassword(password);
		this.name = new Name();
		this.address = new Address();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws InvalidEmailException {

		EmailValidator emailValidator = new EmailValidator();
		if (emailValidator.validate(email)) {
			this.email = email;
		} else {
			throw new InvalidEmailException(email);
		}
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	private void setUsername(String username) throws InvalidUserNameException {
		if (username.length() > 4) {
			this.username = username;
		} else {
			throw new InvalidUserNameException(username);
		}
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEgn() {
		return egn;
	}

	public void setEgn(String egn) {
		this.egn = egn;
	}

	private class Name {
		private String firstName;
		private String middleName;
		private String lastName;

		public Name() {

		}

		public String getFirstName() {
			return firstName;
		}

		@SuppressWarnings("unused")
		public void setFirstName(String firstName) {
			if (firstName != null && firstName.length() > 0) {
				this.firstName = firstName;
			} else {
				this.firstName = "";
			}
		}

		public String getMiddleName() {
			return middleName;
		}

		@SuppressWarnings("unused")
		public void setMiddleName(String middleName) {
			if (middleName != null && middleName.length() > 0) {
				this.middleName = middleName;
			} else {
				this.middleName = "";
			}
		}

		public String getLastName() {
			return lastName;
		}

		@SuppressWarnings("unused")
		public void setLastName(String lastName) {
			if (lastName != null && lastName.length() > 0) {
				this.lastName = lastName;
			} else {
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
