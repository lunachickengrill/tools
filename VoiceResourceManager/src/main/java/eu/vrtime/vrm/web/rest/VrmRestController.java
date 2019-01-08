package eu.vrtime.vrm.web.rest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import org.slf4j.Logger;
import eu.vrtime.vrm.api.exceptions.StatusCodeException;
import eu.vrtime.vrm.api.messages.AllocateResourceResponse;
import eu.vrtime.vrm.api.messages.ReleaseResourceResponse;
import eu.vrtime.vrm.api.messages.ServiceInfoResponse;
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
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AllocateResourceResponse> allocateResource(@RequestParam(value = "dn") String directoryNumber,
			@RequestParam(value = "PrimaryNumber") Optional<String> primaryNumber,
			@RequestParam(value = "SwitchId") Optional<SwitchId> switchId) {

		LOGGER.info("Received request on " + API_PATH + "/allocateResource " + " dn:" + directoryNumber);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		AllocateResourceResponse resp;

		try {
			if (primaryNumber.isPresent()) {
				System.out.println(primaryNumber.get());
				resp = vrmService.allocateResource(directoryNumber, primaryNumber.get());
				LOGGER.debug(resp.toString());

				ResponseEntity<AllocateResourceResponse> re = new ResponseEntity<AllocateResourceResponse>(resp,
						headers, HttpStatus.OK);
				LOGGER.info(re.getHeaders() + " " + re.getBody() + " " + re.getStatusCodeValue());
				return re;
			}

			if (switchId.isPresent()) {
				resp = vrmService.allocateResource(directoryNumber, switchId.get());
				LOGGER.debug(resp.toString());
				ResponseEntity<AllocateResourceResponse> re = new ResponseEntity<AllocateResourceResponse>(resp,
						headers, HttpStatus.OK);
				LOGGER.info(re.getHeaders() + " " + re.getBody() + " " + re.getStatusCodeValue());

				return re;
			}
			resp = vrmService.allocateResource(directoryNumber);
			LOGGER.debug(resp.toString());

			ResponseEntity<AllocateResourceResponse> re = new ResponseEntity<AllocateResourceResponse>(resp, headers,
					HttpStatus.OK);
			LOGGER.info(re.getHeaders() + " " + re.getBody() + " " + re.getStatusCodeValue());
			return re;
		} catch (StatusCodeException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}

	@RequestMapping(value = "/releaseResource", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ReleaseResourceResponse> releaseResource(
			@RequestParam(value = "dn") String directoryNumber) {
		LOGGER.info("Received request on " + API_PATH + "/releaseResource " +  " dn:" + directoryNumber);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
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
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ServiceInfoResponse> getServiceInfo(@RequestParam(value = "customerId") String customerId) {
		LOGGER.info("Received request on " + API_PATH + "/getServiceInfo " + "customerId: " + customerId);
		try {
			ServiceInfoResponse resp = vrmService.getServiceInfo(customerId);
			LOGGER.debug(resp.toString());

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			ResponseEntity<ServiceInfoResponse> re = new ResponseEntity<ServiceInfoResponse>(resp, headers,
					HttpStatus.OK);
			LOGGER.info(re.getHeaders() + " " + re.getBody() + " " + re.getStatusCodeValue());
			return re;
		} catch (StatusCodeException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}

	@RequestMapping(value = "/testXml", method = RequestMethod.GET, produces = { MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ServiceInfoResponse> testXml() {
		LOGGER.info("Received request on " + API_PATH + "/testXml");

		ServiceInfoResponse resp = new ServiceInfoResponse();
		resp.setCustomerId("TEST123");
		resp.addNumber("0123456", "SS 00 01 02");
		resp.addNumber("0198765", "SS 00 02 03");
		resp.setNic("123456");
		resp.setSmId("100");
		resp.setSwitchId("TEST SWICHT");

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
