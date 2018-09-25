package com.genericinventory.domain.objecttype;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.genericinventory.domain.shared.ValueObject;

@Embeddable
public final class ObjectTypeStructure implements ValueObject<ObjectTypeStructure>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2323785350185962046L;

	@Column
	private Long relatedTypeId;

	@Column
	private Long relatedClassId;

	@Enumerated(EnumType.STRING)
	private ObjectClass objectClass;

	@Enumerated(EnumType.STRING)
	private Geometry geometry;

	private ObjectTypeStructure(final Long relatedTypeId, final Long relatedClassId, final ObjectClass objectClass,
			final Geometry geometry) {
		Validate.notNull(relatedTypeId, "Related ObjectTypeId is required");
		Validate.notNull(relatedClassId, "Related ClassId is required");
		Validate.notNull(objectClass, "Related ObjectClass is required");
		Validate.notNull(geometry, "Geometry is required");
		this.relatedTypeId = relatedTypeId;
		this.relatedClassId = relatedClassId;
		this.objectClass = objectClass;
		this.geometry = geometry;
	}

	public static ObjectTypeStructure createHorizontalRelation(final ObjectType objectType) {
		Validate.notNull(objectType, "ObjectType is required");
		return new ObjectTypeStructure(objectType.getId(), objectType.getObjectClassId(), objectType.getObjectClass(),
				Geometry.HORIZONTAL);
	}

	public static ObjectTypeStructure createVerticalRelation(final ObjectType objectType) {
		Validate.notNull(objectType, "ObjectType is required");
		return new ObjectTypeStructure(objectType.getId(), objectType.getObjectClassId(), objectType.getObjectClass(),
				Geometry.VERTICAL);
	}

	public final Long getRelatedClassId() {
		return relatedClassId;
	}

	public final Long getRelatedTypeId() {
		return relatedTypeId;
	}

	public final ObjectClass getObjectClass() {
		return objectClass;
	}

	public final Geometry getGeometry() {
		return geometry;
	}

	@Override
	public boolean sameValueAs(ObjectTypeStructure other) {
		return other != null && new EqualsBuilder().append(relatedTypeId, other.relatedTypeId)
				.append(relatedClassId, other.relatedClassId).append(objectClass, other.objectClass)
				.append(geometry, other.geometry).build();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(relatedTypeId).append(relatedClassId).append(objectClass).append(geometry)
				.build();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		final ObjectTypeStructure other = (ObjectTypeStructure) obj;
		return sameValueAs(other);
	}

	@Override
	public String toString() {
		return "ObjectTypeStructure [relatedTypeId=" + relatedTypeId + ", relatedClassId=" + relatedClassId
				+ ", objectClass=" + objectClass + ", geometry=" + geometry + "]";
	}

	ObjectTypeStructure() {

	}

}
