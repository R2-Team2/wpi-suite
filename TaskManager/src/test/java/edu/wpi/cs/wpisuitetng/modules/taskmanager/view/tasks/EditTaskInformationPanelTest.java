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
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.TaskStatus;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.attributes.Comment;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.attributes.CommentList;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

public class EditTaskInformationPanelTest {

    private ViewEventController mockViewEventController = mock(ViewEventController.class);
    private Task mockTask = mock(Task.class);
    private EditTaskPanel mockEditTaskPanel = mock(EditTaskPanel.class);
    private EditTaskInformationPanel editPanel;

    @Before
    public void setUp() throws Exception {
        List<String> activityList = new ArrayList<String>();
        activityList.add("Activity 1");
        List<String> userList = new ArrayList<String>();
        userList.add("User 1");
        CommentList commentList = new CommentList();
        commentList.add("Comment 1");

        when(mockTask.getTaskID()).thenReturn((long) 1);
        when(mockTask.getActualEffort()).thenReturn(1);
        when(mockTask.getActivityList()).thenReturn(activityList);
        when(mockTask.getAssignedUsers()).thenReturn(userList);
        when(mockTask.getComments()).thenReturn(commentList);
        when(mockTask.getDescription()).thenReturn("Test EditTaskInformationPanelTest");
        when(mockTask.getDueDate()).thenReturn(new Date());
        when(mockTask.getEstimatedEffort()).thenReturn(2);
        when(mockTask.getRequirement()).thenReturn(-1);
        when(mockTask.getStartDate()).thenReturn(new Date());
        when(mockTask.getTitle()).thenReturn("Mock Task");
        when(mockTask.getUserForTaskCard()).thenReturn("Tester");
        when(mockTask.getStatus()).thenReturn(new TaskStatus("Edit"));

        mockEditTaskPanel.aTask = mockTask;

        Request mockReq = mock(Request.class);
        Network mockNet = mock(Network.class);
        when(mockNet.makeRequest(anyString(), isA(HttpMethod.class))).thenReturn(mockReq);
        Network.setInstance(mockNet);

        editPanel = new EditTaskInformationPanel(mockEditTaskPanel);

        ViewEventController.setInstance(mockViewEventController);
    }

    @Test
    public void testConstructor() {

        assertNotNull(editPanel);
        assertEquals(mockEditTaskPanel, editPanel.parentPanel);

        assertEquals(new Dimension(540, 200), editPanel.getMinimumSize());
    }

    @Test
    public void testGetTask() {
        assertEquals(editPanel.getTask().getTaskID(), 1);
    }

    @After
    public void cleanup() {
        // IterationModel.setInstance(null);
        ViewEventController.setInstance(null);
    }

    /*
     * @Test public void testEditTaskInformationPanel() { fail("Not yet implemented"); }
     * @Test public void testSetupTask() { fail("Not yet implemented"); }
     * @Test public void testSetupListeners() { fail("Not yet implemented"); }
     * @Test public void testBuildLayout() { fail("Not yet implemented"); }
     * @Test public void testGetTitle() { fail("Not yet implemented"); }
     * @Test public void testGetDescription() { fail("Not yet implemented"); }
     * @Test public void testGetEstimatedEffort() { fail("Not yet implemented"); }
     * @Test public void testGetActualEffort() { fail("Not yet implemented"); }
     * @Test public void testGetStatus() { fail("Not yet implemented"); }
     * @Test public void testGetRequirement() { fail("Not yet implemented"); }
     * @Test public void testGetStartDate() { fail("Not yet implemented"); }
     * @Test public void testGetDueDate() { fail("Not yet implemented"); }
     * @Test public void testGetAssignedUsers() { fail("Not yet implemented"); }
     * @Test public void testOpenSelectedRequirement() { fail("Not yet implemented"); }
     * @Test public void testDisableAll() { fail("Not yet implemented"); }
     * @Test public void testOpenRequirement() { fail("Not yet implemented"); }
     */

}
