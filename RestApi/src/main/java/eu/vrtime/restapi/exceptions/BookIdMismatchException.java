package eu.vrtime.restapi.exceptions;

public class BookIdMismatchException extends RuntimeException {

	public BookIdMismatchException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public BookIdMismatchException(String message) {
		super(message);
	}
	
	public BookIdMismatchException(Throwable cause) {
		super(cause);
	}
	
	public BookIdMismatchException() {
		super();
	}

}
