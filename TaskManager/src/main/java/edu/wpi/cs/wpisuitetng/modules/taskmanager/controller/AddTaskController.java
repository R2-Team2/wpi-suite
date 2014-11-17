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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.NewTaskPanel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.PostBoardMessage;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.PostBoardModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.characteristics.TaskStatus;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * This controller responds when the user clicks the Submit button by
 * adding the contents of the task text field to the model as a new
 * task.
 *
 */
public class AddTaskController implements ActionListener {
	
	private final NewTaskPanel view;
	
	/**
	 * Construct an AddTaskController for the given model, view pair
	 * @param model the model containing the messages
	 * @param view the view where the user enters new messages
	 */
	public AddTaskController(NewTaskPanel view) {
		this.view = view;
	}

	/* 
	 * This method is called when the user clicks the Submit button
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		List<User> userList = new ArrayList<User>();
		Date date = new Date();
		int id = (int)(Math.random() * 1000);
		String title = view.getTitle();
		String description = view.getDescription();
		int effort = (int)view.getEstimatedEffort();
		
		// Get the text that was entered
		//String message = view.getTxtNewMessage().getText();
		Task newTask = new Task(id, title, description, userList, effort, 0, date, new ArrayList<String>(), 0, TaskStatus.NEW);
		
		System.out.println("Created task successfully");
		
		// Make sure there is text
		if (/*message.length() > 0*/true) {
			// Clear the text field
			//view.getTxtNewMessage().setText("");
			
			// Send a request to the core to save this message
			final Request request = Network.getInstance().makeRequest("taskmanager/task", HttpMethod.PUT); // PUT == create
			request.setBody(newTask.toJson()); // put the new message in the body of the request
			request.addObserver(new AddTaskRequestObserver(this)); // add an observer to process the response
			request.send(); // send the request
			System.out.println("Sent task to database");
		}
	}
}
