/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.taskstatus.TaskStatusView;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

public class RetrieveTasksController {
	
	private TaskStatusView view;
	
	public RetrieveTasksController(TaskStatusView view) {
		 this.view = view;
	}
	
	public void requestTasks() {
		Request request = Network.getInstance().makeRequest("taskmanager/task", HttpMethod.GET);
		request.addObserver(new RetrieveTasksRequestObserver(this));
		request.send();
	}

	public void displayTasks(Task[] taskArray) {
		view.fillTaskList(taskArray);
	}
}
