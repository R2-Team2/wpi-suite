package edu.wpi.cs.wpisuitetng.modules.taskmanager.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TaskStatusTest {

    /** The TaskStatus to be tested. */
    TaskStatus ts1;

    /** The TaskList */
    List<Task> taskList = new ArrayList<Task>();

    /** Test Tasks */
    Task t1;
    Task t2;

    @Before
    public void setUp() throws Exception {
        ts1 = new TaskStatus("Task Status 1");
        ts1.setTaskStatusID(1);

        String user1 = "mknightley";
        String user2 = "jdoe";
        long taskID1 = 23;
        long taskID2 = 25;
        String title1 = "Task 1";
        String title2 = "Task 2";
        String description1 = "Description 1";
        String description2 = "Description 2";
        int estimatedEffort1 = 1;
        int estimatedEffort2 = 1;
        int actualEffort1 = 1;
        int actualEffort2 = 1;
        int requirement1 = 1;
        int requirement2 = 2;
        Date startDate1 = new Date();
        Date startDate2 = new Date();
        Date dueDate1 = new Date();
        Date dueDate2 = new Date();
        List<String> userList1 = new ArrayList<String>();
        userList1.add(user1);
        List<String> userList2 = new ArrayList<String>();
        userList2.add(user1);
        userList2.add(user2);
        List<String> activityList = new ArrayList<String>();

        t1 =
                new Task(taskID1, title1, description1, estimatedEffort1, actualEffort1, ts1,
                        requirement1, startDate1, dueDate1, userList1, activityList);
        t2 =
                new Task(taskID2, title2, description2, estimatedEffort2, actualEffort2, ts1,
                        requirement2, startDate2, dueDate2, userList2, activityList);

        taskList.add(t1);
        taskList.add(t2);

    }

    @Test
    public void testTaskStatusConstructor() {
        TaskStatus testTaskStatus = new TaskStatus("Test");
        assertEquals(testTaskStatus.getName(), "Test");
        assertNotNull(testTaskStatus.getTaskList());
    }

    @Test
    public void testSetName() {
        assertEquals(ts1.getName(), "Task Status 1");
        ts1.setName("foo");
        assertEquals(ts1.getName(), "foo");
    }

    @Test
    public void testSetTaskList() {
        ts1.setTaskList(taskList);
        assertEquals(ts1.getTaskList(), taskList);
    }

    @Test
    public void testAddTask() {
        ts1.setTaskList(taskList);
        assertEquals(ts1.getTaskList(), taskList);

        ts1.setTaskList(new ArrayList<Task>());
        assertEquals(ts1.getTaskList().size(), 0);

        ts1.addTask(t1);
        assertTrue(ts1.getTaskList().contains(t1));
        assertEquals(ts1.getTaskList().size(), 1);

        ts1.addTask(t2);
        assertTrue(ts1.getTaskList().contains(t2));
        assertEquals(ts1.getTaskList().size(), 2);

        ts1.addTask(t2);
        assertTrue(ts1.getTaskList().contains(t2));
        assertEquals(ts1.getTaskList().size(), 3);
    }

    @Test
    public void testRemTask() {
        ts1.setTaskList(taskList);
        assertTrue(ts1.getTaskList().contains(t1));

        ts1.remTask(t1);
        assertFalse(ts1.getTaskList().contains(t1));
    }

    @Test
    public void testSetTaskStatusID() {
        ts1.setTaskStatusID(21);
        assertEquals(ts1.getTaskStatusID(), 21);
    }

    @Test
    public void testGetSize() {
        ts1.setTaskList(taskList);
        assertEquals(ts1.getSize(), taskList.size());
    }

    @Test
    public void testGetElementAt() {
        ts1.setTaskList(taskList);
        for (int i = 0; i < taskList.size(); i++) {
            assertEquals(ts1.getElementAt(i), taskList.get(i));
        }
    }

    @Test
    public void testGetName() {
        assertEquals(ts1.toString(), "Task Status 1");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testIdentify() throws UnsupportedOperationException {
        final TaskStatus unTested = new TaskStatus("Test");
        final Object o = new Object();
        unTested.identify(o);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testSave() throws UnsupportedOperationException {
        final TaskStatus unTested = new TaskStatus("Test");
        unTested.save();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testDelete() throws UnsupportedOperationException {
        final TaskStatus unTested = new TaskStatus("Test");
        unTested.delete();
    }
}
