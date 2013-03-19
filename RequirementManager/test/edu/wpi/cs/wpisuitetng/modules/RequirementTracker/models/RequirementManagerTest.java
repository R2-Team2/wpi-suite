/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.RequirementTracker.models;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.Session;
import edu.wpi.cs.wpisuitetng.exceptions.BadRequestException;
import edu.wpi.cs.wpisuitetng.exceptions.NotFoundException;
import edu.wpi.cs.wpisuitetng.exceptions.NotImplementedException;
import edu.wpi.cs.wpisuitetng.exceptions.UnauthorizedException;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.MockData;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.RequirementManager;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.RequirementEntityManager;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.RequirementStatus;
import edu.wpi.cs.wpisuitetng.modules.core.models.Project;
import edu.wpi.cs.wpisuitetng.modules.core.models.Role;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
/**
 * @author Dylan
 *
 */
public class RequirementManagerTest {

	MockData db;
	User existingUser;
	Requirement existingRequirement;
	Session defaultSession;
	String mockSsid;
	RequirementEntityManager manager;
	Requirement newRequirement;
	User bob;
	Requirement goodUpdatedRequirement;
	Session adminSession;
	Project testProject;
	Project otherProject;
	Requirement otherRequirement;
	
	@Before
	public void setUp() throws Exception {
		User admin = new User("admin", "admin", "1234", 27);
		admin.setRole(Role.ADMIN);
		testProject = new Project("test", "1");
		otherProject = new Project("other", "2");
		mockSsid = "abc123";
		adminSession = new Session(admin, testProject, mockSsid);
		
		existingUser = new User("joe", "joe", "1234", 2);
		existingRequirement = new Requirement("Bob", "1.0", RequirementStatus.DUMMY, "Desc", 1, 1);
		existingRequirement.setId(1);
		
		otherRequirement = new Requirement("Joe", "2.0", RequirementStatus.DUMMY, "Description", 2, 2);
		otherRequirement.setId(2);
				
		defaultSession = new Session(existingUser, testProject, mockSsid);
		newRequirement = new Requirement("Jim", "3.0", RequirementStatus.DUMMY, "Desc", 1, 2);
		newRequirement.setId(3);
		
		db = new MockData(new HashSet<Object>());
		db.save(existingRequirement, testProject);
		db.save(existingUser);
		db.save(otherRequirement, otherProject);
		db.save(admin);
		manager = new RequirementEntityManager(db);
	}

	@Test
	public void testMakeEntity() throws WPISuiteException {
		Requirement created = manager.makeEntity(defaultSession, newRequirement.toJSON());
		assertEquals(3, created.getId()); // IDs are unique across projects
		assertEquals("Jim", created.getName());
		assertEquals("3.0", created.getRelease());
		assertEquals(RequirementStatus.DUMMY, created.getStatus());
		assertEquals("Desc", created.getDescription());
		assertEquals(1, created.getEstimate());
		assertEquals(2, created.getEffort());
		assertSame(db.retrieve(Requirement.class, "id", 3).get(0), created);
	}
	
	@Test
	public void testGetEntity() throws NotFoundException {
		Requirement[] gotten = manager.getEntity(defaultSession, "1");
		assertSame(existingRequirement, gotten[0]);
	}

	@Test(expected=NotFoundException.class)
	public void testGetBadId() throws NotFoundException {
		manager.getEntity(defaultSession, "-1");
	}

	@Test(expected=NotFoundException.class)
	public void testGetMissingEntity() throws NotFoundException {
		manager.getEntity(defaultSession, "2");
	}
	
	@Test
	public void testGetAll() {
		Requirement[] gotten = manager.getAll(defaultSession);
		assertEquals(1, gotten.length);
		assertSame(existingRequirement, gotten[0]);
	}
	
	@Test
	public void testSave() {
		/*Requirement newRequirement = new Requirement();
		manager.save(defaultSession, newRequirement);
		assertSame(newRequirement, db.retrieve(Requirement.class, "id", 3).get(0));
		assertSame(testProject, newRequirement.getProject());*/
	}
	
	@Test
	public void testDelete() throws WPISuiteException {
		assertSame(existingRequirement, db.retrieve(Requirement.class, "id", 1).get(0));
		assertTrue(manager.deleteEntity(adminSession, "1"));
		assertEquals(0, db.retrieve(Requirement.class, "id", 1).size());
	}
	
	@Test(expected=NotFoundException.class)
	public void testDeleteMissing() throws WPISuiteException {
		manager.deleteEntity(adminSession, "4534");
	}
	
	@Test(expected=UnauthorizedException.class)
	public void testDeleteNotAllowed() throws WPISuiteException {
		manager.deleteEntity(defaultSession, Integer.toString(existingRequirement.getId()));
	}
	
	@Test
	public void testDeleteAll() throws WPISuiteException {
		Requirement anotherRequirement = new Requirement();
		manager.makeEntity(defaultSession, anotherRequirement.toJSON());
		assertEquals(2, db.retrieveAll(new Requirement(), testProject).size());
		manager.deleteAll(adminSession);
		assertEquals(0, db.retrieveAll(new Requirement(), testProject).size());
		// otherRequirement should still be around
		assertEquals(1, db.retrieveAll(new Requirement(), otherProject).size());
	}

	@Test
	public void testDeleteAllWhenEmpty() throws WPISuiteException {
		manager.deleteAll(adminSession);
		manager.deleteAll(adminSession);
		// no exceptions
	}
	
	@Test
	public void testCount() throws WPISuiteException {
		assertEquals(2, manager.Count());
	}
}

