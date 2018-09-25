package com.addressmanagement.domain.model.location;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.apache.commons.lang3.Validate;

import com.addressmanagement.domain.model.address.Address;
import com.addressmanagement.domain.model.city.City;
import com.addressmanagement.domain.model.country.Country;
import com.addressmanagement.domain.model.region.Region;
import com.addressmanagement.domain.model.shared.EntityObject;

/**
 * The persistent class for the LOCATION database table.
 * 
 * @author tschwaiger
 *
 */
@Entity
public class Location implements Serializable, EntityObject<Location> {
	private static final long serialVersionUID = 1L;

	// @EmbeddedId
	// @AttributeOverride(name = "objectId", column = @Column(name =
	// "LOCATION_ID") )
	// private ObjectId locationId;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID")
	@SequenceGenerator(name = "SEQ_ID", sequenceName = "SEQ_ID", allocationSize = 1)
	private Long locationId;

	@Column(nullable = true)
	private String description;

	@OneToOne(optional = false)
	@JoinColumn(name = "ADDRESS_ID")
	private Address address;

	@OneToOne(optional = false)
	@JoinColumn(name = "CITY_ID")
	private City city;

	@OneToOne(optional = false)
	@JoinColumn(name = "COUNTRY_ID")
	private Country country;

	@OneToOne(optional = false)
	@JoinColumn(name = "REGION_ID")
	private Region region;

	private Location() {
	}

	public Location(final Region region, final Country country, final City city, final Address address) {
		Validate.notNull(region, "Region is required");
		Validate.notNull(country, "Country is required");
		Validate.notNull(city, "City is required");
		Validate.notNull(address, "Address is required");
		this.region = region;
		this.country = country;
		this.city = city;
		this.address = address;
	}

	public Long getLocationId() {
		return locationId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	@Override
	public boolean sameIdentityAs(final Location other) {
		return other != null && locationId.equals(other.locationId);
	}

	@Override
	public int hashCode() {
		return this.locationId.hashCode();
	}

	@Override
	public boolean equals(final Object object) {
		if (this == object)
			return true;

		if (object == null || getClass() != object.getClass())
			return true;

		final Location other = (Location) object;
		return sameIdentityAs(other);

	}

	@Override
	public String toString() {
		return locationId.toString();
	}

}
