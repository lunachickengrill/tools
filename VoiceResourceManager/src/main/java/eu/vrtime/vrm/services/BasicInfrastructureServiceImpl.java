package eu.vrtime.vrm.services;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.vrtime.vrm.domain.model.Resource;
import eu.vrtime.vrm.domain.model.SessionManager;
import eu.vrtime.vrm.domain.model.Softswitch;
import eu.vrtime.vrm.domain.model.VoiceService;
import eu.vrtime.vrm.domain.shared.ResourceCountingResult;
import eu.vrtime.vrm.domain.shared.ResourceNotFoundException;
import eu.vrtime.vrm.domain.shared.ResourceStatus;
import eu.vrtime.vrm.domain.shared.SoftswitchStatus;
import eu.vrtime.vrm.repositories.ResourceRepository;
import eu.vrtime.vrm.repositories.SessionManagerRepository;
import eu.vrtime.vrm.repositories.SoftswitchRepository;
import eu.vrtime.vrm.repositories.VoiceServiceRepository;

@Service
public class BasicInfrastructureServiceImpl implements BasicInfrastructureService {

	final Logger logger = LoggerFactory.getLogger(BasicInfrastructureServiceImpl.class);

	private SoftswitchRepository switchRepository;

	private SessionManagerRepository sessionManagerRepository;

	private ResourceRepository resourceRepository;

	private VoiceServiceRepository serviceRepository;

	@Autowired
	public BasicInfrastructureServiceImpl(final SoftswitchRepository switchRepository,
			final SessionManagerRepository sessionManagerRepository, final ResourceRepository resourceRepository,
			final VoiceServiceRepository serviceRepository) {
		this.switchRepository = switchRepository;
		this.sessionManagerRepository = sessionManagerRepository;
		this.resourceRepository = resourceRepository;
		this.serviceRepository = serviceRepository;

	}

	@Override
	@Transactional
	public void addSoftswitch(String switchId, String name, SoftswitchStatus status, String nic) {
		Softswitch sw = new Softswitch(switchId, name, status, nic);
		logger.debug(name + " " + status);
		switchRepository.saveAndFlush(sw);

	}

	@Override
	@Transactional
	public void addSessionManager(final String smId, final Softswitch softswitch) {
		Long oid = softswitch.getOid();
		Optional<Softswitch> dbSw = switchRepository.findById(oid);
		if (!dbSw.isPresent()) {
			throw new ResourceNotFoundException("Softswitch with oid " + oid + " not found");
		}

		Softswitch sw = dbSw.get();
		SessionManager sm = new SessionManager(smId, sw);
		sessionManagerRepository.saveAndFlush(sm);

	}

	@Override
	@Transactional
	public SessionManager getSessionManagerWithMaxFreeResources() {

		List<ResourceCountingResult> result = resourceRepository.queryResouces();
		result.sort(Comparator.comparing(ResourceCountingResult::getCnt).reversed());
		Optional<ResourceCountingResult> sm = result.stream().findFirst();

		if (!sm.isPresent()) {
			throw new ResourceNotFoundException("No softswitch found");
		}

		return sm.get().getSessionManager();

	}

	@Override
	@Transactional
	public void addResource(final String smId, final Resource resource) {
		Optional<SessionManager> dbSm = sessionManagerRepository.findBySmId(smId);

		if (!dbSm.isPresent()) {
			throw new ResourceNotFoundException("No sessionManager not found for SessionManagerId " + smId);
		}

		SessionManager sm = dbSm.get();
		sm.addResource(resource);
		resource.setSessionManager(sm);
		resourceRepository.saveAndFlush(resource);

	}

	@Override
	@Transactional
	public Resource getFirstFreeeResourceBySessionManager(SessionManager sessionManager) {
		Optional<Resource> rsc = resourceRepository.findTopByStatusAndSessionManagerOrderByOid(ResourceStatus.FREE,
				sessionManager);

		if (!rsc.isPresent()) {
			throw new ResourceNotFoundException("No free resource found on SessionManager " + sessionManager.getSmId());
		}

		return rsc.get();
	}

	@Override
	@Transactional
	public void lockResource(Resource resource) {
		Optional<Resource> rsc = resourceRepository.findById(resource.getOid());
		if (!rsc.isPresent()) {
			throw new ResourceNotFoundException("Resource with oid " + resource.getOid() + " not found");
		}

		rsc.get().setStatus(ResourceStatus.LOCKED);
		resourceRepository.saveAndFlush(rsc.get());

	}

}
