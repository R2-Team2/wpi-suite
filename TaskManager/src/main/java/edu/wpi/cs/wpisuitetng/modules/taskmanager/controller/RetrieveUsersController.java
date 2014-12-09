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

import java.util.List;

import javax.swing.DefaultListModel;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

public class RetrieveUsersController {

	private DefaultListModel<User> model;
	private List<String> filter;

	public RetrieveUsersController(DefaultListModel<User> model) {
		this(model, null);
	}
	
	public RetrieveUsersController(DefaultListModel<User> model, List<String> filter) {
		this.model = model;
		this.filter = filter;
	}

	public void requestUsers() {
		Request request = Network.getInstance().makeRequest("core/user", HttpMethod.GET);
		request.addObserver(new RetrieveUsersRequestObserver(this));
		request.send();
	}
	
	public void requestUser(String username) {
		Request request = Network.getInstance().makeRequest("core/user/" + username, HttpMethod.GET);
		request.addObserver(new RetrieveUsersRequestObserver(this));
		request.send();
	}

	public void populateList(User[] userArray) {
	    for (User u : userArray) {
    		model.addElement(u);
		}
    	if (filter != null) {
    		for (User u : userArray) {
    			if (filter.contains(u.getUsername())) {
    				model.removeElement(u);
    			}
    		}
    	}
	}
}
