/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.task;

import java.util.Date;
import java.util.List;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.NewTaskPanel;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

// TODO: Auto-generated Javadoc
/**
 * This controller responds when the user clicks the Submit button by adding the contents of the
 * task text field to the model as a new task.
 *
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
public class AddTaskController {

    /** The view. */
    private final NewTaskPanel view;

    /**
     * Construct an AddTaskController for the given model, view pair.
     *
     * @param view the view where the user enters new messages
     */
    public AddTaskController(NewTaskPanel view) {
        this.view = view;
    }

    /**
     * This method is called when the user clicks the Submit button.
     *
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void addTask() {
        final int taskID = 0; // generate task ID somehow
        final String title = view.getTitle();
        final String description = view.getDescription();
        final int estimatedEffort = view.getEstimatedEffort();
        final int actualEffort = view.getActualEffort();
        final int taskStatusID = view.getStatus();
        final String requirement = view.getRequirement();
        final Date startDate = view.getStartDate();
        final Date dueDate = view.getDueDate();
        final List<User> assignedUsers = view.getAssignedUsers();
        final Task newTask;

        // Create Task
        newTask = new Task(taskID, title, description, estimatedEffort, actualEffort,
                taskStatusID, requirement, startDate, dueDate, assignedUsers);

        // Send a request to the core to save this message
        final Request request =
                Network.getInstance().makeRequest("taskmanager/task", HttpMethod.PUT); // PUT ==
        // create
        request.setBody(newTask.toJson()); // put the new message in the body of the request
        request.addObserver(new AddTaskRequestObserver(this)); // add an observer to process the
        // response
        request.send(); // send the request
    }
}
