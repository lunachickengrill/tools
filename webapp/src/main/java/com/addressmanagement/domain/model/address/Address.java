package com.addressmanagement.domain.model.address;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.apache.commons.lang3.Validate;

import com.addressmanagement.domain.model.city.City;
import com.addressmanagement.domain.model.shared.EntityObject;

/**
 * The persistence class for the ADDRESS database table.
 * 
 * @author tschwaiger
 *
 */
@Entity
public class Address implements EntityObject<Address>, Serializable {
	private static final long serialVersionUID = 1L;

	// @EmbeddedId
	// @AttributeOverride(name = "objectId", column = @Column(name =
	// "ADDRESS_ID") )
	// private ObjectId addressId;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID")
	@SequenceGenerator(name = "SEQ_ID", sequenceName = "SEQ_ID", allocationSize = 1)
	private Long addressId;

	@Column(nullable = true)
	private String description;

	@Column(unique = false, nullable = false)
	private AddressDetail addressDetail;

	@ManyToOne(optional = false)
	@JoinColumn(name = "CITY_ID")
	private City city;

	private Address() {
	}

	public Address(City city, String streetName, String streetNumber, String entrance) {
		Validate.notNull(city, "City is required");
		this.city = city;
		this.addressDetail = new AddressDetail(streetName, streetNumber, entrance);
	}

	public Address(City city, AddressDetail addressDetail) {

		Validate.notNull(city, "City is required");
		Validate.notNull(addressDetail, "AddressDetail is required");

		this.city = city;
		this.addressDetail = addressDetail;
	}

	public Long getAddressId() {
		return addressId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AddressDetail getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(AddressDetail addressDetail) {
		this.addressDetail = addressDetail;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getParamsAsString() {
		return addressDetail.toString();
	}

	public String idString() {
		return this.addressId.toString();
	}

	@Override
	public boolean sameIdentityAs(final Address other) {
		return other != null && this.addressId.equals(other.addressId);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null || (!(obj instanceof Address)))
			return false;

		final Address other = (Address) obj;
		return sameIdentityAs(other);
	}

	@Override
	public int hashCode() {
		return this.addressId.hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Address [addressId=%s, description=%s, addressDetail=%s, city=%s]", addressId,
				description, addressDetail, city);
	}

}