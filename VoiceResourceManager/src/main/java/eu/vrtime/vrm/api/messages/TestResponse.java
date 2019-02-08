package eu.vrtime.vrm.api.messages;

import java.io.Serializable;

public class TestResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6801768438236319330L;

	private String value;

	private String customerId;

	public TestResponse(String customerId, String value) {
		this.customerId = customerId;
		this.value=value;

	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}