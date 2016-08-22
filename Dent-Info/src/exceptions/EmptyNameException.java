package exceptions;

public class EmptyNameException extends Throwable {

	private static final long serialVersionUID = -7979332863456621885L;
	
	@Override
	public String getMessage() {
		return "You have entered zero length name.";
	}

}
