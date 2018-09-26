package eu.vrtime;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import eu.vrtime.vrm.domain.Resource;
import eu.vrtime.vrm.domain.Softswitch;
import eu.vrtime.vrm.domain.shared.ResourceStatus;
import eu.vrtime.vrm.domain.shared.SoftswitchStatus;
import eu.vrtime.vrm.repositories.ResourceRepository;
import eu.vrtime.vrm.repositories.VoiceServiceRepository;
import eu.vrtime.vrm.repositories.SoftswitchRepository;

public class BaseTest {

	public static final Softswitch CS2K = new Softswitch("1", "cs2k", SoftswitchStatus.ONLINE);
	public static final Softswitch NGCP = new Softswitch("2", "ngcp", SoftswitchStatus.ONLINE);

	public static final Set<Resource> cs2kResources = new HashSet<>();

	@Autowired
	protected SoftswitchRepository switchRepository;

	@Autowired
	protected ResourceRepository resourceRepository;

	@Autowired
	protected VoiceServiceRepository serviceRepository;


	protected void deleteAll() {
		serviceRepository.deleteAll();
		resourceRepository.deleteAll();
		switchRepository.deleteAll();
	}


	protected void fillSoftswitch() {
		switchRepository.saveAndFlush(CS2K);
		switchRepository.saveAndFlush(NGCP);
	}

	protected void fillLens() {
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

}
