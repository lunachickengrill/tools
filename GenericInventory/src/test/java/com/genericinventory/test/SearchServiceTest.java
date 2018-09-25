package com.genericinventory.test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

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
import com.genericinventory.domain.dataobject.DataObject;
import com.genericinventory.domain.objecttype.ObjectType;
import com.genericinventory.domain.service.MetaDataServiceImpl;
import com.genericinventory.domain.service.SearchServiceImpl;
import com.genericinventory.domain.shared.AttributeId;
import com.genericinventory.domain.shared.Value;
import com.genericinventory.infrastructure.persistence.ObjectTypeRepository;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(ApplicationProfiles.APPLICATION)
@ContextConfiguration(classes = { ApplicationContext.class })
public class SearchServiceTest {

	public static final Logger logger = LoggerFactory.getLogger(SearchServiceTest.class);
	StopWatch stopWatch = new StopWatch();

	@Autowired
	SearchServiceImpl searchService;

	@Autowired
	MetaDataServiceImpl metadataService;

	@Autowired
	ObjectTypeRepository objectTypeRepository;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		List<TaskInfo> tasks = Arrays.asList(stopWatch.getTaskInfo());
		for (TaskInfo t : tasks) {
			logger.info("Task {} ended, execution time: {}", t.getTaskName(), t.getTimeSeconds());
		}
	}

//	@Test
//	public void testQueryByValue() {
//
//		ObjectType dbObjectType = objectTypeRepository.findOne(Long.valueOf(20));
//		Value value = new Value("FF:00:00:0F:42:40");
//		stopWatch.start("testQueryByValue");
//		List<DataObject> dbObject = searchService.findObjectByValue(dbObjectType, value);
//		Assert.notEmpty(dbObject);
//		stopWatch.stop();
//
//		dbObject.forEach(p -> logger.debug(dbObject.toString()));
//
//	}

	@Test
	public void testQueryByValueAndAttribute() {

		ObjectType dbObjectType = objectTypeRepository.findOne(Long.valueOf(20));
		AttributeId attributeId = new AttributeId(Long.valueOf(6));
		Value value = new Value("FF:00:00:0F:42:40");
		stopWatch.start("testQueryByValueAndAttribute");
		List<DataObject> dbObject = searchService.findObjectByAttributeAndValue(dbObjectType, attributeId, value);
		Assert.notEmpty(dbObject);
		stopWatch.stop();

		dbObject.forEach(p -> logger.debug(dbObject.toString()));

	}

}
