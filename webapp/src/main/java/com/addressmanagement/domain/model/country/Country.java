package com.addressmanagement.domain.model.country;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.apache.commons.lang3.Validate;

import com.addressmanagement.domain.model.city.City;
import com.addressmanagement.domain.model.region.Region;
import com.addressmanagement.domain.model.shared.EntityObject;

/**
 * The persistent class for the COUNTRY database table.
 * 
 */
@Entity
public class Country implements EntityObject<Country>, Serializable {
	private static final long serialVersionUID = 1L;

	// @EmbeddedId
	// @AttributeOverride(name = "objectId", column = @Column(name =
	// "COUNTRY_ID") )
	// private ObjectId countryId;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID")
	@SequenceGenerator(name = "SEQ_ID", sequenceName = "SEQ_ID", allocationSize = 1)
	private Long countryId;

	@Column(unique = false, nullable = true)
	private String name;

	@Column(unique = true, nullable = true)
	private String countryCode;

	@Column(nullable = true)
	private String description;

	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
	private List<City> cities;

	@ManyToOne(optional = false)
	@JoinColumn(name = "REGION_ID")
	private Region region;

	private Country() {
	}

	public Country(Region region, String name, String countryCode) {

		Validate.notNull(region, "Region is required");
		Validate.notNull(name, "Name is required");

		this.region = region;
		this.name = name;
		this.countryCode = countryCode.toUpperCase();
	}

	public Long getCountryId() {
		return this.countryId;
	}

	public String getName() {
		return this.name;
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

	public List<City> getCities() {
		return this.cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public String idString() {
		return this.countryId.toString();
	}

	@Override
	public boolean sameIdentityAs(final Country other) {
		return other != null && this.countryId.equals(other.countryId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || (!(obj instanceof Country)))
			return false;
		final Country other = (Country) obj;
		return sameIdentityAs(other);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((countryId == null) ? 0 : countryId.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return String.format("Country [countryId=%s, name=%s, countryCode=%s, description=%s, region=%s]", countryId,
				name, countryCode, description, region);
	}

}