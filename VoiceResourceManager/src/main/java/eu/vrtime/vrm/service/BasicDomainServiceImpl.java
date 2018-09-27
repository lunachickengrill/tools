package eu.vrtime.vrm.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.vrtime.vrm.domain.Resource;
import eu.vrtime.vrm.domain.SessionManager;
import eu.vrtime.vrm.domain.Softswitch;
import eu.vrtime.vrm.domain.VoiceService;
import eu.vrtime.vrm.domain.shared.ResourceStatus;
import eu.vrtime.vrm.domain.shared.SoftswitchStatus;
import eu.vrtime.vrm.repositories.ResourceRepository;
import eu.vrtime.vrm.repositories.SessionManagerRepository;
import eu.vrtime.vrm.repositories.SoftswitchRepository;
import eu.vrtime.vrm.repositories.VoiceServiceRepository;

@Service
@Transactional
public class BasicDomainServiceImpl implements BasicDomainService {

	private SoftswitchRepository switchRepository;

	private SessionManagerRepository sessionManagerRepository;

	private ResourceRepository resourceRepository;

	private VoiceServiceRepository serviceRepository;

	@Autowired
	public BasicDomainServiceImpl(final SoftswitchRepository switchRepository,
			final SessionManagerRepository sessionManagerRepository, final ResourceRepository resourceRepository,
			final VoiceServiceRepository serviceRepository) {
		this.switchRepository = switchRepository;
		this.sessionManagerRepository = sessionManagerRepository;
		this.resourceRepository = resourceRepository;
		this.serviceRepository = serviceRepository;

	}

	@Override
	public void addSoftswitch(String switchId, String name, SoftswitchStatus status) {
		Softswitch sw = new Softswitch(switchId, name, status);
		switchRepository.saveAndFlush(sw);

	}

	@Override
	public void addSessionManager(final String smId, final Softswitch softswitch) {
		Long oid = softswitch.getOid();
		Optional<Softswitch> dbSw = switchRepository.findById(oid);
		if (dbSw.isPresent()) {
			Softswitch sw = dbSw.get();
			SessionManager sm = new SessionManager(smId, sw);
			sessionManagerRepository.saveAndFlush(sm);

		}

	}

	@Override
	public Softswitch getSuitableSoftswitch() {
		Long count = resourceRepository.countByStatus(ResourceStatus.FREE);
		return null;
	}

	@Override
	public void addResource(final String smId, final Resource resource) {
		Optional<SessionManager> dbSm = sessionManagerRepository.findBySmId(smId);
		if (dbSm.isPresent()) {
			SessionManager sm = dbSm.get();
			sm.addResource(resource);
			resource.setSessionManager(sm);
			resourceRepository.saveAndFlush(resource);
		}
	}

	@Override
	public void addVoiceService(final String resourceId, final VoiceService voiceService) {
		Optional<Resource> dbResource = resourceRepository.findByIdentifier(resourceId);
		if (dbResource.isPresent()) {
			Resource r = dbResource.get();
			voiceService.setResource(r);
			serviceRepository.saveAndFlush(voiceService);
		}

	}

	@Override
	public Resource getFirstAvailableResource(Softswitch softswitch) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addAdditionalLineResource(String serviceId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void lockResource(Resource resource) {
		// TODO Auto-generated method stub

	}

}
