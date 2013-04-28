/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.buttons;

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
 * @author justinhess
 * @version $Revision: 1.0 $
 */
public class RequirementButtonsPanel extends JPanel{
	
	// initialize the main view toolbar buttons
		private JButton createButton = new JButton("Create Requirement");
		
		private final JButton createIterationButton = new JButton("Create Iteration");
	
	public RequirementButtonsPanel(){
		setBorder(BorderFactory.createTitledBorder("Create")); // add a border so you can see the panel
		
		SpringLayout toolbarLayout = new SpringLayout();
		this.setLayout(toolbarLayout);
		
		createButton.setIcon(new ImageIcon("../RequirementManager/resources/new-requirement-icon.png"));
		createButton.setHorizontalTextPosition(AbstractButton.CENTER);
		createButton.setVerticalTextPosition(AbstractButton.BOTTOM);
		
		createIterationButton.setIcon(new ImageIcon("../RequirementManager/resources/new-iteration-icon.png"));
		createIterationButton.setHorizontalTextPosition(AbstractButton.CENTER);
		createIterationButton.setVerticalTextPosition(AbstractButton.BOTTOM);

		// the action listener for the Create Requirement Button
		createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// bring up a create requirement pane if not in Multiple Requirement Editing Mode
				//if (!ViewEventController.getInstance().getOverviewTable().getEditFlag()) {
					ViewEventController.getInstance().createRequirement();
			//	}
			}
		});		
		
		//action listener for the Create Iteration Button
		createIterationButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//if (!ViewEventController.getInstance().getOverviewTable().getEditFlag()) {
					ViewEventController.getInstance().createIteration();
				}
		//	}
		});
		
		toolbarLayout.putConstraint(SpringLayout.WEST, createButton, 15,SpringLayout.WEST, this);
		toolbarLayout.putConstraint(SpringLayout.WEST, createIterationButton, 30, SpringLayout.EAST, createButton);
		
		this.add(createButton);
		this.add(createIterationButton);
	}
	/**
	 * Method getCreateButton.
	
	 * @return JButton */
	public JButton getCreateButton() {
		return createButton;
	}

	/**
	 * Method getCreateIterationButton.
	
	 * @return JButton */
	public JButton getCreateIterationButton() {
		return createIterationButton;
	}

	
}
