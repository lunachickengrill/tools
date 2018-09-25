package com.addressmanagement.domain.model.shared;

import java.io.Serializable;

public interface EntityObject<T> extends Serializable {

	/**
	 * Entities compare by identity, not by attributes.
	 *
	 * @param other
	 *            The other entity.
	 * @return true if the identities are the same, regardles of other
	 *         attributes.
	 */
	boolean sameIdentityAs(T other);

}
