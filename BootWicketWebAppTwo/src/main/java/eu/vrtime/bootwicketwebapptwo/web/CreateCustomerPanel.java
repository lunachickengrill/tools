package eu.vrtime.bootwicketwebapptwo.web;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.validation.validator.RfcCompliantEmailAddressValidator;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
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
	private static final String PANEL_LABEL_ID = "createCustomerPanelLabel";

	TextField tfCustomerId = new TextField<>(CUSTOMERID_ID);
	TextField tfFirstName = new TextField<>(FIRSTNAME_ID);
	TextField tfLastName = new TextField<>(LASTNAME_ID);
	TextField tfEmail = new RequiredTextField<>(EMAIL_ID);

	private FeedbackPanel feedbackPanel;
	private Label panelLabel;
	private CompoundPropertyModel<Customer> model;

	private Customer customer;
	private Form<Customer> createCustomerForm;

	@SpringBean
	private CustomerRepository customerRepository;

	public CreateCustomerPanel(final String id) {
		super(id);
		this.customer = new Customer();
		feedbackPanel = new FeedbackPanel(FEEDBACKPANEL_ID);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		feedbackPanel.setOutputMarkupId(true);
		add(feedbackPanel);
		panelLabel = new Label(PANEL_LABEL_ID, "create a customer");
		panelLabel.setOutputMarkupId(true);
		add(panelLabel);

		createCustomerForm = new Form<Customer>(FORM_ID);

		model = new CompoundPropertyModel<Customer>(customer);
		createCustomerForm.setDefaultModel(model);

//		TextField tfCustomerId = new TextField<>(CUSTOMERID_ID);
		tfCustomerId.add(new RangeValidator<Long>(new Long("100"), new Long("99999")));

//		TextField tfFirstName = new TextField<>(FIRSTNAME_ID);
		tfFirstName.add(StringValidator.lengthBetween(0, 18));

//		TextField tfLastName = new TextField<>(LASTNAME_ID);
		tfLastName.add(StringValidator.lengthBetween(0, 18));

//		TextField tfEmail = new RequiredTextField<>(EMAIL_ID);
		tfEmail.add(RfcCompliantEmailAddressValidator.getInstance());

		createCustomerForm.add(tfCustomerId);
		createCustomerForm.add(tfFirstName);
		createCustomerForm.add(tfLastName);
		createCustomerForm.add(tfEmail);

		createCustomerForm.add(new AjaxButton(CREATEBTN_ID) {

			private static final long serialVersionUID = 6305472785650865717L;

			@Override
			public void onSubmit(AjaxRequestTarget target) {
				super.onSubmit();
				Customer cust = (Customer) model.getObject();
				Customer dbCustomer = customerRepository.saveAndFlush(cust);

				/**
				 * updating the label with a new model
				 */
				feedbackPanel.info("Customer created");
				panelLabel.setDefaultModel(Model.of("customer with oid " + dbCustomer.getOid() + " created."));

				/**
				 * set the current model to null,create a new one with a new customer object and
				 * set it as the defaultModel in order to reset textfields when modalwindow is
				 * opened the time
				 */

				model = new CompoundPropertyModel<Customer>(new Customer());
				createCustomerForm.setDefaultModel(model);

				/**
				 * adding the components that should be updated by the AjaxRequestTarget
				 */

				target.add(panelLabel);
				target.add(feedbackPanel);

				/**
				 * what should this do? It should actually refresh the whole CreateCustomerPanel
				 * component. I expect that the textfields are null after the Ajax OnSubmit.
				 * However it is not working.
				 */

				target.add(this);

			}

		}

		);
		setOutputMarkupId(true);
		add(createCustomerForm);

	}

}
