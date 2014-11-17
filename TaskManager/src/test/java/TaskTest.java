///*******************************************************************************
// * Copyright (c) 2013 WPI-Suite
// * All rights reserved. This program and the accompanying materials
// * are made available under the terms of the Eclipse Public License v1.0
// * which accompanies this distribution, and is available at
// * http://www.eclipse.org/legal/epl-v10.html
// * 
// * Contributors:
// * 		R2-Team2
// ************************************/
//
//import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;
//import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.taskstatus.TaskStatus;
//import edu.wpi.cs.wpisuitetng.modules.core.models.User;
//import static org.junit.Assert.*;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.Date;
//import java.util.List;
//import java.util.ArrayList;
//
//public class TaskTest {
//
//	Task t1;
//	User u1;
//	User u2;
//	Date date;
//	
//	@Before
//	public void setUp() throws Exception {
//		u1 = new User("John Doe", "jdoe", "pswd", 3);
//		u2 = new User("Mark Knights", "mknights", "", 5);
//		
//		List<User> userList = new ArrayList<User>();
//		userList.add(u1);
//		date = new Date();
//		
//		t1 = new Task(12, "Title", "Description", userList, 3, 2, date, new ArrayList<String>(), 0, TaskStatus.NEW);
//	}
//
//	@Test
//	public void testConstructor() {
//		assertEquals(t1.getID(), 12);
//		assertEquals(t1.getTitle(), "Title");
//		assertEquals(t1.getDescription() , "Description");
//		
//		List<User> assignedUsers = t1.getAssignedUsers();
//		
//		assertTrue(assignedUsers.contains(u1));
//		assertEquals(assignedUsers.size(), 1);
//		
//		assertEquals(t1.getEstimatedEffort(), 3);
//		assertEquals(t1.getActualEffort(), 2);
//		assertEquals(t1.getDueDate(), date);
//		assertEquals(t1.getActivityList().size(), 0);
//		assertEquals(t1.getRequirement(), 0);
//		assertEquals(t1.getStatus(), TaskStatus.NEW);
//	}
//	
//	@Test
//	public void testSetTitle() {
//		t1.setTitle("Foo");
//		assertEquals(t1.getTitle(), "Foo");
//	}
//	
//	@Test
//	public void testSetDescription() {
//		t1.setDescription("Four score and seven years ago");
//		assertEquals(t1.getDescription(), "Four score and seven years ago");
//	}
//	
//	@Test
//	public void testSetEstimatedEffort() {
//		t1.setEstimatedEffort(27);
//		assertEquals(t1.getEstimatedEffort(), 27);
//	}
//	
//	@Test
//	public void testSetActualEffort() {
//		t1.setActualEffort(13);
//		assertEquals(t1.getActualEffort(), 13);
//	}
//	
//	@Test
//	public void testSetDueDate() {
//		Date newDate = new Date();
//		t1.setDueDate(newDate);
//		assertEquals(t1.getDueDate(), newDate);
//	}
//	
//	@Test
//	public void testSetRequirementID() {
//		t1.setRequirementID(51);
//		assertEquals(t1.getRequirement(), 51);
//	}
//	
//	@Test
//	public void testSetStatus() {
//		t1.setStatus(TaskStatus.IN_PROGRESS);
//		assertEquals(t1.getStatus(), TaskStatus.IN_PROGRESS);
//	}
//	
//	@Test
//	public void testAddAssignedUser() {
//		t1.addAssignedUser(u1);
//		assertTrue(t1.getAssignedUsers().contains(u1));
//		assertEquals(t1.getAssignedUsers().size(), 1);
//		
//		t1.addAssignedUser(u2);
//		assertTrue(t1.getAssignedUsers().contains(u1));
//		assertTrue(t1.getAssignedUsers().contains(u2));
//		assertEquals(t1.getAssignedUsers().size(), 2);
//	}
//	
//	@Test
//	public void testDeleteUser() {
//		t1.deleteUser(u2.getIdNum());
//		assertTrue(t1.getAssignedUsers().contains(u1));
//		assertEquals(t1.getAssignedUsers().size(), 1);
//		
//		t1.deleteUser(u1.getIdNum());
//		assertFalse(t1.getAssignedUsers().contains(u1));
//		assertEquals(t1.getAssignedUsers().size(), 0);
//		
//		t1.addAssignedUser(u1);
//		t1.addAssignedUser(u2);
//		t1.deleteUser(u1.getIdNum());
//		assertTrue(t1.getAssignedUsers().contains(u2));
//		assertFalse(t1.getAssignedUsers().contains(u1));
//		assertEquals(t1.getAssignedUsers().size(), 1);
//	}
//	
//	@Test
//	public void testAddActivity() {
//		String comment1 = "John Doe commented on this task.";
//		String comment2 = "The task was closed.";
//		
//		t1.addActivity(comment1);
//		assertTrue(t1.getActivityList().contains(comment1));
//		assertEquals(t1.getActivityList().size(), 1);
//		
//		t1.addActivity(comment1);
//		assertTrue(t1.getActivityList().contains(comment1));
//		assertEquals(t1.getActivityList().size(), 2);
//		
//		t1.addActivity(comment2);
//		assertTrue(t1.getActivityList().contains(comment2));
//		assertEquals(t1.getActivityList().size(), 3);
//	}
//
//}
