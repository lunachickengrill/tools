package eu.vrtime.vrm.domain.shared;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class VoiceNumber implements ValueObject<VoiceNumber> {

	private String dn;

	private String len;

	public VoiceNumber(final String dn, final String len) {
		Validate.notNull(dn, "dn is null");
		Validate.notNull(len,"len is null");
		
		this.dn = dn;
		this.len = len;
	}

	public String getDn() {
		return dn;
	}

	public void setDn(String dn) {
		this.dn = dn;
	}

	public String getLen() {
		return len;
	}

	public void setLen(String len) {
		this.len = len;
	}

	@Override
	public boolean sameValueAs(VoiceNumber other) {
		return other != null && ((dn.equals(other.dn)) && (len.equals(other.len)));
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(dn).append(len).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		final VoiceNumber other = (VoiceNumber) obj;
		return sameValueAs(other);
	}

	@Override
	public String toString() {
		return "VoiceNumber [dn=" + dn + ", len=" + len + "]";
	}

}
