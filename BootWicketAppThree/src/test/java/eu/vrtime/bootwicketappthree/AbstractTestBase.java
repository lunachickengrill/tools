package eu.vrtime.bootwicketappthree;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import eu.vrtime.bootwicketappthree.BootWicketApplication;
import eu.vrtime.bootwicketappthree.model.Customer;
import eu.vrtime.bootwicketappthree.model.Device;
import eu.vrtime.bootwicketappthree.model.Service;
import eu.vrtime.bootwicketappthree.repositories.CustomerRepository;
import eu.vrtime.bootwicketappthree.repositories.DeviceRepository;
import eu.vrtime.bootwicketappthree.repositories.ServiceRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=BootWicketApplication.class)
public abstract class AbstractTestBase {
	
	public static final Device DEVICE = new Device("01:02:03:04:05:06", "ABC0000001", "CM");
	public static final Customer CUSTOMER = new Customer("1233", "Tom", "Turbo");
	public static final Service SERVICE =  new Service("SID00001", "DUMMY");
	
	@Autowired
	public DeviceRepository deviceRepo;
	
	@Autowired
	public CustomerRepository customerRepo;
	
	@Autowired
	public ServiceRepository serviceRepo;

	@Test
	public void contextLoads() {
	}
	


}
