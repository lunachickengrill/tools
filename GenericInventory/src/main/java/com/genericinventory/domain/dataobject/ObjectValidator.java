package com.genericinventory.domain.dataobject;

public interface ObjectValidator<T> {

	boolean isValid(T object);

}
