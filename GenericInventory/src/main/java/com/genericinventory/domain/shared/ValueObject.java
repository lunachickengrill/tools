package com.genericinventory.domain.shared;

public interface ValueObject<T> {
	
	boolean sameValueAs(T other);

}
