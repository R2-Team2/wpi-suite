package edu.wpi.cs.wpisuitetng.modules.RequirementManager.view.Requirements;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import edu.wpi.cs.wpisuitetng.modules.RequirementManager.controller.UpdateRequirementController;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.Iteration;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.RequirementPriority;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.RequirementStatus;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.RequirementType;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.view.ViewEventController;
/**
 * 
 * @author Pi 
 * @author Chris
 * @author Brian
 *
 */
public class EditRequirementPanel extends RequirementPanel 
{	
	private Requirement requirementBeingEdited;
	
	/**
	 * Constructor for a new requirement panel
	 * @param reqModel Local requirement model for containing data
	 */
	public EditRequirementPanel(Requirement req) {
		contentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		contentPanel.add(buildLeftPanel()); //add left panel
		contentPanel.add(buildRightPanel()); //add right panel
		
		contentPanel.setMinimumSize(new Dimension(500,465));
		contentPanel.setPreferredSize(new Dimension(500,465));
		
		this.setViewportView(contentPanel);
		
		requirementBeingEdited = req;
		fillFieldsForRequirement();
	}
	
	/**
	 * Fills the fields of the edit requirement panel based on the current settings of the
	 * edited requirement.
	 */
	private void fillFieldsForRequirement()
	{
		getBoxName().setText(requirementBeingEdited.getName());
		getBoxDescription().setText(requirementBeingEdited.getDescription());
		getBoxEstimate().setText(String.valueOf(requirementBeingEdited.getEstimate()));
		boxReleaseNum.setText(requirementBeingEdited.getRelease());
		getDropdownStatus().setSelectedItem(requirementBeingEdited.getStatus());
		getDropdownType().setSelectedItem(requirementBeingEdited.getType());
		getBoxIteration().setText(requirementBeingEdited.getIteration().toString());
		
		switch(requirementBeingEdited.getPriority())
		{
		case BLANK:
			priorityBlank.setSelected(true);
			break;
		case LOW:
			priorityLow.setSelected(true);
			break;
		case MEDIUM:
			priorityMedium.setSelected(true);
			break;
		case HIGH:
			priorityHigh.setSelected(true);
			break;
		}
		
		if(requirementBeingEdited.getStatus() == RequirementStatus.INPROGRESS || requirementBeingEdited.getStatus() == RequirementStatus.COMPLETE)
		{
			getBoxEstimate().setEnabled(false);
		}
		else
		{
			getBoxEstimate().setEnabled(true);
		}
		
		if(!(requirementBeingEdited.getEstimate() > 0)) getBoxIteration().setEnabled(false);
		
		//reset the error messages.
		this.getErrorEstimate().setText("");
		getBoxEstimate().setBorder(defaultBorder);
		this.getErrorDescription().setText("");
		getBoxDescription().setBorder(defaultBorder);
		this.getErrorName().setText("");
		getBoxName().setBorder(defaultBorder);
		
		repaint();
	}
	
	/**
	 * Builds the right panel
	 */
	@Override
	protected JPanel buildRightPanel()
	{
		super.buildRightPanel();

		//setup the buttons
		JPanel buttonPanel = new JPanel();
		JButton buttonUpdate = new JButton("Update");
		JButton buttonCancel = new JButton("Cancel");
		JButton buttonClear = new JButton("Undo Changes");
		
		// Construct the add requirement controller and add it to the update button
		buttonUpdate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(validateFields()) update();
			}
		});
		
		buttonClear.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				fillFieldsForRequirement();
			}
		
		});
		
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});

		buttonPanel.add(buttonUpdate);
		buttonPanel.add(buttonClear);
		buttonPanel.add(buttonCancel);
		
		SpringLayout rightLayout = (SpringLayout)rightPanel.getLayout();
		
		rightLayout.putConstraint(SpringLayout.NORTH, buttonPanel, 15,
				SpringLayout.SOUTH, getErrorEstimate());
		rightLayout.putConstraint(SpringLayout.WEST, buttonPanel, 15,
				SpringLayout.WEST, rightPanel);
		
		rightPanel.add(buttonPanel);
		
		return rightPanel;
	}
	
	/**
	 * Updates the requirement.
	 */
	private void update()
	{
		// Extract the name, release number, and description from the GUI fields
		String stringName = this.getBoxName().getText();
		String stringReleaseNum = this.boxReleaseNum.getText();
		String stringDescription = this.getBoxDescription().getText();
		String stringEstimate = this.getBoxEstimate().getText();
		String stringIteration = this.getBoxIteration().getText();


		RequirementPriority priority;
		RequirementStatus status;
		RequirementType type = (RequirementType)getDropdownType().getSelectedItem();
		
		int estimate = stringEstimate.trim().length() == 0 ? 0 : Integer.parseInt(stringEstimate);
		// Extract the status from the GUI
		status = (RequirementStatus)this.getDropdownStatus().getSelectedItem();
		Iteration iteration = new Iteration(stringIteration);
		// Extract which radio is selected for the priority
		boolean stateHigh = priorityHigh.isSelected();
		boolean stateMedium = priorityMedium.isSelected();
		boolean stateLow = priorityLow.isSelected();

		// Convert the priority string to its corresponding enum
		if (stateHigh)
			priority = RequirementPriority.HIGH;
		else if (stateMedium)
			priority = RequirementPriority.MEDIUM;
		else if (stateLow)
			priority = RequirementPriority.LOW;
		else
			priority = RequirementPriority.BLANK;

		// Set to false to indicate the requirement is being newly created
		boolean created = false;
		
		// Create a new requirement object based on the extracted info
		requirementBeingEdited.setName(stringName);
		requirementBeingEdited.setRelease(stringReleaseNum);
		requirementBeingEdited.setDescription(stringDescription);
		requirementBeingEdited.setStatus(status, created);
		requirementBeingEdited.setPriority(priority, created);
		requirementBeingEdited.setEstimate(estimate);
		requirementBeingEdited.setIteration(iteration, created);
		requirementBeingEdited.setType(type);
		UpdateRequirementController.getInstance().updateRequirement(requirementBeingEdited);
		ViewEventController.getInstance().refreshTable();
		ViewEventController.getInstance().removeTab(this);
	}
	
	/**
	 * Cancels the editing of the requirement.
	 */
	private void cancel()
	{
		ViewEventController.getInstance().removeTab(this);
	}
}
