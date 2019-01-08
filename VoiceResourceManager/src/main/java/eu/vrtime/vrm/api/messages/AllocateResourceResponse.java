package eu.vrtime.vrm.api.messages;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import eu.vrtime.vrm.domain.shared.VoiceNumber;

public class AllocateResourceResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -977653430804986366L;

	private String smId;

	private String nic;

	private String switchId;

	private List<VoiceNumber> number = new ArrayList<>();

	public AllocateResourceResponse(String smId, String nic, String switchId) {
		Validate.notNull(smId, "smId is null");
		Validate.notNull(nic, "nic is null");
		Validate.notNull(switchId, "switchId is null");
		
		this.smId = smId;
		this.nic = nic;
		this.switchId = switchId;

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
		return "AllocateResourceResponse [smId=" + smId + ", nic=" + nic + ", switchId=" + switchId + ", number="
				+ number + "]";
	}

}
