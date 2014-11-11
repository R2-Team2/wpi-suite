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
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.BoardPanel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;
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
 * @author R2-Team2
 *
 */
public class AddTaskController implements ActionListener {
	
	private final PostBoardModel model;
	private final BoardPanel view;
	
	/**
	 * Construct an AddTaskController for the given model, view pair
	 * @param model the model containing the messages
	 * @param view the view where the user enters new messages
	 */
	public AddTaskController(PostBoardModel model, BoardPanel view) {
		this.model = model;
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
		int id = 14;
		String title = "Title";
		
		
		// Get the text that was entered
		//String message = view.getTxtNewMessage().getText();
		Task newTask = new Task(id, title, "Description", userList, 0, 0, date, new ArrayList<String>(), 0, TaskStatus.NEW);
		
		
		// Make sure there is text
		if (/*message.length() > 0*/true) {
			// Clear the text field
			//view.getTxtNewMessage().setText("");
			
			// Send a request to the core to save this message
			final Request request = Network.getInstance().makeRequest("taskmanager/task", HttpMethod.PUT); // PUT == create
			request.setBody(newTask.toJson()); // put the new message in the body of the request
			request.addObserver(new AddTaskRequestObserver(this)); // add an observer to process the response
			request.send(); // send the request
		}
	}

	/**
	 * When the new message is received back from the server, add it to the local model.
	 * @param message
	 */
	public void addMessageToModel(PostBoardMessage message) {
		model.addMessage(message);
	}
}
