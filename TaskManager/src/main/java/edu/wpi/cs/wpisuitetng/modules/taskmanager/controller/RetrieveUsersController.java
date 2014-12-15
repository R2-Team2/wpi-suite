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

/**
 * Controller used to populate lists of Users from the server database
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
public class RetrieveUsersController {

	private DefaultListModel<User> model;
	private List<String> filter;

	/**
	 * Constructor for RetrieveUsersController.
	 * @param model The list model for retrieved users to be put in
	 */
	public RetrieveUsersController(DefaultListModel<User> model) {
		this(model, null);
	}
	
	/**
	 * If given a filter, RetrieveUsersController will disallow these
	 * 	from the list model
	 * @param model The list model for retrieved users to be put in
	 * @param filter Usernames to be left out of the list model
	 */
	public RetrieveUsersController(DefaultListModel<User> model, List<String> filter) {
		this.model = model;
		this.filter = filter;
	}

	/**
	 * Calling this method sends a request to the database to retrieve
	 *   all Users.
	 */
	public void requestAllUsers() {
		final Request request = Network.getInstance()
				.makeRequest("core/user", HttpMethod.GET);
		request.addObserver(new RetrieveUsersRequestObserver(this));
		request.send();
	}
	
	/**
	 * Calling this method sends a request to the database to retrieve
     *   the User with the given username
	 * @param username String
	 */
	public void requestUser(String username) {
		final Request request = Network.getInstance()
				.makeRequest("core/user/" + username, HttpMethod.GET);
		request.addObserver(new RetrieveUsersRequestObserver(this));
		request.send();
	}

	/**
     * Populates the model list provided initially with the users
     *   retrieved from the database. This method is called by the
	 *   request observer with the response from the server.
	 * @param userArray the array of Users from the database
	 */
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
