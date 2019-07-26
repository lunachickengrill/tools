package eu.vrtime.bootwicketappthree.web;

import java.util.Collections;
import java.util.List;

import org.apache.wicket.model.LoadableDetachableModel;

import eu.vrtime.bootwicketappthree.model.Customer;
import eu.vrtime.bootwicketappthree.model.CustomerSpecification;
import eu.vrtime.bootwicketappthree.repositories.CustomerRepository;

public class CustomerModel extends LoadableDetachableModel<List<Customer>> {

	private static final long serialVersionUID = 1L;

	private CustomerRepository customerRepository;
	private CustomerSpecification customerSpecification;

	public CustomerModel(CustomerRepository customerRepository, CustomerSpecification customerSpecification) {
		this.customerRepository = customerRepository;
		this.customerSpecification = customerSpecification;
	}

	@Override
	protected List<Customer> load() {
		return (customerSpecification != null) && ((customerSpecification.getCustomerId() != null)
				|| (customerSpecification.getFirstName() != null) || (customerSpecification.getLastName() != null))
						? customerRepository.findAll(customerSpecification)
						: Collections.emptyList();
	}

	public CustomerRepository getCustomerRepository() {
		return customerRepository;
	}

	public CustomerSpecification getCustomerSpecification() {
		return customerSpecification;
	}

	public void setCustomerSpecification(CustomerSpecification customerSpecification) {
		this.customerSpecification = customerSpecification;
	}

}
