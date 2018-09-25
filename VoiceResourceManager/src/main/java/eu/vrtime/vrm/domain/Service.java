package eu.vrtime.vrm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import eu.vrtime.vrm.domain.shared.AbstractBaseEntity;

@Entity
public class Service extends AbstractBaseEntity {

	@Column(name = "service_id", nullable = false, updatable = true)
	private String serviceId;

	@Column(name = "directory_number", nullable = false, updatable = true)
	private String directoryNumber;

	@OneToOne
	@JoinColumn(name = "resourceId")
	public Resource resource;

	public Service(String serviceId, String directoryNumber) {
		this.serviceId = serviceId;
		this.directoryNumber = directoryNumber;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
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
	public String toString() {
		return "Service [serviceId=" + serviceId + ", directoryNumber=" + directoryNumber + ", resource=" + resource
				+ ", oid=" + oid + ", createDate=" + createDate + ", lastModified=" + lastModified + "]";
	}

	Service() {

	}

}
