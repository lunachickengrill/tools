package eu.vrtime.vrm.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.vrtime.vrm.domain.exceptions.ResourceNotFoundException;
import eu.vrtime.vrm.domain.model.Resource;
import eu.vrtime.vrm.domain.model.SessionManager;
import eu.vrtime.vrm.domain.model.VoiceService;
import eu.vrtime.vrm.domain.shared.ResourceIdentifier;
import eu.vrtime.vrm.domain.shared.ResourceStatus;
import eu.vrtime.vrm.repositories.ResourceRepository;
import eu.vrtime.vrm.repositories.SessionManagerRepository;
import eu.vrtime.vrm.repositories.VoiceServiceRepository;

@Service
public class BasicResourceManagementServiceImpl implements BasicResourceManagementService {

	private VoiceServiceRepository serviceRepository;

	private ResourceRepository resourceRepository;

	private SessionManagerRepository sessionManagerRepository;

	@Autowired
	public BasicResourceManagementServiceImpl(final VoiceServiceRepository serviceRepository,
			final ResourceRepository resourceRepository, final SessionManagerRepository sessionManagerRepository) {
		this.serviceRepository = serviceRepository;
		this.resourceRepository = resourceRepository;
		this.sessionManagerRepository = sessionManagerRepository;
	}

	@Override
	@Transactional
	public void allocateResourceForVoiceService(final Resource resource, final VoiceService voiceService) {
			
		Long oid = voiceService.getOid();
		Optional<Resource> dbResource = resourceRepository.findById(resource.getOid());
		if(!(dbResource.isPresent())) {
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
		Optional<Resource> dbResource = resourceRepository
				.findTopByStatusAndSessionManagerOrderByOid(ResourceStatus.FREE, sessionManager);
		Resource res = dbResource.get();
		return res;
	}

	@Override
	public Resource getResourceForSecondService(VoiceService voiceService) {
		Optional<VoiceService> dbService = serviceRepository.findByOid(voiceService.getOid());
		VoiceService svc = dbService.get();
		Resource res = svc.getResource();
		Optional<Resource> dbRes = resourceRepository.findTopByStatusAndSessionManagerOrderByOid(ResourceStatus.FREE,
				res.getSessionManager());

		return dbRes.get();
	}

	@Override
	public void getVoiceServiceData() {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public void releaseResouceForVoiceService(VoiceService voiceService) {
		
		// TODO Auto-generated method stub
		
		Optional<VoiceService> dbVoiceService = serviceRepository.findByOid(voiceService.getOid());
		VoiceService dbSvc = dbVoiceService.get();
		Resource res = dbSvc.getResource();
		
		

	}

}
