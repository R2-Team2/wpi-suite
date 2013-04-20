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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.controller.UpdateRequirementController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.AcceptanceTest;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.RequirementPanelListener;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.RequirementViewMode;

public class RequirementTestPanel extends JPanel implements RequirementPanelListener{

	private Requirement currentRequirement;
	private RequirementTabsPanel parentPanel;
	private RequirementViewMode viewMode;
	private int testsAdded;
	private JScrollPane testsScroll;

	/**
	 * Constructor for the requirement test panel
	 * @param parent parent panel
	 * @param vM view mode
	 * @param req current requirement
	 */
	public RequirementTestPanel(RequirementTabsPanel parent, RequirementViewMode vM, Requirement req) {
		this.currentRequirement = req;
		this.parentPanel = parent;
		this.viewMode = vM;
		testsAdded = 0;
		// Button used to add a test and update status
		JButton buttonAddTest = new JButton("Add Test");

		// Error message field
		final JLabel error = new JLabel("");

		// Create new scroll pane for notes
		testsScroll = new JScrollPane();
		testsScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// Always show scroll bar

		// Layout manager for acceptance test panel
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();

		// Layout manager for button panel
		GridBagLayout bl = new GridBagLayout();
		JPanel buttons = new JPanel(bl);
		GridBagConstraints bc = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH; // Fill grid cell with elements
		c.anchor = GridBagConstraints.NORTH; // Anchor to top of panel
		c.weightx = 1; // Fill horizontal space
		c.weighty = 1; // Fill all the vertical space
		this.add(testsScroll, c); // Add scroll pane to panel

		bc.anchor = GridBagConstraints.WEST; // Anchor to left
		buttons.add(buttonAddTest, bc);

		bc.gridx = 1; // Column 2
		buttons.add(error, bc);

		c.fill = GridBagConstraints.NONE; // Don't fill cell
		c.anchor = GridBagConstraints.WEST; // Anchor to left of panel
		c.gridy = 1; // Row 1
		c.weighty = 0; // Do not stretch vertically
		this.add(buttons, c); // Add buttons to panel

		// Listener for addTest button
		buttonAddTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField title = new JTextField();
				JTextArea description = new JTextArea(6, 6);
				description.setWrapStyleWord(true);
				description.setLineWrap(true);
				JScrollPane dScroll = new JScrollPane(description);
				dScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				int response = 1;

				// Options for dialog box
				Object[] options = { "OK", "Cancel" };

				final JComponent[] inputs = new JComponent[] {
						new JLabel("Title"), title, new JLabel("Description"),
						dScroll };
				response = JOptionPane.showOptionDialog(null, inputs,
						"Add Acceptance Test", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

				if (response == 0) {
					if (title.getText().length() <= 0) {
						error.setText(" Title can not be blank");
					} else if (title.getText().length() > 100) {
						error.setText(" Title is too long: Max 100 characters");
					} else {
						error.setText("");

						// Set timestamp for transaction history
						currentRequirement.getHistory().setTimestamp(
								System.currentTimeMillis());

						int maxTestId = 0;
						for (int i = 0; i < currentRequirement.getTests()
								.size(); i++) {
							if (currentRequirement.getTests().get(i)
									.getId() > maxTestId) {
								maxTestId = currentRequirement.getTests()
										.get(i).getId();
							}
						}

						// Add test to requirement
						AcceptanceTest addTest = new AcceptanceTest(
								maxTestId + 1, title.getText(), description
										.getText());
						currentRequirement.addTest(addTest);

						refresh();

						// Update history panel
						parentPanel.fireRefresh();
						testsAdded++;
						// Update database so requirement stores new test
						UpdateRequirementController.getInstance()
								.updateRequirement(currentRequirement);
					}
				}
			}
		});
		
		this.refresh();
	}

	private void refresh() {

		testsScroll.setViewportView(SingleAcceptanceTestPanel.createList(currentRequirement));
	}

	@Override
	public boolean readyToRemove() {
		return testsAdded == 0 || viewMode == RequirementViewMode.EDITING;
	}

	@Override
	public void fireDeleted(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fireValid(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fireChanges(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fireRefresh() {
		this.refresh();
	}
}
