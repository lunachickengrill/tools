package eu.vrtime.BootWicketWebApp.web;

import javax.inject.Inject;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.modelmapper.ModelMapper;

import eu.vrtime.BootWicketWebApp.model.Customer;
import eu.vrtime.BootWicketWebApp.model.CustomerDTO;
import eu.vrtime.BootWicketWebApp.repositories.CustomerRepository;

public class CreateCustomerPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6692757100141085920L;

	private static final String TEXTFIELD_CUSTOMERID = "customerId";
	private static final String TEXTFIELD_FIRSTNAME = "firstName";
	private static final String TEXTFIELD_LASTNAME = "lastName";
	private static final String TEXTFIELD_EMAIL = "email";
	private static final String FORM_NAME = "createCustomerForm";
	private static final String BUTTON_SUBMIT = "createCustomer";

	private CustomerDTO custDto = new CustomerDTO();
	private TextField<String> customerId = new TextField<String>(TEXTFIELD_CUSTOMERID);
	private TextField<String> firstName = new TextField<String>(TEXTFIELD_FIRSTNAME);
	private TextField<String> lastName = new TextField<String>(TEXTFIELD_LASTNAME);
	private TextField<String> email = new TextField<String>(TEXTFIELD_EMAIL);
	
	private CompoundPropertyModel<CustomerDTO> model;

	@Inject
	private CustomerRepository customerRepository;

	@Inject
	private ModelMapper mapper;

	public CreateCustomerPanel(String id) {
		super(id);
		model = new CompoundPropertyModel<CustomerDTO>(custDto);
		add(createForm(FORM_NAME));
	}

	private Form<CustomerDTO> createForm(String id) {
		Form<CustomerDTO> createCustomerForm = new Form<CustomerDTO>(FORM_NAME);
		createCustomerForm.setDefaultModel(model);
		createCustomerForm.add(customerId);
		createCustomerForm.add(firstName);
		createCustomerForm.add(lastName);
		createCustomerForm.add(email);

		createCustomerForm.add(new Button(BUTTON_SUBMIT) {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				System.out.println(">>> createCustomer Button in CreateCustomerpanel clicked <<<");
				Customer customer = mapper.map(custDto, Customer.class);
				customerRepository.saveAndFlush(customer);

			}

		});

		return createCustomerForm;
	}

}
