package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;

public class TaskRequestObserver implements RequestObserver{
    private final TaskController controller;
    
    public TaskRequestObserver(TaskController controller) {
        this.controller = controller;
    }
    
    /*
     * Parse the message that was received from the server then pass them to
     * the controller.
     * @see
     * edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi
     * .cs.wpisuitetng.network.models.IRequest)
     */
    @Override
    public void responseSuccess(IRequest iReq) {
        // Get the response to the given request
        final ResponseModel response = iReq.getResponse();
    }
    
    @Override
    public void responseError(IRequest iReq) {
        //TODO replace with log slf4j?
        System.err.println("The request to add a message failed.");
    }
    
    @Override
    public void fail(IRequest iReq, Exception exception) {
        //TODO replace with log slf4j?
        System.err.println("The request to add a message failed.");
    }
    
    /**
     * @return the controller
     */
    public TaskController getController() {
        return this.controller;
    }

}
