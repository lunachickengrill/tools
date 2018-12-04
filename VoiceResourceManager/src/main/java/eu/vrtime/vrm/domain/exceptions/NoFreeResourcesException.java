package eu.vrtime.vrm.domain.exceptions;

public class NoFreeResourcesException extends StatusCodeException {

	private static final long serialVersionUID = -7870183804670195906L;

	public NoFreeResourcesException(String message) {
		super(message);
	}

}
