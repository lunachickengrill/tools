package eu.vrtime;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import eu.vrtime.vrm.domain.model.Resource;
import eu.vrtime.vrm.domain.model.SessionManager;
import eu.vrtime.vrm.domain.model.VoiceService;
import eu.vrtime.vrm.domain.shared.ResourceCountingResult;
import eu.vrtime.vrm.domain.shared.ResourceStatus;
import eu.vrtime.vrm.repositories.ResourceRepository;
import eu.vrtime.vrm.repositories.SessionManagerRepository;
import eu.vrtime.vrm.repositories.SoftswitchRepository;
import eu.vrtime.vrm.repositories.VoiceServiceRepository;
import eu.vrtime.vrm.services.BasicInfrastructureService;
import eu.vrtime.vrm.services.BasicResourceManagementService;

/**
 * @Transaction
 * Enable if you want the transaction be rolled back after the test has finished. Disable to get the data written to the test db
 * @author tschwaiger
 *
 */
@Transactional
public class BasicResourceManagementServiceTest extends BaseTest {

	@Test
	public void contextLoads() {

	}

	@Test
	public void queryResourcesTest() {
		List<ResourceCountingResult> queryResult = resourceRepository.queryResouces();
		assertTrue(queryResult.size() > 0);

		Long smOid = queryResult.get(0).getSessionManager().getOid();
		assertNotNull(smOid);

		SessionManager dbSM = sessionManagerRepository.findById(smOid).get();
		assertNotNull(dbSM);
	}

	@Test
	public void getSessionManagerWithMaxFreeResourcesTest() {
		SessionManager dbSm = infraService.getSessionManagerWithMaxFreeResources();
		assertNotNull("SessionManager is null", dbSm);
	}

	@Test
	public void allocateResourceForVoiceServiceTest() {

		SessionManager dbSm = infraService.getSessionManagerWithMaxFreeResources();
		assertNotNull("SessionManager is null", dbSm);

		Resource res = resourceService.getFirstAvailableResource(dbSm);
		assertNotNull("Resource is null", res);

		resourceService.allocateResourceForVoiceService(res, VS_CUST1_DN1);
		VoiceService dbVs = serviceRepository.findByCustomerIdAndLineNo(CUST1_CUSTID, 1).get();
		assertNotNull(dbVs);
		assertTrue("CustomerId not matching", dbVs.getCustomerId().equals(CUST1_CUSTID));
		assertTrue("DN not matching", dbVs.getDirectoryNumber().equals(CUST1_DN1));
		assertTrue("LineNo not matching", dbVs.getLineNo() == 1);
		System.out.println("dbVs " + dbVs.toString());
	}

	@Test
	public void allocateSecondLineResourceTest() {

		SessionManager dbSm = infraService.getSessionManagerWithMaxFreeResources();
		assertNotNull("SessionManager is null", dbSm);

		Resource res = resourceService.getFirstAvailableResource(dbSm);
		assertNotNull("Resource is null", res);

		resourceService.allocateResourceForVoiceService(res, VS_CUST2_DN1);
		VoiceService dbVs1 = serviceRepository.findByCustomerIdAndLineNo(CUST2_CUSTID, 1).get();
		assertNotNull("VoiceService is null", dbVs1);
		assertTrue("CustomerId not matching", dbVs1.getCustomerId().equals(CUST2_CUSTID));
		assertTrue("DN not matching", dbVs1.getDirectoryNumber().equals(CUST2_DN1));

		Resource res2 = resourceService.getResourceForSecondService(dbVs1);
		assertNotNull("No resource", res2);

		SessionManager dbSm2 = res2.getSessionManager();
		assertNotNull("No SessionManager", dbSm2);
		assertTrue("SessionManager not matching", dbSm.getSmId().equals(dbSm2.getSmId()));

		resourceService.allocateResourceForVoiceService(res2, VS_CUST2_DN2);
		VoiceService dbVs2 = serviceRepository.findByCustomerIdAndLineNo(CUST2_CUSTID, 2).get();
		assertNotNull("Second VoiceService is null", dbVs2);
		assertTrue("CustomerId not matching", dbVs2.getCustomerId().equals(CUST2_CUSTID));
		assertTrue("DN not matching", dbVs2.getDirectoryNumber().equals(CUST2_DN2));
		assertTrue("LineNo not matching", dbVs2.getLineNo() == 2);
		System.out.println("dbVS2 " + dbVs2.toString());

	}

}
