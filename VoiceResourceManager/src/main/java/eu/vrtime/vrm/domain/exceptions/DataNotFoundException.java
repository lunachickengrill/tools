package eu.vrtime.vrm.domain.exceptions;

public class DataNotFoundException extends RuntimeException {

	public DataNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataNotFoundException(String message) {
		super(message);
	}

	public DataNotFoundException(Throwable cause) {
		super(cause);
	}

	public DataNotFoundException() {
		super();
	}
}
