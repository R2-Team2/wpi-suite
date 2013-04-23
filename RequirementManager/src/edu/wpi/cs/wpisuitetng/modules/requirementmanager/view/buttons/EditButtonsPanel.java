/**
 *  * Copyright (c) 2013 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 */
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EtchedBorder;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;

/**
 * @author Kevin
 *
 */
public class EditButtonsPanel extends JPanel{
	
	public EditButtonsPanel(){
		
		setBorder(BorderFactory.createTitledBorder("Edit Estimates")); // add a border so you can see the panel
		
		SpringLayout editButtonsLayout = new SpringLayout();
		this.setLayout(editButtonsLayout);

		final JButton createEditButton = new JButton("Edit Estimates");
		final JButton createCancelButton = new JButton("Cancel Changes");
		
		createCancelButton.setVisible(false);
		
		createEditButton.setVisible(true);
		// the action listener for the Edit Estimates button
		createEditButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// check to see if any other tab is currently open
				if (ViewEventController.getInstance().getMainView().getTabCount() == 1) {
					// toggle the editing overview table mode
					ViewEventController.getInstance().toggleEditingTable(false);
					// edits the Edit Button text based on whether in editing overview table mode or not
					if (ViewEventController.getInstance().getOverviewTable().getEditFlag()) {
						createEditButton.setText("Save Changes");
						createCancelButton.setVisible(true);
					}	
					else {
						createEditButton.setText("Edit Estimates");
						createCancelButton.setVisible(false);
					}
				}
			}
		});
		
		createCancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// toggle the editing overview table mode
				ViewEventController.getInstance().toggleEditingTable(true);

				createEditButton.setText("Edit Estimates");
				createCancelButton.setVisible(false);

			}
		});
		
		editButtonsLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, createEditButton, 0,SpringLayout.HORIZONTAL_CENTER, this);
		editButtonsLayout.putConstraint(SpringLayout.NORTH, createEditButton, 5,SpringLayout.NORTH, this);
		this.add(createEditButton);
		
		editButtonsLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, createCancelButton, 0,SpringLayout.HORIZONTAL_CENTER, createEditButton);
		editButtonsLayout.putConstraint(SpringLayout.NORTH, createCancelButton, 5, SpringLayout.SOUTH, createEditButton);
		this.add(createCancelButton);
	}
	
	public JButton getEditButton() {
		return this.getEditButton();
	}
}
