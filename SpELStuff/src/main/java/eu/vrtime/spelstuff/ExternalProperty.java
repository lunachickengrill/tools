package eu.vrtime.spelstuff;

public class ExternalProperty {

	private String system;
	private String reason;

	public ExternalProperty(final String system, final String reason) {
		this.system = system;
		this.reason = reason;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "ExternalProperty [system=" + system + ", reason=" + reason + "]";
	}

}
