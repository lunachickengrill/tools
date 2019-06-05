package eu.vrtime.spelstuff;

/**
 * Class holding person properties like age and mail. To be used together with {@link Person}.
 * @author tschwaiger
 *
 */

public class PersonProperty {
	
	private int age;
	private String mail;
	
	public PersonProperty(final int age, final String mail) {
		this.age=age;
		this.mail=mail;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	public String toString() {
		return "PersonProperties [age=" + age + ", mail=" + mail + "]";
	}
	
	

}
