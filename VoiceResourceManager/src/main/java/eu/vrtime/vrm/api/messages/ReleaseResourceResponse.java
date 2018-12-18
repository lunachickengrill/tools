package eu.vrtime.vrm.api.messages;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import eu.vrtime.vrm.domain.shared.VoiceNumber;

@JacksonXmlRootElement(localName = "ReleaseResourceResponse")
public class ReleaseResourceResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5512621358722170724L;

	@JacksonXmlProperty(localName = "customerId")
	private String customerId;

	@JacksonXmlProperty(localName = "sid")
	private String sid;

	@JacksonXmlElementWrapper(localName = "numbers")
	private List<VoiceNumber> number = new ArrayList<>();

	public ReleaseResourceResponse(String smId, String nic, String switchId, String customerId, String sid) {
		this.customerId = customerId;
		this.sid = sid;

	}

	public ReleaseResourceResponse() {

	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public List<VoiceNumber> getNumber() {
		return number;
	}

	public void setNumber(List<VoiceNumber> number) {
		this.number = number;
	}

	public void addNumber(String dn, String len) {
		number.add(new VoiceNumber(dn, len));
	}

	@Override
	public String toString() {
		return "ReleaseResourceResponse [customerId=" + customerId + ", sid=" + sid + ", number=" + number + "]";
	}

}
