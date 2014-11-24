package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.taskstatus;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.TaskStatus;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;

public class RetrieveTaskStatusRequestObserver implements RequestObserver {

    private final RetrieveTaskStatusController controller;

    public RetrieveTaskStatusRequestObserver(RetrieveTaskStatusController controller) {
        this.controller = controller;
    }

    /*
     * Step 4 (non-Javadoc)
     * @see
     * edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network
     * .models.IRequest)
     */
    @Override
    public void responseSuccess(IRequest iReq) {
        // TODO Auto-generated method stub
        final ResponseModel response = iReq.getResponse();
        final String responseBody = response.getBody();
        controller.displayTaskStatus(TaskStatus.fromJsonArray(responseBody));

    }

    @Override
    public void responseError(IRequest iReq) {
        // TODO Auto-generated method stub
        System.err.println("The request to get task statuses failed.");

    }

    @Override
    public void fail(IRequest iReq, Exception exception) {
        // TODO Auto-generated method stub
        System.err.println("The request to get task statuses failed.");

    }

}
