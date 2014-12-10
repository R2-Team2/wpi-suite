/**
 * ****************************************************************************** Copyright (c) 2013
 * WPI-Suite All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team R2-Team2
 **/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
public class TaskTest {

    /** The t1. */
    Task t1;
    
    /** Test users */
    String user1;
    String user2;

    /** The date. */
    Date date;

    /** The newest. */
    TaskStatus newest = new TaskStatus("NEW");

    /** The activity list */
    List<String> activityList = new ArrayList<String>();

    /**
     * Begin test.
     */
    @Before
    public void beginTest() {
    	user1 = "mknightley";
    	user2 = "jdoe";
    	
        final List<String> userList = new ArrayList<String>();
        userList.add(user1);
        date = new Date();


        t1 =
                new Task(12, "Title", "Description", 3, 2, newest, "Requirement", date, date,
                        userList, activityList);
    }

    /**
     * Test constructor.
     */
    @Test
    @Ignore
    public void testConstructor() {
        assertEquals(t1.getTaskID(), 12);
        assertEquals(t1.getTitle(), "Title");
        assertEquals(t1.getDescription(), "Description");

        final List<String> assignedUsers = t1.getAssignedUsers();

        // assertTrue(assignedUsers.contains(u1));
        assertEquals(assignedUsers.size(), 1);

        assertEquals(t1.getEstimatedEffort(), 3);
        assertEquals(t1.getActualEffort(), 2);
        assertEquals(t1.getDueDate(), date);
        // TODO: get activityList to work
        // assertTrue(t1.getActivityList().size() >= (int)0);
        assertEquals(t1.getRequirement(), "Requirement");
        assertEquals(t1.getStatus(), newest);
    }

    /**
     * Test set title.
     */
    @Test
    public void testSetTitle() {
        t1.setTitle("Foo");
        assertEquals(t1.getTitle(), "Foo");
    }

    /**
     * Test set description.
     */
    @Test
    public void testSetDescription() {
        t1.setDescription("Four score and seven years ago");
        assertEquals(t1.getDescription(), "Four score and seven years ago");
    }

    /**
     * Test set estimated effort.
     */
    @Test
    public void testSetEstimatedEffort() {
        t1.setEstimatedEffort(27);
        assertEquals(t1.getEstimatedEffort(), 27);
    }

    /**
     * Test set actual effort.
     */
    @Test
    public void testSetActualEffort() {
        t1.setActualEffort(13);
        assertEquals(t1.getActualEffort(), 13);
    }

    /**
     * Test set due date.
     */
    @Test
    public void testSetDueDate() {
        final Date newDate = new Date();
        t1.setDueDate(newDate);
        assertEquals(t1.getDueDate(), newDate);
    }

    /**
     * Test set status.
     */
    @Test
    public void testSetStatus() {
        final TaskStatus progress = new TaskStatus("IN_PROGRESS");
        t1.setStatus(progress);
        assertEquals(t1.getStatus(), progress);
    }

    /**
     * Test add assigned user.
     */
    @Test
    @Ignore
    public void testAddAssignedUser() {
        t1.addAssignedUser(user1);
        assertTrue(t1.getAssignedUsers().contains(user1));
        assertEquals(t1.getAssignedUsers().size(), 1);

        t1.addAssignedUser(user2);
        assertTrue(t1.getAssignedUsers().contains(user1));
        assertTrue(t1.getAssignedUsers().contains(user2));
        assertEquals(t1.getAssignedUsers().size(), 2);
    }

    /**
     * Test delete user.
     */
    @Test
    @Ignore
    public void testDeleteUser() {
        t1.deleteUser(user2);
        assertTrue(t1.getAssignedUsers().contains(user1));
        assertEquals(t1.getAssignedUsers().size(), 1);

        t1.deleteUser(user1);
        assertFalse(t1.getAssignedUsers().contains(user1));
        assertEquals(t1.getAssignedUsers().size(), 0);

        t1.addAssignedUser(user1);
        t1.addAssignedUser(user2);
        t1.deleteUser(user1);
        assertTrue(t1.getAssignedUsers().contains(4));
        assertFalse(t1.getAssignedUsers().contains(3));
        assertEquals(t1.getAssignedUsers().size(), 1);
    }

    // TODO get activityList to work
    /**
     * Method testAddActivity.
     */
    @Test
    public void testAddActivity() {
        final String comment1 = "John Doe commented on this task.";
        final String comment2 = "The task was closed.";

        t1.addActivity(comment1);
        assertTrue(t1.getActivityList().contains(comment1));
        assertEquals(t1.getActivityList().size(), 1);

        t1.addActivity(comment1);
        assertTrue(t1.getActivityList().contains(comment1));
        assertEquals(t1.getActivityList().size(), 2);

        t1.addActivity(comment2);
        assertTrue(t1.getActivityList().contains(comment2));
        assertEquals(t1.getActivityList().size(), 3);
    }

}
