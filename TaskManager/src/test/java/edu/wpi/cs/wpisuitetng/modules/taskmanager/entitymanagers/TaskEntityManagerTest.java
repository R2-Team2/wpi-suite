package edu.wpi.cs.wpisuitetng.modules.taskmanager.entitymanagers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.BaseMatcher;
import org.hamcrest.CustomMatcher;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;
import edu.wpi.cs.wpisuitetng.Session;
import edu.wpi.cs.wpisuitetng.database.Data;
import edu.wpi.cs.wpisuitetng.exceptions.BadRequestException;
import edu.wpi.cs.wpisuitetng.exceptions.UnauthorizedException;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.Model;
import edu.wpi.cs.wpisuitetng.modules.core.models.Role;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.IDNum;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;

public class TaskEntityManagerTest {

    TaskEntityManager manager;
    Data db;
    Session s;
    Task task1, task2, task3, task4, task5, task6;
    User user;

    /**
     * Create mock database and stub functions as needed
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        s = mock(Session.class);
        db = mock(Data.class);

        // By default, return true (success) when anything is saved to the database
        when(db.save(any(), any())).thenReturn(true);

        // Store an IDNum "in the database"
        when(db.retrieveAll(isA(IDNum.class))).thenAnswer(new Answer<List<Model>>() {

            List<Model> idNumList = new ArrayList<Model>();
            {
                idNumList.add(new IDNum(db));
            }

            @Override
            public List<Model> answer(InvocationOnMock invocation) throws Throwable {
                return idNumList;
            }

        });

        manager = new TaskEntityManager(db);
        task1 = new Task(0, "Task 1", "to be stored", 0, 0, null, null, null, null, null, null);
        task2 = new Task(0, "Task 2", "to be stored", 0, 0, null, null, null, null, null, null);
        task3 = new Task(0, "Task 3", "to be stored", 0, 0, null, null, null, null, null, null);

        task4 = new Task(4, "Task 4", "to be retrieved", 0, 0, null, null, null, null, null, null);
        task5 = new Task(5, "Task 5", "to be retrieved", 0, 0, null, null, null, null, null, null);
        task6 = new Task(6, "Task 6", "to be retrieved", 0, 0, null, null, null, null, null, null);

        user = new User("jdoe", "John Doe", "xyzzy", 1);
        user.setRole(Role.ADMIN);
    }

    @Test
    public void testMakeEntity() throws WPISuiteException {
        Task retTask1 = manager.makeEntity(s, task1.toJson());
        assertEquals(0, retTask1.getTaskID());
        assertEquals("Task 1", retTask1.getTitle());

        Task retTask2 = manager.makeEntity(s, task2.toJson());
        assertEquals(1, retTask2.getTaskID());
        assertEquals("Task 2", retTask2.getTitle());

        Task retTask3 = manager.makeEntity(s, task3.toJson());
        assertEquals(2, retTask3.getTaskID());
        assertEquals("Task 3", retTask3.getTitle());

    }

    @Test(expected = WPISuiteException.class)
    public void testMakeEntityDBFails() throws WPISuiteException {
        // The database fails to save a task
        when(db.save(isA(Task.class), any())).thenReturn(false);
        manager.makeEntity(s, task1.toJson());
    }

    @Test
    public void testMakeEntityNoIDNumExists() throws WPISuiteException {
        // There are no IDNum objects in the database
        when(db.retrieveAll(isA(IDNum.class))).thenReturn(new ArrayList<IDNum>());

        Task retTask1 = manager.makeEntity(s, task1.toJson());
        assertEquals(0, retTask1.getTaskID());
        assertEquals("Task 1", retTask1.getTitle());
    }

    @Test(expected = WPISuiteException.class)
    public void testMakeEntityNoIDNumExistsDBFails() throws WPISuiteException {
        // There are no IDNum objects in the database
        when(db.retrieveAll(isA(IDNum.class))).thenReturn(new ArrayList<IDNum>());
        // The database fails to save a task
        when(db.save(isA(Task.class), any())).thenReturn(false);

        manager.makeEntity(s, task1.toJson());
    }


    @Test
    public void testGetEntity() throws WPISuiteException {
        // Task with id 4 exists in the database
        List<Model> retrievedList = new ArrayList<Model>();
        retrievedList.add(task4);
        when(db.retrieve(eq(Task.class), eq("id"), eq(4), any())).thenReturn(retrievedList);

        // Task with id 8 does not exist in the database
        when(db.retrieve(eq(Task.class), eq("id"), eq(8), any()))
        .thenReturn(new ArrayList<Model>());

        Task[] taskArray1 = manager.getEntity(s, "4");
        assertEquals(1, taskArray1.length);
        assertEquals(task4, taskArray1[0]);

        Task[] taskArray2 = manager.getEntity(s, "8");
        assertEquals(0, taskArray2.length);
    }

    @Test
    public void testGetAll() {
        // Populate the database
        List<Model> retrievedList = new ArrayList<Model>();
        retrievedList.add(task4);
        retrievedList.add(task5);
        retrievedList.add(task6);
        when(db.retrieveAll(isA(Task.class), any())).thenReturn(retrievedList);

        Task[] allTasksArray = manager.getAll(s);
        assertEquals(3, allTasksArray.length);
        assertEquals("Task 4", allTasksArray[0].getTitle());
        assertEquals("Task 5", allTasksArray[1].getTitle());
        assertEquals("Task 6", allTasksArray[2].getTitle());
    }

    @Test
    public void testUpdate() throws WPISuiteException {
        // When queried for Task with id 4, return task4
        List<Model> retrievedList = new ArrayList<Model>();
        retrievedList.add(task4);
        when(db.retrieve(eq(Task.class), eq("taskID"), eq(4L), any())).thenReturn(retrievedList);

        task4.setTitle("New task 4");
        Task retTask = manager.update(s, task4.toJson());
        assertEquals("New task 4", retTask.getTitle());
    }

    @Test(expected = BadRequestException.class)
    public void testUpdateNoSuchTask() throws WPISuiteException {
        manager.update(s, task5.toJson());
    }

    @Test(expected = WPISuiteException.class)
    public void testUpdateDBFails() throws WPISuiteException {
        // When queried for Task with id 4, return task4
        List<Model> retrievedList = new ArrayList<Model>();
        retrievedList.add(task4);
        when(db.retrieve(eq(Task.class), eq("taskID"), eq(4L), any())).thenReturn(retrievedList);

        when(db.save(isA(Task.class), any())).thenReturn(false);
        manager.update(s, task4.toJson());
    }

    @Test
    public void testSave() {
        manager.save(s, task1);
        verify(db).save(task1);
    }

    @Test
    public void testDeleteEntity() throws WPISuiteException {
        // Return jdoe when asked for the session username
        when(s.getUsername()).thenReturn("jdoe");
        List<Model> userList = new ArrayList<Model>();
        userList.add(user);
        // Return a list of Users when queried
        when(db.retrieve(eq(User.class), eq("username"), eq("jdoe"))).thenReturn(userList);
        // Return task5 when queried with id 5
        List<Model> taskList = new ArrayList<Model>();
        taskList.add(task5);
        when(db.retrieve(eq(Task.class), eq("id"), eq(5), any())).thenReturn(taskList);
        // If delete is called on a task, return task5
        when(db.delete(isA(Task.class))).thenReturn(
                new Task(0, null, null, 0, 0, null, null, null, null, null, null));

        assertTrue(manager.deleteEntity(s, "5"));
    }

    @Test
    public void testDeleteEntityNotFound() throws WPISuiteException {
        // Return jdoe when asked for the session username
        when(s.getUsername()).thenReturn("jdoe");
        List<Model> userList = new ArrayList<Model>();
        userList.add(user);
        // Return a list of Users when queried
        when(db.retrieve(eq(User.class), eq("username"), eq("jdoe"))).thenReturn(userList);
        // Return task5 when queried with id 5
        List<Model> taskList = new ArrayList<Model>();
        taskList.add(task5);
        when(db.retrieve(eq(Task.class), eq("id"), eq(5), any())).thenReturn(taskList);
        // If delete is called on a task, return task5
        when(db.delete(isA(Task.class))).thenReturn(null);

        assertFalse(manager.deleteEntity(s, "5"));
    }

    @Test(expected = UnauthorizedException.class)
    public void testDeleteEntityNotAdmin() throws WPISuiteException {
        // Change user's role to USER
        user.setRole(Role.USER);
        // Return jdoe when asked for the session username
        when(s.getUsername()).thenReturn("jdoe");
        List<Model> userList = new ArrayList<Model>();
        userList.add(user);
        // Return a list of Users when queried
        when(db.retrieve(eq(User.class), eq("username"), eq("jdoe"))).thenReturn(userList);

        manager.deleteEntity(s, "5");
    }

    @Test(expected = WPISuiteException.class)
    public void testDeleteAllException() throws WPISuiteException {
        manager.deleteAll(s);
    }

    @Test
    public void testCount() {
        List<Task> retrievedList = new ArrayList<Task>();
        retrievedList.add(task4);
        retrievedList.add(task5);
        retrievedList.add(task6);
        when(db.retrieveAll(isA(Task.class))).thenReturn(retrievedList);

        assertEquals(3, manager.Count());
    }
}
