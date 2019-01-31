package eu.vrtime.vrm.services;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.vrtime.vrm.api.exceptions.DataNotFoundException;
import eu.vrtime.vrm.api.exceptions.NoFreeResourcesException;
import eu.vrtime.vrm.api.exceptions.ResourceNotFoundException;
import eu.vrtime.vrm.api.exceptions.SessionManagerNotFoundException;
import eu.vrtime.vrm.api.exceptions.SoftswitchNotFoundException;
import eu.vrtime.vrm.domain.model.Resource;
import eu.vrtime.vrm.domain.model.SessionManager;
import eu.vrtime.vrm.domain.model.Softswitch;
import eu.vrtime.vrm.domain.shared.ResourceCountingResult;

import eu.vrtime.vrm.domain.shared.ResourceIdentifier;

import eu.vrtime.vrm.domain.shared.ResourceStatus;
import eu.vrtime.vrm.domain.shared.SoftswitchStatus;
import eu.vrtime.vrm.domain.shared.SwitchId;
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
	public Softswitch addSoftswitch(final String switchId, final String nic, final String name,
			final SoftswitchStatus status, final Boolean isLenEnabled) {
		Validate.notNull(switchId, "switchId is null");
		Validate.notNull(nic, "nic is null");
		Validate.notNull(name, "name is null");
		Validate.notNull(status, "status is null");

		SwitchId swId = new SwitchId(switchId);

		Softswitch sw = new Softswitch(swId, nic, name, status, isLenEnabled);
		return switchRepository.saveAndFlush(sw);
	}

	@Override
	@Transactional
	public SessionManager addSessionManager(final String smId, final Softswitch softswitch) {
		Validate.notNull(smId, "smId is null");
		Validate.notNull(softswitch, "softswitch is null");

		Optional<Softswitch> dbSw = switchRepository.findById(softswitch.getOid());
		if (!(dbSw.isPresent())) {
			throw new SoftswitchNotFoundException("Softswitch not found");
		}

		Softswitch sw = dbSw.get();
		SessionManager sm = new SessionManager(smId, sw);
		return sessionManagerRepository.saveAndFlush(sm);

	}

	@Override
	@Transactional
	public Softswitch changeSoftswitch(final Softswitch softswitch) {
		Validate.notNull(softswitch, "softswitch is null");

		return switchRepository.saveAndFlush(softswitch);
	}

	@Override
	@Transactional
	public SessionManager changeSessionManager(final SessionManager sessionManager) {
		Validate.notNull(sessionManager, "sessionManager is null");

		Optional<SessionManager> dbSessionManager = sessionManagerRepository.findByOid(sessionManager.getOid());
		if (!(dbSessionManager.isPresent())) {
			throw new SessionManagerNotFoundException("SessionManager not found");
		}

		return sessionManagerRepository.saveAndFlush(sessionManager);
	}

	@Override
	@Transactional
	public Resource changeResource(final Resource resource) {
		Validate.notNull(resource, "resource is null");

		return resourceRepository.saveAndFlush(resource);
	}

	@Override
	@Transactional
	public void deleteSoftswitch(final Softswitch softswitch) {
		Validate.notNull(softswitch, "softswitch is null");

		Optional<Softswitch> dbSoftswitch = switchRepository.findByOid(softswitch.getOid());
		if (!(dbSoftswitch.isPresent())) {
			throw new SoftswitchNotFoundException("Softswitch not found");
		}
		switchRepository.delete(softswitch);
	}

	@Override
	@Transactional
	public void deleteSessionManager(final SessionManager sessionManager) {
		Validate.notNull(sessionManager, "sessionManager is null");

		Optional<SessionManager> dbSessionManager = sessionManagerRepository.findByOid(sessionManager.getOid());
		if (!(dbSessionManager.isPresent())) {
			throw new SessionManagerNotFoundException("SessionManager not found");
		}
		sessionManagerRepository.delete(sessionManager);
	}

	@Override
	@Transactional
	public void deleteResource(final Resource resource) {
		Validate.notNull(resource, "resource is null");

		Optional<Resource> dbResource = resourceRepository.findByOid(resource.getOid());
		if (!(dbResource.isPresent())) {
			throw new ResourceNotFoundException("Resource not found");
		}
		resourceRepository.delete(resource);

	}

	@Override
	@Transactional
	public SessionManager getSessionManagerWithMaxFreeResources() {

		List<ResourceCountingResult> result = resourceRepository.queryResouces();
		result.sort(Comparator.comparing(ResourceCountingResult::getCnt).reversed());
		Optional<ResourceCountingResult> sm = result.stream().findFirst();

		if (!(sm.isPresent())) {
			throw new NoFreeResourcesException("No SessionManager with free Resources found");
		}
		return sm.get().getSessionManager();

	}

	@Override
	@Transactional
	public SessionManager getSessionManagerWithMaxFreeResources(SwitchId switchId) {
		Validate.notNull(switchId, "switchid is null");

		List<ResourceCountingResult> result = resourceRepository.queryResourcesBySwitchId(switchId);
		result.sort(Comparator.comparing(ResourceCountingResult::getCnt).reversed());
		Optional<ResourceCountingResult> sm = result.stream().findFirst();
		if (!(sm.isPresent())) {
			throw new NoFreeResourcesException("No SessionManager with free Resources found on Softswitch " + switchId);

		}

		return sm.get().getSessionManager();

	}

	// @Override
	// @Transactional
	// public Resource getFirstFreeeResourceBySessionManager(SessionManager
	// sessionManager) {
	// Optional<Resource> rsc =
	// resourceRepository.findTopByStatusAndSessionManagerOrderByOid(ResourceStatus.FREE,
	// sessionManager);
	//
	// if (!rsc.isPresent()) {
	// throw new ResourceNotFoundException("No free resource found on SessionManager
	// " + sessionManager.getSmId());
	// }
	//
	// return rsc.get();
	//
	//
	// }

	@Override
	@Transactional
	public SessionManager getSessionManagerWithMaxFreeResourcesLenEnabled() {
		List<ResourceCountingResult> result = resourceRepository.queryResourcesLenEnabled();
		result.sort(Comparator.comparing(ResourceCountingResult::getCnt).reversed());
		Optional<ResourceCountingResult> sm = result.stream().findFirst();
		if (!(sm.isPresent())) {
			throw new NoFreeResourcesException("No SessionManager with free Resources found for LEN enabled switch");
		}
		return sm.get().getSessionManager();
	}

	@Override
	@Transactional
	public Resource addResource(final String smId, final Resource resource) {
		Validate.notEmpty(smId, "smId is null");
		Validate.notNull(resource, "resource is null");

		Optional<SessionManager> dbSessionManager = sessionManagerRepository.findBySmId(smId);
		if (!(dbSessionManager.isPresent())) {
			throw new DataNotFoundException("SessionManager not found for Resource " + resource.getIdentifier());
		}

		SessionManager dbSm = dbSessionManager.get();
		dbSm.addResource(resource);
		resource.setSessionManager(dbSm);
		return resourceRepository.saveAndFlush(resource);
	}

	@Override
	@Transactional
	public void addResource(final ResourceIdentifier identifier, final SessionManager sessionManager) {
		Validate.notNull(identifier, "identifier is null");
		Validate.notNull(sessionManager, "sessionManager is null");

		Optional<SessionManager> dbSessionManager = sessionManagerRepository.findById(sessionManager.getOid());

		if (!(dbSessionManager.isPresent())) {
			throw new SessionManagerNotFoundException("SessionManager not found " + dbSessionManager.get().getSmId());
		}

		SessionManager sm = dbSessionManager.get();
		sm.addResource(new Resource(identifier, ResourceStatus.FREE));
		sessionManagerRepository.saveAndFlush(sm);

	}

	@Override
	@Transactional
	public Resource getFirstFreeeResourceBySessionManager(final SessionManager sessionManager) {
		Validate.notNull(sessionManager, "sessionManager is null");

		Optional<Resource> dbResource = resourceRepository
				.findTopByStatusAndSessionManagerOrderByOid(ResourceStatus.FREE, sessionManager);
		if (!(dbResource.isPresent())) {
			throw new NoFreeResourcesException("No free resource on SessionManager " + sessionManager.getSmId());
		}
		return dbResource.get();
	}

	@Override
	@Transactional
	public void lockResource(final Resource resource) {
		Validate.notNull(resource, "resource is null");

		Optional<Resource> dbResource = resourceRepository.findByOid(resource.getOid());
		if (!(dbResource.isPresent())) {
			throw new ResourceNotFoundException("Resource not found");
		}

		Resource dbRes = dbResource.get();
		dbRes.setStatus(ResourceStatus.LOCKED);
		resourceRepository.saveAndFlush(dbRes);

	}

	@Override
	public void unlockResource(final Resource resource) {
		Validate.notNull(resource, "resource is null");

		Optional<Resource> dbResource = resourceRepository.findByOid(resource.getOid());
		if (!(dbResource.isPresent())) {
			throw new ResourceNotFoundException("Resource not found");
		}

		Resource dbRes = dbResource.get();
		dbRes.setStatus(ResourceStatus.FREE);
		resourceRepository.saveAndFlush(dbRes);

	}

}
