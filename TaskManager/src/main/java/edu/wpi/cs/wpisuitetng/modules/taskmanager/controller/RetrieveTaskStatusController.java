/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import java.util.ArrayList;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.TaskStatus;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.AbsView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview.WorkFlowView;
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

    private final AbsView view;

    /**
     * Constructor for the RetrieveTaskStatusController.
     */
    public RetrieveTaskStatusController(AbsView view) {
        this.view = view;
    }

    public void requestTaskStatuses() {
        Request request =
                Network.getInstance().makeRequest("taskmanager/taskstatus", HttpMethod.GET);
        request.addObserver(new RetrieveTaskStatusRequestObserver(this));
        request.send();
        // System.out.println("Request Task Status sent");
    }

    public void displayTaskStatuses(TaskStatus[] taskStatusArray) {
        // Change from Array to List
        ArrayList<TaskStatus> tsList = new ArrayList<TaskStatus>();
        for (int i = 0; i < taskStatusArray.length; i++) {
            tsList.add(taskStatusArray[i]);
        }
        // Set the list of status objects in the Workflow view.
        ViewEventController.getInstance().getWorkflow().setStatuses(tsList);
        WorkFlowView.getInstance().setStatuses(tsList);
    }

}
