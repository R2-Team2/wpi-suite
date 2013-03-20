
package edu.wpi.cs.wpisuitetng.modules.RequirementManager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.RequirementModel;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * This controller responds when the user clicks the Submit button by
 * adding the contents of the requirement text field to the model as a new
 * requirement.
 * 
 *
 */
public class AddRequirementController implements ActionListener {
	
	private final RequirementModel model;
	//private final BoardPanel view;
	
	/**
	 * Construct an AddRequirementController for the given model, view pair
	 * @param model the model containing the requirements
	 * @param view the view where the user enters new requirements
	 */
	public AddRequirementController(RequirementModel model/*, BoardPanel view*/) {
		this.model = model;
		//this.view = view;
	}

	/* 
	 * This method is called when the user clicks the Submit button
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		Requirement someRequirement = new Requirement(); // Create new requirement object based on field values in view
		// if (requirementIsFine) {
		// Send a request to the core to save this requirement
		final Request request = Network.getInstance().makeRequest("requirementmanager/requirement", HttpMethod.PUT); // PUT == create
		request.setBody(someRequirement.toJSON()); // put the new requirement in the body of the request
		request.addObserver(new AddRequirementRequestObserver(this)); // add an observer to process the response
		request.send(); // send the request
		//}
	}

	/**
	 * When the new requirement is received back from the server, add it to the local model.
	 * @param requirement
	 */
	public void addRequirementToModel(Requirement requirement) {
		model.addRequirement(requirement);
	}
}
