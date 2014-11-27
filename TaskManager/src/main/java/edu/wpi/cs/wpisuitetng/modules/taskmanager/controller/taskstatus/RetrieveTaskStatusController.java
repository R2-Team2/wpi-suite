package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.taskstatus;

import java.util.List;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.taskstatus.observers.RetrieveTaskStatusRequestObserver;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.TaskStatus;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.WorkFlow;
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
    public void requestTaskStatus() {
        final Request request =
                Network.getInstance().makeRequest("taskmanager/workflow", HttpMethod.GET);
        request.addObserver(new RetrieveTaskStatusRequestObserver(this));
        request.send();
    }

    /**
     * Step 5
     *
     * @param taskStatusArray
     */
    public void displayTaskStatus(WorkFlow[] workFlow) {
        List<TaskStatus> tempList = workFlow[0].getTaskStatusList();
        view.updateTaskList(tempList.toArray(new TaskStatus[tempList.size()]));
    }
}
