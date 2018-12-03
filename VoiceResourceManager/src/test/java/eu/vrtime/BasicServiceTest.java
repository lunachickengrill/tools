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

public class BasicServiceTest extends BaseTest {

	public static final VoiceService VS_CUST_3 = new VoiceService("VOIP111111", "111111", "019111111");


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
		assertNotNull(dbSm);
	}

	@Test
	public void allocateResourceForVoiceServiceTest() {

		SessionManager dbSm = infraService.getSessionManagerWithMaxFreeResources();
		Resource res = resourceRepository.findTopByStatusAndSessionManagerOrderByOid(ResourceStatus.FREE, dbSm).get();
		resourceService.allocateResourceForVoiceService(res, VS_CUST_3);
		VoiceService dbVs3 = serviceRepository.findByCustomerId(VS_CUST_3.getCustomerId()).get();
		assertNotNull(dbVs3);
		assertTrue(dbVs3.getCustomerId().equals(VS_CUST_3.getCustomerId()));

	}

	@Test
	@Ignore
	public void allocateSecondLineResourceTest() {

		Optional<VoiceService> voiceService = serviceRepository.findByCustomerId(CUSTID_1);
		assertTrue("voiceService is not present", voiceService.isPresent());

		Resource res = voiceService.get().getResource();
		SessionManager sm = res.getSessionManager();
		assertNotNull("SessionManager is null", sm);

		Optional<Resource> newRes = resourceRepository.findTopByStatusAndSessionManagerOrderByOid(ResourceStatus.FREE,
				sm);
		assertTrue("No new resource", newRes.isPresent());

		resourceService.allocateResourceForVoiceService(newRes.get(), VS_CUST_1_2nd);
		Optional<VoiceService> dbVS = serviceRepository.findByServiceId(VS_CUST_1_2nd.getServiceId());
		assertTrue(dbVS.isPresent());

	}

}
