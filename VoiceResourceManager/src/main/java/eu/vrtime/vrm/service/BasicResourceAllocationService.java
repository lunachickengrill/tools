package eu.vrtime.vrm.service;

import eu.vrtime.vrm.domain.Resource;
import eu.vrtime.vrm.domain.Softswitch;

public interface BasicResourceAllocationService {
	
	public Resource getFirstResource(Softswitch softswitch);
	
	public Resource addAdditionalLineResource(String serviceId);
	
	public void lockResource(Resource resource);
	
	

}
