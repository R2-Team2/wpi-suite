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

// TODO: Auto-generated Javadoc
/**
 * This observer is called when a response is received from a request to the server to add a
 * message.
 *
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
public class AddTaskRequestObserver implements RequestObserver {

    /** The controller. */
    private final AddTaskController controller;

    /**
     * This method is called when information about an AddTaskRequest which was previously requested
     * using an asynchronous interface becomes available.
     *
     * @param controller the controller
     */
    public AddTaskRequestObserver(AddTaskController controller) {
        this.controller = controller;
    }

    // Parse the message that was received from the server then pass them to the controller.
    @Override
    public void responseSuccess(IRequest iReq) {
        // Get the response to the given request
        final ResponseModel response = iReq.getResponse();
    }

    @Override
    public void responseError(IRequest iReq) {
        // TODO replace with log slf4j?
        System.err.println("The request to add a message failed.");
    }

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
    public AddTaskController getController() {
        return controller;
    }
}
