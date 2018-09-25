package com.genericinventory.domain.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genericinventory.domain.dataobject.DataObject;
import com.genericinventory.domain.objecttype.ObjectType;
import com.genericinventory.domain.shared.AttributeId;
import com.genericinventory.domain.shared.AttributeSpecification;
import com.genericinventory.domain.shared.DomainServiceException;
import com.genericinventory.domain.shared.Value;
import com.genericinventory.infrastructure.persistence.AttributeRepository;
import com.genericinventory.infrastructure.persistence.DataObjectRepository;

@Service
public final class SearchServiceImpl implements SearchService {

	@Autowired
	MetaDataServiceImpl metadataService;

	@Autowired
	AttributeRepository attributeRepository;

	@Autowired
	DataObjectRepository dataObjectRepository;

	public SearchServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<DataObject> findObjectByValue(ObjectType objectType, Value value) {

		List<DataObject> result = dataObjectRepository.findObjectByValue(objectType.getId(), value.getValue());
		Set<AttributeSpecification> specs = metadataService.getSpecificationForObjectType(objectType);

		result.forEach(p -> p.setAttributeSpecs(specs));
		return Collections.unmodifiableList(result);
	}

	@Override
	public List<DataObject> findObjectByAttributeAndValue(ObjectType objectType, AttributeId attributeId, Value value) {
		List<DataObject> result = dataObjectRepository.findObjectByAttributeAndValue(objectType.getId(),
				attributeId.getAttributeId(), value.getValue());
		Set<AttributeSpecification> specs = metadataService.getSpecificationForObjectType(objectType);
		result.forEach(p -> p.setAttributeSpecs(specs));
		return Collections.unmodifiableList(result);
	}

}
