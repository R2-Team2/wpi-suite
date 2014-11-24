package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.workflow;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.WorkFlow;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview.WorkFlowView;

public class RetrieveWorkFlowController {

    private final WorkFlowView view; // change this data type to Selim's

    public RetrieveWorkFlowController(WorkFlowView view) {
        this.view = view;
    }

    /**
     * @param workFlowID
     */
    public void requestTaskStatus(int workFlowID) {

    }

    public void displayWorkFlow(WorkFlow[] workFlow) {

    }

}
