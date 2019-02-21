package eu.vrtime.bootwicketwebapptwo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_CUSTOMER_SERVICE")
public class CustomerService extends AbstractBaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6986546218840319528L;

	@Column(nullable = false, unique = true, updatable = true)
	private String serviceId;

	@Column(nullable = true, unique = false, updatable = true)
	private String serviceName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	public CustomerService(final String serviceId) {
		this.serviceId = serviceId;
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof CustomerService))
			return false;
		return getOid() != null && getOid().equals(((CustomerService) obj).getOid());
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public String toString() {
		return "CustomerService [serviceId=" + serviceId + ", serviceName=" + serviceName + ", toStringId()="
				+ toStringOid() + "]";
	}

	CustomerService() {

	}

}
