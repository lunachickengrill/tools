package eu.vrtime.vrm.services;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import eu.vrtime.vrm.domain.shared.ResourceIdentifier;

@JacksonXmlRootElement(localName = "GetServiceInfoResponse")
public class GetServiceInfoResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8563800613252717971L;

	@JacksonXmlProperty(localName="LEN")
	private List<String> len;
	
	@JacksonXmlProperty(localName="SMID")
	private String smId;
	
	@JacksonXmlProperty(localName="NIC")
	private String nic;
	
	@JacksonXmlProperty(localName="SW-ID")
	private String switchId;

	public GetServiceInfoResponse(String smId, String nic, String switchId) {
		this.smId = smId;
		this.nic = nic;
		this.switchId = switchId;

	}

	public GetServiceInfoResponse() {

	}

	public List<String> getLen() {
		return len;
	}

	public void setLen(List<String> len) {
		this.len = len;
	}

	public String getSmId() {
		return smId;
	}

	public void setSmdId(String smId) {
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

}
