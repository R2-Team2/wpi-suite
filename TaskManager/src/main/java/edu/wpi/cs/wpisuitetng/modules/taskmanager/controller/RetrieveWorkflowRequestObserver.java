package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;

public class RetrieveWorkflowRequestObserver implements RequestObserver {

    /** The controller. */
    private final RetrieveWorkflowController controller;
    
    /**
     * This method is called when information about an RetrieveWorkflow which was previously requested
     * using an asynchronous interface becomes available.
     *
     * @param controller the controller
     */
    public RetrieveWorkflowRequestObserver(RetrieveWorkflowController controller) {
        this.controller = controller;
    }

	@Override
	public void responseSuccess(IRequest iReq) {
		// TODO Auto-generated method stub

	}

	@Override
	public void responseError(IRequest iReq) {
		// TODO Auto-generated method stub

	}

	@Override
	public void fail(IRequest iReq, Exception exception) {
		// TODO Auto-generated method stub

	}

}
