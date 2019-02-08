package eu.vrtime.vrm.services;

import eu.vrtime.vrm.domain.model.Resource;
import eu.vrtime.vrm.domain.model.SessionManager;
import eu.vrtime.vrm.domain.model.Softswitch;
<<<<<<< HEAD
import eu.vrtime.vrm.domain.shared.ResourceIdentifier;
=======
>>>>>>> parent of cc1bf15... Merge branch 'master' of
import eu.vrtime.vrm.domain.shared.SoftswitchStatus;

public interface BasicInfrastructureService {

<<<<<<< HEAD
	public Softswitch addSoftswitch(String switchId, String nic, String name, SoftswitchStatus status,
			Boolean isLenEnabled);

	public SessionManager addSessionManager(String smId, Softswitch softswitch);

	public Resource addResource(String smId, Resource resource);

	public Softswitch changeSoftswitch(Softswitch softswitch);

	public SessionManager changeSessionManager(SessionManager sessionManager);

	public Resource changeResource(Resource resource);

	public void deleteSoftswitch(Softswitch softswitch);

	public void deleteSessionManager(SessionManager sessionManager);

	public void deleteResource(Resource resource);

	public void addResource(ResourceIdentifier identifier, SessionManager sessionManager);

	public SessionManager getSessionManagerWithMaxFreeResources();

	public SessionManager getSessionManagerWithMaxFreeResourcesLenEnabled();

	public SessionManager getSessionManagerWithMaxFreeResources(SwitchId switchId);
=======
	public void addSoftswitch(String switchId, String name, SoftswitchStatus status, String nic);
	
	public void addSessionManager(String smId, Softswitch softswitch);
	
	public void addResource(String smId,Resource resource);

	public SessionManager getSessionManagerWithMaxFreeResources();
>>>>>>> parent of cc1bf15... Merge branch 'master' of

	public Resource getFirstFreeeResourceBySessionManager(SessionManager sessionManager);

	public void lockResource(Resource resource);
<<<<<<< HEAD

	public void unlockResource(Resource resource);
=======
>>>>>>> parent of cc1bf15... Merge branch 'master' of

}
