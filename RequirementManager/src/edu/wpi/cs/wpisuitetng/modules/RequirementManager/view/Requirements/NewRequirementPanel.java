package edu.wpi.cs.wpisuitetng.modules.RequirementManager.view.Requirements;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.RequirementModel;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.Iterations.IterationModel;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.Iterations.RequirementIteration;
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

public class NewRequirementPanel extends RequirementPanel 
{	
	
	/**
	 * Constructor for a new requirement panel
	 * @param reqModel Local requirement model for containing data
	 */
	public NewRequirementPanel() {
		contentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		contentPanel.add(buildLeftPanel()); //add left panel
		contentPanel.add(buildRightPanel()); //add right panel
		
		contentPanel.setMinimumSize(new Dimension(500,465));
		contentPanel.setPreferredSize(new Dimension(500,465));
		
		this.setViewportView(contentPanel);
	}
	
	/**
	 * Builds the left panel
	 */
	@Override
	protected JPanel buildLeftPanel()
	{
		super.buildLeftPanel();
		boxIteration.setEnabled(false);
		boxIteration.setBackground(leftPanel.getBackground());
		return leftPanel;
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
				if(validateFields()) update();
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
				SpringLayout.SOUTH, errorEstimate);
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
		String stringIteration = this.boxIteration.getText();
		String stringEstimate = this.boxEstimate.getText();
		
		RequirementPriority priority;
		RequirementStatus status;
		RequirementType type;
		int estimate = stringEstimate.trim().length() == 0 ? 0 : Integer.parseInt(stringEstimate);
		
		// Extract the status from the GUI
		status = (RequirementStatus)this.dropdownStatus.getSelectedItem();
		type = (RequirementType)this.dropdownType.getSelectedItem();
		// Extract which radio is selected for the priority
		boolean stateHigh = priorityHigh.isSelected();
		boolean stateMedium = priorityMedium.isSelected();
		boolean stateLow = priorityLow.isSelected();
		boolean stateBlank = priorityBlank.isSelected();

		// Convert the priority string to its corresponding enum
		if (stateHigh)
			priority = RequirementPriority.HIGH;
		else if (stateMedium)
			priority = RequirementPriority.MEDIUM;
		else if (stateLow)
			priority = RequirementPriority.LOW;
		else if(stateBlank)
			priority = RequirementPriority.BLANK;
		else
			priority = RequirementPriority.BLANK;

		// Set to true to indicate the requirement is being newly created
		boolean created = true;
		
		// Create a new requirement object based on the extracted info
		Requirement newRequirement = new Requirement(RequirementModel.getInstance().getNextID(), stringName, stringDescription);
		newRequirement.setRelease(stringReleaseNum);
		newRequirement.setStatus(status, created);
		newRequirement.setPriority(priority, created);
		newRequirement.setType(type);
		newRequirement.setEstimate(estimate);
		newRequirement.setIteration("Backlog", created);
		newRequirement.getHistory().add("REQUIREMENT CREATED");

		RequirementModel.getInstance().addRequirement(newRequirement);
		ViewEventController.getInstance().removeTab(this);
	}
	
	/**
	 * Clears the editing of the requirement.
	 */
	private void clear() 
	{
		boxName.setText("");
		boxDescription.setText("");
		this.priorityBlank.setSelected(true);
		dropdownType.setSelectedItem(RequirementType.BLANK);
		boxReleaseNum.setText("");
		boxEstimate.setText("");
		
		this.errorEstimate.setText("");
		boxEstimate.setBorder(defaultBorder);
		this.errorDescription.setText("");
		boxDescription.setBorder(defaultBorder);
		this.errorName.setText("");
		boxName.setBorder(defaultBorder);
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
