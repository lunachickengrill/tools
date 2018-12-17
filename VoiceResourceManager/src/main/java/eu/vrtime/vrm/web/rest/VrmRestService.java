package eu.vrtime.vrm.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eu.vrtime.vrm.domain.shared.ResourceIdentifier;
import eu.vrtime.vrm.repositories.ResourceRepository;
import eu.vrtime.vrm.services.AllocateResourceResponse;
import eu.vrtime.vrm.services.BasicInfrastructureService;
import eu.vrtime.vrm.services.ReleaseResourceResponse;
import eu.vrtime.vrm.services.ServiceInfoResponse;
import eu.vrtime.vrm.services.VoiceResourceManagementService;

@RestController
@RequestMapping("/api/rest")
public class VrmRestService {

	// TODO implement proper exception handling

	VoiceResourceManagementService vrmService;

	@Autowired
	public VrmRestService(final VoiceResourceManagementService vrmService) {
		this.vrmService = vrmService;
	}

	@RequestMapping(value = "/allocateResource", method = RequestMethod.POST)
	public ResponseEntity<AllocateResourceResponse> allocateResource(@RequestParam(value = "customerId") String customerId,
			@RequestParam(value = "sid") String sid, @RequestParam(value = "dn") String directoryNumber,
			@RequestParam(value = "lineNo") String lineNo) {

		AllocateResourceResponse resp = vrmService.allocateResource(customerId, sid, directoryNumber, lineNo);

		return new ResponseEntity<AllocateResourceResponse>(resp, HttpStatus.OK);

	}

	@RequestMapping(value = "/releaseResource", method = RequestMethod.POST)
	public ReleaseResourceResponse releaseResource(@RequestParam(value = "customerId") String customerId,
			@RequestParam(value = "sid") String sid, @RequestParam(value = "dn") String directoryNumber,
			@RequestParam(value = "lineNo") String lineNo) {

		ReleaseResourceResponse resp = vrmService.releaseResource(customerId, directoryNumber);

		return resp;
	}

	@RequestMapping(value = "/getServiceInfo", method = RequestMethod.POST)
	public ServiceInfoResponse getServiceInfo(@RequestParam(value = "customerId") String customerId) {

		ServiceInfoResponse resp = vrmService.getServiceInfo(customerId);

		return resp;
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ResponseEntity<ServiceInfoResponse> test() {
		ServiceInfoResponse resp = new ServiceInfoResponse();
		resp.setCustomerId("TEST123");
		resp.addDN("0123456");
		resp.addLen(new ResourceIdentifier("TEST 00 00 01"));
		resp.setNic("123456");
		resp.setSmId("100");
		resp.setSwitchId("TEST SWICHT");

		return new ResponseEntity<ServiceInfoResponse>(resp, HttpStatus.OK);
	}

}
