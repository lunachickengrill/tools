package eu.vrtime.vrm.services;

import java.util.Optional;

import javax.management.ServiceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.vrtime.vrm.domain.exceptions.IllegalStateException;
import eu.vrtime.vrm.domain.exceptions.VoiceServiceNotFoundException;
import eu.vrtime.vrm.domain.model.Resource;
import eu.vrtime.vrm.domain.model.SessionManager;
import eu.vrtime.vrm.domain.model.Softswitch;
import eu.vrtime.vrm.domain.model.VoiceService;
import eu.vrtime.vrm.repositories.SessionManagerRepository;
import eu.vrtime.vrm.repositories.VoiceServiceRepository;

@Service
public class VoiceResourceManagementServiceImpl implements VoiceResourceManagementService {

	private BasicInfrastructureService infraService;
	private BasicResourceService resourceService;
	private SessionManagerRepository sessionManagerRepository;

	private VoiceServiceRepository serviceRepository;

	@Autowired
	public VoiceResourceManagementServiceImpl(final BasicInfrastructureService infraService,
			final BasicResourceService resourceService, final SessionManagerRepository sessionManagerRepository,
			VoiceServiceRepository serviceRepository) {
		this.infraService = infraService;
		this.resourceService = resourceService;
		this.sessionManagerRepository = sessionManagerRepository;
		this.serviceRepository = serviceRepository;
	}

	@Override
	public AllocateResourceResponse allocateResource(String customerId, String SID, String directoryNumber,
			String lineNo) {
		
		AllocateResourceResponse resp = new AllocateResourceResponse();
		VoiceService vs = new VoiceService(SID, customerId, directoryNumber, Integer.parseInt(lineNo));
		
		if (lineNo.equals("1")) {

			SessionManager dbSm = infraService.getSessionManagerWithMaxFreeResources();
			Softswitch dbSw = dbSm.getSoftswitch();
			Resource dbRes = resourceService.getFirstAvailableResource(dbSm);
			resourceService.allocateResourceForVoiceService(dbRes, vs);
			VoiceService dbVs = serviceRepository.findByResource(dbRes).get();

			resp.setLen(dbRes.getIdentifier().getIdentifier());
			resp.setSwitchId(dbSw.getSwitchId());
			resp.setNic(dbSw.getNic());
			resp.setSmId(dbSm.getSmId());
			resp.setCustomerId(dbVs.getCustomerId());
			resp.setDirectoryNumber(dbVs.getDirectoryNumber());
			resp.setSid(dbVs.getServiceId());

		} else {

			VoiceService dbVsExisting = serviceRepository.findByCustomerIdAndLineNo(customerId, 1).get();
			Resource dbRes = resourceService.getResourceForSecondService(dbVsExisting);
			SessionManager dbSm = dbRes.getSessionManager();
			Softswitch dbSw = dbSm.getSoftswitch();
			resourceService.allocateResourceForVoiceService(dbRes, vs);
			VoiceService dbVs = serviceRepository.findByResource(dbRes).get();

			resp.setLen(dbRes.getIdentifier().getIdentifier());
			resp.setSwitchId(dbSw.getSwitchId());
			resp.setNic(dbSw.getNic());
			resp.setSmId(dbSm.getSmId());
			resp.setCustomerId(dbVs.getCustomerId());
			resp.setDirectoryNumber(dbVs.getDirectoryNumber());
			resp.setSid(dbVs.getServiceId());
		}
		return resp;
	}

	@Override
	public ReleaseResourceResponse releaseResource(String customerId, String SID, String directoryNumber, String lineNo) {
		ReleaseResourceResponse res = new ReleaseResourceResponse();
		
		Optional<VoiceService> dbVs = serviceRepository.findByDirectoryNumber(directoryNumber);
		if (!(dbVs.isPresent())) {
			throw new VoiceServiceNotFoundException("No VoiceService found for DN " + directoryNumber);
		}
		
		resourceService.releaseResouceForVoiceService(dbVs.get());
		//TODO finish implementation
		return null;
	}

	@Override
	public GetServiceInfoResponse getServiceInfo(String customerId) {
		// TODO Auto-generated method stub
		return null;
	}

}
