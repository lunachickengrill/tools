package eu.vrtime;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import eu.vrtime.vrm.domain.exceptions.VoiceServiceNotFoundException;
import eu.vrtime.vrm.services.AllocateResourceResponse;
import eu.vrtime.vrm.services.ServiceInfoResponse;
import eu.vrtime.vrm.services.VoiceResourceManagementService;

//Enable @Transactional annotation if you want the transaction be rolled back after the test has finished. Disable to get the data written to the test db
//@Transactional
public class VoiceResourceManagerServiceTest extends BaseTest {

	@Autowired
	public VoiceResourceManagementService vrmService;

	@Test
	public void contextLoads() {

	}

	@Test
	public void allocateResourceForOneServiceTest() {

		AllocateResourceResponse resp = vrmService.allocateResource(CUST1_CUSTID, CUST1_SID, CUST1_DN1, "1");
		assertNotNull(resp);

		assertTrue(resp.getCustomerId().equals(CUST1_CUSTID) && resp.getDirectoryNumber().equals(CUST1_DN1));
		System.out.println(resp.toString());

		ServiceInfoResponse svcResp = vrmService.getServiceInfo(CUST1_CUSTID);
		assertNotNull(svcResp);

	}

	@Test
	public void allocateResourceForTwoServicesTest() {

		AllocateResourceResponse respOne = vrmService.allocateResource(CUST2_CUSTID, CUST2_SID, CUST2_DN1, "1");
		assertNotNull(respOne);
		assertTrue(respOne.getCustomerId().equals(CUST2_CUSTID) && respOne.getDirectoryNumber().equals(CUST2_DN1));

		ServiceInfoResponse svcRespOne = vrmService.getServiceInfo(CUST2_CUSTID);
		assertNotNull(svcRespOne);

		AllocateResourceResponse respTwo = vrmService.allocateResource(CUST2_CUSTID, CUST2_SID, CUST2_DN2, "2");
		assertNotNull(respTwo);
		assertTrue(respTwo.getCustomerId().equals(CUST2_CUSTID) && respTwo.getDirectoryNumber().equals(CUST2_DN2));

		ServiceInfoResponse svcRespTwo = vrmService.getServiceInfo(CUST2_CUSTID);
		assertNotNull(svcRespTwo);

	}

	@Test(expected = VoiceServiceNotFoundException.class)
	public void allocateAndReleaseResourceTest() {

		AllocateResourceResponse resp = vrmService.allocateResource(CUST3_CUSTID, CUST3_SID, CUST3_DN1, "1");
		assertNotNull(resp);

		assertTrue(resp.getCustomerId().equals(CUST3_CUSTID) && resp.getDirectoryNumber().equals(CUST3_DN1));
		System.out.println(resp.toString());

		ServiceInfoResponse svcResp = vrmService.getServiceInfo(CUST3_CUSTID);
		assertNotNull(svcResp);

		vrmService.releaseResource(CUST3_CUSTID, CUST3_DN1);

		svcResp = vrmService.getServiceInfo(CUST3_CUSTID);
		System.out.println(svcResp.toString());

	}

}