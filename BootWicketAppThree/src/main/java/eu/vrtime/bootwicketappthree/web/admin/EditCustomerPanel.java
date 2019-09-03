package eu.vrtime.bootwicketappthree.web.admin;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import eu.vrtime.bootwicketappthree.model.Customer;
import eu.vrtime.bootwicketappthree.model.CustomerSpecification;
import eu.vrtime.bootwicketappthree.repositories.CustomerRepository;

public class EditCustomerPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3547316812955541782L;
	private static final String FORM_ID = "searchCustomerForm";
	private static final String FORM_CUSTOMERID_ID = "customerId";
	private static final String FORM_FIRSTNAME_ID = "firstName";
	private static final String FORM_LASTNAME_ID = "lastName";
	private static final String FORM_BUTTON_ID = "submit";
	private static final String FEEDBACKPANEL_ID = "feedback";

	private Long oid;
	private String customerId;
	private String firstName;
	private String LastName;
	
	

	@SpringBean
	CustomerRepository customerRepository;
	
	

	public EditCustomerPanel(final String id, IModel<Customer> customerModel) {
		super(id);

	}

	private Form createForm(final String id) {

		Form form = new Form(id);
		form.setDefaultModel(new CompoundPropertyModel(this));

		TextField<Void> tfCustomerId = new TextField<>(FORM_CUSTOMERID_ID);
		tfCustomerId.add(new AttributeModifier("placeholder", "123456"));
		form.add(tfCustomerId);

		TextField<Void> tfFirstname = new TextField<>(FORM_FIRSTNAME_ID);
		tfFirstname.add(new AttributeModifier("placeholder", "firstname"));
		form.add(tfFirstname);

		TextField<Void> tfLastname = new TextField<>(FORM_LASTNAME_ID);
		tfLastname.add(new AttributeModifier("placeholder", "lastname"));
		form.add(tfLastname);

		form.add(new AjaxButton(FORM_BUTTON_ID) {

			private static final long serialVersionUID = 730391491166543176L;

			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				// TODO Auto-generated method stub
				super.onSubmit(target);
				

			}



		});
		return form;
	};

}
