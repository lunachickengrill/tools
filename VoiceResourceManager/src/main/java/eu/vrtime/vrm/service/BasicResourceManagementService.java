package eu.vrtime.vrm.service;

import eu.vrtime.vrm.domain.VoiceService;

public interface BasicResourceManagementService {
	
	public void allocateResourceForVoiceService(final String resourceId, final VoiceService voiceService);
	
	public void getVoiceServiceData();
	
	public void freeResouceForVoiceService();
	

}
