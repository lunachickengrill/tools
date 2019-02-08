package eu.vrtime.vrm.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.vrtime.vrm.domain.model.Resource;
import eu.vrtime.vrm.domain.model.VoiceService;
import eu.vrtime.vrm.domain.shared.ResourceIdentifier;
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
	public void allocateResourceForVoiceService(final ResourceIdentifier identifier, final VoiceService voiceService) {
		Optional<Resource> dbResource = resourceRepository.findByIdentifier(identifier);

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
	@Transactional
	public void freeResouceForVoiceService() {
		// TODO Auto-generated method stub

	}

}
