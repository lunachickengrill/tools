package eu.vrtime.vrm.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import eu.vrtime.vrm.domain.model.Resource;
import eu.vrtime.vrm.domain.model.SessionManager;
import eu.vrtime.vrm.domain.model.Softswitch;
import eu.vrtime.vrm.domain.shared.ResourceIdentifier;
import eu.vrtime.vrm.domain.shared.ResourceStatus;
import eu.vrtime.vrm.domain.shared.SoftswitchStatus;
import eu.vrtime.vrm.repositories.SessionManagerRepository;
import eu.vrtime.vrm.repositories.SoftswitchRepository;

@Service
public class AutConfigService {

	@Value("${vrm.softswitch.cs2k.name}")
	private String SOFTSWITCH_CS2K_NAME;

	@Value("${vrm.softswitch.cs2k.id}")
	private String SOFTSWITCH_CS2K_ID;

	@Value("${vrm.softswitch.ngcp.name}")
	private String SOFTSWITCH_NGCP_NAME;

	@Value("${vrm.softswitch.ngcp.id}")
	private String SOFTSWITCH_NGCP_ID;

	@Value("${vrm.setupconfigdata}")
	private boolean SETUPCONFIGDATA;

	private SoftswitchRepository switchRepository;
	private SessionManagerRepository sessionManagerRepository;

	@Autowired
	public AutConfigService(final SoftswitchRepository switchRepository,
			final SessionManagerRepository sessionManagerRepository) {
		this.switchRepository = switchRepository;
		this.sessionManagerRepository = sessionManagerRepository;

	}

	@PostConstruct
	@Transactional
	private void setup() {
		if (SETUPCONFIGDATA == true) {
			Softswitch dbSw1 = switchRepository
					.save(new Softswitch(SOFTSWITCH_CS2K_ID, SOFTSWITCH_CS2K_NAME, SoftswitchStatus.ONLINE));
			Softswitch dbSw2 = switchRepository
					.save(new Softswitch(SOFTSWITCH_NGCP_ID, SOFTSWITCH_NGCP_NAME, SoftswitchStatus.OFFLINE));

			SessionManager sm1 = new SessionManager("1", dbSw1);
			Set<Resource> res1 = fillResources(10, 100);
			sm1.setResources(res1);
			dbSw1.addSessionManager(sm1);

			SessionManager sm2 = new SessionManager("2", dbSw1);
			Set<Resource> res2 = fillResources(20, 100);
			sm2.setResources(res2);
			dbSw1.addSessionManager(sm2);

			SessionManager sm3 = new SessionManager("3", dbSw1);
			Set<Resource> res3 = fillResources(30, 100);
			sm3.setResources(res3);
			dbSw1.addSessionManager(sm3);

			SessionManager sm4 = new SessionManager("4", dbSw1);
			Set<Resource> res4 = fillResources(40, 100);
			sm4.setResources(res4);
			dbSw1.addSessionManager(sm4);

			SessionManager sm5 = new SessionManager("5", dbSw1);
			Set<Resource> res5 = fillResources(50, 100);
			sm5.setResources(res5);
			dbSw1.addSessionManager(sm5);

			SessionManager sm6 = new SessionManager("6", dbSw1);
			Set<Resource> res6 = fillResources(60, 100);
			sm6.setResources(res6);
			dbSw1.addSessionManager(sm6);

			SessionManager sm7 = new SessionManager("7", dbSw1);
			Set<Resource> res7 = fillResources(70, 100);
			sm7.setResources(res7);
			dbSw1.addSessionManager(sm7);

			SessionManager sm8 = new SessionManager("8", dbSw1);
			Set<Resource> res8 = fillResources(80, 100);
			sm8.setResources(res8);
			dbSw1.addSessionManager(sm8);

			SessionManager sm9 = new SessionManager("9", dbSw1);
			Set<Resource> res9 = fillResources(90, 100);
			sm9.setResources(res9);
			dbSw1.addSessionManager(sm9);

			SessionManager sm20 = new SessionManager("20", dbSw2);
			Set<Resource> res20 = fillResources(20, 10);
			sm20.setResources(res20);
			dbSw2.addSessionManager(sm20);

			dbSw1 = switchRepository.save(dbSw1);
			dbSw2 = switchRepository.save(dbSw2);

		}

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

}
