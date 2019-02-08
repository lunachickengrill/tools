<<<<<<< HEAD
package eu.vrtime.vrm.domain.shared;

import javax.persistence.Embeddable;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Embeddable
public class SwitchId implements ValueObject<SwitchId> {

	private String swId;

	public SwitchId(final String swId) {
		Validate.notNull(swId, "swId is null");
		setSwId(swId);
	}

	public String getSwId() {
		return swId;
	}

	private void setSwId(final String swId) {
		this.swId = swId;
	}

	public String toStringSwId() {
		return swId;
	}

	@Override
	public boolean sameValueAs(SwitchId other) {
		return other != null && swId.equals(other.swId);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(swId).build();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		final SwitchId other = (SwitchId) obj;
		return sameValueAs(other);
	}

	@Override
	public String toString() {
		return "SwitchId [swId=" + swId + "]";
	}

	SwitchId() {

	}
}
=======
package eu.vrtime.vrm.domain.shared;

public class SwitchId implements ValueObject<SwitchId> {

	private String identifier;

	public SwitchId(final String identifier) {
		setIdentifier(identifier);
	}

	public String getIdentifier() {
		return identifier;
	}

	private void setIdentifier(final String identifier) {
		if (identifier == null || identifier.isEmpty()) {
			throw new IllegalArgumentException("SwitchId is null");
		}

		this.identifier = identifier;
	}

	@Override
	public boolean sameValueAs(SwitchId other) {
		return other != null && identifier.equals(other.identifier);
	}

	@Override
	public int hashCode() {
		return identifier.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		final SwitchId other = (SwitchId) obj;
		return sameValueAs(other);
	}

	@Override
	public String toString() {
		return "SwitchId [identifier=" + identifier + "]";
	}

	SwitchId() {

	}
}
>>>>>>> 8dc4866f8ccba5a3c2d16602cbcc6c1b79c5597a
