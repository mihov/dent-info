package exceptions;

public class InvalidPhoneException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	public InvalidPhoneException(String msg) {
	
		this.msg = msg;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Invalid phone: " + msg;
	}
	
	
	
	



}
