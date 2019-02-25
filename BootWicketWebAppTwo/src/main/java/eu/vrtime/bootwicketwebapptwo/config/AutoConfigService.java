package eu.vrtime.bootwicketwebapptwo.config;

import java.util.Arrays;
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
	private static final String CUST2_FIRSTNAME = "Walter";
	private static final String CUST2_LASTNAME = "Pürstl";

	private static final Long CUST3_ID = new Long("789");
	private static final String CUST3_FIRSTNAME = "Sabine";
	private static final String CUST3_LASTNAME = "Huber";

	private static final Long CUST4_ID = new Long("159");
	private static final String CUST4_FIRSTNAME = "Gabi";
	private static final String CUST4_LASTNAME = "Schrott";

	private static final Long CUST5_ID = new Long("357");
	private static final String CUST5_FIRSTNAME = "Franz";
	private static final String CUST5_LASTNAME = "Hofer";

	private List<Customer> customers = Arrays.asList(new Customer(CUST1_ID, CUST1_FIRSTNAME, CUST1_LASTNAME),
			new Customer(CUST2_ID, CUST2_FIRSTNAME, CUST2_LASTNAME),
			new Customer(CUST3_ID, CUST3_FIRSTNAME, CUST3_LASTNAME),
			new Customer(CUST4_ID, CUST4_FIRSTNAME, CUST4_LASTNAME),
			new Customer(CUST5_ID, CUST5_FIRSTNAME, CUST5_LASTNAME));

	private CustomerRepository customerRepository;

	@Autowired
	public AutoConfigService(final CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@PostConstruct
	private void createDummyCustomer() {

		customerRepository.saveAll(customers);
		customerRepository.flush();

	}

}
