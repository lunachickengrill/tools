package eu.vrtime.vrm.api.exceptions;

public class IllegalStateException extends StatusCodeException {

	private static final long serialVersionUID = 5989420715745711378L;

	public IllegalStateException(String message) {
		super(message);
	}

}