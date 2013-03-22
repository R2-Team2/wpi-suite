
package edu.wpi.cs.wpisuitetng.modules.RequirementManager.controller;

import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;

/**
 * This observer handles responses to requests for all requirements
 *
 */
public class GetRequirementsRequestObserver implements RequestObserver {
	
	public GetRequirementsController controller;
	
	/**
	 * Constructs the observer given a GetRequirementsController
	 * @param controller the controller used to retrieve requirements
	 */
	public GetRequirementsRequestObserver(GetRequirementsController controller) {
		this.controller = controller;
	}

	/**
	 * Parse the requirements out of the response body and pass them to the controller
	 * 
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
	 */
	@Override
	public void responseSuccess(IRequest iReq) {
		// Convert the JSON array of requirements to a Requirement object array
		Requirement[] requirements = Requirement.fromJsonArray(iReq.getResponse().getBody());
		
		// Pass these Requirements to the controller
		controller.receivedRequirements(requirements);
		
		// Have the controller update the table with the fresh list
		ViewEventController.getInstance().updateTable(requirements);
	}

	/*
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseError(edu.wpi.cs.wpisuitetng.network.models.IRequest)
	 */
	@Override
	public void responseError(IRequest iReq) {
		fail(iReq, null);
	}

	/*
	 * Put an error requirement in the PostBoardPanel if the request fails.
	 * 
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#fail(edu.wpi.cs.wpisuitetng.network.models.IRequest, java.lang.Exception)
	 */
	@Override
	public void fail(IRequest iReq, Exception exception) {
		Requirement[] errorRequirement = { new Requirement(6, "Error", "error desc") };
		controller.receivedRequirements(errorRequirement);
	}

}
