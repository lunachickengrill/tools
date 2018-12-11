package eu.vrtime;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import eu.vrtime.vrm.services.AllocateResourceResponse;
import eu.vrtime.vrm.services.VoiceResourceManagementService;

@Transactional
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

	}

	@Test
	public void allocateResourceForTwoServicesTest() {

		AllocateResourceResponse respOne = vrmService.allocateResource(CUST2_CUSTID, CUST2_SID, CUST2_DN1, "1");
		assertNotNull(respOne);
		assertTrue(respOne.getCustomerId().equals(CUST2_CUSTID) && respOne.getDirectoryNumber().equals(CUST2_DN1));

		AllocateResourceResponse respTwo = vrmService.allocateResource(CUST2_CUSTID, CUST2_SID, CUST2_DN2, "2");
		assertNotNull(respTwo);
		assertTrue(respTwo.getCustomerId().equals(CUST2_CUSTID) && respTwo.getDirectoryNumber().equals(CUST2_DN2));

	}

}
