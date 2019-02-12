package eu.vrtime.BootWicketWebApp.web;

import javax.inject.Inject;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;

import eu.vrtime.BootWicketWebApp.model.Customer;
import eu.vrtime.BootWicketWebApp.repositories.CustomerRepository;

public class InputForm extends Form<Customer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1062248959713797077L;
	private Customer customer = new Customer();
	
	
	@Inject
	private CustomerRepository customerRepo;

	public InputForm(String id) {
		super(id);
		CompoundPropertyModel<Customer> model = new CompoundPropertyModel<Customer>(customer);
		setDefaultModel(model);
		
		add(new TextField<String>("customerId"));
		add(new TextField<String>("firstName"));
		add(new TextField<String>("lastName"));
		add(new TextField<String>("email"));

//		add(new TextField<String>("customerId", new PropertyModel<String>(customer, "customerId")));
//		add(new TextField<String>("firstName", new PropertyModel<String>(customer, "firstName")));
//		add(new TextField<String>("lastName", new PropertyModel<String>(customer, "lastName")));
//		add(new TextField<String>("email", new PropertyModel<String>(customer, "email")));
		add(addSubmitButton());

	}

	private Button addSubmitButton() {
		Button submitButton = new Button("submitButton") {

			@Override
			public void onSubmit() {
				// TODO Auto-generated method stub
				super.onSubmit();
				
				System.out.println(">>> SUBMITBUTTON CLICKED <<<");
				Customer dbCustomer = customerRepo.saveAndFlush(customer);
				System.out.println(">>> dbCustomerdb <<<" + dbCustomer.toString());
			}

		};
		return submitButton;
	}

}
