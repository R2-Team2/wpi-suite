/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import java.util.Date;
import java.util.List;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.AbstractTaskPanel;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * The Class UpdateTaskController.
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
public class UpdateTaskController {
	private final AbstractTaskPanel view;
	
	/**
	 * Constructor for the UpdateTaskController.
	 *
	 * @param view the parent view
	 */
	public UpdateTaskController(AbstractTaskPanel view) {
		this.view = view;
	}
	
	/**
	 * Calls the request from the request observer.
	 *
	 * @param toSave is the task object to be updated in the database.
	 */
	public void updateTask(Task toSave) {
		// Send a request to the core to save this message
		final Request request = Network.getInstance().makeRequest("taskmanager/task", HttpMethod.POST); // POST == save
		request.setBody(toSave.toJson()); // put the new message in the body of the request
		request.addObserver(new UpdateTaskRequestObserver(this)); // add an observer to process the response
		request.send(); // send the request
		System.out.println("Sent task to database");
		//		}
	}
}