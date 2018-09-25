package com.genericinventory.domain.service;

import com.genericinventory.domain.dataobject.DataObject;
import com.genericinventory.domain.objecttype.ObjectType;

public interface DataObjectService {

	DataObject constructFromObjectType(final ObjectType objectType);

}
