package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.taskstatus;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.TaskStatus;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.taskstatus.TaskStatusView;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

public class RetrieveTaskStatusController {

    private final TaskStatusView view;

    public RetrieveTaskStatusController(TaskStatusView view) {
        this.view = view;
    }

    /**
     * Step 3
     *
     * @param workFlowID
     */
    public void requestTaskStatus(int workFlowID) {
        final Request request =
                Network.getInstance().makeRequest("taskmanager/taskstatus" + workFlowID,
                        HttpMethod.GET);
        request.addObserver(new RetrieveTaskStatusRequestObserver(this));
        request.send();
    }

    /**
     * Step 5
     *
     * @param taskStatusArray
     */
    public void displayTaskStatus(TaskStatus[] taskStatusArray) {
        view.updateTaskList(taskStatusArray);
    }
}
