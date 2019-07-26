package eu.vrtime.bootwicketappthree.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Customer extends AbstractBaseEntity {

	@Column(nullable = false,updatable=false, unique=true)
	private String customerId;

	@Column(nullable=false, updatable=true,unique=false)
	private String firstName;

	@Column(nullable=false, updatable=true, unique=false)
	private String lastName;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Service> services = new HashSet<>();

	public Customer() {

	}

	public Customer(final String customerId, final String firstName, final String lastName) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
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
		lastName = lastName;
	}

	public Set<Service> getServices() {
		return this.services;
	}

	public void addService(final Service service) {
		service.setCustomer(this);
		services.add(service);
	}

	public void removeService(final Service service) {
		service.setCustomer(null);
		services.remove(service);
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	

}
