package com.genericinventory.domain.shared;

import org.apache.commons.lang3.Validate;

import com.genericinventory.domain.objecttype.AttributeType;

public final class AttributeSpecification implements Attribute<AttributeSpecification> {

	AttributeId attributeId;
	AttributeType attributeType;

	public AttributeSpecification(final AttributeId attributeId, final AttributeType attributeType) {
		Validate.notNull(attributeId, "AttributeId is required");
		Validate.notNull(attributeType, "AttributeType is required");
		this.attributeId = attributeId;
		this.attributeType = attributeType;
	}

	public boolean isPrimary(final AttributeId attributeId) {
		return (this.attributeId.sameValueAs(attributeId) && this.attributeType.equals(AttributeType.PRIMARY));
	}

	public final AttributeId getAttributeId() {
		return attributeId;
	}

	public final AttributeType getAttributeType() {
		return attributeType;
	}
	
	public final Long toIdLong() {
		return attributeId.getAttributeId();
	}

	@Override
	public boolean sameAttributeAs(final AttributeSpecification other) {
		return other != null && attributeId.equals(other.attributeId);
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
		final AttributeSpecification other = (AttributeSpecification) obj;
		return sameAttributeAs(other);
	}

	@Override
	public String toString() {
		return "AttributeSpecification [attributeId=" + attributeId + ", attributeType=" + attributeType + "]";
	}

}
