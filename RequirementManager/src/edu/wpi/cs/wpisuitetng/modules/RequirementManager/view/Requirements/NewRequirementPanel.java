package edu.wpi.cs.wpisuitetng.modules.RequirementManager.view.Requirements;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.RequirementModel;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.RequirementPriority;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.RequirementStatus;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.view.ViewEventController;
/**
 * 
 * @author Pi 
 * @author Chris
 * @author Brian
 *
 */
public class NewRequirementPanel extends RequirementPanel 
{	
	/**
	 * Constructor for a new requirement panel
	 * @param reqModel Local requirement model for containing data
	 */
	public NewRequirementPanel() {
		setLayout(new GridLayout(1, 2));


		this.add(buildLeftPanel()); //add left panel
		this.add(buildRightPanel()); //add right panel
	}
	
	/**
	 * Builds the right panel
	 */
	@Override
	protected JPanel buildRightPanel()
	{
		super.buildRightPanel();

		dropdownStatus.setEnabled(false);

		//setup the buttons
		JPanel buttonPanel = new JPanel();
		JButton buttonUpdate = new JButton("Create");
		JButton buttonCancel = new JButton("Cancel");
		JButton buttonClear = new JButton("Clear");
		
		// Construct the add requirement controller and add it to the update button
		buttonUpdate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				boolean isNameValid;
				boolean isDescriptionValid;
				boolean isPriorityValid;
				
				if (boxName.getText().length() >= 8)
				{
					isNameValid = false;
					errorName.setText("No more than 8 chars");
				}
				else if(boxName.getText().trim().length() <= 0)
				{
					isNameValid = false;
					errorName.setText("Name is REQUIRED");
				}
				else
				{
					errorName.setText(null);
					isNameValid = true;
					
				}
				if (boxDescription.getText().trim().length() <= 0)
				{
					isDescriptionValid = false;
					errorDescription.setText("Description is REQUIRED");
				}
				else
				{	
					errorDescription.setText(null);
					isDescriptionValid = true;
				}
				
				if (!(priorityHigh.isSelected() || priorityMedium.isSelected() || priorityLow.isSelected()))
				{
					isPriorityValid = false;
					errorPriority.setText("Priority selection is REQUIRED");
				}
				else
				{	
					errorPriority.setText(null);
					isPriorityValid = true;
				}
				
				
				if(isNameValid && isDescriptionValid && isPriorityValid)
				{
					update();
				}
			}
		});
		
		buttonClear.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				clear();
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
				SpringLayout.SOUTH, boxReleaseNum);
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
		String stringName = this.boxName.getText();
		String stringReleaseNum = this.boxReleaseNum.getText();
		String stringDescription = this.boxDescription.getText();
		
		RequirementPriority priority;
		RequirementStatus status;
		
		// Extract the status from the GUI
		status = (RequirementStatus)this.dropdownStatus.getSelectedItem();

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

		// Create a new requirement object based on the extracted info
		Requirement newRequirement = new Requirement(RequirementModel.getInstance().getNextID(), stringName, stringDescription);
		newRequirement.setRelease(stringReleaseNum);
		newRequirement.setStatus(status);
		newRequirement.setPriority(priority);
		RequirementModel.getInstance().addRequirement(newRequirement);
		ViewEventController.getInstance().removeTab(this);
	}
	
	/**
	 * Clears the editing of the requirement.
	 */
	private void clear() 
	{
		boxName.setText(null);
		boxDescription.setText(null);
		dropdownStatus.setSelectedItem("Not Selected");
		group.clearSelection();
		boxReleaseNum.setText(null);
		errorName.setText(null);
		errorDescription.setText(null);
		errorPriority.setText(null);
		repaint(); //repaint the entire panel.
	}
	
	/**
	 * Cancels the editing of the requirement.
	 */
	private void cancel()
	{
		ViewEventController.getInstance().removeTab(this);
	}
 
}
