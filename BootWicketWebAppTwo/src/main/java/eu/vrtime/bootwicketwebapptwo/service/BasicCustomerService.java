package eu.vrtime.bootwicketwebapptwo.service;

import eu.vrtime.bootwicketwebapptwo.model.Customer;

public interface BasicCustomerService {
	
	public Customer createCustomer(final Long customerId, final String emailAddress);

}
