package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.WorkFlow;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;

public class RetrieveWorkflowRequestObserver implements RequestObserver {

    /** The controller. */
    private final RetrieveWorkflowController controller;

    /**
     * This method is called when information about an RetrieveWorkflow which was previously
     * requested using an asynchronous interface becomes available.
     *
     * @param controller the controller
     */
    public RetrieveWorkflowRequestObserver(RetrieveWorkflowController controller) {
        this.controller = controller;
    }

    @Override
    public void responseSuccess(IRequest iReq) {
        System.out.println("Request to retrieve WorkFlow was successful.");
        final ResponseModel response = iReq.getResponse();
        final String responseBody = response.getBody();
        controller.displayWorkflow(WorkFlow.fromJson(responseBody));

    }

    @Override
    public void responseError(IRequest iReq) {
        System.err.println("Request to retrieve WorkFlow resulted in an error.");

    }

    @Override
    public void fail(IRequest iReq, Exception exception) {
        System.err.println("Request to retrieve WorkFlow failed.");

    }

}
