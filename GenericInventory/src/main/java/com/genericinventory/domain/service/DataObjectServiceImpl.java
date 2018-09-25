package com.genericinventory.domain.service;

import java.util.Set;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genericinventory.domain.dataobject.DataObject;
import com.genericinventory.domain.objecttype.ObjectType;
import com.genericinventory.domain.objecttype.ObjectTypeStructure;
import com.genericinventory.domain.shared.AttributeSpecification;
import com.genericinventory.domain.shared.DomainServiceException;
import com.genericinventory.infrastructure.persistence.AttributeRepository;
import com.genericinventory.infrastructure.persistence.DataObjectRepository;
import com.genericinventory.infrastructure.persistence.ObjectTypeRepository;

@Service
public final class DataObjectServiceImpl implements DataObjectService {

	@Autowired
	DataObjectRepository dataObjectRepository;

	@Autowired
	ObjectTypeRepository objectTypeRepository;

	@Autowired
	AttributeRepository attributeRepository;

	@Autowired
	MetaDataServiceImpl metadataService;

	@Override
	public DataObject constructFromObjectType(final ObjectType objectType) {
		Validate.notNull(objectType, "ObjectType is required");
		ObjectType dbObjectType = objectTypeRepository.findOne(objectType.getId());
		if (dbObjectType == null)
			throw new DomainServiceException("ObjectType not found in repository");

		Set<AttributeSpecification> specs = metadataService.getSpecificationForObjectType(dbObjectType);
		Set<ObjectTypeStructure> structure = metadataService.getStructureForObjectType(dbObjectType.getId());
		DataObject dataObject = DataObject.createDataObject(dbObjectType, specs, structure);
		return dataObject;

	}

}
