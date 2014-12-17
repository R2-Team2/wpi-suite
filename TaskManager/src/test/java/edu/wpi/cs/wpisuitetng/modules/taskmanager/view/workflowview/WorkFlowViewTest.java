package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.TaskStatus;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.WorkFlow;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.taskstatus.TaskStatusView;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

public class WorkFlowViewTest {

    private ViewEventController mockViewEventController = mock(ViewEventController.class);
    private WorkFlow mockWorkFlow = mock(WorkFlow.class);
    private TaskStatusView mockTaskStatusView = mock(TaskStatusView.class);

    @Before
    public void setUp() throws Exception {
        when(mockWorkFlow.getWorkFlowID()).thenReturn((long) 0);

        List<Long> mockTaskStatusList = new ArrayList<Long>();
        mockTaskStatusList.add((long) 1);
        mockTaskStatusList.add((long) 2);
        mockTaskStatusList.add((long) 3);
        mockTaskStatusList.add((long) 4);
        when(mockWorkFlow.getTaskStatusList()).thenReturn(mockTaskStatusList);

        Request mockReq = mock(Request.class);
        Network mockNet = mock(Network.class);
        when(mockNet.makeRequest(anyString(), isA(HttpMethod.class))).thenReturn(mockReq);
        Network.setInstance(mockNet);

        ViewEventController.setInstance(mockViewEventController);
    }

    @Test
    public void testConstructor() {
        WorkFlowView newView = new WorkFlowView();
        assertNotNull(newView);

        assertEquals(new ArrayList<TaskStatus>(), newView.getWorkFlowObj().getTaskStatusList());
    }

    @Test
    public void testSetWorkFlowObj() {
        WorkFlowView newView = new WorkFlowView();
        newView.setWorkFlowObj(mockWorkFlow);

        List<Long> mockTaskStatusList = new ArrayList<Long>();
        mockTaskStatusList.add((long) 1);
        mockTaskStatusList.add((long) 2);
        mockTaskStatusList.add((long) 3);
        mockTaskStatusList.add((long) 4);

        assertEquals(mockWorkFlow.getWorkFlowID(), 0);
        assertEquals(newView.getWorkFlowObj().getTaskStatusList(), mockTaskStatusList);

    }

    @Test
    public void testAddTaskStatusView() {
        WorkFlowView newView = new WorkFlowView();
        List<TaskStatusView> mockTSLV = new ArrayList<TaskStatusView>();

        final TaskStatusView taskStatusNew = new TaskStatusView(new TaskStatus("New"));
        final TaskStatusView taskStatusSelDev =
                new TaskStatusView(new TaskStatus("Selected for Development"));
        final TaskStatusView taskStatusInDev =
                new TaskStatusView(new TaskStatus("Currently in Development"));
        final TaskStatusView taskStatusDone = new TaskStatusView(new TaskStatus("Completed"));
        final TaskStatusView taskStatusMock = new TaskStatusView(new TaskStatus("Mock"));

        mockTSLV.add(taskStatusNew);
        mockTSLV.add(taskStatusSelDev);
        mockTSLV.add(taskStatusInDev);
        mockTSLV.add(taskStatusDone);

        assertNotNull(newView.views);
        for (int i = 0; i < newView.views.size(); i++) {
            assertEquals(newView.views.get(i).toString(), mockTSLV.get(i).toString());
        }

        newView.addTaskStatusView(taskStatusMock);
        mockTSLV.add(taskStatusMock);

        for (int i = 0; i < newView.views.size(); i++) {
            assertEquals(newView.views.get(i).toString(), mockTSLV.get(i).toString());
        }
    }

    @Test
    public void testGetTaskStatusPanel() {
        WorkFlowView newView = new WorkFlowView();
        assertNotNull(newView.getTaskStatusPanel());
    }

    @Test
    public void testGetViews() {
        WorkFlowView newView = new WorkFlowView();
        List<TaskStatusView> mockTSLV = new ArrayList<TaskStatusView>();

        final TaskStatusView taskStatusNew = new TaskStatusView(new TaskStatus("New"));
        final TaskStatusView taskStatusSelDev =
                new TaskStatusView(new TaskStatus("Selected for Development"));
        final TaskStatusView taskStatusInDev =
                new TaskStatusView(new TaskStatus("Currently in Development"));
        final TaskStatusView taskStatusDone = new TaskStatusView(new TaskStatus("Completed"));
        final TaskStatusView taskStatusMock = new TaskStatusView(new TaskStatus("Mock"));

        mockTSLV.add(taskStatusNew);
        mockTSLV.add(taskStatusSelDev);
        mockTSLV.add(taskStatusInDev);
        mockTSLV.add(taskStatusDone);

        for (int i = 0; i < newView.views.size(); i++) {
            assertEquals(newView.getViews().get(i).toString(), mockTSLV.get(i).toString());
        }

        newView.addTaskStatusView(taskStatusMock);
        mockTSLV.add(taskStatusMock);
        for (int i = 0; i < newView.views.size(); i++) {
            assertEquals(newView.getViews().get(i).toString(), mockTSLV.get(i).toString());
        }
    }

}
