package eu.vrtime.bootwicketappthree.config;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.vrtime.bootwicketappthree.model.AppUser;
import eu.vrtime.bootwicketappthree.model.Customer;
import eu.vrtime.bootwicketappthree.model.Device;
import eu.vrtime.bootwicketappthree.repositories.AppUserRepository;
import eu.vrtime.bootwicketappthree.repositories.CustomerRepository;
import eu.vrtime.bootwicketappthree.repositories.DeviceRepository;

@Service
public class AutoSetupService {

	private CustomerRepository customerRepo;
	private DeviceRepository deviceRepo;
	private AppUserRepository userRepo;

	@Autowired
	public AutoSetupService(final CustomerRepository customerRepository, final DeviceRepository deviceRepository,
			final AppUserRepository userRepository) {
		this.customerRepo = customerRepository;
		this.deviceRepo = deviceRepository;
		this.userRepo = userRepository;
	}

	@PostConstruct
	public void setupDummyData() {

		createDevices();
		createCustomer();
		createUser();

	}

	private void createUser() {
		Set<AppUser> users = new HashSet<>();

		users.add(new AppUser("test", "lgs123"));
		users.add(new AppUser("tom", "turbo", "tom", "lgs123"));

		userRepo.saveAll(users);
	}

	private void createDevices() {
		Set<Device> devices = new HashSet<>();

		devices.add(new Device("00:00:00:00:00:01", "ABC0001", "CM"));
		devices.add(new Device("01:01:01:05:06:12", "ABC0002", "CM"));
		devices.add(new Device("00:05:46:13:13:13", "ABC0003", "CM"));
		devices.add(new Device("00:05:99:CC:46:26", "ABC0004", "CM"));
		devices.add(new Device("00:05:99:BB:45:46", "ABC0004", "CM"));
		devices.add(new Device("00:05:99:DD:46:46", "ABC0004", "CM"));
		devices.add(new Device("00:05:99:EE:41:46", "ABC0004", "CM"));
		devices.add(new Device("00:05:99:FF:46:49", "ABC0004", "CM"));
		devices.add(new Device("00:05:99:AA:46:76", "ABC0004", "CM"));
		devices.add(new Device("00:01:99:AA:22:22", "ABC0005", "MTA"));
		devices.add(new Device("00:01:99:AA:33:33", "ABC0005", "MTA"));

		deviceRepo.saveAll(devices);

	}

	private void createCustomer() {
		Set<Customer> customers = new HashSet<>();

		customers.add(new Customer("123", "asdf", "asdf"));
		customers.add(new Customer("456", "qwer", "qwer"));
		customers.add(new Customer("789", "yxcxv", "yxyv"));

		customerRepo.saveAll(customers);
	}

}
