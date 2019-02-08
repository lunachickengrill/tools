<<<<<<< HEAD
package eu.vrtime.vrm.services;

import eu.vrtime.vrm.api.messages.AllocateResourceResponse;
import eu.vrtime.vrm.api.messages.LockResourceResponse;
import eu.vrtime.vrm.api.messages.ReleaseResourceResponse;
import eu.vrtime.vrm.api.messages.ServiceInfoResponse;
import eu.vrtime.vrm.domain.shared.ResourceIdentifier;
import eu.vrtime.vrm.domain.shared.SwitchId;

public interface VoiceResourceManagementServiceFacade {

//	public AllocateResourceResponse allocateResource(String customerId, String SID, String directoryNumber,
//			String lineNo);
	
	public AllocateResourceResponse allocateResource(String directoryNumber);
	
	public AllocateResourceResponse allocateResource(String directoryNumber, String primaryNumber);
	
	public AllocateResourceResponse allocateResource(String directoryNumber, SwitchId switchId);

	public ReleaseResourceResponse releaseResource(String directoryNumber);

	public ServiceInfoResponse getServiceInfo(String directoryNumber);
	
	public LockResourceResponse lockResource(ResourceIdentifier resourceIdentifier);
	
	
	

}
=======
package eu.vrtime.vrm.services;

import eu.vrtime.vrm.api.messages.AllocateResourceResponse;
import eu.vrtime.vrm.api.messages.ReleaseResourceResponse;
import eu.vrtime.vrm.api.messages.ServiceInfoResponse;
import eu.vrtime.vrm.domain.shared.SwitchId;

public interface VoiceResourceManagementServiceFacade {

//	public AllocateResourceResponse allocateResource(String customerId, String SID, String directoryNumber,
//			String lineNo);
	
	public AllocateResourceResponse allocateResource(String directoryNumber);
	
	public AllocateResourceResponse allocateResource(String directoryNumber, String primaryNumber);
	
	public AllocateResourceResponse allocateResource(String directoryNumber, SwitchId switchId);

	public ReleaseResourceResponse releaseResource(String directoryNumber);

	public ServiceInfoResponse getServiceInfo(String directoryNumber);

}
>>>>>>> 8dc4866f8ccba5a3c2d16602cbcc6c1b79c5597a
