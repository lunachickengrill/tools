package com.genericinventory.domain.dataobject;

import java.util.Optional;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.genericinventory.domain.objecttype.AttributeType;
import com.genericinventory.domain.shared.Attribute;
import com.genericinventory.domain.shared.AttributeId;
import com.genericinventory.domain.shared.Value;

public final class AttributeValue implements Attribute<AttributeValue> {

	private AttributeId attributeId;
	private Value value;
	private AttributeType attributeType;

	// TODO eventuell Builder besser?!
	// nein!!

	// not to use Optional
	// in the domain model layer (not serializable)
	// in DTOs (same reason)
	// in input parameters of methods
	// in constructor parameters

	private AttributeValue(final AttributeId attributeId, final Value value) {
		Validate.notNull(attributeId, "AttributeId is required");
		this.attributeId = attributeId;
		this.value = value;
	}

	private AttributeValue(final AttributeId attributeId, final Value value, final AttributeType attributeType) {
		Validate.notNull(attributeId, "AttributeId is required");
		Validate.notNull(value, "Value is required");
		Validate.notNull(attributeType, "AttributeType is required");
		this.attributeId = attributeId;
		this.value = value;
		this.attributeType = attributeType;
	}

	private AttributeValue(final AttributeId attributeId, final AttributeType attributeType) {
		Validate.notNull(attributeId, "AttributeId is required");
		Validate.notNull(attributeType, "AttributeType is required");
		this.attributeId = attributeId;
		this.attributeType = attributeType;
	}

	public static AttributeValue createAttributeValue(final AttributeId attributeId, final Value value) {
		return new AttributeValue(attributeId, value);
	}

	public static AttributeValue createEmptyAttributeValue(final AttributeId attributeId,
			final AttributeType attributeType) {
		return new AttributeValue(attributeId, attributeType);
	}

	public final AttributeId getAttributeId() {
		return attributeId;
	}

	public final Value getValue() {
		if (value == null)
			return new Value("n/a");
		return value;
	}

	public final AttributeType getAttributeType() {
		return attributeType;
	}

	@Override
	public boolean sameAttributeAs(AttributeValue other) {
		return other != null && attributeId.equals(other.attributeId);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(attributeId).build();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		final AttributeValue other = (AttributeValue) obj;
		// return new EqualsBuilder().append(attributeId,
		// other.attributeId).build();
		return sameAttributeAs(other);
	}

	@Override
	public String toString() {
		return "AttributeValue [attributeId=" + attributeId + ", value=" + value + "]";
	}

}
