package eu.vrtime.vrm.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eu.vrtime.vrm.repositories.ResourceRepository;
import eu.vrtime.vrm.services.AllocateResourceResponse;
import eu.vrtime.vrm.services.BasicInfrastructureService;
import eu.vrtime.vrm.services.ReleaseResourceResponse;
import eu.vrtime.vrm.services.VoiceResourceManagementService;

@RestController
@RequestMapping("/api/rest")
public class VrmRestService {

	VoiceResourceManagementService vrmService;

	@Autowired
	ResourceRepository resourceRepository;

	@Autowired
	public VrmRestService(final VoiceResourceManagementService vrmService) {
		this.vrmService = vrmService;
	}

	@RequestMapping(value = "/allocateResource", method = RequestMethod.POST)
	public AllocateResourceResponse allocateResource(@RequestParam(value = "customerId") String customerId,
			@RequestParam(value = "sid") String sid, @RequestParam(value = "dn") String directoryNumber,
			@RequestParam(value = "lineNo") String lineNo) {

		// TODO method body
		return null;

	}

	@RequestMapping(value = "/releaseResource", method = RequestMethod.POST)
	public ReleaseResourceResponse releaseResource(@RequestParam(value = "customerId") String customerId,
			@RequestParam(value = "sid") String sid, @RequestParam(value = "dn") String directoryNumber,
			@RequestParam(value = "lineNo") String lineNo) {

		// TODO method body
		return null;
	}

}
