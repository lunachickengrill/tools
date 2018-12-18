package eu.vrtime.vrm.api.exceptions;

public class ResourceNotFoundException extends StatusCodeException {

	private static final long serialVersionUID = 7067703867844020037L;

	public ResourceNotFoundException(String message) {
		super(message);
	}

}
