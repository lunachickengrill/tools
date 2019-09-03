package eu.vrtime.bootwicketappthree.web.admin;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import eu.vrtime.bootwicketappthree.model.Customer;
import eu.vrtime.bootwicketappthree.model.CustomerSpecification;
import eu.vrtime.bootwicketappthree.repositories.CustomerRepository;

public class EditCustomerPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3547316812955541782L;
	private static final String FORM_ID = "editCustomerForm";
	private static final String FORM_CUSTOMERID_ID = "customerId";
	private static final String FORM_FIRSTNAME_ID = "firstName";
	private static final String FORM_LASTNAME_ID = "lastName";
	private static final String FORM_BUTTON_ID = "submitCustomer";
	private static final String FEEDBACKPANEL_ID = "feedback";

	private FeedbackPanel feedback;
	private Label panelLabel;

	IModel<Customer> customerModel;
	Customer customer;

	@SpringBean
	CustomerRepository customerRepository;

	public EditCustomerPanel(final String id, IModel<Customer> customerModel) {
		super(id);
		this.customerModel = customerModel;
		this.customer = customerModel.getObject();
		feedback = new FeedbackPanel(FEEDBACKPANEL_ID);
		panelLabel = new Label("editCustomerPanelLabel", "EDIT CUSTOMER PANEL");

	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(createForm(FORM_ID));
		add(panelLabel);
		add(feedback);

	}

	private Form<Customer> createForm(final String id) {

		Form<Customer> form = new Form<Customer>(id);
		form.setDefaultModel(new CompoundPropertyModel<Customer>(customer));

		RequiredTextField<Void> tfCustomerId = new RequiredTextField<>(FORM_CUSTOMERID_ID);
		form.add(tfCustomerId);

		RequiredTextField<Void> tfFirstname = new RequiredTextField<>(FORM_FIRSTNAME_ID);
		form.add(tfFirstname);

		RequiredTextField<Void> tfLastname = new RequiredTextField<>(FORM_LASTNAME_ID);
		form.add(tfLastname);
		form.setOutputMarkupId(true);

		form.add(new AjaxButton(FORM_BUTTON_ID) {

			private static final long serialVersionUID = 730391491166543176L;

			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				super.onSubmit(target);

				Customer dbCustomer = customerRepository.saveAndFlush(customer);
				feedback.info(dbCustomer.getId());

			}

		});
		return form;
	};

}
