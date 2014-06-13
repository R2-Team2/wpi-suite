/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.tabs;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.controller.UpdateRequirementController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.AcceptanceTest;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.RequirementPanelListener;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.ViewMode;

/**
 * @author Rolling Thunder
 * @version $Revision: 1.0 $
 */
public class RequirementTestPanel extends JPanel implements RequirementPanelListener{

	private Requirement currentRequirement;
	private RequirementTabsPanel parentPanel;
	private ViewMode viewMode;
	private int testsAdded;
	private JScrollPane testsScroll;
	private final JTextField testTitle;
	private final JTextArea testMessage;
	private final JButton buttonAddTest;
	private final JButton buttonClear;
	private final JLabel errorMsg;
	private final JLabel labelTitle;
	private final JLabel labelMessage;
	private final Border defaultBorder = (new JTextField()).getBorder();
	

	
	/**
	 * Constructor for RequirementTestPanel.
	 * @param parent RequirementTabsPanel
	 * @param vm RequirementViewMode
	 * @param current Requirement
	 */
	public RequirementTestPanel(RequirementTabsPanel parent, ViewMode vm, Requirement current) {
		currentRequirement = current;
		viewMode = vm;
		testsAdded = 0;
		
		labelTitle = new JLabel("Title *");
		labelMessage = new JLabel("Description *");
		testTitle = new JTextField();
		testMessage = new JTextArea();
		testsScroll = new JScrollPane();

		// Buttons to be added to the bottom of the NotePanel
		buttonAddTest = new JButton("Add Test");
		buttonClear = new JButton("Clear");
		buttonAddTest.setEnabled(false);
		buttonClear.setEnabled(false);
		
		testTitle.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyReleased(KeyEvent e)
			{
				boolean enabledButtons = !testTitle.getText().trim().isEmpty() && !testMessage.getText().trim().isEmpty();
				buttonAddTest.setEnabled(enabledButtons);
				buttonClear.setEnabled(enabledButtons);
			}
		});
		
		testMessage.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyReleased(KeyEvent e)
			{
				boolean enabledButtons = !testTitle.getText().trim().isEmpty() && !testMessage.getText().trim().isEmpty();
				buttonAddTest.setEnabled(enabledButtons);
				buttonClear.setEnabled(enabledButtons);
			}
		});
		
		testMessage.setLineWrap(true); // If right of box is reach, goes down a
										// line
		testMessage.setWrapStyleWord(true); // Doesn't chop off words
		testMessage.setBorder(defaultBorder);

		// Error message label in case no note was included
		errorMsg = new JLabel();

		// Layout manager for entire note panel
		final GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		final GridBagConstraints c = new GridBagConstraints();

		// Layout manager for panel that contains the buttons
		final GridBagLayout bottomLayout = new GridBagLayout();
		JPanel bottomPanel = new JPanel(bottomLayout);
		final GridBagConstraints bc = new GridBagConstraints();

		// Create new scroll pane for notes
		testsScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// Always show scroll bar
		testsScroll.setMinimumSize(new Dimension(100,100));

		c.fill = GridBagConstraints.BOTH; // Fill grid cell with elements
		c.weightx = .9; // Fill horizontal space
		c.weighty = .9; // Fill all the vertical space
		this.add(testsScroll, c);

		c.gridy = 1; // Row 1
		c.weighty = 0; // Fill 0% of vertical space
		this.add(labelTitle, c);
		
		c.gridy = 2; // Row 1
		c.weighty = 0; // Fill 0% of vertical space
		this.add(testTitle, c);
		
		c.gridy = 3; // Row 1
		c.weighty = 0; // Fill 0% of vertical space
		this.add(labelMessage, c);
		
		c.gridy = 4; // Row 1
		c.weighty = .2; // Fill 0% of vertical space
		this.add(testMessage, c);
		

		bc.anchor = GridBagConstraints.WEST; // Anchor buttons to west of bottom
												// panel
		bottomPanel.add(buttonAddTest, bc); // Include "Add note" button to
											// bottom panel

		bc.gridx = 1; // Column 1
		bottomPanel.add(buttonClear, bc); // Include "Clear" button to bottom
											// panel

		bc.gridx = 2; // Column 2
		bottomPanel.add(errorMsg, bc); // Add error message label to bottom
										// panel

		c.weighty = 0; // Do not stretch
		c.gridy = 5; // Row 2
		c.fill = GridBagConstraints.NONE; // Do not fill cell
		c.anchor = GridBagConstraints.WEST; // Anchor buttons to west of panel
		this.add(bottomPanel, c); // Add buttons to the panel
		
		this.setupListeners();
		this.refresh();
	}
	
	/**
	 * Refreshes the acceptance tests panel
	 */
	private void refresh() {

		testsScroll.setViewportView(SingleAcceptanceTestPanel.createList(currentRequirement));
	}
	
	/**
	 * Sets up the listeners 
	 */
	private void setupListeners()
	{
		// Listener for add note button
		buttonAddTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Display error message if there is no text in noteMessage
				if (testMessage.getText().length() <= 0 && testTitle.getText().length() <= 0) {
					errorMsg.setText(" Error: Must have a title and a description.");
				} else if(testTitle.getText().length() > 100) {
					errorMsg.setText(" Error: Title must be less than 100 characters.");
				}
				else {
					
					String title = testTitle.getText();
					String msg = testMessage.getText(); // Get text from
														// noteMessage					
									
					AcceptanceTest tempTest = new AcceptanceTest(testsAdded, title, msg);
					// Clear all text areas
					testTitle.setText("");
					testMessage.setText("");
					errorMsg.setText("");
					buttonClear.setEnabled(false);
					buttonAddTest.setEnabled(false);
										
					// Add acceptance test to requirement
					currentRequirement.addTest(tempTest);

					refresh();
					testsAdded++;
					// Update database so requirement stores new note
					UpdateRequirementController.getInstance()
							.updateRequirement(currentRequirement);
				}
			}
		});

		// Listener for the Clear button
		buttonClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Clear all text fields
				testTitle.setText("");
				testMessage.setText("");
				errorMsg.setText("");
				buttonClear.setEnabled(false);
				buttonAddTest.setEnabled(false);
			}
		});
	}
	

	/**
	 * Method readyToRemove.
	
	
	 * @return boolean * @see edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.RequirementPanelListener#readyToRemove() * @see edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.RequirementPanelListener#readyToRemove()
	 */
	@Override
	public boolean readyToRemove() {
		return testMessage.getText().length() == 0 && testTitle.getText().length() == 0 && 
				(testsAdded == 0 || viewMode == ViewMode.EDITING);
	}

	/**
	 * Method fireDeleted.
	 * @param b boolean
	
	 * @see edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.RequirementPanelListener#fireDeleted(boolean) */
	@Override
	public void fireDeleted(boolean b) {		
	}

	/**
	 * Method fireValid.
	 * @param b boolean
	
	 * @see edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.RequirementPanelListener#fireValid(boolean) */
	@Override
	public void fireValid(boolean b) {		
	}

	/**
	 * Method fireChanges.
	 * @param b boolean
	
	 * @see edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.RequirementPanelListener#fireChanges(boolean) */
	@Override
	public void fireChanges(boolean b) {		
	}

	/**
	 * Method fireRefresh.
	 * @see edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.RequirementPanelListener#fireRefresh()
	 */
	@Override
	public void fireRefresh() {
		this.refresh();
	}

	/**
	 * Method getTestsAdded.
	 * @return int
	 */
	public int getTestsAdded() {
		return testsAdded;
	}

	/**
	 * Method getAddTestButton.
	 * @return JButton
	 */
	public JButton getAddTestButton() {
		return buttonAddTest;
	}

	/**
	 * Method getTestTitle.
	 * @return JTextField
	 */
	public JTextField getTestTitle() {
		return testTitle;
	}

	/**
	 * Method getTestMessage.
	 * @return JTextArea
	 */
	public JTextArea getTestMessage() {
		return testMessage;
	}

	/**
	 * Method getClearButton.
	 * @return JButton
	 */
	public JButton getClearButton() {
		return buttonClear;
	}
}
