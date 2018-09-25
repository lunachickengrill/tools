package com.genericinventory.domain.service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genericinventory.domain.objecttype.ObjectClass;
import com.genericinventory.domain.objecttype.ObjectType;
import com.genericinventory.domain.objecttype.ObjectTypeAttribute;
import com.genericinventory.domain.objecttype.ObjectTypeStructure;
import com.genericinventory.domain.shared.AttributeId;
import com.genericinventory.domain.shared.AttributeSpecification;
import com.genericinventory.domain.shared.DomainServiceException;
import com.genericinventory.infrastructure.persistence.AttributeRepository;
import com.genericinventory.infrastructure.persistence.ObjectTypeRepository;

@Service
public class MetaDataServiceImpl implements MetaDataService {

	public static final Logger logger = LoggerFactory.getLogger(MetaDataServiceImpl.class);

	@Autowired
	ObjectTypeRepository objectTypeRepository;

	@Autowired
	AttributeRepository attributeRepository;

	public MetaDataServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Set<AttributeSpecification> getSpecificationForObjectType(final ObjectType objectType) {
		Validate.notNull(objectType, "ObjectType is required");

		ObjectType dbObjectType = objectTypeRepository.findOne(objectType.getId());
		if (dbObjectType == null || (!(dbObjectType.getObjectClass().equals(ObjectClass.CLASS))))
			throw new DomainServiceException("Failed to create Specification for ObjectType");

		Set<ObjectTypeAttribute> attributes = new HashSet<ObjectTypeAttribute>();
		Set<AttributeSpecification> specs = new HashSet<AttributeSpecification>();
		attributes = recursiveAttributeFetch(dbObjectType, attributes);

		attributes
				.forEach(a -> specs.add(new AttributeSpecification(new AttributeId(a.getId()), a.getAttributeType())));

		return specs;
	}

	private Set<ObjectTypeAttribute> recursiveAttributeFetch(final ObjectType objectType,
			final Set<ObjectTypeAttribute> set) {

		if ((!(objectType.getClass().equals(ObjectClass.SYSTEM))) && objectType.getParent() != null) {

			if (!(objectType.getAttributeList().isEmpty())) {
				set.addAll(objectType.getAttributeList());
			}

			recursiveAttributeFetch(objectType.getParent(), set);
		}

		return set;

	}

	@Override
	public Set<ObjectTypeStructure> getStructureForObjectType(final Long objectTypeId) {
		Validate.notNull(objectTypeId, "ObjectTypeId is required");
		ObjectType dbObjectType = objectTypeRepository.findOne(objectTypeId);
		if (dbObjectType == null)
			throw new DomainServiceException("ObjectType not found");
		if (!(dbObjectType.getObjectClass().equals(ObjectClass.CLASS)))
			throw new DomainServiceException("Provided ObjectType is not a CLASS");
		Set<ObjectTypeStructure> structure = dbObjectType.getStructureInformation();
		if (!(structure.isEmpty()))
			return structure;
		return new HashSet<ObjectTypeStructure>();
	}

	@Override
	public void deleteObjectType(final ObjectType objectType) {
		objectTypeRepository.deleteCascade(objectType.getId());

	}

	@Override
	public ObjectType moveObjectType(final ObjectType objectType, final ObjectType newParent) {
		ObjectType dbObjectType = objectTypeRepository.findOne(objectType.getId());
		ObjectType dbNewParent = objectTypeRepository.findOne(newParent.getId());

		if (dbObjectType == null || dbNewParent == null)
			throw new DomainServiceException(
					"Cannot move ObjectType. ObjectType or new Parent not found in repository");
		dbObjectType.adjunctToParent(dbNewParent);
		ObjectType saved = objectTypeRepository.saveAndFlush(dbObjectType);
		return saved;
	}

	@Override
	public List<ObjectTypeAttribute> getAttributesByObjectType(final ObjectType objectType) {
		ObjectType dbObjType = objectTypeRepository.findOne(objectType.getId());
		List<ObjectTypeAttribute> attributes = dbObjType.getAttributeList();
		return Collections.unmodifiableList(attributes);
	}

	@Override
	public Map<String, AttributeId> getAttributes(ObjectType objectType) {
		ObjectType dbObjType = objectTypeRepository.findOne(objectType.getId());

		Map<String, AttributeId> mapping = new HashMap<String, AttributeId>();
		Set<ObjectTypeAttribute> attributes = new HashSet<ObjectTypeAttribute>();

		attributes = recursiveAttributeFetch(dbObjType, attributes);
		attributes.forEach(p -> mapping.put(p.getName(), new AttributeId(p.getId())));

		return mapping;
	}

}
