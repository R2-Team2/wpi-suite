/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;


import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * The Class RetrieveTaskStatusController.
 * 
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
public class RetrieveTaskStatusController {

    /**
     * Constructor for the RetrieveTaskStatusController.
     */
    /*
     * public RetrieveTaskStatusController(){ //TODO get parent view }
     */

    public void requestTaskStatus() {
        final Request request =
                Network.getInstance().makeRequest("taskmanager/taskstatus", HttpMethod.GET);
        request.addObserver(new RetrieveTaskStatusRequestObserver(this));
        request.send();
    }

    // TODO Display task statuses needed?
    /*
     * public void displayTasks(Task[] taskArray) { view.fillTaskList(taskArray); }
     */
}
