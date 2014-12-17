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
                new Task(12, "Title", "Description", 3, 2, newest, -1, date, date, userList,
                        activityList);
    }

    /**
     * Test constructor.
     */
    @Test
    public void testTaskConstructor() {
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
     * Test set start date.
     */
    @Test
    public void testSetStartDate() {
        final Date newDate = new Date();
        t1.setStartDate(newDate);
        assertEquals(t1.getStartDate(), newDate);
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
     * Test set requirement.
     */
    @Test
    public void testSetRequirement() {
        final String requirement = "Create Task Feature";
        t1.setRequirement(requirement);
        assertEquals(t1.getRequirement(), requirement);
    }

    /**
     * Test add assigned user.
     */
    @Test
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
    public void testDeleteUser() {
        t1.deleteUser(user2);
        assertTrue(t1.getAssignedUsers().contains(user1));
        assertFalse(t1.getAssignedUsers().contains(user2));
        assertEquals(t1.getAssignedUsers().size(), 1);

        t1.deleteUser(user1);
        assertFalse(t1.getAssignedUsers().contains(user1));
        assertEquals(t1.getAssignedUsers().size(), 0);

        t1.addAssignedUser(user1);
        t1.addAssignedUser(user2);
        t1.addAssignedUser(user1);
        t1.addAssignedUser(user2);
        assertEquals(t1.getAssignedUsers().size(), 2);
        assertFalse(t1.getAssignedUsers().contains("3"));

        t1.deleteUser(user1);
        assertEquals(t1.getAssignedUsers().size(), 1);

        t1.deleteUser(user2);
        assertEquals(t1.getAssignedUsers().size(), 0);
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

    @Test
    public void testGetUserForTaskCard() {
        t1.addAssignedUser(user1);
        assertEquals(t1.getUserForTaskCard(), user1);

        t1.addAssignedUser(user2);
        assertEquals(t1.getUserForTaskCard(), user1 + " ...");

        t1.deleteUser(user1);
        assertEquals(t1.getUserForTaskCard(), user2);

        t1.deleteUser(user2);
        assertEquals(t1.getUserForTaskCard(), "");

        Task testNullUsers =
                new Task(12, "Title", "Description", 3, 2, newest, "Requirement", date, date,
                        null, activityList);
        assertEquals(testNullUsers.getUserForTaskCard(), "");

    }

    @Test
    public void testUpdate() {
        TaskStatus oldest = new TaskStatus("OLD");
        Task t2 = new Task(12, "Title", "Description", 3, 2, oldest, "Requirement", date, date,
                userList, activityList);

        t1.update(t1);
        assertEquals(t1.getActivityList().size(), 0);

        t1.update(t2);
        assertEquals(t1.getActivityList().size(), 1);

        t2.setStatus(newest);
        t1.update(t2);
        assertEquals(t1.getActivityList().size(), 2);
    }

    @Test
    public void testToAndFromJson() {
        final String testToJson = t1.toJson();
        final Task testFromJson = Task.fromJson(testToJson);

        assertEquals(t1.getActivityList(), testFromJson.getActivityList());
        assertEquals(t1.getActualEffort(), testFromJson.getActualEffort());
        assertEquals(t1.getAssignedUsers(), testFromJson.getAssignedUsers());
        assertEquals(t1.getComments(), testFromJson.getComments());
        assertEquals(t1.getDescription(), testFromJson.getDescription());
        // assertEquals(t1.getDueDate(), testFromJson.getDueDate());
        assertEquals(t1.getEstimatedEffort(), testFromJson.getEstimatedEffort());
        assertEquals(t1.getRequirement(), testFromJson.getRequirement());
        // assertEquals(t1.getStartDate(), testFromJson.getStartDate());
        // assertEquals(t1.getStatus(), testFromJson.getStatus());
        assertEquals(t1.getTaskID(), testFromJson.getTaskID());
        assertEquals(t1.getTitle(), testFromJson.getTitle());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testIdentify() throws UnsupportedOperationException {
        final Task unTested =
                new Task(0, user1, user1, 0, 0, newest, user1, date, date, activityList,
                        activityList);
        final Object o = new Object();
        unTested.identify(o);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testSave() throws UnsupportedOperationException {
        final Task unTested =
                new Task(0, user1, user1, 0, 0, newest, user1, date, date, activityList,
                        activityList);
        unTested.save();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testDelete() throws UnsupportedOperationException {
        final Task unTested =
                new Task(0, user1, user1, 0, 0, newest, user1, date, date, activityList,
                        activityList);
        unTested.delete();
    }

}
