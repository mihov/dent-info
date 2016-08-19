package exceptions;

public class InvalidEmailException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2045484855560157616L;
	private String email;

	public InvalidEmailException(String email) {
		this.email = email;
	}

	@Override
	public String getMessage() {

		return "Invalid e-mail: " + this.email;
	}

}
