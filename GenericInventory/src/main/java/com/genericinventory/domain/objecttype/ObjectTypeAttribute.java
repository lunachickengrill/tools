package com.genericinventory.domain.objecttype;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.genericinventory.domain.shared.DomainServiceException;
import com.genericinventory.domain.shared.Identity;

@Entity
@Table(name = "ATTRIBUTE")
public class ObjectTypeAttribute implements Serializable, Identity<ObjectTypeAttribute> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ATTRIBUTE_ID")
	private Long id;

	@ManyToOne(targetEntity = ObjectType.class, fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "OBJECTTYPE_ID", unique = false, nullable = true, updatable = true)
	private ObjectType objectType;

	@Column
	private String name;

	@Enumerated(EnumType.STRING)
	private AttributeType attributeType;

	private ObjectTypeAttribute(final ObjectType objectType, final String name, final AttributeType attributeType) {
		if (objectType.getObjectClass().equals(ObjectClass.SYSTEM))
			throw new DomainServiceException("System Class must not have attributes");
		this.objectType = objectType;
		this.name = name;
		this.attributeType = attributeType;
	}
	
	@Version
	private Long version;

	public static ObjectTypeAttribute createPrimaryAttribute(ObjectType objectType, String name) {
		return new ObjectTypeAttribute(objectType, name, AttributeType.PRIMARY);
	}

	public static ObjectTypeAttribute createRequiredAttribute(ObjectType objectType, String name) {
		return new ObjectTypeAttribute(objectType, name, AttributeType.REQUIRED);
	}

	public static ObjectTypeAttribute createMultipleAttribute(ObjectType objectType, String name) {
		return new ObjectTypeAttribute(objectType, name, AttributeType.MULTIPLE);
	}

	public static ObjectTypeAttribute createOptionalAttribute(ObjectType objectType, String name) {
		return new ObjectTypeAttribute(objectType, name, AttributeType.OPTIONAL);

	}

	public static ObjectTypeAttribute createAttribute(ObjectType objectType, String name, AttributeType attributeType) {
		return new ObjectTypeAttribute(objectType, name, attributeType);
	}

	public final Long getId() {
		return id;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final ObjectType getObjectType() {
		return objectType;
	}

	public final AttributeType getAttributeType() {
		return attributeType;
	}

	public void setObjectType(ObjectType objectType) {
		this.objectType = objectType;
		if (!objectType.getAttributeList().contains(this)) {
			objectType.getAttributeList().add(this);
		}
	}

	@Override
	public boolean sameIdentityAs(ObjectTypeAttribute otherEntity) {
		return otherEntity != null && id.equals(otherEntity.id);
	}

	@Override
	public String toString() {
		return "ObjectTypeAttribute [id=" + id + ", objectType=" + objectType + ", name=" + name + ", attributeType="
				+ attributeType + "]";
	}

	ObjectTypeAttribute() {
	}

}
