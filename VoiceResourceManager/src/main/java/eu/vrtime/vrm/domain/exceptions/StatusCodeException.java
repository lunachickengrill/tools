package eu.vrtime.vrm.domain.exceptions;

import org.apache.commons.lang3.StringUtils;

import static org.apache.commons.lang3.StringUtils.join;
import static org.apache.commons.lang3.StringUtils.splitByCharacterTypeCamelCase;

/**
 * TMNGx exception that carries a status code string.
 *
 * <p>
 * The status code of sub classes is determined automatically by naming
 * convention: CustomerNotFoundException -> CUSTOMER_NOT_FOUND.
 *
 * @author Robert Handschmann
 */

public class StatusCodeException extends RuntimeException {

	private static final long serialVersionUID = 1279308157484717086L;

	private String statusCode;

	public StatusCodeException() {
		this(null, null);
	}

	public StatusCodeException(String message) {
		this(message, null);
	}

	public StatusCodeException(String message, Throwable cause) {
		super(message, cause);
		this.statusCode = determineStatusCode();
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	protected final String determineStatusCode() {
		return determineStatusCode(getClass());
	}

	public static String determineStatusCode(Class<? extends StatusCodeException> ec) {
		String base = StringUtils.removeEnd(ec.getSimpleName(), "Exception");
		return join(splitByCharacterTypeCamelCase(base), '_').toUpperCase();
	}

}
