package eu.vrtime.BootWicketWebApp.web;

import javax.inject.Inject;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;

import eu.vrtime.BootWicketWebApp.model.CustomerDTO;
import eu.vrtime.BootWicketWebApp.repositories.CustomerRepository;

public class SearchForm extends Form<CustomerDTO> {

	private CustomerDTO customerDto = new CustomerDTO();
	private TextField<String> customerId = new TextField<>("customerId");
	private TextField<String> email = new TextField<>("email");

	@Inject
	private CustomerRepository customerRepository;

	public SearchForm(String id) {
		super(id);

		CompoundPropertyModel<CustomerDTO> model = new CompoundPropertyModel<CustomerDTO>(customerDto);
		this.setDefaultModel(model);

		add(customerId);
		add(email);
		add(createSearchButton());

	}

	private Button createSearchButton() {
		Button searchButton = new Button("searchButton") {
			@Override
			public void onSubmit() {

			}

		};
		return searchButton;
	}

}
