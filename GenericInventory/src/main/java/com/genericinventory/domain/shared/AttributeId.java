package com.genericinventory.domain.shared;

import java.io.Serializable;

import javax.persistence.Embeddable;

import org.apache.commons.lang3.Validate;

@Embeddable
public final class AttributeId implements ValueObject<AttributeId>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7881880818366144802L;
	private Long attributeId;

	public AttributeId(final Long id) {
		Validate.notNull(id, "AttributeId is required");
		this.attributeId = id;
	}

	public final Long getAttributeId() {
		return attributeId;
	}

	@Override
	public boolean sameValueAs(final AttributeId otherEntity) {
		return otherEntity != null && this.attributeId.equals(otherEntity.attributeId);
	}

	@Override
	public int hashCode() {
		return attributeId.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		final AttributeId other = (AttributeId) obj;
		return sameValueAs(other);
	}

	@Override
	public String toString() {
		return "AttributeId [attributeId=" + attributeId + "]";
	}

	AttributeId() {
	}

}
