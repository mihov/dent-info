package exceptions;

public class InvalidEgnException extends Throwable{
	
	@Override
	public String getMessage() {
		return "Invalid EGN!";
	}

}
