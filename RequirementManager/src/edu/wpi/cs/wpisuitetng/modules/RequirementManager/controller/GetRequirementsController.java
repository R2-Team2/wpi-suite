
package edu.wpi.cs.wpisuitetng.modules.RequirementManager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.RequirementModel;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * This controller coordinates retrieving all of the requirements
 * from the server.
 *
 */
public class GetRequirementsController implements ActionListener {

	private final RequirementModel model;

	/**
	 * Constructs the controller given a RequirementModel
	 * @param model the model holding the requirements data
	 */
	public GetRequirementsController(RequirementModel model) {
		this.model = model;
	}

	/**
	 * Sends an HTTP request to store a requirement when the
	 * update button is pressed
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Send a request to the core to save this requirement
		final Request request = Network.getInstance().makeRequest("requirementmanager/requirement", HttpMethod.GET); // GET == read
		request.addObserver(new GetRequirementsRequestObserver(this)); // add an observer to process the response
		request.send(); // send the request
	}
	
	/**
	 * Sends an HTTP request to retrieve all requirements
	 */
	public void retrieveRequirements() {
		final Request request = Network.getInstance().makeRequest("requirementmanager/requirement", HttpMethod.GET); // GET == read
		request.addObserver(new GetRequirementsRequestObserver(this)); // add an observer to process the response
		request.send(); // send the request
	}

	/**
	 * Add the given requirements to the local model (they were received from the core).
	 * This method is called by the GetRequirementsRequestObserver
	 * 
	 * @param an array of requirements received from the server
	 */
	public void receivedRequirements(Requirement[] requirements) {
		// Empty the local model to eliminate duplications
		model.emptyModel();
		
		// Make sure the response was not null
		if (requirements != null) {
			
			// add the requirements to the local model
			model.addRequirements(requirements);
		}
	}
}
