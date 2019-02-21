package eu.vrtime.BootWicketWebApp.web;

import org.apache.wicket.markup.html.basic.Label;

public class HomePage extends AbstractBasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4925547325501010236L;
	private static final String PAGE_LABEL_ID = "homePageLabel";
	private static final String PAGE_LABEL_MODEL = "this is the homePage Label";
	private static final String FORM_ID = "inputFormPanel";

	public HomePage() {
		super();
		add(new Label(PAGE_LABEL_ID, PAGE_LABEL_MODEL));
		add(new InputFormPanel(FORM_ID));
	}

}
