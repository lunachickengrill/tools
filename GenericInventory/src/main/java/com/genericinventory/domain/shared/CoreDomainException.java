package com.genericinventory.domain.shared;

import java.io.Serializable;

public final class CoreDomainException extends RuntimeException implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4823630763070645762L;

	public CoreDomainException(String msg) {
		super(msg);
	}

	public CoreDomainException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
