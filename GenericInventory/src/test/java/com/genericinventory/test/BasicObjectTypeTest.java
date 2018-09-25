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
import org.springframework.util.StopWatch;
import org.springframework.util.StopWatch.TaskInfo;

import com.genericinventory.application.config.ApplicationContext;
import com.genericinventory.application.config.ApplicationProfiles;
import com.genericinventory.domain.objecttype.ObjectClass;
import com.genericinventory.domain.objecttype.ObjectType;
import com.genericinventory.domain.objecttype.ObjectTypeAttribute;
import com.genericinventory.infrastructure.persistence.AttributeRepository;
import com.genericinventory.infrastructure.persistence.DataObjectRepository;
import com.genericinventory.infrastructure.persistence.ObjectTypeRepository;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(ApplicationProfiles.TEST)
@ContextConfiguration(classes = { ApplicationContext.class })
public class BasicObjectTypeTest {

	public static final Logger logger = LoggerFactory.getLogger(BasicObjectTypeTest.class);

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

		abstractProject = ObjectType.createAbstract("Project");

		abstractRepository = ObjectType.createAbstract("Repository");

		abstractService = ObjectType.createAbstract("VoiceService");

		abstractPhysicalResource = ObjectType.createAbstract("Physical Resource");
		abstractLogicalResource = ObjectType.createAbstract("Logical Resource");

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
		saveSystemClasses();
		saveAbstractClasses();
		// inheritObjectType();
		// getParent();
		// getName();
		// queryAbstractTypes();
		// addAttribute();
		// createClassModem();
		// deleteResource();
		// createClassProject();
		// deleteClassModem();
		// findAll();

	}
	
	public void addAbstractClass() {
		
	}

	public void saveSystemClasses() {
		stopWatch.start("saveSystemClasses");

		ObjectType dbRoot = metadataRepository.saveAndFlush(root);
		assertNotNull("save root failed", dbRoot);

		ObjectType dbContainer = metadataRepository.saveAndFlush(container);
		assertNotNull("save container failed", dbContainer);
		dbContainer.adjunctToParent(dbRoot);

		ObjectType dbService = metadataRepository.saveAndFlush(service);
		assertNotNull("save service failed", dbService);
		dbService.adjunctToParent(dbRoot);

		ObjectType dbResource = metadataRepository.saveAndFlush(resource);
		assertNotNull("save resource failed", dbResource);
		dbResource.adjunctToParent(dbRoot);

		dbContainer = metadataRepository.saveAndFlush(dbContainer);
		dbService = metadataRepository.saveAndFlush(dbService);
		dbResource = metadataRepository.saveAndFlush(dbResource);
		stopWatch.stop();

	}

	public void saveAbstractClasses() {
		stopWatch.start("saveAbstractClasses");

		// ### Resource + Physical Resource ###

		ObjectType dbPhysRes = metadataRepository.saveAndFlush(abstractPhysicalResource);
		assertNotNull("save device failed", dbPhysRes);
		ObjectType dbResource = metadataRepository.findByNameEqualsIgnoreCase("Resource");
		assertNotNull("find ObjectType failed", dbResource);
		dbPhysRes.adjunctToParent(dbResource);
		dbPhysRes = metadataRepository.saveAndFlush(dbPhysRes);
		assertNotNull("save device failed", dbPhysRes);

		ObjectTypeAttribute physResAttr = ObjectTypeAttribute.createRequiredAttribute(dbPhysRes,
				"Device Type Description");
		ObjectTypeAttribute dbPhysResAttr = attributeRepository.save(physResAttr);
		assertNotNull("save deviceAttr failed", dbPhysResAttr);

		// ### Container + Project ###

		ObjectType dbContainer = metadataRepository.findByNameEqualsIgnoreCase("Container");
		assertNotNull("find ObjectType Container failed", dbContainer);
		abstractProject.adjunctToParent(dbContainer);
		ObjectType dbProject = metadataRepository.saveAndFlush(abstractProject);
		assertNotNull("save project failed", dbProject);

		ObjectTypeAttribute projectAttr = ObjectTypeAttribute.createRequiredAttribute(dbProject, "Project Description");
		assertNotNull("create required attribute failed", projectAttr);
		ObjectTypeAttribute dbProjectAttr = attributeRepository.save(projectAttr);
		assertNotNull("save projectAttr failed", dbProjectAttr);

		// ### Container + Repository ###

		dbContainer = metadataRepository.findByNameEqualsIgnoreCase("Container");
		assertNotNull("find ObjectType Container failed", dbContainer);
		abstractRepository.adjunctToParent(dbContainer);
		ObjectType dbRepository = metadataRepository.saveAndFlush(abstractRepository);
		assertNotNull("save repository failed", dbRepository);

		ObjectTypeAttribute repositoryAttr = ObjectTypeAttribute.createRequiredAttribute(dbRepository,
				"Repository Description");
		assertNotNull("create required attribute failed", repositoryAttr);
		ObjectTypeAttribute dbRepositoryAttr = attributeRepository.save(repositoryAttr);
		assertNotNull("save repositoryAttr failed", dbRepositoryAttr);

		// ### Resource + Logical Resource ###

		dbResource = metadataRepository.findByNameEqualsIgnoreCase("Resource");
		assertNotNull("find ObjectType Resource failed", dbResource);
		abstractLogicalResource.adjunctToParent(dbResource);
		ObjectType dbLogicalRes = metadataRepository.saveAndFlush(abstractLogicalResource);

		ObjectTypeAttribute logicalResAttr = ObjectTypeAttribute.createRequiredAttribute(dbLogicalRes,
				"Logical Resource Type Description");
		ObjectTypeAttribute dbLogicalResAttr = attributeRepository.save(logicalResAttr);
		assertNotNull("save logicalResourceAttr failed", dbLogicalResAttr);

		stopWatch.stop();
	}


	public void getParent() {
		stopWatch.start("getParent");

		ObjectType dbObj = metadataRepository.findByNameEqualsIgnoreCase("Device");
		assertNotNull("Type Device not found", dbObj);

		ObjectType parent = dbObj.getParent();
		assertNotNull("No Parent", parent);
		System.out.println("Parent is: " + parent.toString());
	}

	public void queryAbstractTypes() {
		stopWatch.start("queryAbstractTypes");
		List<ObjectType> dbList = metadataRepository.findByObjectClass(ObjectClass.ABSTRACT);
		assertNotNull(dbList);

		for (ObjectType o : dbList) {
			System.out.println("queryAbstractTypes: " + o.toString());
		}
		stopWatch.stop();

	}

	public void addAttribute() {
		stopWatch.start("addAttribute");
		List<ObjectType> dbList = metadataRepository.findByObjectClass(ObjectClass.ABSTRACT);
		for (ObjectType o : dbList) {

			ObjectTypeAttribute dbAttr = attributeRepository
					.save(ObjectTypeAttribute.createRequiredAttribute(o, "Name"));
			o.createOrUpdateAttribute(dbAttr);
			ObjectType dbObj1 = metadataRepository.saveAndFlush(o);

			dbAttr = ObjectTypeAttribute.createRequiredAttribute(o, "Description");
			dbAttr = attributeRepository.save(dbAttr);
			o.createOrUpdateAttribute(dbAttr);

			ObjectType dbObj2 = metadataRepository.saveAndFlush(o);
			assertNotNull(dbObj1);
			assertNotNull(dbObj2);
		}
		stopWatch.stop();
	}

	public void createClassModem() {
		stopWatch.start("createClass");
		ObjectType abstractDevice = metadataRepository.findByNameEqualsIgnoreCase("Device");
		assertNotNull("Type Device not found", abstractDevice);

		ObjectType modem = ObjectType.createClass("Modem");
		modem.adjunctToParent(abstractDevice);
		ObjectType dbModem = metadataRepository.saveAndFlush(modem);
		assertNotNull("Modem not saved", dbModem);

		List<ObjectTypeAttribute> modemAttr = new ArrayList<ObjectTypeAttribute>();
		modemAttr.add(ObjectTypeAttribute.createPrimaryAttribute(dbModem, "MAC"));
		modemAttr.add(ObjectTypeAttribute.createRequiredAttribute(dbModem, "serial"));
		modemAttr.add(ObjectTypeAttribute.createRequiredAttribute(dbModem, "Vendor"));
		List<ObjectTypeAttribute> dbModemAttr = attributeRepository.save(modemAttr);
		assertNotNull("no modem attributes", dbModemAttr);

		for (ObjectTypeAttribute a : dbModemAttr) {
			dbModem.createOrUpdateAttribute(a);
		}

		dbModem = metadataRepository.saveAndFlush(dbModem);
		assertNotNull("modem with attributes not saved", dbModem);

		dbModemAttr = dbModem.getAttributeList();
		assertNotNull("get modem attributes failed", dbModemAttr);

		for (ObjectTypeAttribute a : dbModemAttr) {
			System.out.println("modem attributes: " + a.toString());
		}

		stopWatch.stop();
	}

	public void deleteClassModem() {

		stopWatch.start("deleteClassModem");

		ObjectType dbClass = metadataRepository.findByNameEqualsIgnoreCase("Modem");
		assertNotNull("Type Modem not found", dbClass);

		metadataRepository.deleteCascade(dbClass.getId());

	}

	public void createClassProject() {
		stopWatch.start("createClassProject");
		ObjectType abstractProject = metadataRepository.findByNameEqualsIgnoreCase("Project");
		assertNotNull("Type Project not found", abstractProject);

		ObjectType warehouse = ObjectType.createClass("Warehouse");
		warehouse.adjunctToParent(abstractProject);
		ObjectType dbWarehouse = metadataRepository.saveAndFlush(warehouse);
		assertNotNull("Warehouse not saved", dbWarehouse);

		List<ObjectTypeAttribute> warehouseAttr = new ArrayList<ObjectTypeAttribute>();
		warehouseAttr.add(ObjectTypeAttribute.createOptionalAttribute(dbWarehouse, "Submitter"));
		List<ObjectTypeAttribute> dbWarehouseAttr = attributeRepository.save(warehouseAttr);
		assertNotNull("attributes not saved", dbWarehouseAttr);

		for (ObjectTypeAttribute a : dbWarehouseAttr) {
			dbWarehouse.createOrUpdateAttribute(a);
		}

		dbWarehouse = metadataRepository.saveAndFlush(dbWarehouse);
		assertNotNull("warehouse with attributes not saved", dbWarehouse);

		dbWarehouseAttr = dbWarehouse.getAttributeList();
		assertNotNull("get attributes failed", dbWarehouseAttr);

		for (ObjectTypeAttribute a : dbWarehouseAttr) {
			System.out.println("warehouse attributes: " + a.toString());
		}

		stopWatch.stop();
	}

	public void deleteAll() {
		stopWatch.start("deleteClass");

		// ObjectType dbObj = metadataRepository.findOne(Long.valueOf(1));
		ObjectType dbObj = metadataRepository.findByNameEqualsIgnoreCase("Modem");
		assertNotNull("find Root failed", dbObj);

		metadataRepository.delete(dbObj);
		ObjectType dbdeleted = metadataRepository.findOne(Long.valueOf(1));
		assertNull("root found, null expected", dbdeleted);
	}

	public void deleteResource() {
		stopWatch.start("deleteResource");

		ObjectType dbObj = metadataRepository.findByNameEqualsIgnoreCase("Resource");
		assertNotNull("find Resource failed", dbObj);

		// metadataRepository.delete(dbObj);
		metadataRepository.deleteCascade(dbObj.getId());

		dbObj = metadataRepository.findByNameEqualsIgnoreCase("Resource");
		assertNull("Resource not deleted", dbObj);
		stopWatch.stop();
	}

	public void getName() {
		stopWatch.start("getName");
		List<ObjectType> dbList = metadataRepository.findAll();
		assertNotNull(dbList);

		for (ObjectType o : dbList) {
			System.out.println("getName: " + o.getName());
		}
		stopWatch.stop();

	}

	public void saveValidateSystemRules() {
		stopWatch.start("save");

		ObjectType dbRoot = metadataRepository.saveAndFlush(root);
		assertNotNull("save root failed", dbRoot);

		ObjectType dbContainer = metadataRepository.saveAndFlush(container);
		ObjectTypeAttribute dbContainerAttr = attributeRepository
				.save(ObjectTypeAttribute.createRequiredAttribute(dbContainer, "Description"));
		assertNotNull("save attribute failed", dbContainerAttr);
		dbContainer.createOrUpdateAttribute(dbContainerAttr);
		assertNotNull("save container failed", dbContainer);
		dbContainer.adjunctToParent(dbRoot);

		ObjectType dbService = metadataRepository.saveAndFlush(service);
		ObjectTypeAttribute dbServiceAttr = attributeRepository
				.save(ObjectTypeAttribute.createRequiredAttribute(dbContainer, "Description"));
		assertNotNull("save attribute failed", dbServiceAttr);
		dbService.createOrUpdateAttribute(dbServiceAttr);
		assertNotNull("save service failed", dbService);
		dbService.adjunctToParent(dbRoot);

		ObjectType dbResource = metadataRepository.saveAndFlush(resource);
		ObjectTypeAttribute dbResourceAttr = attributeRepository
				.save(ObjectTypeAttribute.createOptionalAttribute(dbContainer, "Description"));
		assertNotNull("save attribute failed", dbResourceAttr);
		dbResource.createOrUpdateAttribute(dbResourceAttr);
		assertNotNull("save resource failed", dbResource);
		dbResource.adjunctToParent(dbRoot);

		dbContainer = metadataRepository.saveAndFlush(dbContainer);
		dbService = metadataRepository.saveAndFlush(dbService);
		dbResource = metadataRepository.saveAndFlush(dbResource);

		stopWatch.stop();
	}

	public void findAll() {
		stopWatch.start("findAll");

		List<ObjectType> dbObj = metadataRepository.findAll();
		assertNotNull("findAll failed", dbObj);

		for (ObjectType o : dbObj) {
			System.out.println("ObjectType in db " + o.toString());
		}
		stopWatch.stop();
	}

}
