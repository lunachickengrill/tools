package eu.vrtime.bootwicketwebapptwo.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "T_CUSTOMER")
public class Customer extends AbstractBaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5925995521273751086L;

	@Column(nullable = false, unique = true, updatable = true)
	private Long customerId;

	@Column(nullable = true, unique = false, updatable = true)
	private String firstName;

	@Column(nullable = true, unique = false, updatable = true)
	private String lastName;

	@Column(nullable = false, unique = true, updatable = true)
	private String emailAddress;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<CustomerService> services = new HashSet<>();

	public Customer(final Long customerId) {
		this.customerId = customerId;
	}

	public Customer(final Long customerId, final String firstName, final String lastName) {
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Customer(final Long customerId, final String firstName, final String lastName, final String emailAddress) {
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
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

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Set<CustomerService> getServices() {
		return services;
	}

	public void addCustomerService(CustomerService service) {
		services.add(service);
		service.setCustomer(this);

	}

	public void removeCustomerService(CustomerService service) {
		services.remove(service);
		service.setCustomer(null);

	}

	public int getServiceCount() {
		return services.size();
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

	public Customer() {

	}
}
