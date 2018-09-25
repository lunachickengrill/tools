package com.addressmanagement.service.dto;

import java.io.Serializable;

/**
 * DTO for a Address Object
 * 
 * @author tschwaiger
 */

public final class AddressDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String description;
	private AddressDetailDTO addressDetail;
	private CityDTO city;

	public AddressDTO(String id, String description, AddressDetailDTO addressDetail, CityDTO city) {
		super();
		this.id = id;
		this.description = description;
		this.addressDetail = addressDetail;
		this.city = city;
	}

	public AddressDTO(String description, AddressDetailDTO addressDetail, CityDTO city) {
		super();
		this.description = description;
		this.addressDetail = addressDetail;
		this.city = city;
	}

	public AddressDTO() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AddressDetailDTO getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(AddressDetailDTO addressDetail) {
		this.addressDetail = addressDetail;
	}

	public CityDTO getCity() {
		return city;
	}

	public void setCity(CityDTO city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return String.format("AddressDTO [id=%s, description=%s, addressDetail=%s, city=%s]", id, description,
				addressDetail, city);
	}

}
