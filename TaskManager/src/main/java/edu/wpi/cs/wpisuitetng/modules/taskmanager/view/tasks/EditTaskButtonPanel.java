/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


// TODO: Auto-generated Javadoc
/**
 * The Class EditTaskButtonPanel.
 *
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
@SuppressWarnings("serial")
public class EditTaskButtonPanel extends AbstractButtonPanel {

	protected EditTaskPanel parentPanel;

	/**
	 * Constructor for the EditTaskButtonPanel.
	 *
	 * @param parentPanel the parent panel
	 */
	public EditTaskButtonPanel(EditTaskPanel parentPanel) {
		// Set Panel Layout
		setLayout(new FlowLayout(FlowLayout.LEFT));
		// Set Parent Panel
		this.parentPanel = parentPanel;
		// Set Button Messages
		final String saveString = "Save";
		final String cancelString = "Cancel";
		// Create Buttons
		buttonSave = new JButton(saveString);
		buttonCancel = new JButton(cancelString);
		this.add(buttonSave);
		this.add(buttonCancel);
		// parentPanel.createPressed();

		setupListeners();
	}

	/**
	 * Sets up listeners for the edit task panel, and the edit buttons.
	 */
	protected void setupListeners() {
		buttonSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parentPanel.savePressed();
			}
		});

		buttonCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parentPanel.cancelPressed();
			}

		});
	}

	/**
	 * Validate task info.
	 */
	@Override
	public boolean validateTaskInfo() {
		if (parentPanel.infoPanel.boxTitle.getText().trim().length() <= 0
				|| parentPanel.infoPanel.boxDescription.getText().trim().length() <= 0
				|| (!((String) parentPanel.infoPanel.dropdownStatus.getSelectedItem())
						.equals("New") && parentPanel.infoPanel.listChosenAssignees.getModel()
						.getSize() == 0)) {
			buttonSave.setEnabled(false);
			return false;
		} else {
			buttonSave.setEnabled(true);
			return true;
		}
	}


	/**
	 * Validate task dates
	 */
	@Override
	public void validateTaskDate() {
		if (parentPanel.infoPanel.getDueDate().before(parentPanel.infoPanel.getStartDate())) {
			parentPanel.infoPanel.labelDueDate
			.setText("<html>Due Date: <font color='CC0000'>Preceeds Start Date</font></html>");
		} else {
			parentPanel.infoPanel.labelDueDate.setText("Due Date: ");
		}
	}

}
