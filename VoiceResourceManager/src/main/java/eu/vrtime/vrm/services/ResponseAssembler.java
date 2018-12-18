package eu.vrtime.vrm.services;

import eu.vrtime.vrm.api.messages.AllocateResourceResponse;
import eu.vrtime.vrm.api.messages.ReleaseResourceResponse;
import eu.vrtime.vrm.api.messages.ServiceInfoResponse;

public interface ResponseAssembler {

	public AllocateResourceResponse toAllocateResourceResponse();

	public ReleaseResourceResponse toReleaseResourceResponse();

	public ServiceInfoResponse toServiceInfoResponse();

}