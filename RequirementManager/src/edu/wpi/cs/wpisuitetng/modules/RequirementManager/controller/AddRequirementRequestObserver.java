
package edu.wpi.cs.wpisuitetng.modules.RequirementManager.controller;

import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.Requirement;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;

/**
 * This observer is called when a response is received from a request
 * to the server to add a requirement.
 *
 */
public class AddRequirementRequestObserver implements RequestObserver {
	
	private final AddRequirementController controller;
	
	public AddRequirementRequestObserver(AddRequirementController controller) {
		this.controller = controller;
	}
	
	/*
	 * Parse the requirement that was received from the server then pass them to
	 * the controller.
	 * 
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
	 */
	@Override
	public void responseSuccess(IRequest iReq) {
		// Get the response to the given request
		final ResponseModel response = iReq.getResponse();
		
		// Parse the requirement out of the response body
		final Requirement requirement = Requirement.fromJson(response.getBody());
		
		// Pass the requirements back to the controller
		controller.addRequirementToModel(requirement);
	}

	@Override
	public void responseError(IRequest iReq) {
		System.err.println("The request to add a requirement failed.");
	}

	@Override
	public void fail(IRequest iReq, Exception exception) {
		System.err.println("The request to add a requirement failed.");
	}

}
