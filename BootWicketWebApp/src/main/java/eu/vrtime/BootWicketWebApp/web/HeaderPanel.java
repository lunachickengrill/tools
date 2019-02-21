package eu.vrtime.BootWicketWebApp.web;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

public class HeaderPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3063353639607565539L;
	private static final String HEADER_LABEL_ID = "headerLabel";
	private static final String HEADER_LABEL_MODEL = "HERE GOES THE HEADER";

	public HeaderPanel(String id) {
		super(id);
		add(new Label(HEADER_LABEL_ID, HEADER_LABEL_MODEL));
	}

}
