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

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EtchedBorder;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;

/**
 *
 * @version $Revision: 1.0 $
 * @author justinhess
 */
public class EditButtonsPanel extends JPanel{
	
	JButton createEditButton = new JButton("Edit Estimates");
	
	/**
	 *  disables the Edit Estimates/SaveChanges button 
	 */
	public void disableCreateEditButton() {
		createEditButton.setEnabled(false);
	}
	
	/**
	 *  enables the Edit Estimates/SaveChanges button 
	 */
	public void enableCreateEditButton() {
		createEditButton.setEnabled(true);
	}

	public EditButtonsPanel(){
		
		setBorder(BorderFactory.createTitledBorder("Edit Estimates")); // add a border so you can see the panel
		
		SpringLayout editButtonsLayout = new SpringLayout();
		this.setLayout(editButtonsLayout);

		final JButton createCancelButton = new JButton("Cancel Changes");
		
		createCancelButton.setVisible(false);
		
		createEditButton.setIcon(new ImageIcon("../RequirementManager/resources/edit-estimates-icon.png"));
		createEditButton.setHorizontalTextPosition(AbstractButton.CENTER);
		createEditButton.setVerticalTextPosition(AbstractButton.BOTTOM);
		
		createCancelButton.setIcon(new ImageIcon("../RequirementManager/resources/cancel-icon.png"));
		createCancelButton.setHorizontalTextPosition(AbstractButton.CENTER);
		createCancelButton.setVerticalTextPosition(AbstractButton.BOTTOM);
		
		createEditButton.setVisible(true);
		// the action listener for the Edit Estimates button
		createEditButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// check to see if any other tab is currently open
				// if (ViewEventController.getInstance().getMainView().getTabCount() == 1) {
				
					// toggle the editing overview table mode
					ViewEventController.getInstance().toggleEditingTable(false);
					// edits the Edit Button text based on whether in editing overview table mode or not
					if (ViewEventController.getInstance().getOverviewTable().getEditFlag()) {
						ViewEventController.getInstance().getOverviewTable().repaint();
						createEditButton.setText("Save Changes");
						createEditButton.setIcon(new ImageIcon("../RequirementManager/resources/save-icon.png"));
						createEditButton.setEnabled(false);
						createCancelButton.setVisible(true);												
					}	
					else {
						createEditButton.setText("Edit Estimates");
						createEditButton.setIcon(new ImageIcon("../RequirementManager/resources/edit-estimates-icon.png"));
						createEditButton.setEnabled(true);
						createCancelButton.setVisible(false);
					}
				}
			//}
		});
		
		createCancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// toggle the editing overview table mode
				ViewEventController.getInstance().toggleEditingTable(true);			
				createEditButton.setText("Edit Estimates");
				createEditButton.setIcon(new ImageIcon("../RequirementManager/resources/edit-estimates-icon.png"));
				createEditButton.setEnabled(true);
				createCancelButton.setVisible(false);

			}
		});
		
		editButtonsLayout.putConstraint(SpringLayout.WEST, createEditButton, 35,SpringLayout.WEST, this);
		editButtonsLayout.putConstraint(SpringLayout.WEST, createCancelButton, 30, SpringLayout.EAST, createEditButton);
		
		
		this.add(createEditButton);
		this.add(createCancelButton);
	}
	
	/**
	 * Method getEditButton.
	
	 * @return JButton */
	public JButton getEditButton() {
		return createEditButton;
	}
}
