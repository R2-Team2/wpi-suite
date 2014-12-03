/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.TaskStatus;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * This controller responds when the user clicks the Submit button by
 * adding the contents of the task text field to the model as a new
 * task.
 */
public class AddTaskStatusController {
	
	/**
	 * Construct an AddTaskStatusController for the given model, view pair
	 */
	/*public AddTaskStatusController(){	
	}*/
	
	public void addTaskStatus(){
		//TODO populate TaskStatus object
		
		final TaskStatus newTaskStatus = new TaskStatus(null);
		
		final Request request = Network.getInstance().makeRequest("taskmanager/taskstatus", HttpMethod.PUT); // PUT == create
		request.setBody(newTaskStatus.toJson()); // put the new message in the body of the request
		request.addObserver(new AddTaskStatusRequestObserver(this)); // add an observer to process the response
		request.send(); // send the request
	}
}
