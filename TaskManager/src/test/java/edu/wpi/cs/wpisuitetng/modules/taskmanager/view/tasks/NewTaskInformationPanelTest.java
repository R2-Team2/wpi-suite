package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.IterationModel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.TaskStatus;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.attributes.Comment;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

public class NewTaskInformationPanelTest {

    private ViewEventController mockViewEventController = mock(ViewEventController.class);
    private Task mockTask = mock(Task.class);
    private NewTaskPanel mockNewTaskPanel = mock(NewTaskPanel.class);

    @Before
    public void setUp() throws Exception {
        when(mockTask.getTaskID()).thenReturn((long) 1);
        when(mockTask.getActualEffort()).thenReturn(1);
        when(mockTask.getActivityList()).thenReturn(new ArrayList<String>());
        when(mockTask.getAssignedUsers()).thenReturn(new ArrayList<String>());
        when(mockTask.getComments()).thenReturn(new ArrayList<Comment>());
        when(mockTask.getDescription()).thenReturn("Test NewTaskInformationPanelTest");
        when(mockTask.getDueDate()).thenReturn(new Date());
        when(mockTask.getEstimatedEffort()).thenReturn(2);
        when(mockTask.getRequirement()).thenReturn(null);
        when(mockTask.getStartDate()).thenReturn(new Date());
        when(mockTask.getTitle()).thenReturn("Mock Task");
        when(mockTask.getUserForTaskCard()).thenReturn("Tester");
        when(mockTask.getStatus()).thenReturn(new TaskStatus("New"));

        Request mockReq = mock(Request.class);
        Network mockNet = mock(Network.class);
        when(mockNet.makeRequest(anyString(), isA(HttpMethod.class))).thenReturn(mockReq);
        Network.setInstance(mockNet);

        ViewEventController.setInstance(mockViewEventController);
    }

    @Test
    public void testConstructor() {
        NewTaskInformationPanel newPanel = new NewTaskInformationPanel(mockNewTaskPanel);

        assertNotNull(newPanel);
        assertEquals(mockNewTaskPanel, newPanel.parentPanel);

        assertEquals(new Dimension(540, 200), newPanel.getMinimumSize());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetTask() {
        NewTaskInformationPanel newPanel = new NewTaskInformationPanel(mockNewTaskPanel);
        newPanel.getTask();
    }

    @After
    public void cleanup() {
        IterationModel.setInstance(null);
        ViewEventController.setInstance(null);
    }

}
