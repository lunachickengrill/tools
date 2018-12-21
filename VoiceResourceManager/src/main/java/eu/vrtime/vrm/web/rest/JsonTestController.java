package eu.vrtime.vrm.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import eu.vrtime.vrm.api.messages.TestResponse;

@RestController
@RequestMapping("/api/test")
public class JsonTestController {

	@GetMapping(value = "/testJson", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<TestResponse> testJson() {

		return new ResponseEntity<TestResponse>(new TestResponse("123456789", "blabla"), HttpStatus.OK);

	}

	@GetMapping(value = "/testJson2", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody TestResponse testJson2() {
				return new TestResponse("123456789", "dududu");
	}
	
	@GetMapping(value = "/testJson3", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TestResponse> testJson3() {
		return new ResponseEntity<TestResponse>(new TestResponse("123456789", "dududu"), HttpStatus.OK);
	}

}
