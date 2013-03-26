package edu.wpi.cs.wpisuitetng.modules.RequirementManager.view.Requirements;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.RequirementStatus;
/**
 * 
 * @author Pi 
 * @author Chris
 * @author Brian
 *
 */
abstract public class RequirementPanel extends JPanel 
{
	protected JPanel leftPanel;
	protected JTextField boxName;
	protected JTextArea boxDescription;
	protected JTextField estimate;
	
	protected JPanel rightPanel;
	protected JTextField boxReleaseNum;
	protected JComboBox dropdownStatus;
	protected JRadioButton priorityHigh;
	protected JRadioButton priorityMedium;
	protected JRadioButton priorityLow;
	protected ButtonGroup group;
	
	protected JLabel errorName; 
	protected JLabel errorDescription;
	protected JLabel errorPriority;	
	
	/**
	 * Builds the left panel.
	 * @return the newly created and formatted left panel.
	 */
	protected JPanel buildLeftPanel()
	{
		leftPanel = new JPanel();
		JLabel labelName = new JLabel("Name *");
		JLabel labelDescription = new JLabel("Description *");
		
		boxName = new JTextField();
		boxDescription = new JTextArea();
		boxDescription.setLineWrap(true);
		
		estimate = new JTextField();
		estimate.setPreferredSize(new Dimension(200, 20));

		boxName.setPreferredSize(new Dimension(200, 20));
		boxDescription.setPreferredSize(new Dimension(200, 200));
		
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
		
		leftLayout.putConstraint(SpringLayout.NORTH, errorName, 5,
				SpringLayout.SOUTH, boxName);
		leftLayout.putConstraint(SpringLayout.WEST, errorName, 15,
				SpringLayout.WEST, leftPanel);
		

		leftLayout.putConstraint(SpringLayout.NORTH, labelDescription, 5,
				SpringLayout.SOUTH, errorName);
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
		
		leftPanel.add(labelName);
		leftPanel.add(boxName);
		leftPanel.add(labelDescription);
		leftPanel.add(boxDescription);
//		leftPanel.add(Iteration);
//		leftPanel.add(Estimate);
		leftPanel.add(errorName);
		leftPanel.add(errorDescription);
		
		return leftPanel;
	}
	
	/**
	 * Builds the right panel
	 * @return the newly created and formatted right panel.
	 */
	protected JPanel buildRightPanel()
	{
		rightPanel = new JPanel();

		JLabel labelStatus = new JLabel("Status");
		JLabel labelPriority = new JLabel("Priority");
		JLabel labelReleaseNum = new JLabel("Release #");

		boxReleaseNum = new JTextField();
		boxReleaseNum.setPreferredSize(new Dimension(200, 20));
		
		dropdownStatus = new JComboBox(RequirementStatus.values());
		dropdownStatus.setEditable(false);

		JPanel priorityPanel = new JPanel();
		

		// Radio buttons

		priorityHigh = new JRadioButton("High");
		priorityMedium = new JRadioButton("Medium");
		priorityLow = new JRadioButton("Low");
		
		group = new ButtonGroup();
		group.add(priorityHigh);
		group.add(priorityMedium);
		group.add(priorityLow);

		priorityPanel.add(priorityLow);
		priorityPanel.add(priorityMedium);
		priorityPanel.add(priorityHigh);


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

		
		rightPanel.add(labelStatus);
		rightPanel.add(dropdownStatus);
		rightPanel.add(labelPriority);
		rightPanel.add(priorityPanel);
		rightPanel.add(labelReleaseNum);
		rightPanel.add(boxReleaseNum);
		
		return rightPanel;
	}
}
