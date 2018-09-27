package eu.vrtime;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
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
import eu.vrtime.vrm.service.BasicSoftswitchService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VoiceResourceManagerApplication.class)
public class VoiceResourceManagerApplicationTests extends BaseTest {

	public static final String SWID_1 = "1";
	public static final String SWID_2 = "2";
	public static final String SWID_3 = "3";

	public static final String SWNAME_1 = "cs2k";
	public static final String SWNAME_2 = "ngcp";
	public static final String SWNAME_3 = "unitTest";

	public static final SoftswitchStatus SWSTATUS_1 = SoftswitchStatus.ONLINE;
	public static final SoftswitchStatus SWSTATUS_2 = SoftswitchStatus.OFFLINE;
	public static final SoftswitchStatus SWSTATUS_3 = SoftswitchStatus.ONLINE;

	public static final Set<Resource> cs2kResources = new HashSet<>();

	@Autowired
	private BasicSoftswitchService softswitchService;
	
	@Autowired
	private SoftswitchRepository softswitchRepository;
	
	@Autowired
	private SessionManagerRepository sessionManagerRepository;

	
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

		Softswitch dbSw = softswitchService.createSoftswitch(SWID_1, SWNAME_1, SWSTATUS_1);
		assertNotNull("softswitch not created", dbSw);
		
		SessionManager sm1 = new SessionManager(1);
		SessionManager sm2 = new SessionManager(2);
		
		dbSw.addSessionManager(sm1);
		dbSw.addSessionManager(sm2);
		
		sm1.setSoftswitch(dbSw);
		sm2.setSoftswitch(dbSw);
		

		SessionManager dbSm1 = sessionManagerRepository.saveAndFlush(sm1);
		SessionManager dbSm2 = sessionManagerRepository.saveAndFlush(sm2);
		assertNotNull(dbSm1);
		assertNotNull(dbSm2);
		
		dbSw = softswitchRepository.saveAndFlush(dbSw);
		
		Optional<SessionManager> dbSm = sessionManagerRepository.findBySmId(1);
		assertNotNull(dbSm.get());
		
	
		Set<Resource> resources = fillResources(dbSm.get());
		
		dbSm.get().setResources(resources);
		

	}



}
