package eu.vrtime.BootWicketWebApp.web;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

public class InputFormPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8656817797213862686L;

	public InputFormPanel(String id) {
		super(id);
		add(new Label("form", "here goes the form"));
	}

}
