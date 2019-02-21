package eu.vrtime.bootwicketwebapptwo.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.vrtime.bootwicketwebapptwo.model.Customer;
import eu.vrtime.bootwicketwebapptwo.repositories.CustomerRepository;

@Service
public class AutoConfigService {
	
	private static final Long CUST1_ID = new Long("123");
	private static final String CUST1_FIRSTNAME = "Hansi";
	private static final String CUST1_LASTNAME = "Meier";
	
	private CustomerRepository customerRepository;
	
	@Autowired
	public AutoConfigService(final CustomerRepository customerRepository) {
		this.customerRepository=customerRepository;
	}
	
	@PostConstruct
	private void createDummyCustomer() {
		Customer cust = new Customer(CUST1_ID);
		cust.setFirstName(CUST1_FIRSTNAME);
		cust.setLastName(CUST1_LASTNAME);
		customerRepository.saveAndFlush(cust);
	}

}
