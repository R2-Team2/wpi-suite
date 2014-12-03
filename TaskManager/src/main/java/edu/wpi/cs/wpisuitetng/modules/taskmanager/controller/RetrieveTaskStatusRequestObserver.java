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
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;

/**
 * The Class RetrieveTaskStatusRequestObserver.
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
public class RetrieveTaskStatusRequestObserver implements RequestObserver {
	/** Controller givent*/
	private final RetrieveTaskStatusController controller;
	
	/**
	 * Constructor for the RetrieveTaskStatusRequestObserver.
	 * @param aController the controller for the retrieve task status observer
	 */
	public RetrieveTaskStatusRequestObserver(RetrieveTaskStatusController aController){
		controller = aController;
	}
	
	@Override
	public void responseSuccess(IRequest iReq) {
		// TODO Auto-generated method stub
		final ResponseModel response = iReq.getResponse();
		final String responseBody = response.getBody();
		//TODO is this necessary? 
		//controller.displayTaskStatus(Task.fromJsonArray(responseBody));
	}

	@Override
	public void responseError(IRequest iReq) {
		// TODO Auto-generated method stub
		System.err.println("The request to get tasks failed.");
	}

	@Override
	public void fail(IRequest iReq, Exception exception) {
		// TODO Auto-generated method stub
		System.err.println("The request to get tasks failed.");
	}

}
