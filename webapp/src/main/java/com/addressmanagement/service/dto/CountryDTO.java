package com.addressmanagement.service.dto;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for a Country Object
 * 
 * @author tschwaiger
 *
 */

public class CountryDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String countryCode;
	private String description;
	private List<CityDTO> cities;
	private RegionDTO region;

	public CountryDTO() {
		super();
	}

	public CountryDTO(String id, String name, String countryCode, String description, RegionDTO region) {
		super();
		this.id = id;
		this.name = name;
		this.countryCode = countryCode;
		this.description = description;
		this.region = region;
	}

	public CountryDTO(String name, String countryCode, String description, RegionDTO region) {
		super();
		this.name = name;
		this.countryCode = countryCode;
		this.description = description;
		this.region = region;
	}

	public String getId() {
		return id;
	}

	public void setId(String Id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<CityDTO> getCities() {
		return cities;
	}

	public void setCities(List<CityDTO> cities) {
		this.cities = cities;
	}

	public RegionDTO getRegion() {
		return region;
	}

	public void setRegion(RegionDTO region) {
		this.region = region;
	}

	@Override
	public String toString() {
		return String.format("CountryDTO [id=%s, name=%s, countryCode=%s, description=%s, region=%s]", id, name,
				countryCode, description, region);
	}

}
