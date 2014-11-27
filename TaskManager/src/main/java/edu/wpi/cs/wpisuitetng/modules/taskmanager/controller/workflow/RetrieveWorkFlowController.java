package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.workflow;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.WorkFlow;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview.WorkFlowView;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

public class RetrieveWorkFlowController {

    private final WorkFlowView view;

    public RetrieveWorkFlowController(WorkFlowView view) {
        this.view = view;
    }

    /**
     * @param workFlowID
     */
    public void requestWorkFlow() {
        final Request request =
                Network.getInstance().makeRequest("taskmanager/workflow", HttpMethod.GET);
        request.addObserver(new RetrieveWorkFlowRequestObserver(this));
        request.send();
    }

    public void displayWorkFlow(WorkFlow[] workFlow) {
        view.updateWorkFlowView(workFlow[0]);
    }

    // public void generateWorkFlow() {
    // view.initWorkFlowView();
    // }
}
