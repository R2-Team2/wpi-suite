package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;

public class TaskRequestObserver implements RequestObserver {


    /** The controller. */
    private final TaskController controller;

    /**
     * This method is called when information about an AddTaskRequest which was previously requested
     * using an asynchronous interface becomes available.
     *
     * @param controller the controller
     */
    public TaskRequestObserver(TaskController controller) {
        this.controller = controller;
    }

    /*
     * Parse the message that was received from the server then pass them to the controller.
     * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi
     * .cs.wpisuitetng.network.models.IRequest)
     */
    /*
     * (non-Javadoc)
     * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess
     * (edu.wpi.cs.wpisuitetng.network.models.IRequest)
     */
    @Override
    public void responseSuccess(IRequest iReq) {
        // Get the response to the given request
        final ResponseModel response = iReq.getResponse();
    }

    /*
     * (non-Javadoc)
     * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseError
     * (edu.wpi.cs.wpisuitetng.network.models.IRequest)
     */
    @Override
    public void responseError(IRequest iReq) {
        // TODO replace with log slf4j?
        System.err.println("The request to add a message failed.");
    }

    /*
     * (non-Javadoc)
     * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#fail
     * (edu.wpi.cs.wpisuitetng.network.models.IRequest, java.lang.Exception)
     */
    @Override
    public void fail(IRequest iReq, Exception exception) {
        // TODO replace with log slf4j?
        System.err.println("The request to add a message failed.");
    }

    /**
     * This method is called when information about an AddTaskRequest which was previously requested
     * using an asynchronous interface becomes available.
     *
     * @return the controller
     */
    public TaskController getController() {
        return controller;
    }

}
