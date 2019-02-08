package eu.vrtime.vrm.web.rest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import eu.vrtime.vrm.api.exceptions.StatusCodeException;
import eu.vrtime.vrm.api.messages.AbstractVrmResponse;
import eu.vrtime.vrm.api.messages.AllocateResourceResponse;
import eu.vrtime.vrm.api.messages.LockResourceResponse;
import eu.vrtime.vrm.api.messages.ReleaseResourceResponse;
import eu.vrtime.vrm.api.messages.ServiceInfoResponse;
import eu.vrtime.vrm.domain.shared.ResourceIdentifier;
import eu.vrtime.vrm.domain.shared.SwitchId;
import eu.vrtime.vrm.services.VoiceResourceManagementServiceFacade;

@RestController
@RequestMapping("/api/rest")
public class VrmRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(VrmRestController.class);
	private static final String API_PATH = "/api/rest";

	VoiceResourceManagementServiceFacade vrmService;

	@Autowired
	public VrmRestController(final VoiceResourceManagementServiceFacade vrmService) {
		this.vrmService = vrmService;
	}

	@RequestMapping(value = "/allocateResource", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<AllocateResourceResponse> allocateResource(
			@RequestParam(value = "directoryNumber") String directoryNumber,
			@RequestParam(value = "primaryNumber") Optional<String> primaryNumber,
			@RequestParam(value = "switchId") Optional<SwitchId> switchId) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);

		try {
			if (primaryNumber.isPresent() && (!(switchId.isPresent()))) {
				System.out.println("PrimaryNumber is present: " + primaryNumber.get());
				AllocateResourceResponse resp = vrmService.allocateResource(directoryNumber, primaryNumber.get());

				ResponseEntity<AllocateResourceResponse> re = new ResponseEntity<AllocateResourceResponse>(resp,
						headers, HttpStatus.OK);
				LOGGER.info(re.getHeaders() + " " + re.getBody() + " " + re.getStatusCodeValue());
				return re;
			} else if ((!(primaryNumber.isPresent())) && (switchId.isPresent())) {
				System.out.println("SwitchId is present: " + switchId.toString());
				AllocateResourceResponse resp = vrmService.allocateResource(directoryNumber, switchId.get());

				ResponseEntity<AllocateResourceResponse> re = new ResponseEntity<AllocateResourceResponse>(resp,
						headers, HttpStatus.OK);
				LOGGER.info(re.getHeaders() + " " + re.getBody() + " " + re.getStatusCodeValue());

				return re;
			} else {
				System.out.println("directoryNumber is present: " + directoryNumber);
				AllocateResourceResponse resp = vrmService.allocateResource(directoryNumber);

				ResponseEntity<AllocateResourceResponse> re = new ResponseEntity<AllocateResourceResponse>(resp,
						headers, HttpStatus.OK);
				LOGGER.info(re.getHeaders() + " " + re.getBody() + " " + re.getStatusCodeValue());
				return re;
			}
		} catch (StatusCodeException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}

	@RequestMapping(value = "/releaseResource", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ReleaseResourceResponse> releaseResource(
			@RequestParam(value = "directoryNumber") String directoryNumber) {
		Validate.notNull(directoryNumber, "directoryNumber is null");
		LOGGER.info("Received request on " + API_PATH + "/releaseResource " + " directoryNumber:");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		ReleaseResourceResponse resp;

		try {
			resp = vrmService.releaseResource(directoryNumber);
			LOGGER.debug(resp.toString());

			ResponseEntity<ReleaseResourceResponse> re = new ResponseEntity<ReleaseResourceResponse>(resp, headers,
					HttpStatus.OK);

			LOGGER.info(re.getHeaders() + " " + re.getBody() + " " + re.getStatusCodeValue());

			return re;
		} catch (StatusCodeException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}

	@RequestMapping(value = "/getServiceInfo", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ServiceInfoResponse> getServiceInfo(
			@RequestParam(value = "directoryNumber") String directoryNumber) {
		Validate.notNull(directoryNumber, "dn is null");
		LOGGER.info("Received request on " + API_PATH + "/getServiceInfo " + "directoryNumber: " + directoryNumber);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		ServiceInfoResponse resp;

		try {
			resp = vrmService.getServiceInfo(directoryNumber);

			ResponseEntity<ServiceInfoResponse> re = new ResponseEntity<ServiceInfoResponse>(resp, headers,
					HttpStatus.OK);
			LOGGER.debug(re.getHeaders() + " " + re.getBody() + " " + re.getStatusCodeValue());
			return re;
		} catch (StatusCodeException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}

	@RequestMapping(value = "/lockResource", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<LockResourceResponse> lockResource(
			@RequestParam(value = "resourceIdentifier") ResourceIdentifier resourceIdentifier) {
		Assert.notNull(resourceIdentifier, "resourceIdentifier is null");
		LOGGER.info("Received request on " + API_PATH + "/lockResource " + "resourceIdentifier: " + resourceIdentifier);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		LockResourceResponse resp;

		try {
			resp = vrmService.lockResource(resourceIdentifier);

			ResponseEntity<LockResourceResponse> re = new ResponseEntity<LockResourceResponse>(resp, headers,
					HttpStatus.OK);
			LOGGER.debug(re.getHeaders() + " " + re.getBody() + " " + re.getStatusCodeValue());
			return re;
		} catch (StatusCodeException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getStatusCode());
		}
	}

	@RequestMapping(value = "/testXml", method = RequestMethod.GET, produces = { MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ServiceInfoResponse> testXml() {
		LOGGER.info("Received request on " + API_PATH + "/testXml");

		ServiceInfoResponse resp = new ServiceInfoResponse("0123456");
		resp.addNumber("0123456", "SS 00 01 02");
		resp.setNic("123456");
		resp.setSmId("100");
		resp.setSwitchId("TEST SWITCH");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);

		ResponseEntity<ServiceInfoResponse> re = new ResponseEntity<ServiceInfoResponse>(resp, headers, HttpStatus.OK);
		LOGGER.info(re.getHeaders() + " " + re.getBody() + " " + re.getStatusCodeValue());
		return re;
	}

	public VrmRestController() {
		// TODO Auto-generated constructor stub
	}

}