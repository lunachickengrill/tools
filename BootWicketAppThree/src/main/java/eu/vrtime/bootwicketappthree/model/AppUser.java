package eu.vrtime.bootwicketappthree.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;

import eu.vrtime.bootwicketappthree.repositories.AppRoles;

@Entity
public class AppUser extends AbstractBaseEntity {

	@Column(nullable = true, updatable = true)
	private String firstName;

	@Column(nullable = true, updatable = true)
	private String lastName;

	@Column(nullable = false, updatable = false, unique = true)
	private String username;

	@Column(nullable = false, updatable = true, unique = false)
	private String password;

	@Enumerated
	private AppRoles role;

	public AppUser() {

	}

	public AppUser(final String firstName, final String lastName, final String username, final String password,
			final AppRoles role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public AppUser(final String username, final String password) {
		this.username = username;
		this.password = password;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AppRoles getRole() {
		return this.role;
	}

	public void setRole(AppRoles role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "AppUser [firstName=" + firstName + ", lastName=" + lastName + ", username=" + username + ", password="
				+ password + ", role=" + role + "]";
	}

}
