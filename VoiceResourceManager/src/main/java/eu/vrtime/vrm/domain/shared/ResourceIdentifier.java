package eu.vrtime.vrm.domain.shared;

import javax.persistence.Embeddable;

@Embeddable
public class ResourceIdentifier implements ValueObject<ResourceIdentifier> {

	private String identifier;

	public ResourceIdentifier(final String identifier) {
		setIdentifier(identifier);
	}

	public String getIdentifier() {
		return identifier;
	}

	private void setIdentifier(String identifier) {
		if (identifier == null || identifier.isEmpty()) {
			throw new IllegalArgumentException("Identifier is null or empty");
		}
		this.identifier = identifier;
	}
	
	public String toStringIdentifier() {
		return this.identifier;
	}

	@Override
	public boolean sameValueAs(ResourceIdentifier other) {
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

		final ResourceIdentifier resourceIdentifier = (ResourceIdentifier) obj;
		return sameValueAs(resourceIdentifier);
	}

	@Override
	public String toString() {
		return "ResourceIdentifier [identifier=" + identifier + "]";
	}

	ResourceIdentifier() {
	}

}
