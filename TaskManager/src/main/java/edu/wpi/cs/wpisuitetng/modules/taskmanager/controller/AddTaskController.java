/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Chris Casola
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import java.util.Date;
import java.util.List;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.AbstractTaskPanel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.taskstatus.TaskStatus;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * This controller responds when the user clicks the Submit button by
 * adding the contents of the task text field to the model as a new
 * task.
 */
public class AddTaskController {

	private final AbstractTaskPanel view;

	/**
	 * Construct an AddTaskController for the given model, view pair
	 * @param model the model containing the messages
	 * @param view the view where the user enters new messages
	 */
	public AddTaskController(AbstractTaskPanel view) {
		this.view = view;
	}

	/* 
	 * This method is called when the user clicks the Submit button
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void addTask() {
		int taskID = 0; // generate task ID somehow
		String title = view.getTitle();
		String description = view.getDescription();
		int estimatedEffort = view.getEstimatedEffort();
		int actualEffort = view.getActualEffort();
		String status = view.getStatus();
		String requirement = view.getRequirement();
		Date startDate = view.getStartDate();
		Date dueDate = view.getDueDate();
		List<User> assignedUsers = view.getAssignedUsers();
		System.out.println("Inside AddTaskController");
		// Get the text that was entered
		//String message = view.getTxtNewMessage().getText();
		Task newTask = new Task(taskID, title, description, estimatedEffort, actualEffort, new TaskStatus(status), requirement, startDate, dueDate, assignedUsers);
		//		System.out.println("Created task successfully");

		// Make sure there is text
		//		if (/*message.length() > 0*/true) {
		// Clear the text field
		//view.getTxtNewMessage().setText("");
		
		// Send a request to the core to save this message
		final Request request = Network.getInstance().makeRequest("taskmanager/task", HttpMethod.PUT); // PUT == create
		request.setBody(newTask.toJson()); // put the new message in the body of the request
		request.addObserver(new AddTaskRequestObserver(this)); // add an observer to process the response
		request.send(); // send the request
		System.out.println("Sent task to database");
		//		}
	}
	
	
}
