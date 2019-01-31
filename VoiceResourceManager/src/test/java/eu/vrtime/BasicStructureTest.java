package eu.vrtime;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import eu.vrtime.vrm.domain.model.Resource;
import eu.vrtime.vrm.domain.model.SessionManager;
import eu.vrtime.vrm.domain.model.Softswitch;
import eu.vrtime.vrm.domain.model.VoiceService;
import eu.vrtime.vrm.domain.shared.ResourceCountingResult;
import eu.vrtime.vrm.domain.shared.ResourceStatus;

public class BasicStructureTest extends BaseTest {

	public static final Set<Resource> cs2kResources = new HashSet<>();

	@Before
	public void init() {

	}

	@After
	public void cleanup() {

	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void testCreateStructure() {

		infraService.addSoftswitch(SWID_1, SWNAME_1, SWSTATUS_1,NIC_1);
		Optional<Softswitch> dbSw = switchRepository.findBySwitchId(SWID_1);
		assertTrue(dbSw.isPresent());

		infraService.addSessionManager(SMID_1, dbSw.get());
		infraService.addSessionManager(SMID_2, dbSw.get());

		Optional<SessionManager> dbSm1 = sessionManagerRepository.findBySmId(SMID_1);
		assertTrue(dbSm1.isPresent());

		Optional<SessionManager> dbSm2 = sessionManagerRepository.findBySmId(SMID_2);
		assertTrue(dbSm2.isPresent());

		generateResources();

		Optional<Resource> resource = resourceRepository.findTopByStatusAndSessionManagerOrderByOid(ResourceStatus.FREE,
				dbSm1.get());
		assertTrue(resource.isPresent());

		VoiceService voiceService = new VoiceService(SID_1, CUSTID_1, DN_1);
		resourceService.allocateResourceForVoiceService(resource.get().getIdentifier(), voiceService);

		Optional<VoiceService> dbService = serviceRepository.findByCustomerId(CUSTID_1);
		assertTrue(dbService.isPresent());

		List<ResourceCountingResult> result = resourceRepository.queryResouces();
		assertTrue(result.size() > 0);

		result.sort(Comparator.comparing(ResourceCountingResult::getCnt).reversed());

		result.stream().forEach(System.out::println);

	}
	
	@Test
	@Ignore
	public void testGetOidPrePesist() {
		
		Softswitch sw = new Softswitch(SWID_2, SWNAME_2, SWSTATUS_2,NIC_1);
		assertNull(sw.getOid());
		
	}

}
