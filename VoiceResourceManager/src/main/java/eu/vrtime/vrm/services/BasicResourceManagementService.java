package eu.vrtime.vrm.services;

import eu.vrtime.vrm.domain.VoiceService;

public interface BasicResourceManagementService {
	
	public void allocateResourceForVoiceService(final String resourceId, final VoiceService voiceService);
	
	public void getVoiceServiceData();
	
	public void freeResouceForVoiceService();
	

}
