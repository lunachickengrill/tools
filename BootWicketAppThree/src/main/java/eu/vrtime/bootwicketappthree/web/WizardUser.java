package eu.vrtime.bootwicketappthree.web;

public class WizardUser {

	private String username;

	private String mail;

	private String role;

	public WizardUser(final String username, final String mail, final String role) {
		this.username = username;
		this.mail = mail;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "WizardUser [username=" + username + ", mail=" + mail + ", role=" + role + "]";
	}

}
