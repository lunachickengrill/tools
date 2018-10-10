package eu.vrtime.vrm.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.vrtime.vrm.domain.Resource;
import eu.vrtime.vrm.domain.VoiceService;
import eu.vrtime.vrm.repositories.ResourceRepository;
import eu.vrtime.vrm.repositories.VoiceServiceRepository;

@Service
public class BasicResourceManagementServiceImpl implements BasicResourceManagementService {

	@Autowired
	private VoiceServiceRepository serviceRepository;

	@Autowired
	private ResourceRepository resourceRepository;

	@Override
	@Transactional
	public void allocateResourceForVoiceService(final String resourceId, final VoiceService voiceService) {
		Optional<Resource> dbResource = resourceRepository.findByIdentifier(resourceId);

		if (!dbResource.isPresent()) {
			// TODO throw exception
		}

		Resource r = dbResource.get();
		voiceService.setResource(r);
		serviceRepository.saveAndFlush(voiceService);

	}

	@Override
	public void getVoiceServiceData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void freeResouceForVoiceService() {
		// TODO Auto-generated method stub

	}

}
