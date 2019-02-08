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
