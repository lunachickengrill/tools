package eu.vrtime.bootwicketwebapptwo.model;

import java.util.Date;

public class CustomerServiceDto {

	public Long oid;
	public Date createDate;
	public Date lastModified;
	public String serviceId;
	public String serviceName;

	public CustomerServiceDto() {
		super();
	}

	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	@Override
	public String toString() {
		return "CustomerServiceDto [oid=" + oid + ", createDate=" + createDate + ", lastModified=" + lastModified
				+ ", serviceId=" + serviceId + ", serviceName=" + serviceName + "]";
	}

}
