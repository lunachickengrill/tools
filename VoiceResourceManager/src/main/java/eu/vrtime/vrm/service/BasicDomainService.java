package eu.vrtime.vrm.service;

import eu.vrtime.vrm.domain.Resource;
import eu.vrtime.vrm.domain.Softswitch;
import eu.vrtime.vrm.domain.VoiceService;
import eu.vrtime.vrm.domain.shared.SoftswitchStatus;

public interface BasicDomainService {

	public void addSoftswitch(String switchId, String name, SoftswitchStatus status);
	
	public void addSessionManager(String smId, Softswitch softswitch);
	
	public void addResource(String smId,Resource resource);
	
	public void addVoiceService(String resourceId, VoiceService voiceService);

	public Softswitch getSuitableSoftswitch();

	public Resource getFirstAvailableResource(Softswitch softswitch);

	public void addAdditionalLineResource(String serviceId);

	public void lockResource(Resource resource);

}
