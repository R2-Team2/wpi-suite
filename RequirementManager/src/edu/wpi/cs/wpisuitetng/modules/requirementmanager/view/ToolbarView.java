/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EtchedBorder;

/**
 * Sets up upper toolbar of RequirementManager tab
 * 
 * @author Arianna
 *
 */
public class ToolbarView extends JPanel {

	/**
	 * Creates and positions option buttons in upper toolbar
	 */
	public ToolbarView() {
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED)); // add a border so you can see the panel
		
		SpringLayout toolbarLayout = new SpringLayout();
		this.setLayout(toolbarLayout);
		
		JButton createButton = new JButton("Create Requirement");
		createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewEventController.getInstance().createRequirement();
				
			}
		});
		
		toolbarLayout.putConstraint(SpringLayout.NORTH, createButton, 25,SpringLayout.NORTH, this);
		toolbarLayout.putConstraint(SpringLayout.WEST, createButton, 50, SpringLayout.WEST, this);
		this.add(createButton);
	}
}
