
package edu.wpi.cs.wpisuitetng.modules.RequirementManager.controller;

import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.RequirementPriority;
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
		
		System.out.println("---------------------");
		System.out.println("Requirements stored:");
		for (int i = 0; i < requirements.length; i++) {
			System.out.println("Requirement: ");
			System.out.print(" id: " + requirements[i].getId());
			System.out.print(", name: " + requirements[i].getName());
			System.out.print(", release: " + requirements[i].getRelease());
			System.out.print(", status: " + requirements[i].getStatus());
			System.out.print(", priority: " + requirements[i].getPriority());
			System.out.print(", description: " + requirements[i].getDescription());
			System.out.print(", estimate: " + requirements[i].getEstimate());
			System.out.print(", effort: " + requirements[i].getEffort());
			System.out.println("\n");
		}
		System.out.println("---------------------");
		
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
		Requirement[] errorRequirement = { new Requirement(6, "Error", "0.0", RequirementStatus.NEW, RequirementPriority.BLANK, "error desc", 0, 0) };
		controller.receivedRequirements(errorRequirement);
	}

}
