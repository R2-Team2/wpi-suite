/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;

/**
 * An asynchronous update interface for receiving notifications about RetrieveTasksRequest
 * information as the RetrieveTasksRequest is constructed.
 *
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
public class RetrieveTasksRequestObserver implements RequestObserver {

    /** The controller. */
    private final RetrieveTasksController controller;

    /**
     * This method is called when information about an RetrieveTasksRequest which was previously
     * requested using an asynchronous interface becomes available.
     *
     * @param controller the controller
     */
    public RetrieveTasksRequestObserver(RetrieveTasksController controller) {
        this.controller = controller;
    }

    @Override
    public void responseSuccess(IRequest iReq) {
        final ResponseModel response = iReq.getResponse();
        final String responseBody = response.getBody();
        controller.displayTasks(Task.fromJsonArray(responseBody));
    }

    @Override
    public void responseError(IRequest iReq) {
        System.err.println("The request to get tasks failed.");
    }

    @Override
    public void fail(IRequest iReq, Exception exception) {
        System.err.println("The request to get tasks failed.");
    }

}
