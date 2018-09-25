package com.genericinventory.domain.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.genericinventory.domain.objecttype.ObjectType;
import com.genericinventory.domain.objecttype.ObjectTypeAttribute;
import com.genericinventory.domain.objecttype.ObjectTypeStructure;
import com.genericinventory.domain.shared.AttributeId;
import com.genericinventory.domain.shared.AttributeSpecification;

public interface MetaDataService {

	public Set<AttributeSpecification> getSpecificationForObjectType(final ObjectType objectType);
	
	public Set<ObjectTypeStructure> getStructureForObjectType(final Long objectTypeId);

	public void deleteObjectType(final ObjectType objectType);

	public ObjectType moveObjectType(final ObjectType objectType, final ObjectType newParent);

	public List<ObjectTypeAttribute> getAttributesByObjectType(final ObjectType objectType);

	public Map<String, AttributeId> getAttributes(final ObjectType objectType);

}
