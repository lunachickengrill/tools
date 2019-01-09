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
import eu.vrtime.vrm.repositories.ResourceLogRepository;
import eu.vrtime.vrm.repositories.ResourceRepository;
import eu.vrtime.vrm.repositories.SessionManagerRepository;
import eu.vrtime.vrm.repositories.SoftswitchRepository;
import eu.vrtime.vrm.repositories.VoiceServiceRepository;
import eu.vrtime.vrm.services.BasicInfrastructureService;
import eu.vrtime.vrm.services.BasicResourceService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VoiceResourceManagerApplication.class)
public class BaseTest {

	public static final String CUST1_SID = "VOIP000001";
	public static final String CUST1_CUSTID = "1234567";
	public static final String CUST1_DN1 = "0111111";
	public static final String CUST1_DN2 = "0111112";

	public static final String CUST2_SID = "VOIP000002";
	public static final String CUST2_CUSTID = "456789";
	public static final String CUST2_DN1 = "0122221";
	public static final String CUST2_DN2 = "0122222";

	public static final String CUST3_SID = "VOIP111111";
	public static final String CUST3_CUSTID = "333333";
	public static final String CUST3_DN1 = "019111111";
	public static final String CUST3_DN2 = "019111112";

	public static final VoiceService VS_CUST1_DN1 = new VoiceService(CUST1_DN1);
	public static final VoiceService VS_CUST1_DN2 = new VoiceService(CUST1_DN2);

	public static final VoiceService VS_CUST2_DN1 = new VoiceService( CUST2_DN1);
	public static final VoiceService VS_CUST2_DN2 = new VoiceService(CUST2_DN2);

	public static final VoiceService VS_CUST3_DN1 = new VoiceService(CUST3_DN1);
	public static final VoiceService VS_CUST3_DN2 = new VoiceService(CUST3_DN2);
	
	public static final String SWID_CS2K = "CS2K_AUSTRIA";
	public static final String SWID_NGCP = "NGCP_AUSTRIA";

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
	protected BasicResourceService resourceService;
	
	@Autowired
	protected ResourceLogRepository logRepository;

}
