package eu.vrtime.restapi.exceptions;

public class BookNotFoundException extends RuntimeException {

	public BookNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public BookNotFoundException(String message) {
		super(message);
	}

	public BookNotFoundException(Throwable cause) {
		super(cause);
	}

	public BookNotFoundException() {
		super();
	}

}
