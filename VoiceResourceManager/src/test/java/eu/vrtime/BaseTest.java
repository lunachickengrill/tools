package eu.vrtime;

import static org.junit.Assert.assertTrue;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import eu.vrtime.vrm.VoiceResourceManagerApplication;
import eu.vrtime.vrm.domain.model.Resource;
import eu.vrtime.vrm.domain.model.SessionManager;
import eu.vrtime.vrm.domain.model.Softswitch;
import eu.vrtime.vrm.domain.model.VoiceService;
import eu.vrtime.vrm.domain.shared.ResourceCountingResult;
import eu.vrtime.vrm.domain.shared.ResourceIdentifier;
import eu.vrtime.vrm.domain.shared.ResourceStatus;
import eu.vrtime.vrm.domain.shared.SoftswitchStatus;
import eu.vrtime.vrm.repositories.ResourceRepository;
import eu.vrtime.vrm.repositories.SessionManagerRepository;
import eu.vrtime.vrm.repositories.SoftswitchRepository;
import eu.vrtime.vrm.repositories.VoiceServiceRepository;
import eu.vrtime.vrm.services.BasicInfrastructureService;
import eu.vrtime.vrm.services.BasicResourceManagementService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VoiceResourceManagerApplication.class)
public class BaseTest {

	public static final String SWID_1 = "1";
	public static final String SWID_2 = "2";
	public static final String SWID_3 = "3";

	public static final String SWNAME_1 = "cs2k";
	public static final String SWNAME_2 = "ngcp";
	public static final String SWNAME_3 = "unitTest";

	public static final SoftswitchStatus SWSTATUS_1 = SoftswitchStatus.ONLINE;
	public static final SoftswitchStatus SWSTATUS_2 = SoftswitchStatus.OFFLINE;
	public static final SoftswitchStatus SWSTATUS_3 = SoftswitchStatus.ONLINE;

	public static final String SMID_1 = "1";
	public static final String SMID_2 = "2";

	public static final String SID_1 = "VOIP000001";
	public static final String CUSTID_1 = "1234567";
	public static final String DN_1 = "01987654";

	public static final String SID_2 = "VOIP000002";
	public static final String CUSTID_2 = "456789";
	public static final String DN_2 = "02987654";

	public static final VoiceService VS_CUST_1 = new VoiceService(SID_1, CUSTID_1, DN_1);
	public static final VoiceService VS_CUST_1_2nd = new VoiceService("VOIP222222", "222222", "019222222");
	public static final VoiceService VS_CUST_2 = new VoiceService(SID_2, CUSTID_2, DN_2);
	

	public static final Softswitch CS2K = new Softswitch("1", "cs2k", SoftswitchStatus.ONLINE);
	public static final Softswitch NGCP = new Softswitch("2", "ngcp", SoftswitchStatus.ONLINE);

	@Autowired
	protected SoftswitchRepository switchRepository;

	@Autowired
	protected SessionManagerRepository sessionManagerRepository;

	@Autowired
	protected ResourceRepository resourceRepository;

	@Autowired
	protected VoiceServiceRepository serviceRepository;

	@Autowired
	protected BasicInfrastructureService infraService;

	@Autowired
	protected BasicResourceManagementService resourceService;

	@Transactional
	protected void deleteAll() {
		serviceRepository.deleteAll();
		serviceRepository.flush();
		resourceRepository.deleteAll();
		resourceRepository.flush();
		switchRepository.deleteAll();
		switchRepository.flush();
		sessionManagerRepository.deleteAll();
		sessionManagerRepository.flush();

	}

	protected void fillSoftswitch() {
		switchRepository.saveAndFlush(CS2K);
		switchRepository.saveAndFlush(NGCP);
	}

	protected Set<Resource> fillResources(Integer range, Integer end) {
		Set<Resource> cs2kResources = new HashSet<>();
		String prefix = "SS ";
		Integer start = 0;
		while (start < end) {
			String len = new String(start.toString());

			len = padLeftZeros(len, 4);
			len = prefix + " " + range + " " + len;
			ResourceIdentifier ri = new ResourceIdentifier(len);
			cs2kResources.add(new Resource(ri, ResourceStatus.FREE));
			start++;
		}

		return cs2kResources;
	}

	private static String padLeftZeros(String str, int n) {
		return String.format("%1$" + n + "s", str).replace(' ', '0');
	}

	public void generateResources() {
		Set<Resource> resources1 = fillResources(10, 10);
		resources1.stream().forEach(resource -> infraService.addResource(SMID_1, resource));

		Set<Resource> resources2 = fillResources(20, 10);
		resources2.stream().forEach(resource -> infraService.addResource(SMID_2, resource));

		Set<Resource> resources3 = fillResources(30, 20);
		resources3.stream().forEach(resource -> infraService.addResource(SMID_2, resource));
	}

	@Transactional
	public void createTestData() {

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

		Optional<Resource> resource1 = resourceRepository
				.findTopByStatusAndSessionManagerOrderByOid(ResourceStatus.FREE, dbSm1.get());
		assertTrue(resource1.isPresent());

		resourceService.allocateResourceForVoiceService(resource1.get(), VS_CUST_1);

		Optional<VoiceService> dbService1 = serviceRepository.findByCustomerId(CUSTID_1);
		assertTrue(dbService1.isPresent());

		Optional<Resource> resource2 = resourceRepository
				.findTopByStatusAndSessionManagerOrderByOid(ResourceStatus.FREE, dbSm1.get());
		assertTrue(resource2.isPresent());

		resourceService.allocateResourceForVoiceService(resource2.get(), VS_CUST_2);

		Optional<VoiceService> dbService2 = serviceRepository.findByCustomerId(CUSTID_2);
		assertTrue(dbService2.isPresent());

		List<ResourceCountingResult> result = resourceRepository.queryResouces();
		assertTrue(result.size() > 0);

		// result.sort(Comparator.comparing(ResourceCountingResult::getCnt).reversed());

		// result.stream().forEach(System.out::println);
	}
}
