package eu.vrtime.BootWicketWebApp.web;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

public class InputFormPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8656817797213862686L;

	private static final String PANEL_LABEL_ID = "formLabel";
	private static final String PANEL_LABEL_MODEL = "this is the inputFormPanel";
	private static final String FORM_ID = "form";

	public InputFormPanel(String id) {
		super(id);
		add(new Label(PANEL_LABEL_ID, PANEL_LABEL_MODEL));
		add(new InputForm(FORM_ID));
	}

}
