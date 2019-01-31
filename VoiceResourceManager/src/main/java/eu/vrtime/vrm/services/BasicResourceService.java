package eu.vrtime.vrm.services;

import eu.vrtime.vrm.domain.model.Resource;
import eu.vrtime.vrm.domain.model.SessionManager;
import eu.vrtime.vrm.domain.model.VoiceService;

public interface BasicResourceService {
	
	public void allocateResourceForVoiceService(final Resource resource, final VoiceService voiceService);
	
	public Resource getFirstAvailableResource(final SessionManager sessionManager);
	
	public Resource getResourceForSecondService(final VoiceService voiceService, final VoiceService primary);
	
	public void releaseResouceForVoiceService(final VoiceService voiceService);
	

}
