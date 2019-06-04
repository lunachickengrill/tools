package eu.vrtime.bootwicketwebapptwo.service;

import eu.vrtime.bootwicketwebapptwo.model.Customer;

/**
 * A service to be used to create customer objects. Not implemented yet. Possible consumers are the ApiServlet and any wicket component.
 * @author babis
 *
 */

public interface BasicCustomerService {
	
	public Customer createCustomer(final Long customerId, final String emailAddress);

}
