package eu.vrtime.vrm.services;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.vrtime.vrm.domain.exceptions.DataNotFoundException;
import eu.vrtime.vrm.domain.model.Resource;
import eu.vrtime.vrm.domain.model.SessionManager;
import eu.vrtime.vrm.domain.model.Softswitch;
import eu.vrtime.vrm.domain.model.VoiceService;
import eu.vrtime.vrm.domain.shared.ResourceCountingResult;
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
	public Softswitch addSoftswitch(String switchId, String name, SoftswitchStatus status) {
		Softswitch sw = new Softswitch(switchId, name, status);
		return switchRepository.saveAndFlush(sw);
	}

	@Override
	@Transactional
	public SessionManager addSessionManager(final String smId, final Softswitch softswitch) {
		Long oid = softswitch.getOid();
		Optional<Softswitch> dbSw = switchRepository.findById(oid);
		Softswitch sw = dbSw.get();
		SessionManager sm = new SessionManager(smId, sw);
		return sessionManagerRepository.saveAndFlush(sm);

	}

	@Override
	@Transactional
	public Softswitch changeSoftswitch(Softswitch softswitch) {
		return switchRepository.saveAndFlush(softswitch);
	}

	@Override
	@Transactional
	public SessionManager changeSessionManager(SessionManager sessionManager) {
		return sessionManagerRepository.saveAndFlush(sessionManager);
	}

	@Override
	@Transactional
	public Resource changeResource(Resource resource) {
		return resourceRepository.saveAndFlush(resource);
	}

	@Override
	@Transactional
	public void deleteSoftswitch(Softswitch softswitch) {
		switchRepository.delete(softswitch);
	}

	@Override
	@Transactional
	public void deleteSessionManager(SessionManager sessionManager) {
		sessionManagerRepository.delete(sessionManager);
	}

	@Override
	@Transactional
	public void deleteResource(Resource resource) {
		resourceRepository.delete(resource);

	}

	@Override
	@Transactional
	public SessionManager getSessionManagerWithMaxFreeResources() {

		List<ResourceCountingResult> result = resourceRepository.queryResouces();
		result.sort(Comparator.comparing(ResourceCountingResult::getCnt).reversed());
		Optional<ResourceCountingResult> sm = result.stream().findFirst();
		return sm.get().getSessionManager();

	}

	@Override
	@Transactional
	public Resource addResource(final String smId, final Resource resource) {
		SessionManager dbSm = sessionManagerRepository.findBySmId(smId).orElseThrow(DataNotFoundException::new);

		dbSm.addResource(resource);
		resource.setSessionManager(dbSm);
		return resourceRepository.saveAndFlush(resource);

	}

	@Override
	@Transactional
	public Resource getFirstFreeeResourceBySessionManager(SessionManager sessionManager) {
		Optional<Resource> res = resourceRepository.findTopByStatusAndSessionManagerOrderByOid(ResourceStatus.FREE,
				sessionManager);
		return res.get();
	}

	@Override
	@Transactional
	public void lockResource(Resource resource) {
		// TODO Auto-generated method stub

	}

}
