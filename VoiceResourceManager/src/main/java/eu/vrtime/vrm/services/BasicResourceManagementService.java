package eu.vrtime.vrm.services;

import eu.vrtime.vrm.domain.model.VoiceService;
import eu.vrtime.vrm.domain.shared.ResourceIdentifier;

public interface BasicResourceManagementService {
	
	public void allocateResourceForVoiceService(final ResourceIdentifier identifier, final VoiceService voiceService);
	
	public void getVoiceServiceData();
	
	public void freeResouceForVoiceService();
	

}
