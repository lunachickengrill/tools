package eu.vrtime.BootWicketWebApp.web;

import javax.inject.Inject;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.modelmapper.ModelMapper;
import org.springframework.ui.ModelMap;

import eu.vrtime.BootWicketWebApp.model.Customer;
import eu.vrtime.BootWicketWebApp.model.CustomerDTO;
import eu.vrtime.BootWicketWebApp.repositories.CustomerRepository;

public class CreateCustomerPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6692757100141085920L;
	private CustomerDTO custDto = new CustomerDTO();
	private TextField<String> customerId = new TextField<String>("customerId");
	private TextField<String> firstName = new TextField<String>("firstName");
	private TextField<String> lastName = new TextField<String>("lastName");
	private TextField<String> email = new TextField<String>("email");
	
	
	@Inject
	private CustomerRepository customerRepository;
	
	@Inject
	private ModelMapper mapper;

	public CreateCustomerPanel(String id) {
		super(id);
		add(createForm("createCustomerForm"));
	}

	private Form<CustomerDTO> createForm(String id) {
		Form<CustomerDTO> createCustomerForm = new Form<CustomerDTO>("createCustomerForm");
		CompoundPropertyModel<CustomerDTO> model = new CompoundPropertyModel<CustomerDTO>(custDto);
		createCustomerForm.setDefaultModel(model);
		createCustomerForm.add(customerId);
		createCustomerForm.add(firstName);
		createCustomerForm.add(lastName);
		createCustomerForm.add(email);
		
		createCustomerForm.add(new Button("createCustomer") {
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
