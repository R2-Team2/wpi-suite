package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.taskstatus;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.TaskStatus;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview.WorkFlowView;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

public class AddTaskStatusController {

    private final WorkFlowView view; // Change the data type to the one Selim uses, or wherever the
                                     // task status is created

    public AddTaskStatusController(WorkFlowView view) {
        this.view = view;
    }

    public void addTaskStatus() {
        // TODO: Need Selim's code
        final TaskStatus newTaskStatus =
                new TaskStatus("Test", view.getWorkFlowObj().getWorkFlowID());
        /*
         * Initialize newTaskStatus here (grab data from view): Need Selim's code?
         */

        // Send a request to the core to save this message
        final Request request =
                Network.getInstance().makeRequest("taskmanager/taskstatus", HttpMethod.PUT); // PUT
        // ==
        // create
        request.setBody(newTaskStatus.toJson()); // put the new message in the body of the request
        request.addObserver(new AddTaskStatusRequestObserver(this)); // add an observer to process
        // the
        // response
        request.send(); // send the request
    }

}
