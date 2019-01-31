package eu.vrtime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import eu.vrtime.vrm.VoiceResourceManagerApplication;
import eu.vrtime.vrm.web.rest.VrmRestController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VoiceResourceManagerApplication.class)
public class RestControllerTest {
	
private MockMvc mockMvc;

@Before
public void setup() {
	this.mockMvc = MockMvcBuilders.standaloneSetup(new VrmRestController()).build();
}

	
	@Test
	public void allocateResourceTest() throws Exception {
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/rest/allocateResource").param("dn", "0123456").accept(MediaType.APPLICATION_JSON);
			
		this.mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
		
//		System.out.println(result.toString());
		
	}

}
