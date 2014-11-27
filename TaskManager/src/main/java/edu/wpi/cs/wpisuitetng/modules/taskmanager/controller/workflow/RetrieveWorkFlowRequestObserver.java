package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.workflow;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.WorkFlow;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;

public class RetrieveWorkFlowRequestObserver implements RequestObserver {

    private final RetrieveWorkFlowController controller;

    public RetrieveWorkFlowRequestObserver(RetrieveWorkFlowController controller) {
        this.controller = controller;
    }

    /*
     * (non-Javadoc)
     * @see
     * edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network
     * .models.IRequest)
     */
    @Override
    public void responseSuccess(IRequest iReq) {
        // TODO Auto-generated method stub
        final ResponseModel response = iReq.getResponse();
        final String responseBody = response.getBody();
        controller.displayWorkFlow(WorkFlow.fromJsonArray(responseBody));

    }

    @Override
    public void responseError(IRequest iReq) {
        // TODO Auto-generated method stub
        System.err.println("The request to get the workflow resulted in an error.");
        // controller.generateWorkFlow();

    }

    @Override
    public void fail(IRequest iReq, Exception exception) {
        // TODO Auto-generated method stub
        System.err.println("The request to get the workflow failed.");
        // controller.generateWorkFlow();

    }

}
