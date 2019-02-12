package eu.vrtime.BootWicketWebApp;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import eu.vrtime.BootWicketWebApp.model.Customer;
import eu.vrtime.BootWicketWebApp.model.CustomerDTO;
import eu.vrtime.BootWicketWebApp.repositories.CustomerRepository;

public class DTOConversionTests extends BootWicketWebAppTestBase {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private CustomerRepository repository;

	@Test
	public void contextLoads() {

	}

	@Ignore
	@Test
	public void toDtoSimple() {
		Customer cust = new Customer(CUSTOMERID_1, EMAIL_1);
		cust.setFirstName(FIRSTNAME_1);
		cust.setLastName(LASTNAME_1);

		CustomerDTO custDTO = mapper.map(cust, CustomerDTO.class);

		assertTrue(custDTO.getCustomerId().equals(CUSTOMERID_1));
		assertTrue(custDTO.getEmail().endsWith(EMAIL_1));
		assertTrue(custDTO.getFirstName().equals(FIRSTNAME_1));
		assertTrue(custDTO.getLastName().equals(LASTNAME_1));

	}

	@Ignore
	@Test
	public void fromDtoSimple() {
		CustomerDTO dto = new CustomerDTO();
		dto.setCustomerId(CUSTOMERID_2);
		dto.setEmail(EMAIL_2);
		dto.setFirstName(FIRSTNAME_2);
		dto.setLastName(LASTNAME_2);

		Customer cust = mapper.map(dto, Customer.class);

		assertTrue(cust.getCustomerId().equals(dto.getCustomerId()));
		assertTrue(cust.getEmail().equals(dto.getEmail()));
		assertTrue(cust.getFirstName().equals(dto.getFirstName()));
		assertTrue(cust.getLastName().equals(dto.getLastName()));

	}



	@Test
	public void toDtoSimplePersisted() {
		Customer cust = new Customer(CUSTOMERID_1, EMAIL_1);
		cust.setFirstName(FIRSTNAME_1);
		cust.setLastName(LASTNAME_1);
		Customer dbCust = repository.saveAndFlush(cust);

		assertNotNull(dbCust.getOid());

		CustomerDTO dto = mapper.map(dbCust, CustomerDTO.class);

		assertNotNull(dto.getOid());
		assertTrue(dto.getCustomerId().equals(CUSTOMERID_1));
		assertTrue(dto.getEmail().endsWith(EMAIL_1));
		assertTrue(dto.getFirstName().equals(FIRSTNAME_1));
		assertTrue(dto.getLastName().equals(LASTNAME_1));
	}
	
	@Test
	public void fromDtoSimplePersisted() {
		CustomerDTO dto = new CustomerDTO();
		dto.setCustomerId(CUSTOMERID_2);
		dto.setEmail(EMAIL_2);
		dto.setFirstName(FIRSTNAME_2);
		dto.setLastName(LASTNAME_2);
		
		Customer cust = mapper.map(dto, Customer.class);
		Customer dbCust = repository.saveAndFlush(cust);
		
		assertNotNull(dbCust.getOid());
		assertTrue(dbCust.getCustomerId().equals(dto.getCustomerId()));
		assertTrue(dbCust.getEmail().equals(dto.getEmail()));
		assertTrue(dbCust.getFirstName().equals(dto.getFirstName()));
		assertTrue(dbCust.getLastName().equals(dto.getLastName()));
		
		
	}

}
