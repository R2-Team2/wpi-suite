package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.TaskStatus;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.NewTaskPanel;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

public class AddTaskControllerTest {
    NewTaskPanel view;
    Task task;
    Request mockReq;

    @Before
    public void setUp() throws Exception {
        task =
                new Task(0, "The Title", "Brief description", 1, 2, new TaskStatus("running"),
                        "some requirement", new Date(), new Date(), new ArrayList<String>(),
                        new ArrayList<String>(), "");

        view = mock(NewTaskPanel.class);
        when(view.getTitle()).thenReturn(task.getTitle());
        when(view.getDescription()).thenReturn(task.getDescription());
        when(view.getEstimatedEffort()).thenReturn(task.getEstimatedEffort());
        when(view.getActualEffort()).thenReturn(task.getActualEffort());
        when(view.getStatus()).thenReturn(task.getStatus().getName());
        when(view.getRequirement()).thenReturn(task.getRequirement());
        when(view.getStartDate()).thenReturn(task.getStartDate());
        when(view.getDueDate()).thenReturn(task.getDueDate());
        when(view.getAssignedUsers()).thenReturn(new ArrayList<User>());

        mockReq = mock(Request.class);
        Network mockNet = mock(Network.class);
        when(mockNet.makeRequest(anyString(), isA(HttpMethod.class))).thenReturn(mockReq);
        Network.setInstance(mockNet);
    }

    @Test
    public void testAddTask() {
        new AddTaskController(view).addTask();
        verify(mockReq).setBody(anyString());
        verify(mockReq).addObserver(isA(AddTaskRequestObserver.class));
        verify(mockReq).send();
    }
}
