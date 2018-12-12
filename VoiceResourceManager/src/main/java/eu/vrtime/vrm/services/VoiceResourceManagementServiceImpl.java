package eu.vrtime.vrm.services;

import java.util.List;
import java.util.Optional;

import javax.management.ServiceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.vrtime.vrm.domain.exceptions.IllegalStateException;
import eu.vrtime.vrm.domain.exceptions.ResourceNotFoundException;
import eu.vrtime.vrm.domain.exceptions.SessionManagerNotFoundException;
import eu.vrtime.vrm.domain.exceptions.SoftswitchNotFoundException;
import eu.vrtime.vrm.domain.exceptions.VoiceServiceNotFoundException;
import eu.vrtime.vrm.domain.model.Resource;
import eu.vrtime.vrm.domain.model.SessionManager;
import eu.vrtime.vrm.domain.model.Softswitch;
import eu.vrtime.vrm.domain.model.VoiceService;
import eu.vrtime.vrm.repositories.ResourceRepository;
import eu.vrtime.vrm.repositories.SessionManagerRepository;
import eu.vrtime.vrm.repositories.SoftswitchRepository;
import eu.vrtime.vrm.repositories.VoiceServiceRepository;

@Service
public class VoiceResourceManagementServiceImpl implements VoiceResourceManagementService {

	private BasicInfrastructureService infraService;
	private BasicResourceService resourceService;
	private SessionManagerRepository sessionManagerRepository;
	private ResourceRepository resourceRepository;
	private VoiceServiceRepository serviceRepository;
	private SoftswitchRepository switchRepository;

	@Autowired
	public VoiceResourceManagementServiceImpl(final BasicInfrastructureService infraService,
			final BasicResourceService resourceService, final SessionManagerRepository sessionManagerRepository,
			VoiceServiceRepository serviceRepository, final ResourceRepository resourceRepository,
			final SoftswitchRepository switchRepository) {
		this.infraService = infraService;
		this.resourceService = resourceService;
		this.sessionManagerRepository = sessionManagerRepository;
		this.serviceRepository = serviceRepository;
		this.resourceRepository = resourceRepository;
		this.switchRepository = switchRepository;
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
	public ReleaseResourceResponse releaseResource(String customerId, String SID, String directoryNumber,
			String lineNo) {
		ReleaseResourceResponse resp = new ReleaseResourceResponse();

		Optional<VoiceService> dbVs = serviceRepository.findByDirectoryNumber(directoryNumber);
		if (!(dbVs.isPresent())) {
			throw new VoiceServiceNotFoundException("No VoiceService found for DN " + directoryNumber);
		}
		Resource res = dbVs.get().getResource();
		resp.setCustomerId(customerId);
		resp.setSid(SID);
		resp.setDirectoryNumber(directoryNumber);
		resp.setLen(res.getIdentifier().getIdentifier());

		resourceService.releaseResouceForVoiceService(dbVs.get());

		return resp;
	}

	@Override
	public GetServiceInfoResponse getServiceInfo(String customerId) {
		// TODO Auto-generated method stub
		GetServiceInfoResponse resp = new GetServiceInfoResponse();

		Optional<List<VoiceService>> dbVs = serviceRepository.findByCustomerId(customerId);
		if (!(dbVs.isPresent())) {
			throw new VoiceServiceNotFoundException("No VoiceService found for CustomerId " + customerId);
		}

		List<VoiceService> vcs = dbVs.get();
		vcs.forEach(element -> {
			Optional<Resource> res = resourceRepository.findByOid(element.getResource().getOid());
			if (!(res.isPresent())) {
				throw new ResourceNotFoundException("Resource not found for CustomerId " + customerId);
			}

			resp.addLen(res.get().getIdentifier().getIdentifier());
			resp.addDN(element.getDirectoryNumber());

			Optional<SessionManager> sm = sessionManagerRepository.findBySmId(res.get().getSessionManager().getSmId());
			if (!(sm.isPresent())) {
				throw new SessionManagerNotFoundException("SessionManager not found for CustomerId " + customerId);
			}

			resp.setSmId(sm.get().getSmId());

			Optional<Softswitch> sw = switchRepository.findByOid(sm.get().getSoftswitch().getOid());
			if (!(sw.isPresent())) {
				throw new SoftswitchNotFoundException("Softswitch not found for CustomerId " + customerId);
			}

			resp.setSwitchId(sw.get().getSwitchId());
			resp.setNic(sw.get().getNic());

		});

		return resp;
	}

}
