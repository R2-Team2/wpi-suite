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
public class AddRequirementController{
	
	private static AddRequirementController instance;
	private AddRequirementRequestObserver observer;
	
	/**
	 * Construct an AddRequirementController for the given model, view pair
	
	
	 */
	private AddRequirementController() {
		observer = new AddRequirementRequestObserver(this);
	}
	
	/**
	
	 * @return the instance of the AddRequirementController or creates one if it does not
	 * exist. */
	public static AddRequirementController getInstance()
	{
		if(instance == null)
		{
			instance = new AddRequirementController();
		}
		
		return instance;
	}

	/**
	 * This method adds a requirement to the server.
	 * @param newRequirement is the requirement to be added to the server.
	 */
	public void addRequirement(Requirement newRequirement) 
	{
		final Request request = Network.getInstance().makeRequest("requirementmanager/requirement", HttpMethod.PUT); // PUT == create
		request.setBody(newRequirement.toJSON()); // put the new requirement in the body of the request
		request.addObserver(observer); // add an observer to process the response
		request.send(); 
	}
}
