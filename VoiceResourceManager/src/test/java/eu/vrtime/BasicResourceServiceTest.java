package eu.vrtime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;

import eu.vrtime.vrm.api.exceptions.VoiceServiceNotFoundException;
import eu.vrtime.vrm.domain.model.Resource;
import eu.vrtime.vrm.domain.model.ResourceLog;
import eu.vrtime.vrm.domain.model.SessionManager;
import eu.vrtime.vrm.domain.model.VoiceService;
import eu.vrtime.vrm.domain.shared.ResourceCountingResult;
import eu.vrtime.vrm.domain.shared.ResourceStatus;
import eu.vrtime.vrm.repositories.ResourceRepository;
import eu.vrtime.vrm.repositories.SessionManagerRepository;
import eu.vrtime.vrm.repositories.SoftswitchRepository;
import eu.vrtime.vrm.repositories.VoiceServiceRepository;
import eu.vrtime.vrm.services.BasicInfrastructureService;
import eu.vrtime.vrm.services.BasicResourceService;

//Enable @Transactional annotation if you want the transaction be rolled back after the test has finished. Disable to get the data written to the test db
//@Transactional
public class BasicResourceServiceTest extends BaseTest {

	@Test
	public void contextLoads() {

	}

	@Test
	public void queryResourcesTest() {
		List<ResourceCountingResult> queryResult = resourceRepository.queryResouces();
		assertTrue(queryResult.size() > 0);

		Long smOid = queryResult.get(0).getSessionManager().getOid();
		assertNotNull(smOid);

		SessionManager dbSM = sessionManagerRepository.findById(smOid).get();
		assertNotNull(dbSM);
	}

	@Test
	public void getSessionManagerWithMaxFreeResourcesTest() {
		SessionManager dbSm = infraService.getSessionManagerWithMaxFreeResources();
		assertNotNull("SessionManager is null", dbSm);
	}

	@Test
	public void allocateResourceForVoiceServiceTest() {

		SessionManager dbSm = infraService.getSessionManagerWithMaxFreeResources();
		assertNotNull("SessionManager is null", dbSm);

		Resource res = resourceService.getFirstAvailableResource(dbSm);
		assertNotNull("Resource is null", res);

		resourceService.allocateResourceForVoiceService(res, VS_CUST1_DN1);
		VoiceService dbVs = serviceRepository.findByDirectoryNumber(CUST1_DN1).get();
		assertNotNull(dbVs);

		assertTrue("DN not matching", dbVs.getDirectoryNumber().equals(CUST1_DN1));

		System.out.println("dbVs " + dbVs.toString());
	}

	@Test
	public void allocateSecondLineResourceTest() {

		SessionManager dbSm = infraService.getSessionManagerWithMaxFreeResources();
		assertNotNull("SessionManager is null", dbSm);

		Resource res = resourceService.getFirstAvailableResource(dbSm);
		assertNotNull("Resource is null", res);

		resourceService.allocateResourceForVoiceService(res, VS_CUST2_DN1);

		VoiceService dbVs1 = serviceRepository.findByDirectoryNumber(CUST2_DN1).get();

		assertNotNull("VoiceService is null", dbVs1);
		assertTrue("DN not matching", dbVs1.getDirectoryNumber().equals(CUST2_DN1));

		Resource res2 = resourceService.getResourceForSecondService(VS_CUST2_DN2,dbVs1);
		assertNotNull("No resource", res2);

		SessionManager dbSm2 = res2.getSessionManager();
		assertNotNull("No SessionManager", dbSm2);
		assertTrue("SessionManager not matching", dbSm.getSmId().equals(dbSm2.getSmId()));

		resourceService.allocateResourceForVoiceService(res2, VS_CUST2_DN2);
		VoiceService dbVs2 = serviceRepository.findByDirectoryNumber(CUST2_DN2).get();
		assertNotNull("Second VoiceService is null", dbVs2);

		assertTrue("DN not matching", dbVs2.getDirectoryNumber().equals(CUST2_DN2));

		System.out.println("dbVS2 " + dbVs2.toString());

	}

	@Test
	public void releaseReosurceTest() {
		SessionManager dbSm = infraService.getSessionManagerWithMaxFreeResources();
		Resource res = resourceService.getFirstAvailableResource(dbSm);
		Long resOid = res.getOid();
		resourceService.allocateResourceForVoiceService(res, VS_CUST3_DN1);
		Optional<VoiceService> dbVs = serviceRepository.findByDirectoryNumber(CUST3_DN1);

		assertTrue(dbVs.get().getResource().getIdentifier() != null);

		resourceService.releaseResouceForVoiceService(VS_CUST3_DN1);
		Optional<VoiceService> dbVsdeleted = serviceRepository.findByDirectoryNumber(CUST3_DN1);
		assertFalse(dbVsdeleted.isPresent());

		res = resourceRepository.findById(resOid).get();
		assertTrue(res.getStatus().equals(ResourceStatus.FREE));

		Optional<List<ResourceLog>> resourceLogEntry = logRepository.findByDn(CUST3_DN1);
		assertTrue(resourceLogEntry.isPresent());
		assertTrue(resourceLogEntry.get().size() == 1);

		assertTrue(resourceLogEntry.get().get(0).getDn().equals(CUST3_DN1));
		assertTrue(resourceLogEntry.get().get(0).getLen().equals(dbVs.get().getResource().getIdentifier()));

	}

}
