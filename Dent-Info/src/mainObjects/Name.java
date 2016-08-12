package mainObjects;

public class Name {
	private String firstName;
	private String middleName;
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
