package eu.vrtime;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import eu.vrtime.vrm.domain.model.Resource;
import eu.vrtime.vrm.domain.model.SessionManager;
import eu.vrtime.vrm.domain.shared.ResourceIdentifier;
import eu.vrtime.vrm.domain.shared.ResourceStatus;
import eu.vrtime.vrm.repositories.SessionManagerRepository;
import eu.vrtime.vrm.repositories.SoftswitchRepository;

public class AutoConfigServiceTest extends BaseTest {
	
	@Autowired
	private SoftswitchRepository switchRepository;
	
	@Autowired
	private SessionManagerRepository sessionManagerRepository;

	@Test
	public void contextLoads() {

	}
	
	@Test
	@Transactional
	public void initialDataTest() {
		long countSwitch = switchRepository.count();
		assertTrue(countSwitch>0);
					
		Set<SessionManager> dbSm = switchRepository.findBySwitchId("1").get().getSessionManagers();
		assertTrue(dbSm.size()>0);	
		
	}
	
	@Test
	@Transactional
	public void addResource() {
		Resource r = new Resource(new ResourceIdentifier("SS 00 00 00 02"), ResourceStatus.FREE);
		
		SessionManager dbSm = sessionManagerRepository.findBySmId("1").get();
		dbSm.addResource(r);
		dbSm = sessionManagerRepository.save(dbSm);
		assertTrue(dbSm.getResources().size()>0);
	}

}
