package eu.vrtime.vrm.api.messages;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import eu.vrtime.vrm.domain.shared.ResourceIdentifier;
import eu.vrtime.vrm.domain.shared.VoiceNumber;

@JacksonXmlRootElement(localName = "GetServiceInfoResponse")
public class ServiceInfoResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8563800613252717971L;

	@JacksonXmlProperty(localName = "customerId")
	private String customerId;

	@JacksonXmlProperty(localName = "smid")
	private String smId;

	@JacksonXmlProperty(localName = "nic")
	private String nic;

	@JacksonXmlProperty(localName = "swId")
	private String switchId;

	@JacksonXmlElementWrapper(localName = "numbers")
	private List<VoiceNumber> number = new ArrayList<>();

	public ServiceInfoResponse(final String customerId, final String smId, final String nic, final String switchId) {
		this.customerId = customerId;
		this.smId = smId;
		this.nic = nic;
		this.switchId = switchId;

	}

	public ServiceInfoResponse() {

	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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

	public List<VoiceNumber> getNumber() {
		return number;
	}

	public void setNumber(List<VoiceNumber> number) {
		this.number = number;
	}

	public void addNumber(String dn, String len) {
		number.add(new VoiceNumber(dn, len));
	}

}
