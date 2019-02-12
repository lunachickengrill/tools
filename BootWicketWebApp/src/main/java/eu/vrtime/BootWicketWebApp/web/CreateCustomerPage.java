package eu.vrtime.BootWicketWebApp.web;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public class CreateCustomerPage extends WebPage {

	public CreateCustomerPage() {
		super();
		add(new Label("createCustomerLabel", "this is the page to create a new customer"));
		add(new InputFormPanel("createCustomerFormPanel"));

	}

}
