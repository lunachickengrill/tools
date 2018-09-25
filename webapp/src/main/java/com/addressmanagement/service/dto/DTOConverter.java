package com.addressmanagement.service.dto;

import java.util.List;

public interface DTOConverter<D, T> {

	/**
	 * Converts from a domain object to the DTO. D domain object, T transfer
	 * object
	 * 
	 * @param domainObject
	 * @return A DTO
	 */
	public T toDTO(D domainObject);

	/**
	 * Converts from a DTO without id to a new domain object. D domain object, T
	 * transfer object
	 * 
	 * @param dto
	 * @return A domain object
	 */

	public D fromDTO(T dto);

	/**
	 * Converts from a collection of domain objects to a list of DTO. D domain
	 * object, T transfer object
	 * 
	 * @param domainObjectList
	 * @return A list of DTO
	 */
	public List<T> toDTOList(List<D> domainObjectList);

	/**
	 * Converts from a collection of DTO to a list of domain objects. D domain
	 * object, T transfer object
	 * 
	 * @param dtoList
	 * @return
	 */

	public List<D> fromDTOList(List<T> dtoList);

}
