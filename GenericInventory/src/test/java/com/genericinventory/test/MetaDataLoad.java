package com.genericinventory.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import org.springframework.util.StopWatch;
import org.springframework.util.StopWatch.TaskInfo;

import com.genericinventory.application.config.ApplicationContext;
import com.genericinventory.application.config.ApplicationProfiles;
import com.genericinventory.domain.objecttype.ObjectType;
import com.genericinventory.domain.objecttype.ObjectTypeAttribute;
import com.genericinventory.infrastructure.persistence.AttributeRepository;
import com.genericinventory.infrastructure.persistence.DataObjectRepository;
import com.genericinventory.infrastructure.persistence.ObjectTypeRepository;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(ApplicationProfiles.APPLICATION)
@ContextConfiguration(classes = { ApplicationContext.class })
public class MetaDataLoad {

	public static final Logger logger = LoggerFactory.getLogger(MetaDataLoad.class);

	List<ObjectType> objectTypes = new ArrayList<ObjectType>();

	StopWatch stopWatch = new StopWatch();
	ObjectType root;
	ObjectType container;
	ObjectType service;
	ObjectType resource;

	ObjectType abstractProject;
	ObjectType abstractRepository;
	ObjectType abstractService;
	ObjectType abstractPhysicalResource;
	ObjectType abstractLogicalResource;

	@Autowired
	private DataObjectRepository inventoryRepository;

	@Autowired
	private ObjectTypeRepository metadataRepository;

	@Autowired
	private AttributeRepository attributeRepository;

	@Before
	public void setUp() {

		root = ObjectType.createSystem("Thing");

		container = ObjectType.createSystem("Container");

		service = ObjectType.createSystem("Service");

		resource = ObjectType.createSystem("Resource");

		abstractProject = ObjectType.createAbstract("Abstract Project");

		abstractRepository = ObjectType.createAbstract("Abstract Repository");

		abstractService = ObjectType.createAbstract("Abstract Service");

		abstractPhysicalResource = ObjectType.createAbstract("Abstract Device");
		abstractLogicalResource = ObjectType.createAbstract("Abstract LEN");

		objectTypes.add(root);
		objectTypes.add(container);
		objectTypes.add(service);
		objectTypes.add(resource);
		objectTypes.add(abstractProject);
		objectTypes.add(abstractRepository);
		objectTypes.add(abstractService);
		objectTypes.add(abstractPhysicalResource);
		objectTypes.add(abstractLogicalResource);

	}

	@After
	public void tearDown() {

		List<TaskInfo> tasks = Arrays.asList(stopWatch.getTaskInfo());
		for (TaskInfo t : tasks) {
			logger.info("Task {} ended, execution time: {}", t.getTaskName(), t.getTimeSeconds());
		}

	}

	@Test
	public void test() {
		deleteAll();
		saveSystemClasses();
		saveAbstractClasses();
	}

	public void saveSystemClasses() {
		stopWatch.start("saveSystemClasses");

		ObjectType dbRoot = metadataRepository.saveAndFlush(root);
		assertNotNull("save root failed", dbRoot);

		container.adjunctToParent(dbRoot);
		ObjectType dbContainer = metadataRepository.saveAndFlush(container);
		assertNotNull("save container failed", dbContainer);

		service.adjunctToParent(dbRoot);
		ObjectType dbService = metadataRepository.saveAndFlush(service);
		assertNotNull("save service failed", dbService);

		resource.adjunctToParent(dbRoot);
		ObjectType dbResource = metadataRepository.saveAndFlush(resource);
		assertNotNull("save resource failed", dbResource);

		stopWatch.stop();

	}

	public void saveAbstractClasses() {
		stopWatch.start("saveAbstractClasses");

		// ### Resource + Physical Resource ###

		ObjectType dbResource = metadataRepository.findByNameEqualsIgnoreCase("Resource");
		assertNotNull("find ObjectType failed", dbResource);

		abstractPhysicalResource.adjunctToParent(dbResource);
		ObjectType dbPhysRes = metadataRepository.saveAndFlush(abstractPhysicalResource);
		assertNotNull("save device failed", dbPhysRes);

		dbPhysRes.createOrUpdateAttribute(ObjectTypeAttribute.createPrimaryAttribute(dbPhysRes, "MAC"));
		dbPhysRes.createOrUpdateAttribute(ObjectTypeAttribute.createRequiredAttribute(dbPhysRes, "Serial"));
		dbPhysRes.createOrUpdateAttribute(
				ObjectTypeAttribute.createRequiredAttribute(dbPhysRes, "Device Type Description"));
		dbPhysRes.createOrUpdateAttribute(ObjectTypeAttribute.createRequiredAttribute(dbPhysRes, "Vendor"));

		dbPhysRes = metadataRepository.saveAndFlush(dbPhysRes);
		assertNotNull("save device failed", dbPhysRes);

		// ### Container + Project ###

		ObjectType dbContainer = metadataRepository.findByNameEqualsIgnoreCase("Container");
		assertNotNull("find ObjectType Container failed", dbContainer);

		abstractProject.adjunctToParent(dbContainer);
		ObjectType dbProject = metadataRepository.saveAndFlush(abstractProject);
		assertNotNull("save project failed", dbProject);

		dbProject
				.createOrUpdateAttribute(ObjectTypeAttribute.createRequiredAttribute(dbProject, "Project Description"));
		dbProject = metadataRepository.saveAndFlush(dbProject);
		assertNotNull("save dbProject failed", dbProject);

		// ### Container + Repository ###

		dbContainer = metadataRepository.findByNameEqualsIgnoreCase("Container");
		assertNotNull("find ObjectType Container failed", dbContainer);

		abstractRepository.adjunctToParent(dbContainer);
		ObjectType dbRepository = metadataRepository.saveAndFlush(abstractRepository);
		assertNotNull("save repository failed", dbRepository);

		dbRepository.createOrUpdateAttribute(
				ObjectTypeAttribute.createRequiredAttribute(dbRepository, "Repository Description"));

		dbRepository = metadataRepository.saveAndFlush(dbRepository);
		assertNotNull("save dbRepository failed", dbRepository);

		// ### Resource + Logical Resource ###

		dbResource = metadataRepository.findByNameEqualsIgnoreCase("Resource");
		assertNotNull("find ObjectType Resource failed", dbResource);

		abstractLogicalResource.adjunctToParent(dbResource);
		ObjectType dbLogicalRes = metadataRepository.saveAndFlush(abstractLogicalResource);
		Assert.notNull(dbLogicalRes, "save dbLogicalResource failed");

		dbLogicalRes.createOrUpdateAttribute(
				ObjectTypeAttribute.createRequiredAttribute(dbLogicalRes, "Logical Resource Type Description"));
		dbLogicalRes = metadataRepository.saveAndFlush(dbLogicalRes);
		assertNotNull("save dbLogicalRes failed", dbLogicalRes);

		stopWatch.stop();
	}

	public void deleteAll() {
		stopWatch.start("deleteClass");

		// ObjectType dbObj = metadataRepository.findOne(Long.valueOf(1));
		ObjectType dbObj = metadataRepository.findByNameEqualsIgnoreCase("Thing");
		assertNotNull("find Root failed", dbObj);

		metadataRepository.delete(dbObj);
		ObjectType dbdeleted = metadataRepository.findOne(Long.valueOf(1));
		assertNull("root found, null expected", dbdeleted);
		stopWatch.stop();
	}

}
