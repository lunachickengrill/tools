package com.genericinventory.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

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
import org.springframework.util.Assert;
import org.springframework.util.StopWatch;
import org.springframework.util.StopWatch.TaskInfo;

import com.genericinventory.application.config.ApplicationContext;
import com.genericinventory.application.config.ApplicationProfiles;
import com.genericinventory.domain.dataobject.AttributeValue;
import com.genericinventory.domain.dataobject.DataObject;
import com.genericinventory.domain.objecttype.ObjectClass;
import com.genericinventory.domain.objecttype.ObjectType;
import com.genericinventory.domain.objecttype.ObjectTypeAttribute;
import com.genericinventory.domain.service.DataObjectServiceImpl;
import com.genericinventory.domain.service.MetaDataService;
import com.genericinventory.domain.service.MetaDataServiceImpl;
import com.genericinventory.domain.shared.AttributeId;
import com.genericinventory.domain.shared.AttributeSpecification;
import com.genericinventory.domain.shared.DomainServiceException;
import com.genericinventory.domain.shared.Value;
import com.genericinventory.infrastructure.persistence.AttributeRepository;
import com.genericinventory.infrastructure.persistence.DataObjectRepository;
import com.genericinventory.infrastructure.persistence.ObjectTypeRepository;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(ApplicationProfiles.APPLICATION)
@ContextConfiguration(classes = { ApplicationContext.class })
public class DataObjectServiceTest {

	public static final Logger logger = LoggerFactory.getLogger(DataObjectServiceTest.class);

	StopWatch stopWatch = new StopWatch();

	List<String[]> modemData = new ArrayList<String[]>();
	List<String[]> emtaData = new ArrayList<String[]>();
	DataObject project;
	DataObject warehouse;

	@Autowired
	DataObjectServiceImpl dataObjectService;

	@Autowired
	ObjectTypeRepository objectTypeRepository;

	@Autowired
	DataObjectRepository dataObjectRepository;

	@Autowired
	MetaDataServiceImpl metadataService;

	@Autowired
	AttributeRepository AttributeRepository;

	@Before
	public void setUp() {
		long mac = 0xAABBCCDD0000L;
		long emta = 0xFF0000000000L;

		for (long i = 0; i <= 20; i++) {
			// System.out.println(toMacString(mac++));
			modemData.add(new String[] { (toMacString(mac++)), dummySerial(), "DUMMY VENDOR", "CM" });
		}

		for (long i = 0; i <= 20; i++) {
			emtaData.add(new String[] { (toMacString(emta++)), (toMacString(emta++)), dummySerial(), dummyKey(),
					"DUMMY VENDOR", "EMTA" });
		}
	}

	@After
	public void tearDown() {

		List<TaskInfo> tasks = Arrays.asList(stopWatch.getTaskInfo());
		for (TaskInfo t : tasks) {
			logger.info("Task {} ended, execution time: {}", t.getTaskName(), t.getTimeSeconds());
		}

	}

	@Test
	public final void test() {

		 createDataStructure();
		loadModems();
		loadEmtas();
		// delete();

	}

	public final void createDataStructure() {
		stopWatch.start("createDataStructure");

		ObjectType dbProject = objectTypeRepository.findByNameAndObjectClassEqualsIgnoreCase("Project",
				ObjectClass.CLASS);
		Assert.notNull(dbProject, "CLASS Device Project not found");

		DataObject dataObject = dataObjectService.constructFromObjectType(dbProject);
		Assert.notNull(dataObject, "failed to construct DataObject");

		Set<AttributeSpecification> specs = dataObject.getAttributeSpecs();
		Assert.notEmpty(specs, "2 specs are empty");

		ObjectTypeAttribute projectName = AttributeRepository.findByNameEqualsIgnoreCase("Project Name");
		Assert.notNull(projectName, "Attribute not found 1");

		ObjectTypeAttribute projectDesc = AttributeRepository.findByNameEqualsIgnoreCase("Project Description");
		Assert.notNull(projectDesc, "Attribute not found 2");

		Set<AttributeValue> values = new HashSet<AttributeValue>();
		values.add(
				AttributeValue.createAttributeValue(new AttributeId(projectName.getId()), new Value("Device Project")));
		values.add(AttributeValue.createAttributeValue(new AttributeId(projectDesc.getId()),
				new Value("Container for Cable Modem Project")));

		dataObject.setAttributeValues(values);

		Set<AttributeValue> param = dataObject.getParams();
		Assert.notNull(param, "params empty");

		DataObject dbDataObject = dataObjectRepository.save(dataObject);
		Assert.notNull(dbDataObject, "failed to save dbDataObj");

		ObjectType dbWarehouse = objectTypeRepository.findByNameAndObjectClassEqualsIgnoreCase("Warehouse",
				ObjectClass.CLASS);
		Assert.notNull(dbWarehouse, "CLASS Warehouse not found");

		DataObject warehouse = dataObjectService.constructFromObjectType(dbWarehouse);
		Assert.notNull(warehouse, "Failed to construct DataObject Warehouse");

		DataObject saved = dataObjectRepository.save(warehouse);
		Assert.notNull(saved, "Failed to save warehouse");

		this.project = dbDataObject;
		this.warehouse = saved;
		stopWatch.stop();
	}

	public final void loadModems() {
		stopWatch.start("loadModems");

		DataObject root = dataObjectRepository.findByNameEqualsIgnoreCase("warehouse");
		Assert.notNull(root, "root not found");

		ObjectType dbModem = objectTypeRepository.findByNameEqualsIgnoreCase("Cable Modem");
		Assert.notNull(dbModem, "CLASS Cable Modem not found");

		Map<String, AttributeId> mapping = metadataService.getAttributes(dbModem);

		for (String[] s : modemData) {
			DataObject dataObj = dataObjectService.constructFromObjectType(dbModem);
			Assert.notNull(dataObj, "failed to construct DataObject");

			Set<AttributeValue> values = new HashSet<AttributeValue>();

			values.add(AttributeValue.createAttributeValue(mapping.get("MAC"), new Value(s[0])));
			values.add(AttributeValue.createAttributeValue(mapping.get("Serial"), new Value(s[1])));
			values.add(AttributeValue.createAttributeValue(mapping.get("Vendor"), new Value(s[2])));
			values.add(AttributeValue.createAttributeValue(mapping.get("Device Type Description"), new Value(s[3])));

			dataObj.setAttributeValues(values);
			dataObj.adjunctToParent(root);
			DataObject saved = dataObjectRepository.save(dataObj);
			Assert.notNull(saved, "failed to save device");
			logger.debug("Saved Object: {}", saved.toString());
		}

		stopWatch.stop();
	}

	public final void loadEmtas() {
		stopWatch.start("loadEmtas");

		DataObject root = dataObjectRepository.findByNameEqualsIgnoreCase("warehouse");
		Assert.notNull(root, "root not found");

		ObjectType dbModem = objectTypeRepository.findByNameEqualsIgnoreCase("EMTA");
		Assert.notNull(dbModem, "CLASS EMTA Modem not found");

		Map<String, AttributeId> mapping = metadataService.getAttributes(dbModem);
		for (String[] s : emtaData) {
			DataObject dataObj = dataObjectService.constructFromObjectType(dbModem);
			Assert.notNull(dataObj, "failed to construct DataObject");

			Set<AttributeValue> values = new HashSet<AttributeValue>();

			values.add(AttributeValue.createAttributeValue(mapping.get("MAC"), new Value(s[0])));
			values.add(AttributeValue.createAttributeValue(mapping.get("EMTA MAC"), new Value(s[1])));
			values.add(AttributeValue.createAttributeValue(mapping.get("Serial"), new Value(s[2])));
			values.add(AttributeValue.createAttributeValue(mapping.get("Key"), new Value(s[3])));
			values.add(AttributeValue.createAttributeValue(mapping.get("Vendor"), new Value(s[4])));
			values.add(AttributeValue.createAttributeValue(mapping.get("Device Type Description"), new Value(s[5])));

			dataObj.setAttributeValues(values);
			dataObj.adjunctToParent(root);
			DataObject saved = dataObjectRepository.save(dataObj);
			Assert.notNull(saved, "failed to save device");
			logger.debug("Saved Object: {}", saved.toString());
		}

		stopWatch.stop();
	}

	public static String toMacString(long mac) {

		if (mac > 0xFFFFFFFFFFFFL || mac < 0)
			throw new IllegalArgumentException("mac out of range");

		StringBuffer m = new StringBuffer(Long.toString(mac, 16));
		while (m.length() < 12)
			m.insert(0, "0");

		for (int j = m.length() - 2; j >= 2; j -= 2)
			m.insert(j, ":");
		return m.toString().toUpperCase();
	}

	public static String dummySerial() {
		String serial = UUID.randomUUID().toString();
		serial = "SRN" + serial.replace("-", "");
		return serial;
	}

	public static String dummyKey() {
		String key = new StringBuilder().append(UUID.randomUUID().toString()).append(UUID.randomUUID().toString())
				.toString();
		key = "KEY" + key;
		return key;
	}

	public final void delete() {
		DataObject dbDataObject = dataObjectRepository.findByNameEqualsIgnoreCase("project");
		Assert.notNull(dbDataObject, "Project not found");
		dataObjectRepository.deleteCascade(dbDataObject.getId());
	}

}
