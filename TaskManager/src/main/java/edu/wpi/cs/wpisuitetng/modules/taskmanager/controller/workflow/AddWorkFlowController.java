package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.workflow;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.WorkFlow;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview.WorkFlowView;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

public class AddWorkFlowController {

    private final WorkFlowView view; // Change the data type to the one needed to make new work flow

    public AddWorkFlowController(WorkFlowView view) {
        this.view = view;
    }

    public void addWorkFlow() {
        WorkFlow newWorkFlow = view.getWorkFlowObj();
        // Send a request to the core to save this message
        final Request request =
                Network.getInstance().makeRequest("taskmanager/workflow", HttpMethod.PUT);
        // put the new message in the body of the request
        request.setBody(newWorkFlow.toJson());
        // add an observer to process the response
        request.addObserver(new AddWorkFlowRequestObserver(this));
        // send the request
        request.send();
    }
}
