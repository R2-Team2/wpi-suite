package edu.wpi.cs.wpisuitetng.modules.taskmanager.models;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class WorkFlowTest {

    /** The Workflow to be tested. */
    WorkFlow wf;

    /** The task status list. */
    private List<Long> taskStatusList = new ArrayList<Long>();

    /** The task statuses to be tested. */
    TaskStatus ts1;
    TaskStatus ts2;

    @Before
    public void setUp() throws Exception {
        wf = new WorkFlow();
        wf.setWorkFlowID(0);

        ts1 = new TaskStatus("Task Status 1");
        ts2 = new TaskStatus("Task Status 2");

        taskStatusList.add(ts1.getTaskStatusID());
        taskStatusList.add(ts2.getTaskStatusID());
        wf.setTaskStatusList(taskStatusList);
    }

    @Test
    public void testSetWorkFlowID() {
        assertEquals(wf.getWorkFlowID(), 0);
        wf.setWorkFlowID(1);
        assertEquals(wf.getWorkFlowID(), 1);
    }

    @Test
    public void testSetTaskStatusList() {
        assertEquals(wf.getTaskStatusList(), taskStatusList);

        List<Long> aTaskStatusList = new ArrayList<Long>();
        wf.setTaskStatusList(aTaskStatusList);
        assertEquals(wf.getTaskStatusList(), aTaskStatusList);
    }

}
