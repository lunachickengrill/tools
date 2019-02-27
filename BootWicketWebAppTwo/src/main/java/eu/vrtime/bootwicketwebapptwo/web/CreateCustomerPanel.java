package eu.vrtime.bootwicketwebapptwo.web;

import org.apache.wicket.extensions.validation.validator.RfcCompliantEmailAddressValidator;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.RangeValidator;
import org.apache.wicket.validation.validator.StringValidator;

import eu.vrtime.bootwicketwebapptwo.model.Customer;
import eu.vrtime.bootwicketwebapptwo.repositories.CustomerRepository;

public class CreateCustomerPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6374929067460432106L;
	private static final String FORM_ID = "createCustomerForm";
	private static final String CUSTOMERID_ID = "customerId";
	private static final String FIRSTNAME_ID = "firstName";
	private static final String LASTNAME_ID = "lastName";
	private static final String EMAIL_ID = "emailAddress";
	private static final String CREATEBTN_ID = "createCustomer";
	private static final String FEEDBACKPANEL_ID = "feedback";
	private FeedbackPanel feedbackPanel;

	private Customer customer;


	@SpringBean
	private CustomerRepository customerRepository;

	public CreateCustomerPanel(final String id) {
		super(id);
		this.customer= new Customer();

	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(feedbackPanel = new FeedbackPanel(FEEDBACKPANEL_ID));
		add(new Label("createCustomerPanelLabel", "the create customer panel label"));

		Form<Customer> createCustomerForm = new Form<Customer>(FORM_ID);
		
		CompoundPropertyModel<Customer> model = new CompoundPropertyModel<Customer>(customer);
		createCustomerForm.setDefaultModel(model);

		TextField tfCustomerId = new TextField<>(CUSTOMERID_ID);
		tfCustomerId.add(new RangeValidator<Long>(new Long("100"), new Long("99999")));

		TextField tfFirstName = new TextField<>(FIRSTNAME_ID);
		tfFirstName.add(StringValidator.lengthBetween(2, 18));

		TextField tfLastName = new TextField<>(LASTNAME_ID);
		tfLastName.add(StringValidator.lengthBetween(2, 18));

		TextField tfEmail = new RequiredTextField(EMAIL_ID);
		tfEmail.add(RfcCompliantEmailAddressValidator.getInstance());

		createCustomerForm.add(tfCustomerId);
		createCustomerForm.add(tfFirstName);
		createCustomerForm.add(tfLastName);
		createCustomerForm.add(tfEmail);

		Button createBtn = new Button(CREATEBTN_ID) {

			@Override
			public void onSubmit() {
				super.onSubmit();
				Customer cust = (Customer) model.getObject();
				Customer dbCustomer = customerRepository.saveAndFlush(cust);

				feedbackPanel.info(customer.getCustomerId() + " " + customer.getFirstName() + " "
						+ customer.getLastName() + " " + customer.getEmailAddress());
			}

		};
		createCustomerForm.add(createBtn);
		add(createCustomerForm);

	}

}
