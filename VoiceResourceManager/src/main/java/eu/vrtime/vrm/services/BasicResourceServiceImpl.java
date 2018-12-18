package eu.vrtime.vrm.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.vrtime.vrm.api.exceptions.NoFreeResourcesException;
import eu.vrtime.vrm.api.exceptions.ResourceNotFoundException;
import eu.vrtime.vrm.api.exceptions.VoiceServiceNotFoundException;
import eu.vrtime.vrm.domain.model.Resource;
import eu.vrtime.vrm.domain.model.ResourceLog;
import eu.vrtime.vrm.domain.model.SessionManager;
import eu.vrtime.vrm.domain.model.VoiceService;
import eu.vrtime.vrm.domain.shared.ResourceIdentifier;
import eu.vrtime.vrm.domain.shared.ResourceStatus;
import eu.vrtime.vrm.repositories.ResourceLogRepository;
import eu.vrtime.vrm.repositories.ResourceRepository;
import eu.vrtime.vrm.repositories.SessionManagerRepository;
import eu.vrtime.vrm.repositories.VoiceServiceRepository;

@Service
public class BasicResourceServiceImpl implements BasicResourceService {

	private VoiceServiceRepository serviceRepository;

	private ResourceRepository resourceRepository;

	private SessionManagerRepository sessionManagerRepository;

	private ResourceLogRepository logRepository;

	@Autowired
	public BasicResourceServiceImpl(final VoiceServiceRepository serviceRepository,
			final ResourceRepository resourceRepository, final SessionManagerRepository sessionManagerRepository,
			final ResourceLogRepository logRepository) {
		this.serviceRepository = serviceRepository;
		this.resourceRepository = resourceRepository;
		this.sessionManagerRepository = sessionManagerRepository;
		this.logRepository = logRepository;
	}

	@Override
	@Transactional
	public void allocateResourceForVoiceService(final Resource resource, final VoiceService voiceService) {
		Validate.notNull(resource);
		Validate.notNull(voiceService);

		Optional<Resource> dbResource = resourceRepository.findById(resource.getOid());
		if (!(dbResource.isPresent())) {
			throw new ResourceNotFoundException("Resource not found");
		}

		Resource res = dbResource.get();
		res.setStatus(ResourceStatus.ALLOCATED);
		res = resourceRepository.save(res);
		voiceService.setResource(res);
		serviceRepository.saveAndFlush(voiceService);

	}

	@Override
	public Resource getFirstAvailableResource(SessionManager sessionManager) {
		Validate.notNull(sessionManager);
		Validate.notNull(sessionManager.getOid());
		
		Optional<Resource> dbResource = resourceRepository
				.findTopByStatusAndSessionManagerOrderByOid(ResourceStatus.FREE, sessionManager);
		if (!(dbResource.isPresent())) {
			throw new NoFreeResourcesException("No free resources");
		}
		Resource res = dbResource.get();
		return res;
	}

	@Override
	public Resource getResourceForSecondService(VoiceService voiceService) {
		Validate.notNull(voiceService);
		Validate.notNull(voiceService.getOid());
		
		Optional<VoiceService> dbService = serviceRepository.findByOid(voiceService.getOid());
		VoiceService svc = dbService.get();
		Resource res = svc.getResource();
		Optional<Resource> dbRes = resourceRepository.findTopByStatusAndSessionManagerOrderByOid(ResourceStatus.FREE,
				res.getSessionManager());
		if (!(dbRes.isPresent())) {
			throw new NoFreeResourcesException("No free resources for adding additional voice service");
		}

		return dbRes.get();
	}

	@Override
	@Transactional
	public void releaseResouceForVoiceService(VoiceService voiceService) {
		Validate.notNull(voiceService);
		Validate.notNull(voiceService.getOid());

		Optional<VoiceService> dbVoiceService = serviceRepository.findByOid(voiceService.getOid());
		if (!(dbVoiceService.isPresent())) {
			throw new VoiceServiceNotFoundException("VoiceService not found");
		}

		VoiceService dbSvc = dbVoiceService.get();

		if (dbSvc.getResource() == null) {
			throw new ResourceNotFoundException("No Resource for VoiceService " + voiceService.getServiceId());
		}

		ResourceLog logEntry = new ResourceLog(voiceService.getServiceId(), voiceService.getCustomerId(),
				voiceService.getResource().getIdentifier(), voiceService.getDirectoryNumber());

		Resource res = dbSvc.getResource();
		serviceRepository.delete(dbSvc);
		res.setStatus(ResourceStatus.FREE);
		resourceRepository.save(res);
		logRepository.saveAndFlush(logEntry);

	}

}
