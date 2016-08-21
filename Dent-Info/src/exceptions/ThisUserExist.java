package exceptions;

public class ThisUserExist extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4179527998836418095L;
	private String user;

	public ThisUserExist(String user) {
		this.user = user;
	}

	@Override
	public String getMessage() {

		return "The user |" + this.user + "| already exist!";
	}
}
