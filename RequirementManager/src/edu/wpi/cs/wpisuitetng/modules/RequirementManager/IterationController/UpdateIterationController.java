
package edu.wpi.cs.wpisuitetng.modules.RequirementManager.IterationController;


import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.Iterations.RequirementIteration;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * This controller responds when the user clicks the Update button by
 * adding the contents of the Iteration text fields to the model as a new
 * Iteration.
 */
public class UpdateIterationController{
	
	private static UpdateIterationController instance;
	private UpdateIterationRequestObserver observer;
	
	/**
	 * Construct an UpdateIterationController for the given model, view pair
	 * @param model the model containing the Iterations
	 * @param view the view where the user enters new Iterations
	 */
	private UpdateIterationController() {
		observer = new UpdateIterationRequestObserver(this);
	}
	
	/**
	 * Returns the instance of the UpdateIterationController or creates one if it does not
	 * exist.
	 */
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
	public void updateIteration(RequirementIteration newIteration) 
	{
		Request request = Network.getInstance().makeRequest("requirementmanager/iteration", HttpMethod.POST); // POST == update
		request.setBody(newIteration.toJSON()); // put the new Iteration in the body of the request
		request.addObserver(observer); // add an observer to process the response
		request.send(); 
	}
}
