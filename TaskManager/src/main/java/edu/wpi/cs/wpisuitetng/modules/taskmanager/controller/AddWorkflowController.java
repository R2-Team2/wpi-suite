/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Chris
 * Casola
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.WorkFlow;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * This controller responds when the user clicks the Submit button by adding the contents of the
 * task text field to the model as a new task.
 *
 * @version $Revision: 1.0 $
 * @author R2-Team2
 */
public class AddWorkflowController {

    // private final NewTaskPanel view;

    /**
     * Construct an AddWorkflowController for the given model, view pair
     */
    /*
     * public AddWorkflowController() { //TODO find parent panel }
     */

    /**
     * This method is called when the user clicks the Submit button
     * 
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void addWorkflow() {
        // TODO generate workflow get data from view

        // Create Workflow
        final WorkFlow newWF = new WorkFlow();

        // Send a request to the core to save this message
        final Request request =
                Network.getInstance().makeRequest("taskmanager/workflow", HttpMethod.PUT); // PUT ==
                                                                                           // create
        request.setBody(newWF.toJson()); // put the new message in the body of the request
        request.addObserver(new AddWorkflowRequestObserver(this)); // add an observer to process the
                                                                   // response
        request.send(); // send the request
    }

    /**
     * initializes a workflow and neccesary objects
     */
    public void initWF() {
        final WorkFlow newWF = new WorkFlow();

        final Request request =
                Network.getInstance().makeRequest("taskmanager/workflow", HttpMethod.PUT); // PUT ==
                                                                                           // create
        request.setBody(newWF.toJson()); // put the new message in the body of the request
        request.addObserver(new AddWorkflowRequestObserver(this)); // add an observer to process the
                                                                   // response
        request.send(); // send the request
    }

}
