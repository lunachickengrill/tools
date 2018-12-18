package eu.vrtime.vrm.services;

import eu.vrtime.vrm.api.messages.AllocateResourceResponse;
import eu.vrtime.vrm.api.messages.ReleaseResourceResponse;
import eu.vrtime.vrm.api.messages.ServiceInfoResponse;

public interface VoiceResourceManagementService {

	public AllocateResourceResponse allocateResource(String customerId, String SID, String directoryNumber,
			String lineNo);

	public ReleaseResourceResponse releaseResource(String customerId, String directoryNumber);

	public ServiceInfoResponse getServiceInfo(String customerId);

}
