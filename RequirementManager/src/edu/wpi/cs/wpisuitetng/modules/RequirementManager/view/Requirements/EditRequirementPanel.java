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
	private JButton buttonUpdate = new JButton("Update");
	private JButton buttonCancel = new JButton("Cancel");
	private JButton buttonClear = new JButton("Undo Changes");
	
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
		getBoxName().setText(getRequirementBeingEdited().getName());
		getBoxDescription().setText(getRequirementBeingEdited().getDescription());
		getBoxEstimate().setText(String.valueOf(getRequirementBeingEdited().getEstimate()));
		getBoxReleaseNum().setText(getRequirementBeingEdited().getRelease());
		getDropdownStatus().setSelectedItem(getRequirementBeingEdited().getStatus());
		getDropdownType().setSelectedItem(getRequirementBeingEdited().getType());
		getBoxIteration().setText(getRequirementBeingEdited().getIteration().toString());
		
		switch(getRequirementBeingEdited().getPriority())
		{
		case BLANK:
			getPriorityBlank().setSelected(true);
			break;
		case LOW:
			getPriorityLow().setSelected(true);
			break;
		case MEDIUM:
			getPriorityMedium().setSelected(true);
			break;
		case HIGH:
			getPriorityHigh().setSelected(true);
			break;
		}
		
		if(getRequirementBeingEdited().getStatus() == RequirementStatus.INPROGRESS || getRequirementBeingEdited().getStatus() == RequirementStatus.COMPLETE)
		{
			getBoxEstimate().setEnabled(false);
		}
		else
		{
			getBoxEstimate().setEnabled(true);
		}
		
		if(!(getRequirementBeingEdited().getEstimate() > 0)) getBoxIteration().setEnabled(false);
		
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
	
		// Construct the add requirement controller and add it to the update button
		getButtonUpdate().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(validateFields())
				{
					update();
					cancel();
				}
			}
		});
		
		getButtonClear().addActionListener(new ActionListener(){

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

		buttonPanel.add(getButtonUpdate());
		buttonPanel.add(getButtonClear());
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
	public void update()
	{
		// Extract the name, release number, and description from the GUI fields
		String stringName = this.getBoxName().getText();
		String stringReleaseNum = this.getBoxReleaseNum().getText();
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
		boolean stateHigh = getPriorityHigh().isSelected();
		boolean stateMedium = getPriorityMedium().isSelected();
		boolean stateLow = getPriorityLow().isSelected();

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
		getRequirementBeingEdited().setName(stringName);
		getRequirementBeingEdited().setRelease(stringReleaseNum);
		getRequirementBeingEdited().setDescription(stringDescription);
		getRequirementBeingEdited().setStatus(status, created);
		getRequirementBeingEdited().setPriority(priority, created);
		getRequirementBeingEdited().setEstimate(estimate);
		getRequirementBeingEdited().setIteration(iteration, created);
		getRequirementBeingEdited().setType(type);
		UpdateRequirementController.getInstance().updateRequirement(getRequirementBeingEdited());
		
	}
	
	/**
	 * Cancels the editing of the requirement.
	 */
	private void cancel()
	{
		ViewEventController.getInstance().refreshTable();
		ViewEventController.getInstance().removeTab(this);
	}

	public JButton getButtonUpdate() {
		return buttonUpdate;
	}

	public JButton getButtonClear() {
		return buttonClear;
	}

	public Requirement getRequirementBeingEdited() {
		return requirementBeingEdited;
	}




}
