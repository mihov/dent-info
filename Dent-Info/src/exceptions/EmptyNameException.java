package exceptions;

public class EmptyNameException extends IllegalArgumentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "You have entered zero length name.";
	}

}
