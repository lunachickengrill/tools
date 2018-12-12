package eu.vrtime.vrm.services;

public interface VoiceResourceManagementService {

	public AllocateResourceResponse allocateResource(String customerId, String SID, String directoryNumber,
			String lineNo);

	public ReleaseResourceResponse releaseResource(String customerId, String directoryNumber);

	public ServiceInfoResponse getServiceInfo(String customerId);

}
