package com.addressmanagement.domain.model.address;

import javax.persistence.Embeddable;

import org.apache.commons.lang3.Validate;

import com.addressmanagement.domain.model.shared.ValueObject;

@Embeddable
public class AddressDetail implements ValueObject<AddressDetail> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String streetName;

	private String streetNumber;

	private String entrance;

	private AddressDetail() {
	}

	/**
	 * Constructor
	 * 
	 * @param streetName
	 * @param streetNumber
	 * @param entrance
	 */

	public AddressDetail(String streetName, String streetNumber, String entrance) {
		Validate.notNull(streetName, "StreetName is required");
		Validate.notNull(streetNumber, "StreetNumber is required");
		Validate.notNull(entrance, "Entrance is required");
		this.streetName = streetName;
		this.streetNumber = streetNumber;
		this.entrance = entrance;
	}

	public String getStreetName() {
		return streetName;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public String getEntrance() {
		return entrance;
	}

	@Override
	public boolean sameValueAs(AddressDetail other) {
		return other != null && (this.streetName.equals(other.streetName)
				&& this.streetNumber.equals(other.streetNumber) && this.entrance.equals(other.entrance));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((entrance == null) ? 0 : entrance.hashCode());
		result = prime * result + ((streetName == null) ? 0 : streetName.hashCode());
		result = prime * result + ((streetNumber == null) ? 0 : streetNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || (!(obj instanceof AddressDetail)))
			return false;
		final AddressDetail other = (AddressDetail) obj;
		return sameValueAs(other);
	}

	@Override
	public String toString() {
		return String.format("AddressDetail [streetName=%s, streetNumber=%s, entrance=%s]", streetName, streetNumber,
				entrance);
	}

}
