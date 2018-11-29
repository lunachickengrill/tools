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
import org.junit.BeforeClass;
import org.junit.Test;

import eu.vrtime.vrm.domain.model.Resource;
import eu.vrtime.vrm.domain.model.SessionManager;
import eu.vrtime.vrm.domain.model.Softswitch;
import eu.vrtime.vrm.domain.model.VoiceService;
import eu.vrtime.vrm.domain.shared.ResourceCountingResult;
import eu.vrtime.vrm.domain.shared.ResourceIdentifier;
import eu.vrtime.vrm.domain.shared.ResourceStatus;
import eu.vrtime.vrm.repositories.SoftswitchRepository;

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
		
		Softswitch sw = new Softswitch(SWID_1, SWNAME_1, SWSTATUS_1);
		Softswitch dbSW = switchRepository.saveAndFlush(sw);
		assertNotNull(dbSW.hashCode());
		
		SessionManager sm = new SessionManager(SMID_1, dbSW);
		dbSW.addSessionManager(sm);
		dbSW = switchRepository.saveAndFlush(dbSW);
		
		

//		infraService.addSoftswitch(SWID_1, SWNAME_1, SWSTATUS_1);
//		Optional<Softswitch> dbSw = switchRepository.findBySwitchId(SWID_1);
//		assertTrue(dbSw.isPresent());
//
//		infraService.addSessionManager(SMID_1, dbSw.get());
//		infraService.addSessionManager(SMID_2, dbSw.get());
//
//		Optional<SessionManager> dbSm1 = sessionManagerRepository.findBySmId(SMID_1);
//		assertTrue(dbSm1.isPresent());
//
//		Optional<SessionManager> dbSm2 = sessionManagerRepository.findBySmId(SMID_2);
//		assertTrue(dbSm2.isPresent());
//		
//		infraService.addResource(new ResourceIdentifier("SS 00 00 00 01"), dbSm1.get());
		

	}

}
