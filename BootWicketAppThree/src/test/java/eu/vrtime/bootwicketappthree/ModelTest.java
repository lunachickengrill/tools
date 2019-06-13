package eu.vrtime.bootwicketappthree;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;

import eu.vrtime.bootwicketappthree.model.Customer;
import eu.vrtime.bootwicketappthree.model.Device;
import eu.vrtime.bootwicketappthree.model.Service;

public class ModelTest extends AbstractTestBase{
	
	@Ignore
	@Test
	public void createDevice() {
		
		Device dbDevice = deviceRepo.saveAndFlush(DEVICE);
		assertNotNull(dbDevice);
	}
	
	@Ignore
	@Test
	public void createCustomer() {
		
		Customer dbCustomer = customerRepo.saveAndFlush(CUSTOMER);
		assertNotNull(dbCustomer);
		
	}
	
	@Ignore
	@Test
	public void createService() {
		
		Service dbService = serviceRepo.saveAndFlush(SERVICE);
		assertNotNull(dbService);
	}
	
	@Ignore
	@Test
	public void createAll() {
		
		Customer cust = new Customer("123", "firstname", "lastName");	
		Customer dbCustomer = customerRepo.saveAndFlush(cust);
		assertNotNull(dbCustomer);
	
		Service service = new Service("ABC123", "qwer");
		Device device = new Device("0000000000", "ASDBNMASD", "CM");
		service.setDevice(device);
		dbCustomer.addService(service);
		
		dbCustomer = customerRepo.saveAndFlush(dbCustomer);	
		Set<Service> services = dbCustomer.getServices();
		
		assertTrue(services.size()>0);
		assertTrue(deviceRepo.count()>0);
		
		
		
	}
	
	@Test
	public void queryGroupBy() {
		
		List<String> types = deviceRepo.listDeviceTypes();
		assertNotNull(types);
		assertTrue(types.size()>0);
		
		for (String s : types ) {
			System.out.println(s);
		}
	}
}
