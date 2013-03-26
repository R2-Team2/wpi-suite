package edu.wpi.cs.wpisuitetng.modules.RequirementManager.view.Requirements;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import edu.wpi.cs.wpisuitetng.modules.RequirementManager.controller.AddRequirementController;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.RequirementModel;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.RequirementPriority;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.RequirementStatus;
/**
 * 
 * @author Pi 
 * @author Chris
 * @author Brian
 *
 */
public class NewRequirementPanel extends JPanel 
{
	private JPanel leftPanel;
	private JTextField boxName;
	private JTextArea boxDescription;
	private JTextArea resultField;
	private JTextField Iteration;
	private JTextField Estimate;
	
	private JPanel rightPanel;
	private JTextField boxReleaseNum;
	private JComboBox dropdownStatus;
	private JRadioButton priorityHigh;
	private JRadioButton priorityMedium;
	private JRadioButton priorityLow;
	
	private JLabel errorName; 
	private JLabel errorDescription;
	private JLabel errorPriority;
	
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
	 * Builds the left panel.
	 * @return the newly created and formatted left panel.
	 */
	private JPanel buildLeftPanel()
	{
		leftPanel = new JPanel();
		JLabel labelName = new JLabel("Name");
		JLabel labelDescription = new JLabel("Description");
		
		boxName = new JTextField();
		boxDescription = new JTextArea();
		boxDescription.setLineWrap(true);
		
		Iteration = new JTextField();
		Estimate = new JTextField();
		Iteration.setPreferredSize(new Dimension(200, 20));
		Estimate.setPreferredSize(new Dimension(200, 20));

		boxName.setPreferredSize(new Dimension(200, 20));
		boxDescription.setPreferredSize(new Dimension(200, 100));

		resultField = new JTextArea(); // For testing purpose
		resultField.setPreferredSize(new Dimension(200, 200));
		
		errorName = new JLabel();
		errorDescription = new JLabel();

		SpringLayout leftLayout = new SpringLayout();

		leftPanel.setLayout(leftLayout);

		leftLayout.putConstraint(SpringLayout.NORTH, labelName, 15,
				SpringLayout.NORTH, leftPanel);
		leftLayout.putConstraint(SpringLayout.WEST, labelName, 15,
				SpringLayout.WEST, leftPanel);

		leftLayout.putConstraint(SpringLayout.NORTH, boxName, 15,
				SpringLayout.SOUTH, labelName);
		leftLayout.putConstraint(SpringLayout.WEST, boxName, 15, SpringLayout.WEST,
				leftPanel);

		leftLayout.putConstraint(SpringLayout.NORTH, labelDescription, 15,
				SpringLayout.SOUTH, boxName);
		leftLayout.putConstraint(SpringLayout.WEST, labelDescription, 15,
				SpringLayout.WEST, leftPanel);

		leftLayout.putConstraint(SpringLayout.NORTH, boxDescription, 15,
				SpringLayout.SOUTH, labelDescription);
		leftLayout.putConstraint(SpringLayout.WEST, boxDescription, 15,
				SpringLayout.WEST, leftPanel);

		leftLayout.putConstraint(SpringLayout.NORTH, resultField, 15,
				SpringLayout.SOUTH, boxDescription);
		leftLayout.putConstraint(SpringLayout.WEST, resultField, 15,
				SpringLayout.WEST, leftPanel);
		
		leftLayout.putConstraint(SpringLayout.NORTH, errorName, 0,
				SpringLayout.NORTH, boxName);
		leftLayout.putConstraint(SpringLayout.WEST, errorName, 15,
				SpringLayout.EAST, boxName);
		leftLayout.putConstraint(SpringLayout.NORTH, errorDescription, 0,
				SpringLayout.NORTH, boxDescription);
		leftLayout.putConstraint(SpringLayout.WEST, errorDescription, 15,
				SpringLayout.EAST, boxDescription);
		
		leftPanel.add(labelName);
		leftPanel.add(boxName);
		leftPanel.add(labelDescription);
		leftPanel.add(boxDescription);
//		leftPanel.add(Iteration);
//		leftPanel.add(Estimate);
		leftPanel.add(resultField);
		leftPanel.add(errorName);
		leftPanel.add(errorDescription);
		
		return leftPanel;
	}
	
	/**
	 * Builds the right panel
	 * @return the newly created and formatted right panel.
	 */
	private JPanel buildRightPanel()
	{
		rightPanel = new JPanel();

		JLabel labelStatus = new JLabel("Status");
		JLabel labelPriority = new JLabel("Priority");
		JLabel labelReleaseNum = new JLabel("Release #");

		boxReleaseNum = new JTextField();
		boxReleaseNum.setPreferredSize(new Dimension(200, 20));
		
		dropdownStatus = new JComboBox(RequirementStatus.values());
		dropdownStatus.setEditable(false);
		dropdownStatus.setEnabled(false);

		JPanel priorityPanel = new JPanel();
		

		// Radio buttons

		priorityHigh = new JRadioButton("High");
		priorityMedium = new JRadioButton("Medium");
		priorityLow = new JRadioButton("Low");
		
		ButtonGroup group = new ButtonGroup();
		group.add(priorityHigh);
		group.add(priorityMedium);
		group.add(priorityLow);
		
		errorPriority = new JLabel();

		priorityPanel.add(priorityLow);
		priorityPanel.add(priorityMedium);
		priorityPanel.add(priorityHigh);

		//setup the buttons
		JPanel buttonPanel = new JPanel();
		JButton buttonUpdate = new JButton("Create");
		JButton buttonCancel = new JButton("Cancel");
//		JButton buttonDelete = new JButton("Delete");
		
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
					// TODO: Asking someone about this
					update();
				}
			}
		});
		
		
		
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});

//		buttonDelete.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				delete();
//			}
//		});

		buttonPanel.add(buttonUpdate);
		buttonPanel.add(buttonCancel);
//		buttonPanel.add(buttonDelete);

		SpringLayout rightLayout = new SpringLayout();

		rightPanel.setLayout(rightLayout);
 
		//setup the constraints for the layout.
		rightLayout.putConstraint(SpringLayout.NORTH, labelStatus, 15,
				SpringLayout.NORTH, rightPanel);
		rightLayout.putConstraint(SpringLayout.WEST, labelStatus, 15,
				SpringLayout.WEST, rightPanel);

		rightLayout.putConstraint(SpringLayout.NORTH, dropdownStatus, 15,
				SpringLayout.SOUTH, labelStatus);
		rightLayout.putConstraint(SpringLayout.WEST, dropdownStatus, 15,
				SpringLayout.WEST, rightPanel);

		rightLayout.putConstraint(SpringLayout.NORTH, labelPriority, 15,
				SpringLayout.SOUTH, dropdownStatus);
		rightLayout.putConstraint(SpringLayout.WEST, labelPriority, 15,
				SpringLayout.WEST, rightPanel);

		rightLayout.putConstraint(SpringLayout.NORTH, priorityPanel, 15,
				SpringLayout.SOUTH, labelPriority);
		rightLayout.putConstraint(SpringLayout.WEST, priorityPanel, 15,
				SpringLayout.WEST, rightPanel);

		rightLayout.putConstraint(SpringLayout.NORTH, labelReleaseNum, 15,
				SpringLayout.SOUTH, priorityPanel);
		rightLayout.putConstraint(SpringLayout.WEST, labelReleaseNum, 15,
				SpringLayout.WEST, rightPanel);

		rightLayout.putConstraint(SpringLayout.NORTH, boxReleaseNum, 15,
				SpringLayout.SOUTH, labelReleaseNum);
		rightLayout.putConstraint(SpringLayout.WEST, boxReleaseNum, 15,
				SpringLayout.WEST, rightPanel);

		rightLayout.putConstraint(SpringLayout.NORTH, buttonPanel, 15,
				SpringLayout.SOUTH, boxReleaseNum);
		rightLayout.putConstraint(SpringLayout.WEST, buttonPanel, 15,
				SpringLayout.WEST, rightPanel);
		rightLayout.putConstraint(SpringLayout.NORTH, errorPriority, 0,
				SpringLayout.NORTH, priorityPanel);
		rightLayout.putConstraint(SpringLayout.WEST, errorPriority, 15,
				SpringLayout.EAST, priorityPanel);
		
		rightPanel.add(labelStatus);
		rightPanel.add(dropdownStatus);
		rightPanel.add(labelPriority);
		rightPanel.add(priorityPanel);
		rightPanel.add(labelReleaseNum);
		rightPanel.add(boxReleaseNum);
		rightPanel.add(buttonPanel);
		rightPanel.add(errorPriority);
		
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
	}
	
	/**
	 * Deletes the requirement.
	 */
//	private void delete()
//	{
//		resultField.setText("Delete");
//	}
	
	/**
	 * Cancels the editing of the requirement.
	 */
	private void cancel() 
	{
		boxName.setText(null);
		boxDescription.setText(null);
		dropdownStatus.setSelectedItem("Not Selected");
		priorityLow.setSelected(false);
		priorityMedium.setSelected(false);
		priorityHigh.setSelected(false);
		boxReleaseNum.setText(null);
		resultField.setText(null);
		errorName.setText(null);
		errorDescription.setText(null);
		errorPriority.setText(null);
		repaint(); //repaint the entire panel.
	}
 
}
