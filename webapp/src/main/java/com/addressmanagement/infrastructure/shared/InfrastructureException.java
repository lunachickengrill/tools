package com.addressmanagement.infrastructure.shared;

public final class InfrastructureException extends RuntimeException {

	private static final long serialVersionUID = -5074912393526160746L;

	public InfrastructureException() {
		super();
	}

	public InfrastructureException(String message, Throwable cause) {
		super(message, cause);
	}

	public InfrastructureException(String message) {
		super(message);
	}

	public InfrastructureException(Throwable cause) {
		super(cause);
	}

}
