package eu.vrtime.BootWicketWebApp.web;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;

public class InputForm extends Form{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1062248959713797077L;

	public InputForm(String id) {
		super(id);
		
		add(new TextField<String>("firstName"));
		add(new TextField<String>("lastName"));
		add(new TextField<String>("email"));
		
	}

}
