package com.genericinventory.domain.objecttype;

public interface MetaDataRule<T> {

	T applyRule(T metaDataObject);
}
