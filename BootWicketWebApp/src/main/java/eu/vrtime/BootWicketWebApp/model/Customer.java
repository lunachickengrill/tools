package eu.vrtime.BootWicketWebApp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "T_CUSTOMER")
public class Customer extends AbstractBaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 972304658324448565L;

	@Column(name = "customer_id", nullable = false, updatable = false, unique = true)
	private String customerId;

	@Column(name = "first_name", nullable = true, updatable = true, unique = false)
	private String firstName;

	@Column(name = "last_name", nullable = true, updatable = true, unique = false)
	private String lastName;

	@Column(name = "email", nullable = false, updatable = true, unique = true)
	private String email;

	public Customer(final String customerId, final String email) {
		this.customerId = customerId;
		this.email = customerId;
	}

	public Customer() {

	}

	public Customer(final Customer customer) {
		toClone(customer);

	}

	private void toClone(Customer customer) {
		if (!(customer.getCustomerId().isEmpty()) && customer.getCustomerId() != null)
			this.customerId = customer.getCustomerId();
		if (!(customer.getFirstName().isEmpty()) && customer.getFirstName() != null)
			this.firstName = customer.getFirstName();
		if (!(customer.getLastName().isEmpty()) && customer.getLastName() != null)
			this.lastName = customer.getLastName();
		if (!(customer.getEmail().isEmpty()) && customer.getEmail() != null)
			this.email = customer.getEmail();
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

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + "]";
	}

}
