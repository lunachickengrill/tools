package eu.vrtime.vrm.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eu.vrtime.vrm.repositories.ResourceRepository;
import eu.vrtime.vrm.services.BasicInfrastructureService;
import eu.vrtime.vrm.services.VoiceResourceManagementService;

@RestController
@RequestMapping("/api/rest")
public class VrmRestService {
	
	@Autowired
	VoiceResourceManagementService voiceService;
	
	@Autowired
	ResourceRepository resourceRepository;
	
	@GetMapping("/getAllResources")
	public Iterable getAll() {
		return resourceRepository.findAll();
	}
	

	
}
