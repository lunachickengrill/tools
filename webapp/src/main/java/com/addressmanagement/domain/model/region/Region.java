package com.addressmanagement.domain.model.region;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.apache.commons.lang3.Validate;

import com.addressmanagement.domain.model.country.Country;
import com.addressmanagement.domain.model.shared.EntityObject;

/**
 * The persistent class for the REGION database table.
 * 
 */
@Entity
public class Region implements EntityObject<Region>, Serializable {
	private static final long serialVersionUID = 1L;

	// @EmbeddedId
	// @AttributeOverride(name = "objectId", column = @Column(name =
	// "REGION_ID") )
	// private ObjectId regionId;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID")
	@SequenceGenerator(name = "SEQ_ID", sequenceName = "SEQ_ID", allocationSize = 1)
	private Long regionId;

	@Column(unique = true, nullable = false)
	private String regionCode;

	@Column(unique = false, nullable = true)
	private String name;

	@Column(unique = false, nullable = true)
	private String description;

	@OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
	private List<Country> countries;

	private Region() {

	}

	public Region(String regionCode, String name) {
		Validate.notNull(regionCode, "RegionCode is required");
		this.regionCode = regionCode.toUpperCase();
		this.name = name;
	}

	public Long getRegionId() {
		return this.regionId;
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

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	public String idString() {
		return this.regionId.toString();
	}

	@Override
	public boolean sameIdentityAs(final Region other) {
		return other != null && this.regionId.equals(other.regionId);

	}

	@Override
	public int hashCode() {
		return this.regionId.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null || (!(obj instanceof Region)))
			return false;
		final Region other = (Region) obj;
		return sameIdentityAs(other);
	}

	@Override
	public String toString() {
		return String.format("Region [regionId=%s, regionCode=%s, name=%s, description=%s]", regionId, regionCode, name,
				description);
	}

}