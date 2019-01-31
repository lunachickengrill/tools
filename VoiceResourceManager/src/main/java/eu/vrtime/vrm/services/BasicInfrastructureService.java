package eu.vrtime.vrm.services;

import eu.vrtime.vrm.domain.model.Resource;
import eu.vrtime.vrm.domain.model.SessionManager;
import eu.vrtime.vrm.domain.model.Softswitch;
import eu.vrtime.vrm.domain.shared.SoftswitchStatus;

public interface BasicInfrastructureService {

	public void addSoftswitch(String switchId, String name, SoftswitchStatus status, String nic);
	
	public void addSessionManager(String smId, Softswitch softswitch);
	
	public void addResource(String smId,Resource resource);

	public SessionManager getSessionManagerWithMaxFreeResources();

	public Resource getFirstFreeeResourceBySessionManager(SessionManager sessionManager);

	public void lockResource(Resource resource);

}
