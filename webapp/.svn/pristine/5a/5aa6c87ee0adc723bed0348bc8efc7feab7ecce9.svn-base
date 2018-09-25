package com.addressmanagement;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		// AddressmanagementFacade facade = (AddressmanagementFacadeImpl)
		// context.getBean("addressmanagementFacade");
		// RegionDTO dto = new RegionDTO("", "EU", "test");
		// RegionDTO dbDTO = facade.addRegion(dto);

		// LOGGER.info(dbDTO.toString());

		context.close();

	}

}
