
package edu.wpi.cs.wpisuitetng.modules.RequirementManager.IterationController;

import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.Iterations.Iteration;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;
import edu.wpi.cs.wpisuitetng.network.models.ResponseModel;

/**
 * This observer is called when a response is received from a request
 * to the server to add a requirement.
 *
 */
public class AddIterationRequestObserver implements RequestObserver {
	
	private final AddIterationController controller;
	
	/**
	 * Constructs the observer given an AddIterationController
	 * @param controller the controller used to add Iterations
	 */
	public AddIterationRequestObserver(AddIterationController controller) {
		this.controller = controller;
	}
	
	/**
	 * Parse the Iteration that was received from the server then pass them to
	 * the controller.
	 * 
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
	 */
	@Override
	public void responseSuccess(IRequest iReq) {
		// Get the response to the given request
		final ResponseModel response = iReq.getResponse();
		
		// Parse the Iteration out of the response body
		final Iteration iteration = Iteration.fromJson(response.getBody());		
	}

	/**
	 * Takes an action if the response results in an error.
	 * Specifically, outputs that the request failed.
	 */
	@Override
	public void responseError(IRequest iReq) {
		System.err.println("The request to add a Iteration failed.");
	}

	/**
	 * Takes an action if the response fails.
	 * Specifically, outputs that the request failed.
	 */
	@Override
	public void fail(IRequest iReq, Exception exception) {
		System.err.println("The request to add a Iteration failed.");
	}

}
