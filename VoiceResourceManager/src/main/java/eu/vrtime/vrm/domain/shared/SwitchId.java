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