package eu.vrtime.vrm.services;

import eu.vrtime.vrm.domain.model.Resource;
import eu.vrtime.vrm.domain.model.VoiceService;

public interface BasicResourceManagementService {
	
	public void allocateResourceForVoiceService(final Resource resource, final VoiceService voiceService);
	
	public void getVoiceServiceData();
	
	public void freeResouceForVoiceService();
	

}
