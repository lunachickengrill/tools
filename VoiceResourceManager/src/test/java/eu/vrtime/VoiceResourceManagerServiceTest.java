package eu.vrtime;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.transaction.Transactional;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import eu.vrtime.vrm.api.exceptions.VoiceServiceNotFoundException;
import eu.vrtime.vrm.api.messages.AllocateResourceResponse;
import eu.vrtime.vrm.api.messages.ServiceInfoResponse;
import eu.vrtime.vrm.domain.shared.SwitchId;
import eu.vrtime.vrm.services.VoiceResourceManagementServiceFacade;

//Enable @Transactional annotation if you want the transaction be rolled back after the test has finished. Disable to get the data written to the test db
//@Transactional
public class VoiceResourceManagerServiceTest extends BaseTest {

	@Autowired
	public VoiceResourceManagementServiceFacade vrmService;

	@Test
	public void contextLoads() {

	}

	
	@Ignore
	@Test
	public void allocateResourceTest() {
		AllocateResourceResponse resp = vrmService.allocateResource(CUST1_DN1);
		assertNotNull(resp);
		
		vrmService.releaseResource(CUST1_DN1);
		
	}
	
	@Test
	public void allocateResourceForFirstServiceTest() {

		AllocateResourceResponse resp = vrmService.allocateResource(CUST1_DN1);
		assertNotNull(resp);

		assertTrue(resp.getNumbers().size() > 0);
		System.out.println(resp.toString());

		ServiceInfoResponse svcResp = vrmService.getServiceInfo(CUST1_DN1);
		assertNotNull(svcResp);

	}


	@Test
	public void allocateResourceForSecondServiceTest() {

		AllocateResourceResponse respOne = vrmService.allocateResource(CUST2_DN1,new SwitchId(SWID_NGCP));
		assertNotNull(respOne);
		assertTrue(respOne.getNumbers().size() == 1);
		ServiceInfoResponse svcRespOne = vrmService.getServiceInfo(CUST2_DN1);
		assertTrue(svcRespOne.getSwitchId().equals(SWID_NGCP));

		AllocateResourceResponse respTwo = vrmService.allocateResource(CUST2_DN2, CUST2_DN1);
		assertNotNull(respTwo);
		assertTrue(respTwo.getNumbers().size() == 2);
		ServiceInfoResponse svcRespTwo = vrmService.getServiceInfo(CUST2_DN2);
		assertNotNull(svcRespTwo);

	}

	@Test
	public void allocateAndReleaseResourceTest() {
		
		AllocateResourceResponse resp = vrmService.allocateResource(CUST3_DN1);
		assertNotNull(resp);


		ServiceInfoResponse svcResp = vrmService.getServiceInfo(CUST3_DN1);
		assertNotNull(svcResp);

		vrmService.releaseResource(CUST3_DN1);
		
	}

	@Test(expected = VoiceServiceNotFoundException.class)
	public void allocateAndReleaseResourceExpectExceptionTest() {

		AllocateResourceResponse resp = vrmService.allocateResource( CUST3_DN1);
		assertNotNull(resp);
	
		ServiceInfoResponse svcResp = vrmService.getServiceInfo(CUST3_DN1);
		assertNotNull(svcResp);

		vrmService.releaseResource(CUST3_DN1);

		svcResp = vrmService.getServiceInfo(CUST3_CUSTID);
		System.out.println(svcResp.toString());

	}
	

}
