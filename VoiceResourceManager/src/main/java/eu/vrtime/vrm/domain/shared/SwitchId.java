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
