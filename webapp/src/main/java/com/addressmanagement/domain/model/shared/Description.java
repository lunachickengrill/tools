package com.addressmanagement.domain.model.shared;

import java.io.Serializable;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.Validate;

@Embeddable
public class Description implements ValueObject<Description>, Serializable {

	private static final long serialVersionUID = 1L;
	private String description;

	private Description() {

	}

	public Description(String description) {
		Validate.notNull(description, "Description is required");
		this.description = description;
	}

	@Override
	public boolean sameValueAs(Description other) {
		return other != null && this.description.equals(other.description);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || !(obj instanceof Description))
			return false;
		final Description other = (Description) obj;
		return sameValueAs(other);
	}

	@Override
	public int hashCode() {
		return this.description.hashCode();
	}

	@Override
	public String toString() {
		return this.description;
	}

}
