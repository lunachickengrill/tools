package eu.vrtime.bootwicketwebapptwo.web;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import eu.vrtime.bootwicketwebapptwo.model.Customer;

public class EditCustomerPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -387627011053629861L;
	private Customer customer;
	private Label lCustomerId;
	private Label lFirstName;
	private Label lLastName;
	private Label lEmail;

	public EditCustomerPanel(final String id, final Customer customer) {
		super(id);
		this.customer = customer;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		lCustomerId = new Label("lCustomerId", Model.of(customer.getCustomerId()));
		lFirstName = new Label("lFirstName", new PropertyModel<>(customer, "firstName"));
		lLastName = new Label("lLastName", new PropertyModel<>(customer, "lastName"));
		lEmail = new Label("lEmail", new PropertyModel<>(customer, "emailAddress"));

	}

}
