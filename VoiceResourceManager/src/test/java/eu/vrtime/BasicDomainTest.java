package eu.vrtime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import eu.vrtime.vrm.VoiceResourceManagerApplication;
import eu.vrtime.vrm.domain.Resource;
import eu.vrtime.vrm.domain.SessionManager;
import eu.vrtime.vrm.domain.Softswitch;
import eu.vrtime.vrm.domain.VoiceService;
import eu.vrtime.vrm.domain.shared.ResourceStatus;
import eu.vrtime.vrm.domain.shared.SoftswitchStatus;
import eu.vrtime.vrm.repositories.SessionManagerRepository;
import eu.vrtime.vrm.repositories.SoftswitchRepository;
import eu.vrtime.vrm.service.BasicDomainService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VoiceResourceManagerApplication.class)
public class BasicDomainTest extends BaseTest {

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
	public void createStructure() {

		domainService.addSoftswitch(SWID_1, SWNAME_1, SWSTATUS_1);
		Optional<Softswitch> dbSw = switchRepository.findBySwitchId(SWID_1);
		assertTrue(dbSw.isPresent());

		domainService.addSessionManager(SMID_1, dbSw.get());
		domainService.addSessionManager(SMID_2, dbSw.get());

		Optional<SessionManager> dbSm1 = sessionManagerRepository.findBySmId(SMID_1);
		assertTrue(dbSm1.isPresent());

		Optional<SessionManager> dbSm2 = sessionManagerRepository.findBySmId(SMID_2);
		assertTrue(dbSm2.isPresent());

		Set<Resource> resources1 = fillResources(10);
		resources1.stream().forEach(resource -> domainService.addResource(SMID_1, resource));

		Set<Resource> resources2 = fillResources(20);
		resources2.stream().forEach(resource -> domainService.addResource(SMID_2, resource));

		Optional<Resource> resource = resourceRepository.findTopByStatusAndSessionManagerOrderByOid(ResourceStatus.FREE,
				dbSm1.get());
		assertTrue(resource.isPresent());

		VoiceService voiceService = new VoiceService(SID_1, CUSTID_1, DN_1);
		domainService.addVoiceService(resource.get().getIdentifier(), voiceService);

		Optional<VoiceService> dbService = serviceRepository.findByCustomerId(CUSTID_1);
		assertTrue(dbService.isPresent());

	}

}
