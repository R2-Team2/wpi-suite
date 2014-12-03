/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;

public class UpdateWorkflowRequestObserver implements RequestObserver {
	
	/** The controller. */
    private final UpdateWorkflowController controller;

    /**
     * This method is called when information about an AddWorkflowRequest which was previously requested
     * using an asynchronous interface becomes available.
     *
     * @param controller the controller
     */
    public UpdateWorkflowRequestObserver(UpdateWorkflowController controller) {
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
    public UpdateWorkflowController getController() {
        return controller;
    }
	
}
