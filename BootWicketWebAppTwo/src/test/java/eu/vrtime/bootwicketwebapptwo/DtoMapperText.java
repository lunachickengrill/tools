package eu.vrtime.bootwicketwebapptwo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import eu.vrtime.bootwicketwebapptwo.model.Customer;
import eu.vrtime.bootwicketwebapptwo.model.CustomerDto;
import eu.vrtime.bootwicketwebapptwo.model.CustomerService;
import eu.vrtime.bootwicketwebapptwo.model.CustomerServiceDto;
import eu.vrtime.bootwicketwebapptwo.repositories.CustomerRepository;

public class DtoMapperText extends BootWicketWebAppTwoApplicationTestBase {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private CustomerRepository customerRepository;

	@Test
	public void mapModelsToDto() {
		Customer cust = new Customer(CUST1_CUSTOMERID);
		cust.setFirstName(CUST1_FIRSTNAME);
		cust.setLastName(CUST1_LASTNAME);
		CustomerService service = new CustomerService(SERVICEID_1);
		service.setServiceName(SERVICENAME_1);
		cust.addCustomerService(service);
		Customer dbCustomer = customerRepository.saveAndFlush(cust);
		assertNotNull(dbCustomer);

		CustomerDto dto = mapper.map(dbCustomer, CustomerDto.class);
		assertTrue(dto.getCustomerId().equals(CUST1_CUSTOMERID));
		assertTrue(dto.getFirstName().equals(CUST1_FIRSTNAME));
		assertTrue(dto.getLastName().equals(CUST1_LASTNAME));

		assertTrue(dto.getServices().size() > 0);
		System.out.println(dto.toString());
		for (CustomerServiceDto cs : dto.getServices()) {
			System.out.println(cs.toString());
		}

	}

	@Test
	public void mapDtoToModels() {
		CustomerDto dto = new CustomerDto();
		dto.setCustomerId(CUST1_CUSTOMERID);
		dto.setFirstName(CUST1_FIRSTNAME);
		dto.setLastName(CUST1_LASTNAME);

		CustomerServiceDto service = new CustomerServiceDto();
		service.setServiceId(SERVICEID_1);
		service.setServiceName(SERVICENAME_1);
		dto.addService(service);

		Customer cust = mapper.map(dto, Customer.class);
		assertTrue(cust.getCustomerId().equals(CUST1_CUSTOMERID));
		assertTrue(cust.getFirstName().equals(CUST1_FIRSTNAME));
		assertTrue(cust.getLastName().equals(CUST1_LASTNAME));
		
		



	}

}
