package com.addressmanagement.service.dto;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for a City Object
 * 
 * @author tschwaiger
 *
 */
public class CityDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String zip;
	private String name;
	private String description;
	private List<AddressDTO> addresses;
	private CountryDTO country;

	public CityDTO(String id, String zip, String name, String description, CountryDTO country) {
		super();
		this.id = id;
		this.zip = zip;
		this.name = name;
		this.description = description;
		this.country = country;
	}

	public CityDTO(String zip, String name, String description, CountryDTO country) {
		super();
		this.zip = zip;
		this.name = name;
		this.description = description;
		this.country = country;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<AddressDTO> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressDTO> addresses) {
		this.addresses = addresses;
	}

	public CountryDTO getCountry() {
		return country;
	}

	public void setCountry(CountryDTO country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return String.format("CityDTO [id=%s, zip=%s, name=%s, description=%s, country=%s]", id, zip, name, description,
				country);
	}

}
