package eu.vrtime.vrm.domain.exceptions;

public class SessionManagerNotFoundException extends RuntimeException {

	public SessionManagerNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public SessionManagerNotFoundException(String message) {
		super(message);
	}

	public SessionManagerNotFoundException(Throwable cause) {
		super(cause);
	}

	public SessionManagerNotFoundException() {
		super();
	}
}
