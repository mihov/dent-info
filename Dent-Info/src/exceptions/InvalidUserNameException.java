package exceptions;

public class InvalidUserNameException extends Exception {
	private String username;

	public InvalidUserNameException(String username) {
		this.username = username;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Username: " + this.username + " lenght is less than 4";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -3431691429542567082L;

}
