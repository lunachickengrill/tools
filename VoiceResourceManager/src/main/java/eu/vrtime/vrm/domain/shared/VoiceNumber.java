package eu.vrtime.vrm.domain.shared;

public class VoiceNumber {

	private String dn;

	private String len;

	public VoiceNumber(final String dn, final String len) {
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
	

}
