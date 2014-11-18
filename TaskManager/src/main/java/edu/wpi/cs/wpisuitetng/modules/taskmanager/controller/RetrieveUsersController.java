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

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.AbstractInformationPanel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.taskstatus.TaskStatusView;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

public class RetrieveUsersController {

	private AbstractInformationPanel panel;

	public RetrieveUsersController(AbstractInformationPanel panel) {
		this.panel = panel;
	}

	public void requestUsers() {
		Request request = Network.getInstance().makeRequest("core/user", HttpMethod.GET);
		request.addObserver(new RetrieveUsersRequestObserver(this));
		request.send();
	}

	public void returnUsers(User[] userArray) {
		panel.populateUsers(userArray);
	}
}
