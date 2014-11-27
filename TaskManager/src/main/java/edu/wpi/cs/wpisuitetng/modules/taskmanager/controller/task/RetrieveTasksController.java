/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.task;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.TaskStatus;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.WorkFlow;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.taskstatus.TaskStatusView;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

// TODO: Auto-generated Javadoc
/**
 * The Class RetrieveTasksController.
 *
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
public class RetrieveTasksController {

    /** The view. */
    private final TaskStatusView view;

    /**
     * Instantiates a new retrieve tasks controller.
     *
     * @param view the view
     */
    public RetrieveTasksController(TaskStatusView view) {
        this.view = view;
    }

    /**
     * Step 8 Request tasks.
     */
    public void requestTasks() {
        final Request request =
                Network.getInstance().makeRequest("taskmanager/workflow", HttpMethod.GET);
        request.addObserver(new RetrieveTasksRequestObserver(this));
        request.send();
    }

    /**
     * Step 10 Display tasks.
     *
     * @param taskArray the task array
     */
    public void displayTasks(WorkFlow[] workFlow) {
        List<TaskStatus> taskStatusList = workFlow[0].getTaskStatusList();
        List<Task> tempList = new ArrayList<Task>();
        for (TaskStatus ts : taskStatusList) {
            if (ts.getName().equals(view.getTaskStatusObj().getName())) {
                tempList = ts.getTaskList();
            }
        }
        view.fillTaskList(tempList.toArray(new Task[tempList.size()]));
    }
}
