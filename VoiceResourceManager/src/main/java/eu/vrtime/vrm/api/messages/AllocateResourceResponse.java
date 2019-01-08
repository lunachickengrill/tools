package eu.vrtime.vrm.api.messages;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import eu.vrtime.vrm.domain.shared.VoiceNumber;

//@JacksonXmlRootElement(localName = "AllocateResourceResponse")
public class AllocateResourceResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -977653430804986366L;

//	@JacksonXmlProperty(localName = "customerId")
	private String customerId;

//	@JacksonXmlProperty(localName = "smId")
	private String smId;

//	@JacksonXmlProperty(localName = "nic")
	private String nic;

//	@JacksonXmlProperty(localName = "swId")
	private String switchId;

//	@JacksonXmlElementWrapper(localName = "numbers")
	private List<VoiceNumber> number = new ArrayList<>();

	public AllocateResourceResponse(String smId, String nic, String switchId, String customerId, String sid) {
		this.smId = smId;
		this.nic = nic;
		this.switchId = switchId;
		this.customerId = customerId;

	}

	public AllocateResourceResponse() {

	}

	public String getSmId() {
		return smId;
	}

	public void setSmId(String smId) {
		this.smId = smId;
	}

	public String getNic() {
		return nic;
	}

	public void setNic(String nic) {
		this.nic = nic;
	}

	public String getSwitchId() {
		return switchId;
	}

	public void setSwitchId(String switchId) {
		this.switchId = switchId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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
		return "AllocateResourceResponse [customerId=" + customerId + ", smId=" + smId + ", nic=" + nic + ", switchId="
				+ switchId + ", number=" + number + "]";
	}

}
