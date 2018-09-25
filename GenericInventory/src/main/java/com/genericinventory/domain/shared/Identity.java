package com.genericinventory.domain.shared;

public interface Identity<T> {
	
	boolean sameIdentityAs(T otherEntity);


}
