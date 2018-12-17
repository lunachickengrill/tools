package eu.vrtime.vrm.web.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import eu.vrtime.vrm.services.TestJsonResponse;

@RestController
@RequestMapping("/test/json")
public class JsonTestController {

	@RequestMapping(value = "/testJson", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> testJson() {

		return new ResponseEntity<String>(new String("test123"), HttpStatus.OK);

	}

}
