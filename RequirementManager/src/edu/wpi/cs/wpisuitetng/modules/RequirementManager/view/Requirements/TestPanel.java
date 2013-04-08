/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.RequirementManager.view.Requirements;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.AcceptanceTest;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.TestStatus;

/**
 * Class that creates a panel that is used to represent an acceptance test
 * 
 * @author Brian Froehlich
 *
 */
@SuppressWarnings("serial")
public class TestPanel extends JPanel 
{
	@SuppressWarnings("unchecked")
	public TestPanel(AcceptanceTest test)
	{
		// Set border to black
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		
		// Create a text area containing the test's description
		JTextArea description = new JTextArea(test.getDescription());
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		description.setEditable(false); // Do not allow to be edited
		
		// Give the description a black border with 2px padding inside
		Border b = BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.black), 
		           BorderFactory.createEmptyBorder(2, 2, 2, 2));
		description.setBorder(b);
		
		// Get the title and place it in a label
		JLabel testName = new JLabel(test.getName());
		
		// Get status and set drop down box to correct status
		JComboBox dropdownStatus = new JComboBox(TestStatus.values());
		dropdownStatus.setSelectedItem(test.getStatus());
		
		// Create a layout manager for this test panel
		this.setLayout(new GridBagLayout());
		GridBagConstraints testConstraints = new GridBagConstraints();
		
		testConstraints.anchor = GridBagConstraints.NORTHWEST; // Display title in top-left
		testConstraints.fill = GridBagConstraints.NONE; // Don't fill elements
		testConstraints.gridy = 0; // Row 0
		testConstraints.gridx = 0; // Column 0
		this.add(testName, testConstraints); // Add info to testPanel
		
		testConstraints.gridx = 1; // Column 1
		testConstraints.anchor = GridBagConstraints.NORTHEAST; // Display status in top-right
		this.add(dropdownStatus, testConstraints); // Add status to panel
		
		testConstraints.fill = GridBagConstraints.HORIZONTAL; // Fill elements horizontally
		testConstraints.gridy = 1; //Row 1
		testConstraints.weightx = 1; //Fill the width
		testConstraints.insets = new Insets(2,2,2,2); //2px margin
		this.add(description, testConstraints); // Add description to testPanel
	}
}