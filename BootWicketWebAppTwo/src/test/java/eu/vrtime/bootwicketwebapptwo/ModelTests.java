package eu.vrtime.bootwicketwebapptwo;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import eu.vrtime.bootwicketwebapptwo.model.Customer;
import eu.vrtime.bootwicketwebapptwo.model.CustomerService;
import eu.vrtime.bootwicketwebapptwo.repositories.CustomerRepository;
import eu.vrtime.bootwicketwebapptwo.repositories.CustomerServiceRepository;

public class ModelTests extends BootWicketWebAppTwoApplicationTestBase {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerServiceRepository serviceRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void crudCusterAndService() {
		Customer cust = new Customer(CUST1_CUSTOMERID);
		cust.setFirstName(CUST1_FIRSTNAME);
		cust.setLastName(CUST1_LASTNAME);
		cust.addCustomerService(new CustomerService(SERVICEID_1));
		cust.addCustomerService(new CustomerService(SERVICEID_2));

		Customer dbCustomer = customerRepository.saveAndFlush(cust);
		assertTrue(customerRepository.count() == 1);
		assertTrue(serviceRepository.count() == 2);

		Optional<CustomerService> dbService = serviceRepository.findByServiceId(SERVICEID_1);
		dbCustomer.removeCustomerService(dbService.get());
		dbCustomer = customerRepository.saveAndFlush(dbCustomer);
		assertTrue(dbCustomer.getServiceCount() == 1);

	}

}
