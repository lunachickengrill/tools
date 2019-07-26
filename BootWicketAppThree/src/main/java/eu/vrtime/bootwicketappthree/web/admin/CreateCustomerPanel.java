package eu.vrtime.bootwicketappthree.web.admin;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import eu.vrtime.bootwicketappthree.model.Customer;
import eu.vrtime.bootwicketappthree.repositories.CustomerRepository;

public class CreateCustomerPanel extends Panel {

	private static final long serialVersionUID = -4136949383848509483L;
	
	public static final String CUSTOMERFORM_ID = "createCustomerForm";
	public static final String CUSTOMERID_ID = "customerId";
	public static final String FIRSTNAME_ID = "firstName";
	public static final String LASTNAME_ID ="lastName";
	public static final String SUBMIT_ID = "submitCustomer";
	public static final String FEEDBACK_ID ="feedback";
	
	private String customerId;
	private String firstName;
	private String lastName;
	
	private PageParameters parameters;
	private CustomerRepository repository;
	
	private FeedbackPanel feedback;

	public CreateCustomerPanel(final String id, PageParameters parameters, CustomerRepository repository) {
		super(id);
		this.parameters=parameters;
		this.repository = repository;
		feedback = new FeedbackPanel(FEEDBACK_ID);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(feedback);
		add(createCustomerForm(CUSTOMERFORM_ID));
		
		
	}
	
	private Form<Void> createCustomerForm(final String id) {
		Form<Void> form = new Form<Void>(id, new CompoundPropertyModel(this));
		
		form.add(new RequiredTextField<>(CUSTOMERID_ID));
		form.add(new RequiredTextField<>(FIRSTNAME_ID));
		form.add(new RequiredTextField<>(LASTNAME_ID));
		
		form.add(new AjaxButton(SUBMIT_ID) {

			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				super.onSubmit(target);
				
				Customer dbCustomer = repository.saveAndFlush(new Customer(customerId,firstName,lastName));
				feedback.info(dbCustomer.toString());
				
			}
			
			
		});
		form.setOutputMarkupId(true);
		
		return form;
	}
	
	
	
	

}
