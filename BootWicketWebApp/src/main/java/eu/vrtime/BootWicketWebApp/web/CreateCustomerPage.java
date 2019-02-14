package eu.vrtime.BootWicketWebApp.web;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public class CreateCustomerPage extends WebPage {

	private static final long serialVersionUID = 6385126903788304109L;
	private static final String LABEL_ID = "createCustomerLabel";
	private static final String LABEL_MODEL = "this is the page to create a new customer";
	private static final String FORM_ID = "createCustomerFormPanel";

	public CreateCustomerPage() {
		super();
		add(new Label(LABEL_ID, LABEL_MODEL));
		add(new InputFormPanel(FORM_ID));

	}

}
