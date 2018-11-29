package eu.vrtime.vrm.domain.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import eu.vrtime.vrm.domain.shared.AbstractBaseEntity;

@Entity
@Table(name = "T_VOICESERVICE")
public class VoiceService extends AbstractBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6895257404688974719L;

	@Column(name = "service_id", nullable = false, updatable = true, unique = true)
	private String serviceId;

	@Column(name = "customer_id", nullable = false, updatable = true, unique = false)
	private String customerId;

	@Column(name = "directory_number", nullable = false, updatable = true, unique = true)
	private String directoryNumber;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "resourceId")
	public Resource resource;

	public VoiceService(String serviceId, String customerId, String directoryNumber) {
		this.serviceId = serviceId;
		this.customerId = customerId;
		this.directoryNumber = directoryNumber;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getDirectoryNumber() {
		return directoryNumber;
	}

	public void setDirectoryNumber(String directoryNumber) {
		this.directoryNumber = directoryNumber;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof VoiceService))
			return false;
		return oid != null && oid.equals(((VoiceService) o).oid);
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public String toString() {
		return "VoiceService [oid=" + toStringOid() + ", serviceId=" + serviceId + ", customerId=" + customerId
				+ ", directoryNumber=" + directoryNumber + ", resource=" + resource.toStringOid() + "]";
	}

	VoiceService() {

	}

}
