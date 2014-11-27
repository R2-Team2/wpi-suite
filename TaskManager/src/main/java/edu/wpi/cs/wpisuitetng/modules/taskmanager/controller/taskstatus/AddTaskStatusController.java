package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.taskstatus;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.taskstatus.observers.AddTaskStatusRequestObserver;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.TaskStatus;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.WorkFlow;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview.WorkFlowView;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

public class AddTaskStatusController {

    private final WorkFlowView view; // Change the data type to the one Selim uses, or wherever the

    public AddTaskStatusController(WorkFlowView view) {
        this.view = view;
    }

    // no need to update workflowview here because workflowview will be updated automatically via
    // timer?
    public void addTaskStatus() {
        // these getters need to be made in Selim's editWorkFlowView
        // int taskStatusID = view.getTaskStatusID();
        // String name = view.getName();
        // List<Task> taskList = view.getTaskList();
        // int workFlowID = view.getWorkFlowID;

        WorkFlow updatedWorkFlow =
                ViewEventController.getInstance().getWorkFlowView().getWorkFlowObj();

        // final TaskStatus newTaskStatus = new TaskStatus(name);
        final TaskStatus newTaskStatus = new TaskStatus("Test");

        updatedWorkFlow.addTaskStatus(newTaskStatus);

        // Send a request to the core to save this message
        final Request request =
                Network.getInstance().makeRequest("taskmanager/workflow", HttpMethod.PUT);
        request.setBody(updatedWorkFlow.toJson()); // put the new message in the body of the request
        // add an observer to process the response
        request.addObserver(new AddTaskStatusRequestObserver(this));
        request.send(); // send the request
    }
}
