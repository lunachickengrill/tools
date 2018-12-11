package eu.vrtime.vrm.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import eu.vrtime.vrm.domain.shared.ResourceIdentifier;

@JacksonXmlRootElement(localName = "ReleaseResourceResponse")
public class ReleaseResourceResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5512621358722170724L;

	@JacksonXmlProperty(localName = "LEN")
	private String len;

	@JacksonXmlProperty(localName = "SMID")
	private String smId;

	@JacksonXmlProperty(localName = "NIC")
	private String nic;

	@JacksonXmlProperty(localName = "SW-ID")
	private String switchId;

	@JacksonXmlProperty(localName = "DN")
	private String directoryNumber;

	@JacksonXmlProperty(localName = "CUSTOMERID")
	private String customerId;

	@JacksonXmlProperty(localName = "SID")
	private String sid;

	public ReleaseResourceResponse(ResourceIdentifier resourceIdentifier, String smId, String nic, String switchId,
			String directoryNumber, String customerId, String sid) {
		this.len = resourceIdentifier.getIdentifier();
		this.smId = smId;
		this.nic = nic;
		this.switchId = switchId;
		this.directoryNumber = directoryNumber;
		this.customerId = customerId;
		this.sid = sid;

	}

	public ReleaseResourceResponse() {

	}

	public String getLen() {
		return len;
	}

	public void setLen(String len) {
		this.len = len;
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

	public String getDirectoryNumber() {
		return directoryNumber;
	}

	public void setDirectoryNumber(String directoryNumber) {
		this.directoryNumber = directoryNumber;
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

}
