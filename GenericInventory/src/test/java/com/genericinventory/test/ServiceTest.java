package com.genericinventory.test;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
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
import org.springframework.util.StopWatch;
import org.springframework.util.StopWatch.TaskInfo;

import com.genericinventory.application.config.ApplicationContext;
import com.genericinventory.application.config.ApplicationProfiles;
import com.genericinventory.domain.objecttype.Geometry;
import com.genericinventory.domain.objecttype.ObjectClass;
import com.genericinventory.domain.objecttype.ObjectType;
import com.genericinventory.domain.objecttype.ObjectTypeAttribute;
import com.genericinventory.domain.objecttype.ObjectTypeStructure;
import com.genericinventory.domain.service.MetaDataService;
import com.genericinventory.domain.service.MetaDataServiceImpl;
import com.genericinventory.infrastructure.persistence.AttributeRepository;
import com.genericinventory.infrastructure.persistence.ObjectTypeRepository;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(ApplicationProfiles.APPLICATION)
@ContextConfiguration(classes = { ApplicationContext.class })
public class ServiceTest {

	public static final Logger logger = LoggerFactory.getLogger(ServiceTest.class);
	StopWatch stopWatch = new StopWatch();

	@Autowired
	MetaDataService service = new MetaDataServiceImpl();

	@Autowired
	ObjectTypeRepository objectTypeRepository;

	@Autowired
	AttributeRepository attributeRepository;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() {

		List<TaskInfo> tasks = Arrays.asList(stopWatch.getTaskInfo());
		for (TaskInfo t : tasks) {
			logger.info("Task {} ended, execution time: {}", t.getTaskName(), t.getTimeSeconds());
		}

	}

	@Test
	public void serviceTests() {

		addClassProject();
		addClassWarehouse();
		addClassCableModem();
		addClassEmta();

	}

	public void addClassProject() {
		stopWatch.start("addClassProject");
		ObjectType dbAbstractProject = objectTypeRepository.findByNameEqualsIgnoreCase("Abstract Project");
		assertNotNull("Type Project not found", dbAbstractProject);

		ObjectType project = ObjectType.createClass("Project");
		project.adjunctToParent(dbAbstractProject);

		project.createOrUpdateAttribute(ObjectTypeAttribute.createRequiredAttribute(project, "Project name"));
		ObjectType dbProject = objectTypeRepository.saveAndFlush(project);
		assertNotNull("failed to save project", dbProject);

		stopWatch.stop();
	}

	public void addClassWarehouse() {
		stopWatch.start("addClassWarehouse");
		ObjectType dbRepository = objectTypeRepository.findByNameEqualsIgnoreCase("Abstract Repository");
		assertNotNull("Type Repository not found", dbRepository);

		ObjectType wareHouse = ObjectType.createClass("Warehouse");
		wareHouse.adjunctToParent(dbRepository);

		ObjectType dbWareHouse = objectTypeRepository.saveAndFlush(wareHouse);
		assertNotNull("failed to save type", dbWareHouse);
		stopWatch.stop();
	}

	public void addClassCableModem() {
		stopWatch.start("addClassModem");

		ObjectType dbAbstractDevice = objectTypeRepository.findByNameEqualsIgnoreCase("Abstract Device");
		assertNotNull("Type Abstract Device not found", dbAbstractDevice);

		ObjectType newType = ObjectType.createClass("Cable Modem");
		newType.adjunctToParent(dbAbstractDevice);

		ObjectType dbNewType = objectTypeRepository.saveAndFlush(newType);
		assertNotNull("failed to save type", dbNewType);

		ObjectType dbWarehouse = objectTypeRepository.findByNameEqualsIgnoreCase("Warehouse");
		assertNotNull("Type Warehouse not found", dbWarehouse);

		dbNewType.addStructure(ObjectTypeStructure.createVerticalRelation(dbWarehouse));
		dbNewType = objectTypeRepository.saveAndFlush(dbNewType);
		stopWatch.stop();
	}

	public void addClassEmta() {
		stopWatch.start("addClassEmta");
		ObjectType dbAbstractDevice = objectTypeRepository.findByNameAndObjectClassEqualsIgnoreCase("Abstract Device",
				ObjectClass.ABSTRACT);
		assertNotNull("Type Abstract Device not found", dbAbstractDevice);

		ObjectType newType = ObjectType.createClass("EMTA");
		newType.adjunctToParent(dbAbstractDevice);

		newType.createOrUpdateAttribute(ObjectTypeAttribute.createRequiredAttribute(newType, "Key"));
		newType.createOrUpdateAttribute(ObjectTypeAttribute.createRequiredAttribute(newType, "EMTA MAC"));

		ObjectType dbNewType = objectTypeRepository.saveAndFlush(newType);
		assertNotNull("failed to save type", dbNewType);

		ObjectType dbWarehouse = objectTypeRepository.findByNameEqualsIgnoreCase("Warehouse");
		assertNotNull("Type Warehouse not found", dbWarehouse);

		dbNewType.addStructure(ObjectTypeStructure.createVerticalRelation(dbWarehouse));
		dbNewType = objectTypeRepository.saveAndFlush(newType);
		assertNotNull("failed to save type", dbNewType);

		stopWatch.stop();
	}

	public final void deleteType(ObjectType objectType) {
		service.deleteObjectType(objectType);
	}

}
