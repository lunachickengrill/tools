package eu.vrtime.BootWicketWebApp;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import eu.vrtime.BootWicketWebApp.model.Customer;
import eu.vrtime.BootWicketWebApp.repositories.CustomerRepository;


public class BootWicketWebAppApplicationTests extends BootWicketWebAppTestBase {
	
	@Autowired
	private CustomerRepository customerRepo;

	@Test
	public void contextLoads() {
	}

	@Test
	public void createCustoemrTest() {
		Customer cust = new Customer(CUSTOMERID_1, EMAIL_1);
		cust.setLastName(LASTNAME_1);
		Customer dbCustomer = customerRepo.saveAndFlush(cust);
		assertTrue(dbCustomer.getCustomerId().equals(CUSTOMERID_1));
		assertTrue(dbCustomer.getLastName().equals(LASTNAME_1));
		
		Customer cust2 = new Customer(CUSTOMERID_2, EMAIL_2);
		cust2.setLastName(LASTNAME_2);
		Customer dbCustomer2 = customerRepo.saveAndFlush(cust2);
		assertTrue(dbCustomer2.getCustomerId().equals(CUSTOMERID_2));
		assertTrue(dbCustomer2.getLastName().equals(LASTNAME_2));
	}
}

