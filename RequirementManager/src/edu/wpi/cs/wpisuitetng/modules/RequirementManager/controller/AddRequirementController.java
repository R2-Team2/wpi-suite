
package edu.wpi.cs.wpisuitetng.modules.RequirementManager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.RequirementModel;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.RequirementPriority;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.RequirementStatus;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.view.Requirements.NewRequirementPanel;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;

/**
 * This controller responds when the user clicks the Update button by
 * adding the contents of the requirement text fields to the model as a new
 * requirement.
 */
public class AddRequirementController implements ActionListener {
	
	private final RequirementModel model;
	private final NewRequirementPanel view;
	
	/**
	 * Construct an AddRequirementController for the given model, view pair
	 * @param model the model containing the requirements
	 * @param view the view where the user enters new requirements
	 */
	public AddRequirementController(RequirementModel model, NewRequirementPanel view) {
		this.model = model;
		this.view = view;
	}

	/**
	 * This method is called when the user clicks the Update button
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		// Extract the name, release number, and description from the GUI fields
		String stringName = view.getBoxName().getText();
		String stringReleaseNum = view.getBoxReleaseNum().getText();
		String stringDescription = view.getBoxDescription().getText();
		
		RequirementPriority priority;
		RequirementStatus status;
		
		// Extract the status from the GUI
		boolean stateNew = view.getDropdownStatus().getSelectedItem() == "New";
		boolean stateInProgress = view.getDropdownStatus().getSelectedItem() == "In Progress";
		boolean stateOpen = view.getDropdownStatus().getSelectedItem() == "Open";
		boolean stateComplete = view.getDropdownStatus().getSelectedItem() == "Complete";
		boolean stateDeleted = view.getDropdownStatus().getSelectedItem() == "Deleted";
		
		// Convert the status string to its corresponding enum
		if (stateNew)
			status = RequirementStatus.NEW;
		else if (stateInProgress)
			status = RequirementStatus.INPROGRESS;
		else if (stateOpen)
			status = RequirementStatus.OPEN;
		else if (stateComplete)
			status = RequirementStatus.COMPLETE;
		else if (stateDeleted)
			status = RequirementStatus.DELETED;
		else
			status = RequirementStatus.NEW;

		// Extract which radio is selected for the priority
		boolean stateHigh = view.getPriorityHigh().isSelected();
		boolean stateMedium = view.getPriorityMedium().isSelected();
		boolean stateLow = view.getPriorityLow().isSelected();

		// Convert the priority string to its corresponding enum
		if (stateHigh)
			priority = RequirementPriority.HIGH;
		else if (stateMedium)
			priority = RequirementPriority.MEDIUM;
		else if (stateLow)
			priority = RequirementPriority.LOW;
		else
			priority = RequirementPriority.BLANK;

		// Create a new requirement object based on the extracted info
		Requirement newRequirement = new Requirement(1, stringName, stringDescription);
		newRequirement.setRelease(stringReleaseNum);
		newRequirement.setStatus(status);
		newRequirement.setPriority(priority);
		
		// if (requirementIsFine) {
		// Send a request to the core to save this requirement
		final Request request = Network.getInstance().makeRequest("requirementmanager/requirement", HttpMethod.PUT); // PUT == create
		request.setBody(newRequirement.toJSON()); // put the new requirement in the body of the request
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
