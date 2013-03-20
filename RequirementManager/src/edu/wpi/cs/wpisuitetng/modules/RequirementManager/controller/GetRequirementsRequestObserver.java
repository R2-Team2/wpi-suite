
package edu.wpi.cs.wpisuitetng.modules.RequirementManager.controller;

import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.RequirementStatus;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;

/**
 * This observer handles responses to requests for all requirements
 *
 */
public class GetRequirementsRequestObserver implements RequestObserver {
	
	public GetRequirementsController controller;
	
	public GetRequirementsRequestObserver(GetRequirementsController controller) {
		this.controller = controller;
	}

	/*
	 * Parse the requirements out of the response body and pass them to the controller
	 * 
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
	 */
	@Override
	public void responseSuccess(IRequest iReq) {
		Requirement[] requirements = Requirement.fromJsonArray(iReq.getResponse().getBody());
		controller.receivedRequirements(requirements);
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
		Requirement[] errorRequirement = { new Requirement("Error", "0.0", RequirementStatus.DUMMY, "error desc", 0, 0) };
		controller.receivedRequirements(errorRequirement);
	}

}
