package edu.wpi.cs.wpisuitetng.modules.RequirementManager.view.Requirements;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.Border;

import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.RequirementStatus;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.RequirementType;

/**
 * @author Pi 
 * @author Chris
 * @author Brian
 */
abstract public class RequirementPanel extends JScrollPane 
{
	protected JPanel contentPanel;
	protected JPanel leftPanel;
	protected JTextField boxName;
	protected JTextField boxReleaseNum;
	protected JTextArea boxDescription;
	protected JTextField boxIteration;
	final protected Border defaultBorder = (new JTextField()).getBorder();
	final protected Border errorBorder = BorderFactory.createLineBorder(Color.RED);
	
	protected JPanel rightPanel;
	protected JComboBox dropdownType;
	protected JComboBox dropdownStatus;
	protected JRadioButton priorityHigh;
	protected JRadioButton priorityMedium;
	protected JRadioButton priorityLow;
	protected JRadioButton priorityBlank;
	protected JTextField boxEstimate;
	protected ButtonGroup group;
	
	protected JLabel errorName; 
	protected JLabel errorDescription;
	protected JLabel errorEstimate;	
	
	/**
	 * Builds the left panel.
	 * @return the newly created and formatted left panel.
	 */
	protected JPanel buildLeftPanel()
	{
		leftPanel = new JPanel();
		JLabel labelName = new JLabel("Name *");
		JLabel labelReleaseNum = new JLabel("Release Number");
		JLabel labelDescription = new JLabel("Description *");
		JLabel labelIteration = new JLabel("Iteration");
		
		boxName = new JTextField();
		boxName.setPreferredSize(new Dimension(200, 20));
		
		boxReleaseNum = new JTextField();
		boxReleaseNum.setPreferredSize(new Dimension(200, 20));
		
		boxDescription = new JTextArea();
		boxDescription.setLineWrap(true);
		boxDescription.setBorder(defaultBorder);
		boxDescription.setPreferredSize(new Dimension(200, 200));
		
		boxIteration = new JTextField();
		boxIteration.setPreferredSize(new Dimension(200, 20));
		boxIteration.setBackground(Color.WHITE);
		
		errorName = new JLabel();
		errorDescription = new JLabel();

		SpringLayout leftLayout = new SpringLayout();

		leftPanel.setLayout(leftLayout);

		// Name Field
		leftLayout.putConstraint(SpringLayout.NORTH, labelName, 15,
				SpringLayout.NORTH, leftPanel);
		leftLayout.putConstraint(SpringLayout.WEST, labelName, 15,
				SpringLayout.WEST, leftPanel);

		leftLayout.putConstraint(SpringLayout.NORTH, boxName, 15,
				SpringLayout.SOUTH, labelName);
		leftLayout.putConstraint(SpringLayout.WEST, boxName, 15, SpringLayout.WEST,
				leftPanel);
		leftLayout.putConstraint(SpringLayout.NORTH, errorName, 5,
				SpringLayout.SOUTH, boxName);
		leftLayout.putConstraint(SpringLayout.WEST, errorName, 15,
				SpringLayout.WEST, leftPanel);
		
		// Release Field
		leftLayout.putConstraint(SpringLayout.NORTH, labelReleaseNum, 15,
				SpringLayout.SOUTH, errorName);
		leftLayout.putConstraint(SpringLayout.WEST, labelReleaseNum, 15,
				SpringLayout.WEST, leftPanel);
		leftLayout.putConstraint(SpringLayout.NORTH, boxReleaseNum, 15,
				SpringLayout.SOUTH, labelReleaseNum);
		leftLayout.putConstraint(SpringLayout.WEST, boxReleaseNum, 15,
				SpringLayout.WEST, leftPanel);
		
		
		// Description Field
		leftLayout.putConstraint(SpringLayout.NORTH, labelDescription, 15,
				SpringLayout.SOUTH, boxReleaseNum);
		leftLayout.putConstraint(SpringLayout.WEST, labelDescription, 15,
				SpringLayout.WEST, leftPanel);
		leftLayout.putConstraint(SpringLayout.NORTH, boxDescription, 15,
				SpringLayout.SOUTH, labelDescription);
		leftLayout.putConstraint(SpringLayout.WEST, boxDescription, 15,
				SpringLayout.WEST, leftPanel);
		leftLayout.putConstraint(SpringLayout.NORTH, errorDescription, 5,
				SpringLayout.SOUTH, boxDescription);
		leftLayout.putConstraint(SpringLayout.WEST, errorDescription, 15,
				SpringLayout.WEST, leftPanel);
		
		// Iteration Field
		leftLayout.putConstraint(SpringLayout.NORTH, labelIteration, 15,
				SpringLayout.SOUTH, errorDescription);
		leftLayout.putConstraint(SpringLayout.WEST, labelIteration, 15,
				SpringLayout.WEST, leftPanel);
		leftLayout.putConstraint(SpringLayout.NORTH, boxIteration, 15,
				SpringLayout.SOUTH, labelIteration);
		leftLayout.putConstraint(SpringLayout.WEST, boxIteration, 15,
				SpringLayout.WEST, leftPanel);
		
		
		leftPanel.add(labelName);
		leftPanel.add(boxName);
		leftPanel.add(errorName);
		
		leftPanel.add(labelReleaseNum);
		leftPanel.add(boxReleaseNum);
		
		leftPanel.add(labelDescription);
		leftPanel.add(boxDescription);
		leftPanel.add(errorDescription);
		
		leftPanel.add(labelIteration);
		leftPanel.add(boxIteration);
		
		leftPanel.setMinimumSize(new Dimension(250,500));
		leftPanel.setPreferredSize(new Dimension(250,500));
		
		return leftPanel;
	}
	
	/**
	 * Builds the right panel
	 * @return the newly created and formatted right panel.
	 */
	protected JPanel buildRightPanel()
	{
		rightPanel = new JPanel();

		JLabel labelType = new JLabel("Type");
		JLabel labelStatus = new JLabel("Status");
		JLabel labelPriority = new JLabel("Priority");
		JLabel labelEstimate = new JLabel("Estimate");

		dropdownType = new JComboBox(RequirementType.values());
		dropdownType.setEditable(false);
		dropdownType.setBackground(Color.WHITE);
		
		
		dropdownStatus = new JComboBox(RequirementStatus.values());
		dropdownStatus.setEditable(false);
		dropdownStatus.setBackground(Color.WHITE);
		// Radio buttons

		priorityHigh = new JRadioButton("High");
		priorityMedium = new JRadioButton("Medium");
		priorityLow = new JRadioButton("Low");
		priorityBlank = new JRadioButton("Undecided");
		priorityBlank.setSelected(true);
		
		group = new ButtonGroup();
		group.add(priorityBlank);
		group.add(priorityHigh);
		group.add(priorityMedium);
		group.add(priorityLow);
		

		JPanel priorityPanel = new JPanel();
		
		priorityPanel.add(priorityLow);
		priorityPanel.add(priorityMedium);
		priorityPanel.add(priorityHigh);
		priorityPanel.add(priorityBlank);

		boxEstimate = new JTextField();
		boxEstimate.setPreferredSize(new Dimension(200, 20));
		errorEstimate = new JLabel();
		
		SpringLayout rightLayout = new SpringLayout();

		rightPanel.setLayout(rightLayout);
 
		//setup the constraints for the layout.
		
		// Type Field
		rightLayout.putConstraint(SpringLayout.NORTH, labelType, 15,
				SpringLayout.NORTH, rightPanel);
		rightLayout.putConstraint(SpringLayout.WEST, labelType, 15,
				SpringLayout.WEST, rightPanel);
		rightLayout.putConstraint(SpringLayout.NORTH, dropdownType, 15,
				SpringLayout.SOUTH, labelType);
		rightLayout.putConstraint(SpringLayout.WEST, dropdownType, 15,
				SpringLayout.WEST, rightPanel);
		
		// Status Field
		rightLayout.putConstraint(SpringLayout.NORTH, labelStatus, 15,
				SpringLayout.SOUTH, dropdownType);
		rightLayout.putConstraint(SpringLayout.WEST, labelStatus, 15,
				SpringLayout.WEST, rightPanel);
		rightLayout.putConstraint(SpringLayout.NORTH, dropdownStatus, 15,
				SpringLayout.SOUTH, labelStatus);
		rightLayout.putConstraint(SpringLayout.WEST, dropdownStatus, 15,
				SpringLayout.WEST, rightPanel);
		
		// Priority Field
		rightLayout.putConstraint(SpringLayout.NORTH, labelPriority, 15,
				SpringLayout.SOUTH, dropdownStatus);
		rightLayout.putConstraint(SpringLayout.WEST, labelPriority, 15,
				SpringLayout.WEST, rightPanel);
		rightLayout.putConstraint(SpringLayout.NORTH, priorityPanel, 15,
				SpringLayout.SOUTH, labelPriority);
		rightLayout.putConstraint(SpringLayout.WEST, priorityPanel, 15,
				SpringLayout.WEST, rightPanel);

		// Estimate Field
		rightLayout.putConstraint(SpringLayout.NORTH, labelEstimate, 15,
				SpringLayout.SOUTH, priorityPanel);
		rightLayout.putConstraint(SpringLayout.WEST, labelEstimate, 15,
				SpringLayout.WEST, rightPanel);
		rightLayout.putConstraint(SpringLayout.NORTH, boxEstimate, 15,
				SpringLayout.SOUTH, labelEstimate);
		rightLayout.putConstraint(SpringLayout.WEST, boxEstimate, 15,
				SpringLayout.WEST, rightPanel);
		rightLayout.putConstraint(SpringLayout.NORTH, errorEstimate, 5,
				SpringLayout.SOUTH, boxEstimate);
		rightLayout.putConstraint(SpringLayout.WEST, errorEstimate, 15,
				SpringLayout.WEST, rightPanel);

		rightPanel.add(labelType);
		rightPanel.add(dropdownType);
		rightPanel.add(labelStatus);
		rightPanel.add(dropdownStatus);
		rightPanel.add(labelPriority);
		rightPanel.add(priorityPanel);
		rightPanel.add(labelEstimate);
		rightPanel.add(boxEstimate);
		rightPanel.add(errorEstimate);
		
		//restrict size of these elements .
		rightPanel.setMinimumSize(new Dimension(315,500));
		rightPanel.setPreferredSize(new Dimension(315,500));
		
		return rightPanel;
	}
	
	/**
	 * Validates the values of the fields in the requirement panel to ensure they are valid
	 */
	public boolean validateFields()
	{
		boolean isNameValid;
		boolean isDescriptionValid;
		boolean isEstimateValid;
		
		if (boxName.getText().length() >= 100)
		{
			isNameValid = false;
			errorName.setText("No more than 100 chars");
			boxName.setBorder(errorBorder);
			errorName.setForeground(Color.RED);
		}
		else if(boxName.getText().trim().length() <= 0)
		{
			isNameValid = false;
			errorName.setText("** Name is REQUIRED");
			boxName.setBorder(errorBorder);
			errorName.setForeground(Color.RED);
		}
		else
		{
			errorName.setText("");
			boxName.setBorder(defaultBorder);
			isNameValid = true;
			
		}
		if (boxDescription.getText().trim().length() <= 0)
		{
			isDescriptionValid = false;
			errorDescription.setText("** Description is REQUIRED");
			errorDescription.setForeground(Color.RED);
			boxDescription.setBorder(errorBorder);
		}
		else
		{	
			errorDescription.setText("");
			boxDescription.setBorder(defaultBorder);
			isDescriptionValid = true;
		}
		
		if (boxEstimate.getText().trim().length() <= 0)
		{
			boxEstimate.setText("");
			errorEstimate.setText("");
			boxEstimate.setBorder(defaultBorder);
			isEstimateValid = true;
		}
		else if(!(isInteger(boxEstimate.getText())))
		{
			errorEstimate.setText("** Please enter a non-negative integer");
			boxEstimate.setBorder(errorBorder);
			boxEstimate.setBorder((new JTextField()).getBorder());

			isEstimateValid = false;
			errorEstimate.setForeground(Color.RED);
		}
		else if(Integer.parseInt(boxEstimate.getText())<0)
		{
			errorEstimate.setText("** Please enter a non-negative integer");
			boxEstimate.setBorder(errorBorder);
			isEstimateValid = false;
			errorEstimate.setForeground(Color.RED);
		}
		else
		{
			errorEstimate.setText("");
			boxEstimate.setBorder(defaultBorder);
			isEstimateValid = true;
		}
		
		return isNameValid && isDescriptionValid && isEstimateValid;
	}
	
	/**
	 * Checks if the input string is an integer
	 * @param input the string to test
	 * @return true if input is an integer, false otherwise
	 */
	public boolean isInteger( String input ) {
	    try {
	        Integer.parseInt( input );
	        return true;
	    }
	    catch( Exception e ) {
	        return false;
	    }
	}
}
