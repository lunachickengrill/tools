package eu.vrtime.vrm.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import eu.vrtime.vrm.domain.shared.ResourceIdentifier;

@JacksonXmlRootElement(localName = "GetServiceInfoResponse")
public class ServiceInfoResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8563800613252717971L;

	@JacksonXmlProperty(localName = "CUSTOMERID")
	private String customerId;

	@JacksonXmlElementWrapper(localName = "DNs")
	private List<String> dn = new ArrayList<>();

	@JacksonXmlElementWrapper(localName = "LENs")
	private List<ResourceIdentifier> len = new ArrayList<>();

	@JacksonXmlProperty(localName = "SMID")
	private String smId;

	@JacksonXmlProperty(localName = "NIC")
	private String nic;

	@JacksonXmlProperty(localName = "SW-ID")
	private String switchId;

	public ServiceInfoResponse(final String customerId, final String smId, final String nic, final String switchId) {
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

	public List<String> getDn() {
		return dn;
	}

	public void setDn(List<String> dn) {
		this.dn = dn;
	}

	public List<ResourceIdentifier> getLen() {
		return len;
	}

	public void setLen(List<ResourceIdentifier> len) {
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

	public void addDN(String dn) {
		this.dn.add(dn);
	}

	public void addLen(ResourceIdentifier resourceIdentifier) {
		this.len.add(resourceIdentifier);
	}

	@Override
	public String toString() {
		return "GetServiceInfoResponse [customerId=" + customerId + ", dn=" + dn + ", len=" + len + ", smId=" + smId
				+ ", nic=" + nic + ", switchId=" + switchId + "]";
	}

}
