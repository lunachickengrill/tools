package com.addressmanagement.service.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.addressmanagement.service.dto.CountryDTO;

/**
 * DTO for a Region Object
 * 
 * @author tschwaiger
 *
 */

public final class RegionDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String regionCode;
	private String name;
	private String description;
	private List<CountryDTO> countries = new ArrayList<CountryDTO>();

	/**
	 * Constructor
	 * 
	 * @param String
	 *            regionId
	 * @param String
	 *            regionName
	 * @param String
	 *            description
	 */
	public RegionDTO(String regionCode, String name, String description) {
		this.regionCode = regionCode;
		this.name = name;
		this.description = description;
	}

	public RegionDTO(String id, String regionCode, String name, String description) {
		this.id = id;
		this.regionCode = regionCode;
		this.name = name;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
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

	public List<CountryDTO> getCountries() {
		return countries;
	}

	public void setCountries(List<CountryDTO> countries) {
		this.countries = countries;
	}

	@Override
	public String toString() {
		return "RegionDTO [id=" + id + ", regionCode=" + regionCode + ", name=" + name + ", description=" + description
				+ "]";
	}

}
