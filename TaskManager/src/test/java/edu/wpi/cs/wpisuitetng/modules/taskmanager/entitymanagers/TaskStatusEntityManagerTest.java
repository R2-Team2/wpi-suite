package edu.wpi.cs.wpisuitetng.modules.taskmanager.entitymanagers;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import edu.wpi.cs.wpisuitetng.Session;
import edu.wpi.cs.wpisuitetng.database.Data;
import edu.wpi.cs.wpisuitetng.exceptions.UnauthorizedException;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.Model;
import edu.wpi.cs.wpisuitetng.modules.core.models.Role;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.IDNum;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.TaskStatus;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.WorkFlow;

public class TaskStatusEntityManagerTest {

    TaskStatusEntityManager manager;
    Data db;
    Session s;
    User user;
    TaskStatus status1, status2, status3, status4, status5, status6;

    @Before
    public void setUp() throws Exception {
        s = mock(Session.class);
        db = mock(Data.class);

        // By default, return true (success) when anything is saved to the database
        when(db.save(any(), any())).thenReturn(true);

        // Store an IDNum "in the database"
        when(db.retrieve(IDNum.class, "id", 0)).thenAnswer(new Answer<List<Model>>() {

            List<Model> idNumList = new ArrayList<Model>();
            {
                idNumList.add(new IDNum(db));
            }

            @Override
            public List<Model> answer(InvocationOnMock invocation) throws Throwable {
                return idNumList;
            }

        });

        manager = new TaskStatusEntityManager(db);

        // To add to the database
        status1 = new TaskStatus("planning");
        status2 = new TaskStatus("doing");
        status3 = new TaskStatus("reviewing");

        // Stored in the database
        status4 = new TaskStatus("waiting");
        status4.setTaskStatusID(4);
        status5 = new TaskStatus("deciding");
        status5.setTaskStatusID(5);
        status6 = new TaskStatus("presenting");
        status6.setTaskStatusID(6);

        user = new User("jdoe", "John Doe", "xyzzy", 1);
        user.setRole(Role.ADMIN);
    }

    @Test
    public void testMakeEntity() throws WPISuiteException {
        TaskStatus retStatus1 = manager.makeEntity(s, status1.toJson());
        assertEquals(0, retStatus1.getTaskStatusID());
        assertEquals("planning", retStatus1.getName());

        TaskStatus retStatus2 = manager.makeEntity(s, status2.toJson());
        assertEquals(1, retStatus2.getTaskStatusID());
        assertEquals("doing", retStatus2.getName());

        TaskStatus retStatus3 = manager.makeEntity(s, status3.toJson());
        assertEquals(2, retStatus3.getTaskStatusID());
        assertEquals("reviewing", retStatus3.getName());
    }

    @Test(expected = WPISuiteException.class)
    public void testMakeEntityDBFails() throws WPISuiteException {
        // The database fails to save the status
        when(db.save(isA(TaskStatus.class), any())).thenReturn(false);
        manager.makeEntity(s, status1.toJson());
    }

    @Test
    public void testGetEntity() throws WPISuiteException {
        // Status with id 4 stored in the database
        List<Model> retrievedList = new ArrayList<Model>();
        retrievedList.add(status4);
        when(db.retrieve(eq(WorkFlow.class), eq("id"), eq(4), any())).thenReturn(retrievedList);

        // Status with id 8 does not exist in the database
        when(db.retrieve(eq(WorkFlow.class), eq("id"), eq(8), any())).thenReturn(
                new ArrayList<Model>());

        TaskStatus[] statusList1 = manager.getEntity(s, "4");
        assertEquals(1, statusList1.length);
        assertEquals(status4, statusList1[0]);

        TaskStatus[] statusList2 = manager.getEntity(s, "8");
        assertEquals(0, statusList2.length);
    }

    @Test
    public void testGetAll() throws WPISuiteException {
        // Populate the database
        List<Model> retrievedList = new ArrayList<Model>();
        retrievedList.add(status4);
        retrievedList.add(status5);
        retrievedList.add(status6);
        when(db.retrieveAll(isA(WorkFlow.class), any())).thenReturn(retrievedList);

        TaskStatus[] allTasksArray = manager.getAll(s);
        assertEquals(3, allTasksArray.length);
        assertEquals("waiting", allTasksArray[0].getName());
        assertEquals("deciding", allTasksArray[1].getName());
        assertEquals("presenting", allTasksArray[2].getName());
    }

    @Test
    public void testUpdate() throws WPISuiteException {
        // When queried for Task with id 4, return task4
        List<Model> retrievedList = new ArrayList<Model>();
        retrievedList.add(status4);
        when(db.retrieve(eq(TaskStatus.class), eq("id"), eq(4L), any())).thenReturn(retrievedList);

        status4.setName("no more waiting");
        TaskStatus retStatus = manager.update(s, status4.toJson());
        assertEquals("no more waiting", retStatus.getName());
    }

    @Test(expected = WPISuiteException.class)
    public void testUpdateDBFails() throws WPISuiteException {
        // When queried for Task with id 4, return task4
        List<Model> retrievedList = new ArrayList<Model>();
        retrievedList.add(status4);
        when(db.retrieve(eq(TaskStatus.class), eq("id"), eq(4L), any())).thenReturn(retrievedList);

        // Database fails to save
        when(db.save(isA(TaskStatus.class), any())).thenReturn(false);

        manager.update(s, status4.toJson());
    }

    @Test(expected = WPISuiteException.class)
    public void testUpdateNoSuchTask() throws WPISuiteException {
        manager.update(s, status5.toJson());
    }

    @Test
    public void testSave() {
        manager.save(s, status1);
        verify(db).save(status1);
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
        List<Model> statusList = new ArrayList<Model>();
        statusList.add(status5);
        when(db.retrieve(eq(WorkFlow.class), eq("id"), eq(5), any())).thenReturn(statusList);
        // If delete is called on a status, return a status (content unimportant for testing)
        when(db.delete(isA(TaskStatus.class))).thenReturn(new TaskStatus(""));

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
        List<Model> statusList = new ArrayList<Model>();
        statusList.add(status5);
        when(db.retrieve(eq(WorkFlow.class), eq("id"), eq(5), any())).thenReturn(statusList);
        // If delete is called on a task, return null
        when(db.delete(isA(TaskStatus.class))).thenReturn(null);

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
        List<TaskStatus> retrievedList = new ArrayList<TaskStatus>();
        retrievedList.add(status4);
        retrievedList.add(status5);
        retrievedList.add(status6);
        when(db.retrieveAll(isA(TaskStatus.class))).thenReturn(retrievedList);

        assertEquals(3, manager.Count());
    }
}
