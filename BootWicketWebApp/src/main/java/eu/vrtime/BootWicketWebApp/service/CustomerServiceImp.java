package eu.vrtime.BootWicketWebApp.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.vrtime.BootWicketWebApp.model.Customer;
import eu.vrtime.BootWicketWebApp.model.CustomerDTO;
import eu.vrtime.BootWicketWebApp.repositories.CustomerRepository;

@Service
public class CustomerServiceImp implements CustomerService {

	private CustomerRepository repository;
	private ModelMapper mapper;

	@Autowired
	public CustomerServiceImp(final CustomerRepository repository, ModelMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public CustomerDTO findCustomer(CustomerDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerDTO saveCustomer(CustomerDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	private Customer fromDto(final CustomerDTO dto) {
		return mapper.map(dto, Customer.class);
	}

	private CustomerDTO toDto(final Customer customer) {
		return mapper.map(customer, CustomerDTO.class);
	}

}
