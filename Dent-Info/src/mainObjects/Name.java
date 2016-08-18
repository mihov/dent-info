package mainObjects;

import exceptions.EmptyNameException;

public class Name {
	private String firstName;
	private String middleName;
	private String lastName;

	public Name(String firstName, String middleName, String lastName) {
		this.setFirstName(firstName);
		this.setMiddleName(middleName);
		this.setLastName(lastName);

		if (this.firstName.length() == 0 || this.lastName.length() == 0) {
			throw new EmptyNameException();
		}
	}

	public String getFirstName() {
		return firstName;
	}

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

	public void setLastName(String lastName) {
		if (lastName != null && lastName.length() > 0) {
			this.lastName = lastName;
		} else {
			this.lastName = "";
		}
	}

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
