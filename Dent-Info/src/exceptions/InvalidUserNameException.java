package exceptions;

public class InvalidUserNameException extends Exception {
	private String username;
	private static final long serialVersionUID = -3431691429542567082L;

	public InvalidUserNameException(String username) {
		this.username = username;
	}

	@Override
	public String getMessage() {
		return "Username: " + this.username + " lenght is less than 4";
	}
}
