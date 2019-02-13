package eu.vrtime.BootWicketWebApp.web;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;


public class ModalPanel extends Panel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6692757100141085920L;
	
	public ModalPanel(String id) {
		super(id);
		add(new TextField<String>("customerId"));
	}

}
