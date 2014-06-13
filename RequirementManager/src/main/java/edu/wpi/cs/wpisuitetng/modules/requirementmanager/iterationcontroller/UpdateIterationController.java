/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.iterationcontroller;


import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.Iteration;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * This controller responds when the user clicks the Update button by
 * adding the contents of the Iteration text fields to the model as a new
 * Iteration.
 * @version $Revision: 1.0 $
 * @author justinhess
 */
public class UpdateIterationController{
	
	private static UpdateIterationController instance;
	private UpdateIterationRequestObserver observer;
	
	/**
	 * Construct an UpdateIterationController for the given model, view pair
	
	
	 */
	private UpdateIterationController() {
		observer = new UpdateIterationRequestObserver(this);
	}
	
	/**
	
	 * @return the instance of the UpdateIterationController or creates one if it does not
	 * exist. */
	public static UpdateIterationController getInstance()
	{
		if(instance == null)
		{
			instance = new UpdateIterationController();
		}
		
		return instance;
	}

	/**
	 * This method updates a Iteration to the server.
	 * @param newIteration is the Iteration to be updated to the server.
	 */
	public void updateIteration(Iteration newIteration) 
	{
		Request request = Network.getInstance().makeRequest("requirementmanager/iteration", HttpMethod.POST); // POST == update
		request.setBody(newIteration.toJSON()); // put the new Iteration in the body of the request
		request.addObserver(observer); // add an observer to process the response
		request.send(); 
	}
}
