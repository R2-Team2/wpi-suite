package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.taskstatus.observers;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.taskstatus.RetrieveTaskStatusController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.WorkFlow;
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
        System.out.println("The request to get task statuses was successful.");
        controller.displayTaskStatus(WorkFlow.fromJsonArray(responseBody));

    }

    @Override
    public void responseError(IRequest iReq) {
        // TODO Auto-generated method stub
        System.err.println("The request to get task statuses resulted in an error.");

    }

    @Override
    public void fail(IRequest iReq, Exception exception) {
        // TODO Auto-generated method stub
        System.err.println("The request to get task statuses failed.");

    }

}
