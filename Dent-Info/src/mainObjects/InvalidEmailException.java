package mainObjects;

public class InvalidEmailException extends IllegalArgumentException {
	private static final long serialVersionUID = 7533467833884101397L;
	private String email;

	public InvalidEmailException(String email) {
		this.email = email;
	}

	@Override
	public String getMessage() {

		return "Invalid e-mail: " + this.email;
	}

}
