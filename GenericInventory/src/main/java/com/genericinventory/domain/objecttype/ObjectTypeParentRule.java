package com.genericinventory.domain.objecttype;

import com.genericinventory.domain.shared.DomainServiceException;

public class ObjectTypeParentRule implements MetaDataRule<ObjectType> {

	@Override
	public ObjectType applyRule(ObjectType metaDataObject) {

		if (metaDataObject.getObjectClass().equals(ObjectClass.SYSTEM)
				&& (!(metaDataObject.getParent().getObjectClass().equals(metaDataObject.getObjectClass()))))
			throw new DomainServiceException(
					"Parent not allowed:" + "\n" + "This type: " + metaDataObject.getObjectClass() + "\n"
							+ "Parent type: " + metaDataObject.getParent().getObjectClass());
		if (metaDataObject.getObjectClass().equals(ObjectClass.ABSTRACT)
				&& metaDataObject.getParent().getObjectClass().equals(ObjectClass.CLASS))
			throw new DomainServiceException(
					"Parent not allowed:" + "\n" + "This type: " + metaDataObject.getObjectClass() + "\n"
							+ "Parent type: " + metaDataObject.getParent().getObjectClass());
		if (metaDataObject.getObjectClass().equals(ObjectClass.CLASS)
				&& metaDataObject.getParent().getObjectClass().equals(metaDataObject.getObjectClass()))
			throw new DomainServiceException(
					"Parent not allowed:" + "\n" + "This type: " + metaDataObject.getObjectClass() + "\n"
							+ "Parent type: " + metaDataObject.getParent().getObjectClass());

		return metaDataObject;
	}

}
