package eu.vrtime.spelstuff;

/**
 * A simple class to represent a person. References {@link PersonProperty} with additional data.
 * @author tschwaiger
 *
 */

public class Person {

	private String firstname;
	private String lastname;
	private String value;
	private PersonProperty property;

	public Person(final String firstname, final String lastname, final String value, final int age, final String mail) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.value = value;
		this.setProperty(new PersonProperty(age, mail));
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public PersonProperty getProperty() {
		return property;
	}

	public void setProperty(PersonProperty property) {
		this.property = property;
	}

	@Override
	public String toString() {
		return "DummyObject [firstname=" + firstname + ", lastname=" + lastname + ", value=" + value + "]";
	}

}
