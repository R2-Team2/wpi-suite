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
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;

/**
 * This observer handles responses to requests for all Iterations
 *
 * @version $Revision: 1.0 $
 * @author justinhess
 */
public class GetIterationRequestObserver implements RequestObserver {
	
	private GetIterationController controller;
	
	/**
	 * Constructs the observer given a GetIterationsController
	 * @param controller the controller used to retrieve Iterations
	 */
	public GetIterationRequestObserver(GetIterationController controller) {
		this.controller = controller;
	}

	/**
	 * Parse the Iterations out of the response body and pass them to the controller
	 * 
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseSuccess(edu.wpi.cs.wpisuitetng.network.models.IRequest)
	 */
	@Override
	public void responseSuccess(IRequest iReq) {
		// Convert the JSON array of Iterations to a Iteration object array
		Iteration[] Iterations = Iteration.fromJsonArray(iReq.getResponse().getBody());
		
		// Pass these Iterations to the controller
		controller.receivedIterations(Iterations);
	}

	/**
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#responseError(edu.wpi.cs.wpisuitetng.network.models.IRequest)
	 */
	@Override
	public void responseError(IRequest iReq) {
		fail(iReq, null);
	}

	/**
	 * Put an error Iteration in the PostBoardPanel if the request fails.
	 * 
	 * @see edu.wpi.cs.wpisuitetng.network.RequestObserver#fail(edu.wpi.cs.wpisuitetng.network.models.IRequest, java.lang.Exception)
	 */
	@Override
	public void fail(IRequest iReq, Exception exception) {
		Iteration[] errorIteration = { new Iteration(-1, "Error") };
		controller.receivedIterations(errorIteration);
	}

}
