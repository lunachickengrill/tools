package eu.vrtime.bootwicketwebapptwo.config;



import java.util.ArrayList;
import java.util.List;

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
	
	private static final Long CUST2_ID = new Long("456");
	private static final String CUST2_FIRSTNAME ="Hugo";
	private static final String CUST2_LASTNAME = "Pürstl";
	
	private List<Customer> customerList = new ArrayList<>();
	
	
	private CustomerRepository customerRepository;
	
	@Autowired
	public AutoConfigService(final CustomerRepository customerRepository) {
		this.customerRepository=customerRepository;
	}
	
	@PostConstruct
	private void createDummyCustomer() {
		customerList.add(new Customer(CUST1_ID, CUST1_FIRSTNAME,CUST1_LASTNAME));
		customerList.add(new Customer(CUST2_ID, CUST2_FIRSTNAME, CUST2_LASTNAME));
		
		customerRepository.saveAll(customerList);
		customerRepository.flush();

	}

}
