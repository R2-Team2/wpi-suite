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
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;

/**
 * Request observer for the RetrieveUsersController
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
public class RetrieveUsersRequestObserver implements RequestObserver {

	private final RetrieveUsersController controller;

	/**
	 * Creates an observer with a reference back to the controller
	 * @param controller
	 */
	public RetrieveUsersRequestObserver(RetrieveUsersController controller) {
		this.controller = controller;
	}
	@Override
	public void responseSuccess(IRequest iReq) {
		// TODO Auto-generated method stub
		final ResponseModel response = iReq.getResponse();
		final String responseBody = response.getBody();
		controller.populateList(User.fromJsonArray(responseBody));
	}
	@Override
	public void responseError(IRequest iReq) {
		// TODO Auto-generated method stub
		System.err.println("The request to get users failed.");
	}
	@Override
	public void fail(IRequest iReq, Exception exception) {
		// TODO Auto-generated method stub
		System.err.println("The request to get users failed.");
	}
}
