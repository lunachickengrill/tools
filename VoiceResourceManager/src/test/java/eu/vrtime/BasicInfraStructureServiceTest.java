package eu.vrtime;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import eu.vrtime.vrm.domain.model.SessionManager;
import eu.vrtime.vrm.domain.model.Softswitch;
import eu.vrtime.vrm.domain.shared.SwitchId;
import eu.vrtime.vrm.repositories.SoftswitchRepository;
import eu.vrtime.vrm.services.BasicInfrastructureService;

//Enable @Transactional annotation if you want the transaction be rolled back after the test has finished. Disable to get the data written to the test db
//@Transactional
public class BasicInfraStructureServiceTest extends BaseTest {

	// public static final Set<Resource> cs2kResources = new HashSet<>();

	@Autowired
	private BasicInfrastructureService infraService;

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
	public void deleteSessionManagerTest() {

		long cnt = resourceRepository.count();
		assertTrue(cnt > 0);
		System.out.println(cnt);

		Optional<SessionManager> dbSm = sessionManagerRepository.findBySmId("1");
		assertTrue(dbSm.isPresent());

		infraService.deleteSessionManager(dbSm.get());

		System.out.println(resourceRepository.count());

		assertTrue(cnt > resourceRepository.count());

	}

	@Test
	@Ignore
	public void deleteSoftswitchTest() {

		long resCnt = resourceRepository.count();
		long smCnt = sessionManagerRepository.count();

		Optional<Softswitch> dbSw = switchRepository.findBySwitchId(new SwitchId("2"));
		assertTrue(dbSw.isPresent());

		infraService.deleteSoftswitch(dbSw.get());

		assertTrue(resCnt > resourceRepository.count());
		assertTrue(smCnt > sessionManagerRepository.count());

	}

}
