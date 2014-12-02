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

import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.WorkFlow;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

public class RetrieveWorkflowController {

	/**
	 * Constructor for Controller
	 */
	public RetrieveWorkflowController(){
		//TODO find parent view
	}
	
	public void requestWorkflow() {
		Request request = Network.getInstance().makeRequest("taskmanager/workflow", HttpMethod.GET);
		request.addObserver(new RetrieveWorkflowRequestObserver(this));
		request.send();
	}

	public void displayWorkflows(WorkFlow[] WorkflowArray) {
		//TODO publish retrieved workflows.
		//view.fillTaskList(taskArray);
	}
}
