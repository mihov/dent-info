package exceptions;

public class InvalidPhoneException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2706205140129747451L;


	private String msg;
	public InvalidPhoneException(String msg) {
	
		this.msg = msg;
	}
	
	@Override
	public String getMessage() {
		return "Invalid phone: " + msg;
	}
	
	
	
	



}
