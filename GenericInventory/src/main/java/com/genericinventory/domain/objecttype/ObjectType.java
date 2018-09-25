package com.genericinventory.domain.objecttype;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.apache.commons.lang3.Validate;

import com.genericinventory.domain.shared.CoreDomainException;
import com.genericinventory.domain.shared.DomainServiceException;
import com.genericinventory.domain.shared.Identity;

/**
 * ObjectType Class, represents an entity in the metadata model. ParentId
 * references id of parent ObjectType. Attributes are referenced as a @OneToMany
 * collection of ObjectTypeAttribute objects.
 * 
 * @author tschwaiger
 *
 */

@Entity
@Table(name = "OBJECT_TYPE")
public class ObjectType implements Serializable, Identity<ObjectType> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "OBJECTTYPE_ID")
	private Long id;

	@Column
	private Long objectClassId = null;

	@Enumerated(EnumType.STRING)
	private ObjectClass objectClass = ObjectClass.INVALID;

	@Column
	private String name;

	@OneToMany(mappedBy = "objectType", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ObjectTypeAttribute> attributes = new ArrayList<ObjectTypeAttribute>();

	@ManyToOne(targetEntity = ObjectType.class, cascade = CascadeType.REMOVE, optional = true)
	@JoinColumn(name = "PARENT_ID", unique = false, nullable = true, updatable = true)
	private ObjectType parentId;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "OBJECTTYPE_STRUCT", joinColumns = { @JoinColumn(name = "OBJECTTYPE_ID") })
	private Set<ObjectTypeStructure> structure = new HashSet<ObjectTypeStructure>();

	@Version
	private Long version;

//	@Transient
//	MetaDataRule<ObjectType> classIdRule = new ObjectTypeClassIdRule();

	private ObjectType(final String name, final ObjectClass objectClass) {
		Validate.notBlank(name, "Name is required");
		this.name = name;
		this.objectClass = objectClass;
	}

	public static ObjectType createSystem(final String name) {
		return new ObjectType(name, ObjectClass.SYSTEM);
	}

	public static ObjectType createAbstract(final String name) {
		return new ObjectType(name, ObjectClass.ABSTRACT);
	}

	public static ObjectType createClass(final String name) {
		return new ObjectType(name, ObjectClass.CLASS);
	}

	/**
	 * Updates objectClassId automatically after persist or after update on this
	 * object.
	 */

	@PrePersist
	@PreUpdate
	@PostPersist
	private void setClassId() {
		MetaDataRule<ObjectType> rule = new ObjectTypeClassIdRule();
		this.objectClassId = rule.applyRule(this).getObjectClassId();
		// this.objectClassId = classIdRule.applyRule(this).getObjectClassId();
	}

	public final Long getId() {
		return id;
	}

	public final Long getParentLongId() {
		return parentId.getId();
	}

	public final ObjectType getParentId() {
		return parentId;
	}

	public final ObjectType getParent() {
		return parentId;
	}

	public final String getName() {
		return name;
	}

	public final Long getObjectClassId() {
		return objectClassId;
	}

	public final void setObjectClassId(Long objectClassId) {
		this.objectClassId = objectClassId;
	}

	public final ObjectClass getObjectClass() {
		return objectClass;
	}

	public void removeParent() {
		parentId = null;
	}

	public void adjunctToParent(final ObjectType parent) {

		if (objectClass.equals(ObjectClass.SYSTEM) && (!(parent.getObjectClass().equals(objectClass))))
			throw new DomainServiceException("Parent not allowed:" + "\n" + "This type: " + objectClass + "\n"
					+ "Parent type: " + parent.getObjectClass());
		if (objectClass.equals(ObjectClass.ABSTRACT) && parent.getObjectClass().equals(ObjectClass.CLASS))
			throw new DomainServiceException("Parent not allowed:" + "\n" + "This type: " + objectClass + "\n"
					+ "Parent type: " + parent.getObjectClass());
		if (objectClass.equals(ObjectClass.CLASS) && parent.getObjectClass().equals(objectClass))
			throw new DomainServiceException("Parent not allowed:" + "\n" + "This type: " + objectClass + "\n"
					+ "Parent type: " + parent.getObjectClass());
		parentId = parent;
	}

	public void createOrUpdateAttribute(final ObjectTypeAttribute attribute) {

		if (objectClass.equals(ObjectClass.SYSTEM))
			throw new CoreDomainException("System Class must not have attributes");

		this.attributes.add(attribute);
		if (attribute.getObjectType() != this) {
			attribute.setObjectType(this);
		}

	}

	public List<ObjectTypeAttribute> getAttributeList() {
		return attributes;
	}

	public void addStructure(final ObjectTypeStructure structure) {
		if ((!(this.objectClass.equals(ObjectClass.CLASS)))
				|| (!(structure.getObjectClass().equals(ObjectClass.CLASS))))
			throw new CoreDomainException("Structure not allowed");
		this.structure.add(structure);
	}

	public void removeStructure(final ObjectTypeStructure structure) {
		if (this.structure.contains(structure))
			this.structure.remove(structure);
	}

	public Set<ObjectTypeStructure> getStructureInformation() {
		return this.structure;
	}

	@Override
	public boolean sameIdentityAs(final ObjectType otherEntity) {
		return otherEntity != null && id.equals(otherEntity.id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		final ObjectType other = (ObjectType) obj;
		return sameIdentityAs(other);
	};

	@Override
	public String toString() {
		return "ObjectType [id=" + id + ", objectClassId=" + objectClassId + ", objectClass=" + objectClass + ", name="
				+ name + "]";
	}

	ObjectType() {

	}

}
