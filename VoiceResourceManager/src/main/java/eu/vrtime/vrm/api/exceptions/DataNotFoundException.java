package eu.vrtime.vrm.api.exceptions;

public class DataNotFoundException extends StatusCodeException {
	
    private static final long serialVersionUID = -8448294403120090367L;

    public DataNotFoundException(String message) {
        super(message);
    }


}