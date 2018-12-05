package eu.vrtime.vrm.services;

import java.io.Serializable;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import eu.vrtime.vrm.domain.shared.ResourceIdentifier;

@JacksonXmlRootElement(localName = "GetServiceInfoResponse")
public class GetServiceInfoResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8563800613252717971L;

	private String len;
	private String smId;
	private String nic;
	private String switchId;

	public GetServiceInfoResponse(ResourceIdentifier resourceIdentifier, String smId, String nic, String switchId) {
		this.len = resourceIdentifier.getIdentifier();
		this.smId = smId;
		this.nic = nic;
		this.switchId = switchId;

	}

	public String getLen() {
		return len;
	}

	public String getSmId() {
		return smId;
	}

	public String getNic() {
		return nic;
	}

	public String getSwitchId() {
		return switchId;
	}

}
