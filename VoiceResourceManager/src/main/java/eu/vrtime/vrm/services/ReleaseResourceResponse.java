package eu.vrtime.vrm.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import eu.vrtime.vrm.domain.shared.ResourceIdentifier;

@JacksonXmlRootElement(localName = "ReleaseResourceResponse")
public class ReleaseResourceResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5512621358722170724L;

	private String len;
	private String smId;
	private String nic;
	private String switchId;

	public ReleaseResourceResponse(ResourceIdentifier resourceIdentifier, String smId, String nic, String switchId) {
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
