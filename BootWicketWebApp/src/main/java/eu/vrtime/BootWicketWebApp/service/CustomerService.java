package eu.vrtime.BootWicketWebApp.service;

import eu.vrtime.BootWicketWebApp.model.CustomerDTO;

public interface CustomerService {

	public CustomerDTO findCustomer(CustomerDTO dto);

	public CustomerDTO saveCustomer(CustomerDTO dto);

}
