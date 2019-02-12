package eu.vrtime.BootWicketWebApp.model;

import java.io.Serializable;

public class CustomerDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 596793723092523250L;

	private Long oid;
	private String customerId;
	private String firstName;
	private String lastName;
	private String email;

	public CustomerDTO() {

	}

	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
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
