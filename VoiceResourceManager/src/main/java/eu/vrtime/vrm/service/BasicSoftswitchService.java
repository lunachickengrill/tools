package eu.vrtime.vrm.service;

import eu.vrtime.vrm.domain.Softswitch;
import eu.vrtime.vrm.domain.shared.SoftswitchStatus;

public interface BasicSoftswitchService {
	
	public Softswitch createSoftswitch(String switchId, String name, SoftswitchStatus status);
	
	public Softswitch getSuitableSoftswitch();
}
