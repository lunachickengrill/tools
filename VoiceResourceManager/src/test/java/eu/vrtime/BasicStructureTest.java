package eu.vrtime;

import static org.junit.Assert.assertTrue;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import eu.vrtime.vrm.domain.Resource;
import eu.vrtime.vrm.domain.SessionManager;
import eu.vrtime.vrm.domain.Softswitch;
import eu.vrtime.vrm.domain.VoiceService;
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

		infraService.addSoftswitch(SWID_1, SWNAME_1, SWSTATUS_1);
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

}
