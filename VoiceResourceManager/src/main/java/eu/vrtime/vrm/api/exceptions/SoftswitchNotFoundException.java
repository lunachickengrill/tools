package eu.vrtime.vrm.api.exceptions;

public class SoftswitchNotFoundException extends StatusCodeException {

	private static final long serialVersionUID = -610801831978456983L;

	public SoftswitchNotFoundException(String message) {
		super(message);
	}

}