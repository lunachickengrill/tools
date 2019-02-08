package eu.vrtime.vrm.api.exceptions;

public class VoiceServiceNotFoundException extends StatusCodeException {

	private static final long serialVersionUID = -7342833547196604798L;

	public VoiceServiceNotFoundException(String message) {
		super(message);
	}

}