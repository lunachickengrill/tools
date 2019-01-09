package eu.vrtime.vrm.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.vrtime.vrm.api.exceptions.ResourceNotFoundException;
import eu.vrtime.vrm.api.exceptions.SessionManagerNotFoundException;
import eu.vrtime.vrm.api.exceptions.SoftswitchNotFoundException;
import eu.vrtime.vrm.api.exceptions.VoiceServiceNotFoundException;
import eu.vrtime.vrm.api.messages.AllocateResourceResponse;
import eu.vrtime.vrm.api.messages.ReleaseResourceResponse;
import eu.vrtime.vrm.api.messages.ServiceInfoResponse;
import eu.vrtime.vrm.domain.model.Resource;
import eu.vrtime.vrm.domain.model.SessionManager;
import eu.vrtime.vrm.domain.model.Softswitch;
import eu.vrtime.vrm.domain.model.VoiceService;
import eu.vrtime.vrm.domain.shared.SwitchId;
import eu.vrtime.vrm.repositories.ResourceRepository;
import eu.vrtime.vrm.repositories.SessionManagerRepository;
import eu.vrtime.vrm.repositories.SoftswitchRepository;
import eu.vrtime.vrm.repositories.VoiceServiceRepository;

@Service
public class VoiceResourceManagementServiceFacadeImpl implements VoiceResourceManagementServiceFacade {

	private static final Logger LOGGER = LoggerFactory.getLogger(VoiceResourceManagementServiceFacade.class);

	private BasicInfrastructureService infraService;
	private BasicResourceService resourceService;
	private SessionManagerRepository sessionManagerRepository;
	private ResourceRepository resourceRepository;
	private VoiceServiceRepository serviceRepository;
	private SoftswitchRepository switchRepository;

	@Autowired
	public VoiceResourceManagementServiceFacadeImpl(final BasicInfrastructureService infraService,
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
	@Transactional
	public AllocateResourceResponse allocateResource(String directoryNumber) {
		Validate.notNull(directoryNumber, "DN is null");
		LOGGER.debug("allocateResource for: " + directoryNumber);
		
		AllocateResourceResponse resp = new AllocateResourceResponse(directoryNumber);
		VoiceService vs = new VoiceService(directoryNumber);

		SessionManager dbSm = infraService.getSessionManagerWithMaxFreeResourcesLenEnabled();
		Softswitch dbSw = dbSm.getSoftswitch();
		Resource dbRes = resourceService.getFirstAvailableResource(dbSm);
		resourceService.allocateResourceForVoiceService(dbRes, vs);

		resp.setSwitchId(dbSw.getSwitchId().toStringSwId());
		resp.setNic(dbSw.getNic());
		resp.setSmId(dbSm.getSmId());
		resp.addNumber(directoryNumber, dbRes.getIdentifier().getIdentifier());
		LOGGER.debug(resp.toString());
		return resp;
	}

	@Override
	@Transactional
	public AllocateResourceResponse allocateResource(String directoryNumber, String primaryNumber) {
		Validate.notNull(directoryNumber, "DN is null");
		Validate.notNull(primaryNumber, "PrimaryNumber is null");	
		LOGGER.debug("allocateResource for: " + directoryNumber + ", primaryNumber: " + primaryNumber);
		
		AllocateResourceResponse resp = new AllocateResourceResponse(directoryNumber);
		VoiceService vs = new VoiceService(directoryNumber);

		Optional<VoiceService> dbPrimary = serviceRepository.findByDirectoryNumber(primaryNumber);
		if (!(dbPrimary.isPresent())) {
			throw new VoiceServiceNotFoundException("VoiceService for PrimaryNumber " + primaryNumber + " not found");
		}
		Resource dbRes = resourceService.getResourceForSecondService(vs, dbPrimary.get());
		
		SessionManager dbSm = dbRes.getSessionManager();
		Softswitch dbSw = dbSm.getSoftswitch();
		resourceService.allocateResourceForVoiceService(dbRes, vs);

		resp.setSwitchId(dbSw.getSwitchId().toStringSwId());
		resp.setNic(dbSw.getNic());
		resp.setSmId(dbSm.getSmId());
		resp.addNumber(directoryNumber, dbRes.getIdentifier().getIdentifier());
		resp.addNumber(primaryNumber, dbPrimary.get().getResource().getIdentifier().toStringIdentifier());
		return resp;
	}

	@Override
	public AllocateResourceResponse allocateResource(String directoryNumber, SwitchId switchId) {
		LOGGER.debug("allocateResource for: " + directoryNumber);
		AllocateResourceResponse resp = new AllocateResourceResponse(directoryNumber);
		VoiceService vs = new VoiceService(directoryNumber);

		SessionManager dbSm = infraService.getSessionManagerWithMaxFreeResources(switchId);
		Softswitch dbSw = dbSm.getSoftswitch();
		Resource dbRes = resourceService.getFirstAvailableResource(dbSm);
		resourceService.allocateResourceForVoiceService(dbRes, vs);

		resp.setSwitchId(dbSw.getSwitchId().toStringSwId());
		resp.setNic(dbSw.getNic());
		resp.setSmId(dbSm.getSmId());
		resp.addNumber(directoryNumber, dbRes.getIdentifier().getIdentifier());
		LOGGER.debug(resp.toString());
		return resp;
	}

	@Override
	@Transactional
	public ReleaseResourceResponse releaseResource(String directoryNumber) {
		ReleaseResourceResponse resp = new ReleaseResourceResponse(directoryNumber);
		LOGGER.debug("releaseResource " + directoryNumber);
		Optional<VoiceService> dbVs = serviceRepository.findByDirectoryNumber(directoryNumber);
		if (!(dbVs.isPresent())) {
			throw new VoiceServiceNotFoundException("No VoiceService found for DN " + directoryNumber);
		}
		Resource dbRes = dbVs.get().getResource();
		resp.addNumber(directoryNumber, dbRes.getIdentifier().getIdentifier());

		resourceService.releaseResouceForVoiceService(dbVs.get());
		LOGGER.debug(resp.toString());

		return resp;
	}

	@Override
	public ServiceInfoResponse getServiceInfo(String directoryNumber) {

		LOGGER.debug("getServiceInfo " + directoryNumber);

		ServiceInfoResponse resp = new ServiceInfoResponse(directoryNumber);

		Optional<VoiceService> dbVs = serviceRepository.findByDirectoryNumber(directoryNumber);
		if (!(dbVs.isPresent())) {
			throw new VoiceServiceNotFoundException("No VoiceService found for DN " + directoryNumber);
		}

		VoiceService vcs = dbVs.get();

		Optional<Resource> res = resourceRepository.findByOid(vcs.getResource().getOid());
		if (!(res.isPresent())) {
			throw new ResourceNotFoundException("Resource not found for DN " + directoryNumber);
		}

		resp.addNumber(vcs.getDirectoryNumber(), res.get().getIdentifier().getIdentifier());

		Optional<SessionManager> sm = sessionManagerRepository.findBySmId(res.get().getSessionManager().getSmId());
		if (!(sm.isPresent())) {
			throw new SessionManagerNotFoundException("SessionManager not found for DN " + directoryNumber);
		}

		resp.setSmId(sm.get().getSmId());

		Optional<Softswitch> sw = switchRepository.findByOid(sm.get().getSoftswitch().getOid());
		if (!(sw.isPresent())) {
			throw new SoftswitchNotFoundException("Softswitch not found for DN " + directoryNumber);
		}

		resp.setSwitchId(sw.get().getSwitchId().toStringSwId());
		resp.setNic(sw.get().getNic());

		LOGGER.debug(resp.toString());

		return resp;
	}

}
