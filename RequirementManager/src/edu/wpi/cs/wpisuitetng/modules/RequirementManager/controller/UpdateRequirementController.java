
package edu.wpi.cs.wpisuitetng.modules.RequirementManager.controller;


import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.Requirement;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * This controller responds when the user clicks the Update button by
 * adding the contents of the requirement text fields to the model as a new
 * requirement.
 */
public class UpdateRequirementController{
	
	private static UpdateRequirementController instance;
	private UpdateRequirementRequestObserver observer;
	
	/**
	 * Construct an UpdateRequirementController for the given model, view pair
	 * @param model the model containing the requirements
	 * @param view the view where the user enters new requirements
	 */
	private UpdateRequirementController() {
		observer = new UpdateRequirementRequestObserver(this);
	}
	
	/**
	 * Returns the instance of the UpdateRequirementController or creates one if it does not
	 * exist.
	 */
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
