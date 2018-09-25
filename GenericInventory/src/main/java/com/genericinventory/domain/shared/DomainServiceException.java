package com.genericinventory.domain.shared;

import java.io.Serializable;

public final class DomainServiceException extends RuntimeException implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7538058742971192229L;

	public DomainServiceException(String msg) {
		super(msg);
	}

	public DomainServiceException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
