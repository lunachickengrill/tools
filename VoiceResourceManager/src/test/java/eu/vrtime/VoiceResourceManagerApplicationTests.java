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
import eu.vrtime.vrm.domain.Softswitch;
import eu.vrtime.vrm.domain.VoiceService;
import eu.vrtime.vrm.domain.shared.ResourceStatus;
import eu.vrtime.vrm.domain.shared.SoftswitchStatus;
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
	public void createSoftswitch() {

		Softswitch dbSw = softswitchService.createSoftswitch(SWID_3, SWNAME_3, SWSTATUS_3);
		assertNotNull("softswitch not created", dbSw);
	}

	@Test
	public void countResources() {

		assertTrue(resourceRepository.count() > 0);

	}

	 @Test
	 public void getFirstFreeResource() {
	
	 Optional<Resource> dbResource =
	 resourceRepository.findTopByStatusOrderByOid(ResourceStatus.FREE);
	 assertTrue(dbResource.isPresent());

	 }

	 @Test
	 public void addService() {
	
	 VoiceService voice = new VoiceService("VOIP00001","99119911", "012334434");
	 Optional<Resource> dbResource =
	 resourceRepository.findTopByStatusOrderByOid(ResourceStatus.FREE);
	 assertTrue(dbResource.isPresent());
	
	 Resource res = dbResource.get();
	 res.setStatus(ResourceStatus.ALLOCATED);
	 res = resourceRepository.save(res);
	 voice.setResource(res);
	 VoiceService dbService = serviceRepository.saveAndFlush(voice);
	 assertTrue(dbService.getResource() != null);
	 assertTrue(dbService.getResource().getStatus().equals(ResourceStatus.ALLOCATED));
	 System.out.println(dbService.toString());
	
	 }

}
