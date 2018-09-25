package com.genericinventory.domain.objecttype;

public final class ObjectTypeClassIdRule implements MetaDataRule<ObjectType> {

	@Override
	public ObjectType applyRule(ObjectType metaDataObject) {

		if (metaDataObject.getObjectClass().equals(ObjectClass.SYSTEM)) {
			metaDataObject.setObjectClassId(metaDataObject.getId());
			return metaDataObject;
		}

		if (metaDataObject.getParent() != null && (!(metaDataObject.getObjectClass().equals(ObjectClass.SYSTEM)))) {
			metaDataObject.setObjectClassId(metaDataObject.getParent().getObjectClassId());
			return metaDataObject;
		}

		return metaDataObject;
	}

}
