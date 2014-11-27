package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.taskstatus.observers;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.taskstatus.AddTaskStatusController;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;

public class AddTaskStatusRequestObserver implements RequestObserver {

    private final AddTaskStatusController controller;

    public AddTaskStatusRequestObserver(AddTaskStatusController controller) {
        this.controller = controller;
    }

    @Override
    public void responseSuccess(IRequest iReq) {
        System.out.println("The request to add a task status was successful.");
        final ResponseModel response = iReq.getResponse();
    }

    @Override
    public void responseError(IRequest iReq) {
        System.err.println("The request to add a task status failed.");

    }

    @Override
    public void fail(IRequest iReq, Exception exception) {
        System.err.println("The request to add a task status failed.");

    }

    public AddTaskStatusController getController() {
        return controller;
    }

}
