package edu.wpi.cs.wpisuitetng.modules.taskmanager.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
    public void testWorkFlowConstructor() {
        WorkFlow testWorkFlow = new WorkFlow();
        assertNotNull(testWorkFlow.getTaskStatusList());
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

    @Test
    public void testToAndFromJson() {
        final String testToJson = wf.toJson();
        final WorkFlow testFromJson = WorkFlow.fromJson(testToJson);
        assertEquals(wf.getWorkFlowID(), testFromJson.getWorkFlowID());
        assertEquals(wf.getTaskStatusList(), testFromJson.getTaskStatusList());
    }

    @Test
    public void testUpdate() {
        final WorkFlow updatedWF = new WorkFlow();
        wf.update(updatedWF);
        assertEquals(updatedWF.getWorkFlowID(), wf.getWorkFlowID());
        assertEquals(updatedWF.getTaskStatusList(), wf.getTaskStatusList());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testIdentify() throws UnsupportedOperationException {
        final WorkFlow unTested = new WorkFlow();
        final Object o = new Object();
        unTested.identify(o);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testSave() throws UnsupportedOperationException {
        final WorkFlow unTested = new WorkFlow();
        unTested.save();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testDelete() throws UnsupportedOperationException {
        final WorkFlow unTested = new WorkFlow();
        unTested.delete();
    }

}
