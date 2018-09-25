package com.genericinventory.functional;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genericinventory.application.config.ApplicationContext;
import com.genericinventory.application.config.ApplicationProfiles;
import com.genericinventory.domain.dataobject.AttributeValue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(ApplicationProfiles.TEST)
@ContextConfiguration(classes = { ApplicationContext.class })
public class AttributeValueTest {

	AttributeValue value1;
	AttributeValue value2;
	AttributeValue value3;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void test() {

	}

}
