package eu.vrtime;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import eu.vrtime.vrm.VoiceResourceManagerApplication;
import eu.vrtime.vrm.domain.Resource;
import eu.vrtime.vrm.domain.Service;
import eu.vrtime.vrm.domain.Softswitch;
import eu.vrtime.vrm.domain.shared.ResourceStatus;
import eu.vrtime.vrm.domain.shared.SoftswitchStatus;
import eu.vrtime.vrm.repositories.ResourceRepository;
import eu.vrtime.vrm.repositories.ServiceRepository;
import eu.vrtime.vrm.repositories.SoftswitchRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VoiceResourceManagerApplication.class)
public class VoiceResourceManagerApplicationTests {

	public static final Softswitch CS2K = new Softswitch("1", "cs2k", SoftswitchStatus.ONLINE);
	public static final Softswitch NGCP = new Softswitch("2", "ngcp", SoftswitchStatus.ONLINE);

	public static final Set<Resource> cs2kResources = new HashSet<>();

	@Before
	public void init() {
		fillSoftswitch();
		fillLens();

	}

	@After
	public void cleanup() {
//		deleteAll();
	}

	@Autowired
	private SoftswitchRepository switchRepository;

	@Autowired
	private ResourceRepository resourceRepository;

	@Autowired
	private ServiceRepository serviceRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void countResources() {
		assertTrue(resourceRepository.count() > 0);

	}

	@Test
	public void getFirstFreeResource() {
		Optional<Resource> dbResource = resourceRepository.findTopByStatusOrderByOid(ResourceStatus.FREE);
		assertTrue(dbResource.isPresent());

	}

	@Test
	public void addService() {
		Service voice = new Service("VOIP00001", "012334434");
		Optional<Resource> dbResource = resourceRepository.findTopByStatusOrderByOid(ResourceStatus.FREE);
		assertTrue(dbResource.isPresent());

		Resource res = dbResource.get();
		res.setStatus(ResourceStatus.ALLOCATED);
		res = resourceRepository.save(res);
		voice.setResource(res);
		Service dbService = serviceRepository.saveAndFlush(voice);
		assertTrue(dbService.getResource() != null);
		assertTrue(dbService.getResource().getStatus().equals(ResourceStatus.ALLOCATED));
		System.out.println(dbService.toString());

	}

	private void fillLens() {
		String prefix = "SS ";
		Integer end = 10;
		Integer point = 0;

		while (point <= end) {

			String len = new String(point.toString());
			len = padLeftZeros(len, 10);

			len = prefix + len;
			System.out.println(len);

			cs2kResources.add(new Resource(CS2K, len, 1, ResourceStatus.FREE));
			point++;
		}

		for (Resource r : cs2kResources) {
			resourceRepository.saveAndFlush(r);
		}

	}

	private static String padLeftZeros(String str, int n) {
		return String.format("%1$" + n + "s", str).replace(' ', '0');
	}
	

	private void fillSoftswitch() {
		switchRepository.save(CS2K);
		switchRepository.save(NGCP);
	}


	private void deleteAll() {
		serviceRepository.deleteAll();
		resourceRepository.deleteAll();
		switchRepository.deleteAll();
	}

}
