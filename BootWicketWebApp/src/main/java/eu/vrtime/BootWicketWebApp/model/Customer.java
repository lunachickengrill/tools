package eu.vrtime.BootWicketWebApp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="T_CUSTOMER")
public class Customer extends AbstractBaseEntity{
	
	@Column(name="customer_id", nullable=false, updatable=false, unique=true)
	private String customerId;
	
	@Column(name="first_name", nullable=true, updatable=true, unique=false)
	private String firstName;
	
	@Column(name="last_name", nullable=true, updatable=true, unique=false)
	private String lastName;
	
	@Column(name="email", nullable=false, updatable=true, unique=true)
	private String email;
	
	public Customer(final String customerId, final String email) {
		this.customerId=customerId;
		this.email=customerId;
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
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
