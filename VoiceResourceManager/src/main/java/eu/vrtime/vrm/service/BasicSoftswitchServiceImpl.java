package eu.vrtime.vrm.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.vrtime.vrm.domain.Softswitch;
import eu.vrtime.vrm.domain.shared.ResourceStatus;
import eu.vrtime.vrm.domain.shared.SoftswitchStatus;
import eu.vrtime.vrm.repositories.ResourceRepository;
import eu.vrtime.vrm.repositories.SoftswitchRepository;

@Service
@Transactional
public class BasicSoftswitchServiceImpl implements BasicSoftswitchService {

	private SoftswitchRepository switchRepository;

	private ResourceRepository resourceRepository;

	@Autowired
	public BasicSoftswitchServiceImpl(final SoftswitchRepository switchRepository,
			final ResourceRepository reourceRepository) {
		this.switchRepository = switchRepository;
		this.resourceRepository = resourceRepository;

	}

	@Override
	public Softswitch createSoftswitch(String switchId, String name, SoftswitchStatus status) {
		Softswitch sw = new Softswitch(switchId, name, status);
		Softswitch dbSw = switchRepository.saveAndFlush(sw);
		return dbSw;
	}

	@Override
	public Softswitch getSuitableSoftswitch() {
		long count = resourceRepository.countByStatus(ResourceStatus.FREE);
		return null;
	}

}
