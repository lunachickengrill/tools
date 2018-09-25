package com.genericinventory.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.UUIDGenerator;
import com.fasterxml.uuid.impl.RandomBasedGenerator;
import com.genericinventory.application.config.ApplicationContext;
import com.genericinventory.application.config.ApplicationProfiles;
import com.genericinventory.domain.dataobject.AttributeValue;
import com.genericinventory.domain.dataobject.DataObject;
import com.genericinventory.domain.shared.Value;
import com.genericinventory.infrastructure.persistence.DataObjectRepository;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(ApplicationProfiles.TEST)
@ContextConfiguration(classes = { ApplicationContext.class })
public class BasicDataObjectTest {

	public static final Logger logger = LoggerFactory.getLogger(BasicDataObjectTest.class);
	private static final RandomBasedGenerator generator = Generators.randomBasedGenerator();

	StopWatch stopWatch = new StopWatch();

	List<DataObject> objectLoad = new ArrayList<DataObject>();
	List<AttributeValue> paramLoad = new ArrayList<AttributeValue>();

	@Autowired
	private DataObjectRepository repo;

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
	public final void testSearch() {
		stopWatch.start("testSearch");

		stopWatch.stop();
	}

}
