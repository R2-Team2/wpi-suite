/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.Session;
import edu.wpi.cs.wpisuitetng.exceptions.BadRequestException;
import edu.wpi.cs.wpisuitetng.exceptions.NotFoundException;
import edu.wpi.cs.wpisuitetng.exceptions.UnauthorizedException;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.core.models.Project;
import edu.wpi.cs.wpisuitetng.modules.core.models.Role;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.MockData;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.Iteration;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.IterationEntityManager;

/**
 * Tests the IterationEntityManager.java source folder
 * @author Rolling Thunder
 *
 * @version $Revision: 1.0 $
 */
public class IterationEntityManagerTest {
	MockData db;
	Session defaultSession;
	User testUser;
	Project testProject;
	String mockSsid;
	Iteration itr;
	IterationEntityManager manager;

	/**
	 * Setup for tests
	 */
	@Before
	public void setUp() {
		db = new MockData(new HashSet<Object>());
		testUser = new User("joe", "joe", "1234", 2);
		testUser.setRole(Role.ADMIN);
		testProject = new Project("test", "1");
		mockSsid = "abc123";
		defaultSession = new Session(testUser, testProject, mockSsid);
		itr = new Iteration(2, "Rolling Thunder");
		manager = new IterationEntityManager(db);
		db.save(testUser);
	}

	/**
	 * Test making an iteration
	 */
	@Test
	public void testMakeIterationEntity() {
		assertNotNull(manager);
	}
	
	/**
	 * Test making iteration entity manager
	
	 * @throws WPISuiteException if not valid request */
	@Test
	public void testMakeIterationEntityManager() throws WPISuiteException {
		assertNotNull(manager.makeEntity(defaultSession, itr.toJSON()));
	}
	
	/**
	 * Test saving
	 */
	@Test
	public void testSave() {
		manager.save(defaultSession, new Iteration(3, "tester number 3"));
	}
	
	/**
	 * Test count of iterations
	
	 * @throws WPISuiteException if not valid request */
	@Test
	public void testCount() throws WPISuiteException {
		assertEquals(0, manager.Count());
		manager.save(defaultSession, new Iteration(3, "test 3"));
		assertEquals(1, manager.Count());
		manager.save(defaultSession, new Iteration(4, "test 4"));
		assertEquals(2, manager.Count());
		manager.save(defaultSession, new Iteration(5, "test 5"));
		assertEquals(3, manager.Count());
	}
	
	/**
	 * Test deleting all 
	
	 * @throws WPISuiteException if not valid request */
	@Test
	public void testDeleteAllAndEnsureRole() throws WPISuiteException {
		manager.save(defaultSession, new Iteration(3, "test 3"));
		manager.save(defaultSession, new Iteration(4, "test 4"));
		manager.save(defaultSession, new Iteration(5, "test 5"));
		assertEquals(3, manager.Count());
		manager.deleteAll(defaultSession);
		assertEquals(0, manager.Count());
	}
	
	/**
	 * Test deleting without permission
	
	 * @throws WPISuiteException if not valid request */
	@Test(expected=UnauthorizedException.class)
	public void testDeleteAllWithoutPermission() throws WPISuiteException {
		testUser.setRole(Role.USER);
		manager.deleteAll(defaultSession);
	}
	
	/**
	 * Test getting all iterations
	 */
	@Test
	public void testGetAll() {
		manager.save(defaultSession, new Iteration(3, "test 3"));
		manager.save(defaultSession, new Iteration(4, "test 4"));
		manager.save(defaultSession, new Iteration(5, "test 5"));
		Iteration itrList[] = manager.getAll(defaultSession);
		assertEquals(3, itrList.length);
	}
	
	/**
	 * Test getting an entity
	
	 * @throws NotFoundException if not found */
	@Test
	public void testGetEntity() throws NotFoundException {
		manager.save(defaultSession, new Iteration(3, "test 3"));
		manager.save(defaultSession, new Iteration(4, "test 4"));
		manager.save(defaultSession, new Iteration(5, "test 5"));
		Iteration itrList[] = manager.getEntity(defaultSession, "4");
		
		assertEquals(1, itrList.length);
		assertEquals(4, itrList[0].getId());
		assertEquals("test 4", itrList[0].getName());
	}
	
	/**
	 * Test getting an invalid entity
	
	 * @throws NotFoundException expects to not find the entity */
	@Test(expected=NotFoundException.class)
	public void testGetBadEntity() throws NotFoundException {
		manager.getEntity(defaultSession, "0");
	}
	
	/**
	 * Test getting an entity for an invalid iteration
	
	 * @throws NotFoundException expects to not find it */
	@Test
	public void testGetEntityForIterationNotFound() throws NotFoundException {
		boolean exceptionThrown = false;
		manager.save(defaultSession, new Iteration(3, "test 3"));
		manager.save(defaultSession, new Iteration(4, "test 4"));
		manager.save(defaultSession, new Iteration(5, "test 5"));
		try {
			manager.getEntity(defaultSession, "6");
		}catch (NotFoundException e){
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
	}
	
	/**
	 * Test to delete an entity
	
	 * @throws WPISuiteException if not valid request */
	@Test
	public void testDeleteEntity() throws WPISuiteException {
		manager.save(defaultSession, new Iteration(3, "test 3"));
		assertEquals(1, manager.Count());
		assertTrue(manager.deleteEntity(defaultSession, "3"));
		assertEquals(0, manager.Count());
		boolean exceptionThrown = false;
		try {
			manager.deleteEntity(defaultSession, "3");
		} catch (NotFoundException e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
	}
	
	/**
	 * Test updating an iteration
	
	 * @throws WPISuiteException if not valid request */
	@Test
	public void testUpdatingAnInteration() throws WPISuiteException {
		manager.save(defaultSession, new Iteration(3, "test 3"));
		assertEquals(1, manager.Count());
		assertEquals(3, manager.getEntity(defaultSession, "3")[0].getId());
		assertEquals("test 3", manager.getEntity(defaultSession, "3")[0].getName());
		
		manager.update(defaultSession, (new Iteration(3, "changed")).toJSON());
		assertEquals(1, manager.Count());
		assertEquals("changed", manager.getEntity(defaultSession, "3")[0].getName());
		
		boolean exceptionThrown = false;
		try {
			manager.update(defaultSession, new Iteration(4, "change Id 4").toJSON());
		} catch (BadRequestException e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
	}
}
