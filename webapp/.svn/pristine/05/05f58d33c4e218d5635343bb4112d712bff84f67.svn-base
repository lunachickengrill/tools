package com.addressmanagement.webapp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.addressmanagement.service.AdministrationService;
import com.addressmanagement.service.dto.CountryDTO;
import com.addressmanagement.service.dto.RegionDTO;

@Controller
public class AdministrationController {

	private static final Logger logger = LoggerFactory.getLogger(AdministrationController.class);

	private static final String OBJECTLIST = "requestList";
	private static final String DTO = "dto";

	@Autowired
	private AdministrationService adminService;

	@RequestMapping(value = "/region", method = RequestMethod.GET)
	public String list(Model model) {
		logger.info("AdminController list all regions");
		List<RegionDTO> result = adminService.listRegions();
		model.addAttribute(OBJECTLIST, result);
		return "list_regions";
	}

	@RequestMapping(value = "region/{id}", method = RequestMethod.GET)
	public String openRegion(@PathVariable("id") String id, Model model) {
		logger.debug("AdminController RegionId: {}", id);
		Long longId = Long.valueOf(id);
		RegionDTO result = adminService.openRegion(longId);
		model.addAttribute(DTO, result);
		return "open_region";
	}
	
	@RequestMapping(value = "country/{id}", method = RequestMethod.GET)
	public String openCountry(@PathVariable("id") String id, Model model) {
		logger.debug("AdminController CountryId: {}", id);
		Long longId = Long.valueOf(id);
		CountryDTO result = adminService.openCountry(longId);
		model.addAttribute(DTO, result);
		return "open_country";
	}

	@RequestMapping(value = "expand/{id}", method = RequestMethod.GET)
	public String expand(@PathVariable("id") String id) {
		logger.info("Expand Region ID {}", id);

		return "redirect:/country/show/" + id;
	}

	@RequestMapping(value = "show/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") String id, Model model) {
		logger.info("Show Region {}", id);

		return "redirect:/region";
	}

	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") String id, Model model) {
		logger.info("Edit Region {}", id);

		return "redirect:/region";
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") String id, Model model) {
		logger.info("Delete Region {}", id);

		return "redirect:/region";
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		logger.info("Create Region");

		return "form_region";
	}

}
