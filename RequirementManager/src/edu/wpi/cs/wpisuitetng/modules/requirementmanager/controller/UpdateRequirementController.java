/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.controller;


import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * This controller responds when the user clicks the Update button by
 * adding the contents of the requirement text fields to the model as a new
 * requirement.
 * @version $Revision: 1.0 $
 * @author justinhess
 */
public class UpdateRequirementController{
	
	private static UpdateRequirementController instance;
	private UpdateRequirementRequestObserver observer;
	
	/**
	 * Construct an UpdateRequirementController for the given model, view pair
	
	
	 */
	private UpdateRequirementController() {
		observer = new UpdateRequirementRequestObserver(this);
	}
	
	/**
	
	 * @return the instance of the UpdateRequirementController or creates one if it does not
	 * exist. */
	public static UpdateRequirementController getInstance()
	{
		if(instance == null)
		{
			instance = new UpdateRequirementController();
		}
		
		return instance;
	}

	/**
	 * This method updates a requirement to the server.
	 * @param newRequirement is the requirement to be updated to the server.
	 */
	public void updateRequirement(Requirement newRequirement) 
	{
		Request request = Network.getInstance().makeRequest("requirementmanager/requirement", HttpMethod.POST); // POST == update
		request.setBody(newRequirement.toJSON()); // put the new requirement in the body of the request
		request.addObserver(observer); // add an observer to process the response
		request.send(); 
	}
}
