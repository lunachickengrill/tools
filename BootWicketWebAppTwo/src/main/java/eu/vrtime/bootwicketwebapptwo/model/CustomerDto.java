package eu.vrtime.bootwicketwebapptwo.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CustomerDto {

	public Long oid;
	public Date createDate;
	public Date lastModified;
	public Long customerId;
	public String firstName;
	public String lastName;
	public Set<CustomerServiceDto> services = new HashSet<>();

	public CustomerDto() {
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

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<CustomerServiceDto> getServices() {
		return services;
	}

	public void setServices(Set<CustomerServiceDto> services) {
		this.services = services;
	}
	
	public void addService(CustomerServiceDto service) {
		services.add(service);
	}
	
	public void removeService(CustomerServiceDto service) {
		services.remove(service);
	}

	@Override
	public String toString() {
		return "CustomerDto [oid=" + oid + ", createDate=" + createDate + ", lastModified=" + lastModified
				+ ", customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName + ", services="
				+ services + "]";
	}

}
