package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.workflow;

import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;

public class AddWorkFlowRequestObserver implements RequestObserver {

    private final AddWorkFlowController controller;

    public AddWorkFlowRequestObserver(AddWorkFlowController controller) {
        this.controller = controller;
    }

    @Override
    public void responseSuccess(IRequest iReq) {
        final ResponseModel response = iReq.getResponse();
        System.out.println("The request to add a work flow was successful!");
    }

    @Override
    public void responseError(IRequest iReq) {
        System.err.println("The request to add a work flow resulted in an error.");

    }

    @Override
    public void fail(IRequest iReq, Exception exception) {
        System.err.println("The request to add a work flow failed.");

    }

    public AddWorkFlowController getController() {
        return controller;
    }


}
