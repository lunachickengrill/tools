package eu.vrtime.vrm.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.vrtime.vrm.domain.model.Resource;
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
		Optional<Resource> dbResource = resourceRepository.findById(resource.getOid());
		Resource res = dbResource.get();
		res.setStatus(ResourceStatus.ALLOCATED);
		res = resourceRepository.save(res);
		voiceService.setResource(res);
		serviceRepository.saveAndFlush(voiceService);

	}

	@Override
	public void getVoiceServiceData() {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public void freeResouceForVoiceService() {
		// TODO Auto-generated method stub

	}

}
