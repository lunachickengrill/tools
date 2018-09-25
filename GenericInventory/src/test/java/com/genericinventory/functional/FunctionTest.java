package com.genericinventory.functional;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.BaseMatcher.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import org.springframework.util.StopWatch;

import com.genericinventory.application.config.ApplicationContext;
import com.genericinventory.application.config.ApplicationProfiles;
import com.genericinventory.domain.objecttype.ObjectClass;
import com.genericinventory.domain.objecttype.ObjectType;
import com.genericinventory.domain.objecttype.ObjectTypeAttribute;
import com.genericinventory.infrastructure.persistence.AttributeRepository;
import com.genericinventory.infrastructure.persistence.ObjectTypeRepository;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(ApplicationProfiles.TEST)
@ContextConfiguration(classes = { ApplicationContext.class })
public class FunctionTest {

	ObjectType dbDevice = null;

	StopWatch stopWatch = new StopWatch(this.getClass().getSimpleName());
	Set<ObjectType> types = new HashSet<ObjectType>();

	@Autowired
	private ObjectTypeRepository objectTypeRepository;

	@Autowired
	private AttributeRepository attributeRepository;

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
	public final void testForTypeExists() {
		ObjectType dbDevice = objectTypeRepository.findByNameEqualsIgnoreCase("Device");
		assertNotNull("Type Device not found in repository", dbDevice);

	}

	@Test
	public final void testForParentExists() {
		ObjectType dbDevice = objectTypeRepository.findByNameEqualsIgnoreCase("Device");
		assertNotNull("Type Device not found in repository", dbDevice);
		assertEquals("dbDevice not of type ABSTRACT", ObjectClass.ABSTRACT, dbDevice.getObjectClass());
		Assert.notNull(dbDevice.getParentLongId(), "Long ParentId is null");
		Assert.notNull(dbDevice.getParent(), "Parent ObjectType is null");

	}

	@Test
	public final void testRecursion() {
		ObjectType dbType = objectTypeRepository.findByNameEqualsIgnoreCase("Modem");
		assertNotNull("Type Device not found in repository", dbType);

		Set<ObjectType> result = recursiveParentLookup(dbType, types);
		Assert.notEmpty(result, "result Set empty");

		types.clear();
		Set<ObjectType> resultSet = recursiveClassLookup(dbType, ObjectClass.SYSTEM, types);
		Assert.notEmpty(resultSet, "result Set empty");

		Set<ObjectTypeAttribute> attributes = new HashSet<ObjectTypeAttribute>();
		for (ObjectType o : resultSet) {
			attributes.addAll(o.getAttributeList());
		}

		Assert.notEmpty(attributes);
		for (ObjectTypeAttribute a : attributes) {
			System.out.println("ATTRIBUTES TO INHERIT: " + a.toString());
		}

	}

	public Set<ObjectTypeAttribute> getInheritedAttributes(Set<ObjectType> superTypes) {
		Set<ObjectTypeAttribute> result = new HashSet<ObjectTypeAttribute>();
		if (superTypes != null && (!(superTypes.isEmpty()))) {
			for (ObjectType o : superTypes) {
				if (!(o.getAttributeList().isEmpty())) {
					// result.add(o.getAttributeList());
				}

			}
		}

		return null;
	}

	public Set<ObjectType> recursiveParentLookup(ObjectType objectType, Set<ObjectType> set) {

		System.out.println("Recursive: " + objectType.toString());
		ObjectType temp = objectType.getParent();

		if (temp == null || objectType.getObjectClass().equals(ObjectClass.SYSTEM)) {
			set.add(objectType);
			return set;
		}

		else

			set.add(objectType);
		return recursiveParentLookup(temp, set);

	}

	public Set<ObjectType> recursiveClassLookup(final ObjectType start, final ObjectClass stopAtClass,
			Set<ObjectType> resultSet) {
		System.out.println("Resultset add: " + start.toString());
		ObjectType temp = start.getParent();
		if (temp == null || start.getObjectClass().equals(stopAtClass)) {
			resultSet.add(start);
			return resultSet;
		}

		else
			resultSet.add(start);
		return recursiveClassLookup(temp, stopAtClass, resultSet);

	}

}
