package com.genericinventory.domain.dataobject;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyClass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.apache.commons.lang3.Validate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.repository.query.ParametersParameterAccessor;

import com.genericinventory.domain.objecttype.AttributeType;
import com.genericinventory.domain.objecttype.Geometry;
import com.genericinventory.domain.objecttype.ObjectClass;
import com.genericinventory.domain.objecttype.ObjectType;
import com.genericinventory.domain.objecttype.ObjectTypeStructure;
import com.genericinventory.domain.shared.AttributeId;
import com.genericinventory.domain.shared.AttributeSpecification;
import com.genericinventory.domain.shared.CoreDomainException;
import com.genericinventory.domain.shared.Identity;
import com.genericinventory.domain.shared.Value;

@Entity
@Table(name = "OBJECT")
public class DataObject implements Identity<DataObject>, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "OBJECT_ID")
	private Long id;

	@ManyToOne(targetEntity = DataObject.class, cascade = CascadeType.REMOVE, optional = true)
	@JoinColumn(name = "PARENT_ID", unique = false, nullable = true, updatable = true)
	private DataObject parentId;

	@Column
	private Long objectTypeId;

	@Column
	private Long objectClassId;

	/**
	 * OneToMany primary values
	 */

	@ElementCollection(fetch = FetchType.EAGER)
	@MapKeyClass(AttributeId.class)
	@CollectionTable(name = "OBJECT_PARAM_PRIMARY", joinColumns = @JoinColumn(name = "OBJECT_ID"))
	private Map<AttributeId, Value> primaryParams = new HashMap<AttributeId, Value>();

	/**
	 * OneToMany values
	 **/

	@ElementCollection(fetch = FetchType.EAGER)
	@MapKeyClass(AttributeId.class)
	@CollectionTable(name = "OBJECT_PARAM", joinColumns = @JoinColumn(name = "OBJECT_ID"))
	private Map<AttributeId, Value> params = new HashMap<AttributeId, Value>();

	/**
	 * ManyToMany self reference
	 **/

	@JoinTable(name = "OBJECT_REFERENCES", joinColumns = {
			@JoinColumn(name = "OBJECT_ID", referencedColumnName = "OBJECT_ID", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "REFERENCED_OBJECT_ID", referencedColumnName = "OBJECT_ID", nullable = false) })
	@ManyToMany
	private Collection<DataObject> references;

	@Column
	private String name;

	@Version
	private Long version;

	@Transient
	private Set<AttributeSpecification> attributeSpecs;

	@Transient
	private Set<ObjectTypeStructure> structure;

	/**
	 * Constructor
	 * 
	 * @param name
	 */

	private DataObject(final Long objectTypeId, final Long objectClassId, final String name,
			final Set<AttributeSpecification> specs, final Set<ObjectTypeStructure> structure) {
		Validate.notNull(objectTypeId, "ObjectTypeId is required");
		Validate.notNull(objectClassId, "ObjectClassId is required");
		Validate.notEmpty(name, "Name must not be null or zero length");
		Validate.noNullElements(specs, "specifications are required");
		this.objectTypeId = objectTypeId;
		this.objectClassId = objectClassId;
		this.name = name;
		this.attributeSpecs = specs;
		this.structure = structure;
	}

	public static DataObject createDataObject(final ObjectType objectType,
			final Set<AttributeSpecification> specifications, final Set<ObjectTypeStructure> structure) {
		if (!(objectType.getObjectClass().equals(ObjectClass.CLASS)))
			throw new CoreDomainException("Cannot instantiate other ObjectClass than ObjectClass.CLASS");

		return new DataObject(objectType.getId(), objectType.getObjectClassId(), objectType.getName(), specifications,
				structure);
	}

	@PrePersist
	@PreUpdate
	void validateObject() {
		ObjectValidator<DataObject> attrValidator = new AttributeValidator();
		ObjectValidator<DataObject> verticalValidator = new VerticalObjectValidator();
		if (attrValidator.isValid(this) != true)
			throw new CoreDomainException("Attribute validation failed");

		if (verticalValidator.isValid(this) != true)
			throw new CoreDomainException("Vertical validation rule violated" + " " + this.getId());
		// validateStructure();
	}

//	void validateStructure() {
//		Set<Long> vertical = new HashSet<Long>();
//		Set<Long> horizontal = new HashSet<Long>();
//		structure.stream().filter(s -> s.getGeometry() == Geometry.VERTICAL).filter(Objects::nonNull)
//				.forEach(p -> vertical.add(p.getRelatedTypeId()));
//		structure.stream().filter(s -> s.getGeometry() == Geometry.HORIZONTAL).filter(Objects::nonNull)
//				.forEach(p -> horizontal.add(p.getRelatedTypeId()));
//
//		if (!(parentId == null)) {
//			if (vertical != null && (!(vertical.isEmpty()))) {
//				if (!(vertical.contains(parentId.getObjectTypeId())))
//					throw new CoreDomainException(
//							"Vertical validation rule violated" + " " + parentId.getObjectTypeId());
//			}
//		}
//
//		if (!(references == null)) {
//			if (horizontal != null && (!(horizontal.isEmpty()))) {
//				if (!(horizontal.contains(references)))
//					throw new CoreDomainException("Horizontal validation rule violated");
//			}
//		}
//
//	}

	public final Long getId() {
		return id;
	}

	public final DataObject getParentId() {
		return parentId;
	}

	public final Long getObjectTypeId() {
		return objectTypeId;
	}

	public final Long getObjectClassId() {
		return objectClassId;
	}

	public final Set<AttributeSpecification> getAttributeSpecs() {
		return attributeSpecs;
	}

	public final void setAttributeSpecs(Set<AttributeSpecification> attributeSpecs) {
		this.attributeSpecs = attributeSpecs;
	}

	public final Set<ObjectTypeStructure> getStructure() {
		return structure;
	}

	public final void setStructure(Set<ObjectTypeStructure> structure) {
		this.structure = structure;
	}

	public void adjunctToParent(final DataObject parent) {

		// TODO Rules
		parentId = parent;
	}

	public void addParam(final AttributeValue param) {

		if (param.getValue() == null) {
			removeParam(param);
		}
		if (param.getAttributeId() != null) {
			params.put(param.getAttributeId(), param.getValue());
		}
	}

	public final Set<AttributeValue> getParams() {

		Set<AttributeValue> allParams = new HashSet<AttributeValue>();

		params.forEach((k, v) -> allParams.add(AttributeValue.createAttributeValue(k, v)));
		primaryParams.forEach((k, v) -> allParams.add(AttributeValue.createAttributeValue(k, v)));
		attributeSpecs.forEach(
				p -> allParams.add(AttributeValue.createEmptyAttributeValue(p.getAttributeId(), p.getAttributeType())));

		return allParams;
	}

	public DataObject setAttributeValues(Set<AttributeValue> attributeValues) {
		Validate.noNullElements(attributeValues, "AttributeValues contains null elements");
		for (AttributeValue a : attributeValues) {
			for (AttributeSpecification s : attributeSpecs) {
				if (a.getAttributeId().equals(s.getAttributeId()) && s.isPrimary(a.getAttributeId())) {
					setPrimaryAttributeValue(a);
				}
				if (a.getAttributeId().equals(s.getAttributeId()) && (!(s.isPrimary((a.getAttributeId()))))) {
					setAttributeValue(a);

				}
			}
		}
		return this;
	}

	private void setPrimaryAttributeValue(final AttributeValue attributeValue) {
		Value oldValue = primaryParams.putIfAbsent(attributeValue.getAttributeId(), attributeValue.getValue());
		if (oldValue != null) {
			primaryParams.replace(attributeValue.getAttributeId(), attributeValue.getValue());
		}

	}

	private void setAttributeValue(final AttributeValue attributeValue) {
		Value oldValue = params.putIfAbsent(attributeValue.getAttributeId(), attributeValue.getValue());
		if (oldValue != null) {
			params.replace(attributeValue.getAttributeId(), attributeValue.getValue());
		}
	}

	public void removeParam(final AttributeValue param) {
		params.remove(param.getAttributeId());
	}

	@Override
	public boolean sameIdentityAs(final DataObject otherEntity) {
		return otherEntity != null && id.equals(otherEntity.id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		final DataObject other = (DataObject) obj;
		return sameIdentityAs(other);
	}

	@Override
	public String toString() {
		return "DataObject [id=" + id + ", objectTypeId=" + objectTypeId + ", objectClassId=" + objectClassId
				+ ", name=" + name + "]";
	}

	DataObject() {

	}

}
