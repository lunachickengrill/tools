package eu.vrtime.bootwicketwebapptwo.web;

import org.apache.wicket.extensions.validation.validator.RfcCompliantEmailAddressValidator;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.validation.validator.RangeValidator;
import org.apache.wicket.validation.validator.StringValidator;

import eu.vrtime.bootwicketwebapptwo.model.Customer;

public class CreateCustomerPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6374929067460432106L;
	public static final String FORM_ID = "createCustomerForm";
	public static final String CUSTOMERID_ID = "customerId";
	public static final String FIRSTNAME_ID = "firstName";
	public static final String LASTNAME_ID = "lastName";
	public static final String EMAIL_ID = "emailAddress";
	public static final String CREATEBTN_ID = "createCustomer";
	public static final String FEEDBACKPANEL = "feedback";
	private FeedbackPanel feedbackPanel;

	private Customer customer = new Customer();

	public CreateCustomerPanel(final String id) {
		super(id);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(new Label("createCustomerPanelLabel", "the create customer panel label"));
		feedbackPanel = new FeedbackPanel(FEEDBACKPANEL);
		add(feedbackPanel);

		Form<Customer> createCustomerForm = new Form<Customer>(FORM_ID);
		createCustomerForm.setDefaultModel(new CompoundPropertyModel<>(customer));

		TextField tfCustomerId = new TextField<>(CUSTOMERID_ID);
		tfCustomerId.add(new RangeValidator<Long>(new Long("100"), new Long("99999")));

		TextField tfFirstName = new TextField<>(FIRSTNAME_ID);
		tfFirstName.add(StringValidator.lengthBetween(2, 18));

		TextField tfLastName = new TextField<>(LASTNAME_ID);
		tfLastName.add(StringValidator.lengthBetween(2, 18));

		TextField tfEmail = new TextField<>(EMAIL_ID);
		tfEmail.add(RfcCompliantEmailAddressValidator.getInstance());

		createCustomerForm.add(tfCustomerId);
		createCustomerForm.add(tfFirstName);
		createCustomerForm.add(tfLastName);
		createCustomerForm.add(tfEmail);

		Button createBtn = new Button(CREATEBTN_ID) {

			@Override
			public void onSubmit() {
				super.onSubmit();
				feedbackPanel.info(customer.getCustomerId() + " " + customer.getFirstName() + " "
						+ customer.getLastName() + " " + customer.getEmailAddress());
			}

		};
		createCustomerForm.add(createBtn);
		add(createCustomerForm);

	}

}
