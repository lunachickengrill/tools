package eu.vrtime.bootwicketappthree.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class AppUser extends AbstractBaseEntity {

	@Column(nullable = true, updatable = true)
	public String firstName;

	@Column(nullable = true, updatable = true)
	public String lastName;

	@Column(nullable = false, updatable = false, unique = true)
	public String username;

	@Column(nullable = false, updatable = true, unique = false)
	public String password;

	public AppUser() {

	}

	public AppUser(final String firstName, final String lastName, final String username, final String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
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

	@Override
	public String toString() {
		return "AppUser [username=" + username + ", password=" + password + "]";
	}

}
