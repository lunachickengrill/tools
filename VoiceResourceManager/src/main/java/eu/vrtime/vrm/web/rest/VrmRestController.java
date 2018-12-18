package eu.vrtime.vrm.web.rest;

import java.sql.SQLException;

import org.h2.jdbc.JdbcSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import eu.vrtime.vrm.api.exceptions.DataNotFoundException;
import eu.vrtime.vrm.api.exceptions.NoFreeResourcesException;
import eu.vrtime.vrm.api.exceptions.ResourceNotFoundException;
import eu.vrtime.vrm.api.exceptions.SessionManagerNotFoundException;
import eu.vrtime.vrm.api.exceptions.SoftswitchNotFoundException;
import eu.vrtime.vrm.api.exceptions.StatusCodeException;
import eu.vrtime.vrm.api.exceptions.VoiceServiceNotFoundException;
import eu.vrtime.vrm.api.messages.AllocateResourceResponse;
import eu.vrtime.vrm.api.messages.ReleaseResourceResponse;
import eu.vrtime.vrm.api.messages.ServiceInfoResponse;
import eu.vrtime.vrm.api.messages.TestJsonResponse;
import eu.vrtime.vrm.domain.shared.ResourceIdentifier;
import eu.vrtime.vrm.repositories.ResourceRepository;
import eu.vrtime.vrm.services.BasicInfrastructureService;
import eu.vrtime.vrm.services.VoiceResourceManagementService;

@RestController
@RequestMapping("/api/rest")
public class VrmRestController {

	VoiceResourceManagementService vrmService;

	@Autowired
	public VrmRestController(final VoiceResourceManagementService vrmService) {
		this.vrmService = vrmService;
	}

	@RequestMapping(value = "/allocateResource", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<AllocateResourceResponse> allocateResource(
			@RequestParam(value = "customerId") String customerId, @RequestParam(value = "sid") String sid,
			@RequestParam(value = "dn") String directoryNumber, @RequestParam(value = "lineNo") String lineNo) {
		try {
			AllocateResourceResponse resp = vrmService.allocateResource(customerId, sid, directoryNumber, lineNo);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_XML);
			return new ResponseEntity<AllocateResourceResponse>(resp, headers, HttpStatus.OK);
		} catch (StatusCodeException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
	}

	@RequestMapping(value = "/releaseResource", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ReleaseResourceResponse> releaseResource(
			@RequestParam(value = "customerId") String customerId, @RequestParam(value = "sid") String sid,
			@RequestParam(value = "dn") String directoryNumber, @RequestParam(value = "lineNo") String lineNo) {

		try {
			ReleaseResourceResponse resp = vrmService.releaseResource(customerId, directoryNumber);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_XML);

			return new ResponseEntity<ReleaseResourceResponse>(resp, headers, HttpStatus.OK);
		} catch (StatusCodeException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
	}

	@RequestMapping(value = "/getServiceInfo", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ServiceInfoResponse> getServiceInfo(@RequestParam(value = "customerId") String customerId) {

		try {
			ServiceInfoResponse resp = vrmService.getServiceInfo(customerId);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_XML);
			return new ResponseEntity<ServiceInfoResponse>(resp, headers, HttpStatus.OK);
		} catch (StatusCodeException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
	}

	@RequestMapping(value = "/testXml", method = RequestMethod.GET, produces = { "application/xml", "text/xml" })
	public ResponseEntity<ServiceInfoResponse> testXml() {
		ServiceInfoResponse resp = new ServiceInfoResponse();
		resp.setCustomerId("TEST123");
		resp.addNumber("0123456", "SS 00 01 02");
		resp.addNumber("0198765", "SS 00 02 03");
		resp.setNic("123456");
		resp.setSmId("100");
		resp.setSwitchId("TEST SWICHT");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);

		return new ResponseEntity<ServiceInfoResponse>(resp, headers, HttpStatus.OK);
	}

}
