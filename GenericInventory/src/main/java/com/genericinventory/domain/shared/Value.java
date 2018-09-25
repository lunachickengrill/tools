package com.genericinventory.domain.shared;

import java.io.Serializable;

import javax.persistence.Embeddable;

import org.apache.commons.lang3.Validate;

@Embeddable
public final class Value implements ValueObject<Value>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1375839525894783550L;
	private String value;

	public Value(final String value) {
		Validate.notNull(value, "Value is required");
		this.value = value;
	}

	public final String getValue() {
		return value;
	}

	@Override
	public boolean sameValueAs(final Value other) {
		return other != null && this.value.equals(other.value);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		final Value other = (Value) obj;
		return sameValueAs(other);
	}

	@Override
	public String toString() {
		return "Value [value=" + value + "]";
	}

	Value() {
	}

}
