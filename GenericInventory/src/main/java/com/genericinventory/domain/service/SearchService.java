package com.genericinventory.domain.service;

import java.util.List;
import java.util.Set;

import com.genericinventory.domain.dataobject.DataObject;
import com.genericinventory.domain.objecttype.ObjectType;
import com.genericinventory.domain.shared.AttributeId;
import com.genericinventory.domain.shared.Value;

public interface SearchService {

	public List<DataObject> findObjectByValue(ObjectType objectType, Value value);

	public List<DataObject> findObjectByAttributeAndValue(ObjectType objectType, AttributeId attributeId, Value value);

}
