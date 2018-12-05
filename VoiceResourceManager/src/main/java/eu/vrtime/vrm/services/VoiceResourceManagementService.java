package eu.vrtime.vrm.services;

public interface VoiceResourceManagementService {

	public AllocateResourceResponse allocateResource(String customerId, String SID, String directoryNumber,
			String lineNo);

	public ReleaseResourceResponse releaseResource(String customerId, String SID, String directoryNumber);

	public GetServiceInfoResponse getServiceInfo(String customerId);

}
