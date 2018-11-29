package eu.vrtime.vrm.services;

import eu.vrtime.vrm.domain.model.Resource;
import eu.vrtime.vrm.domain.model.SessionManager;
import eu.vrtime.vrm.domain.model.Softswitch;
import eu.vrtime.vrm.domain.model.VoiceService;
import eu.vrtime.vrm.domain.shared.ResourceIdentifier;
import eu.vrtime.vrm.domain.shared.SoftswitchStatus;

public interface BasicInfrastructureService {

	public Softswitch addSoftswitch(String switchId, String name, SoftswitchStatus status);
	
	public SessionManager addSessionManager(String smId, Softswitch softswitch);
	
	public Resource addResource(ResourceIdentifier identifier, SessionManager sessionManager);

	public SessionManager getSessionManagerWithMaxFreeResources();

	public Resource getFirstFreeeResourceBySessionManager(SessionManager sessionManager);

	public void lockResource(Resource resource);

}
