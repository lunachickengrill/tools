package com.addressmanagement.domain.model.city;

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
import javax.persistence.Table;

import org.apache.commons.lang3.Validate;

import com.addressmanagement.domain.model.address.Address;
import com.addressmanagement.domain.model.country.Country;
import com.addressmanagement.domain.model.shared.EntityObject;

/**
 * The persistent class for the CITY database table.
 * 
 */
@Entity
@Table(name = "CITY")
public class City implements EntityObject<City>, Serializable {
	private static final long serialVersionUID = 1L;

	// @EmbeddedId
	// @AttributeOverride(name = "objectId", column = @Column(name = "CITY_ID")
	// )
	// private ObjectId cityId;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID")
	@SequenceGenerator(name = "SEQ_ID", sequenceName = "SEQ_ID", allocationSize = 1)
	private Long cityId;

	@Column(unique = true, nullable = false)
	private String zip;

	@Column(unique = false, nullable = false)
	private String name;

	@Column(nullable = true)
	private String description;

	@ManyToOne(optional = false)
	@JoinColumn(name = "COUNTRY_ID")
	private Country country;

	@OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
	private List<Address> addresses;

	private City() {

	}

	public City(Country country, String zip, String name) {
		Validate.notNull(country, "Country is required");
		Validate.notNull(zip, "ZIP is required");
		Validate.notNull(name, "Name is required");
		this.country = country;
		this.zip = zip;
		this.name = name;
	}

	public Long getCityId() {
		return this.cityId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public String idString() {
		return this.cityId.toString();
	}

	@Override
	public boolean sameIdentityAs(final City other) {
		return other != null && this.cityId.equals(other.cityId);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null || (!(obj instanceof City)))
			return false;

		final City other = (City) obj;
		return sameIdentityAs(other);
	}

	@Override
	public int hashCode() {
		return this.cityId.hashCode();
	}

	@Override
	public String toString() {
		return String.format("City [cityId=%s, zip=%s, name=%s, description=%s, country=%s]", cityId, zip, name,
				description, country);
	}

}