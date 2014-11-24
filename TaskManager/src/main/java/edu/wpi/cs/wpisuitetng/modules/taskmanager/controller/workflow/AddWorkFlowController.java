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

    public void addTaskStatus() {
        // Check out how work flow is created
        final WorkFlow newWorkFlow =
                new WorkFlow();
        /*
         * Initialize newWorkFlow here (grab data from view)
         */

        // Send a request to the core to save this message
        final Request request =
                Network.getInstance().makeRequest("taskmanager/workflow", HttpMethod.PUT); // PUT
        // ==
        // create
        request.setBody(newWorkFlow.toJson()); // put the new message in the body of the request
        request.addObserver(new AddWorkFlowRequestObserver(this)); // add an observer to process
        // the
        // response
        request.send(); // send the request
    }
}
