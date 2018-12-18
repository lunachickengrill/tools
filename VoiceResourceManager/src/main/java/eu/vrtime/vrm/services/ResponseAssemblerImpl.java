package eu.vrtime.vrm.services;

import org.springframework.stereotype.Service;

import eu.vrtime.vrm.api.messages.AllocateResourceResponse;
import eu.vrtime.vrm.api.messages.ReleaseResourceResponse;
import eu.vrtime.vrm.api.messages.ServiceInfoResponse;

@Service
public class ResponseAssemblerImpl implements ResponseAssembler {

	public ResponseAssemblerImpl() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * eu.vrtime.vrm.domain.shared.ResponseAssembler#toAllocateResourceResponse()
	 */
	@Override
	public AllocateResourceResponse toAllocateResourceResponse() {
		return null;
	}

	@Override
	public ReleaseResourceResponse toReleaseResourceResponse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceInfoResponse toServiceInfoResponse() {
		// TODO Auto-generated method stub
		return null;
	}

}
