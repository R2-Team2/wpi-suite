/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.iterations;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.controller.UpdateRequirementController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.RequirementSelector;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements.RequirementSelectorMode;

/**
 * 
 *
 * @author justinhess
 * @version $Revision: 1.0 $
 */
public class IterationRequirementsPanel extends JPanel {
	private JButton addRequirementButton;
	private JButton removeButton;
	private JTable requirementTable;
	
	private RequirementSelector reqSelector;

	public IterationRequirementsPanel() {
		this.setLayout(new BorderLayout());
		
		reqSelector = new RequirementSelector(null, null, RequirementSelectorMode.ITERATION, false);
		
		JScrollPane scroll = new JScrollPane();
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// Always show scroll bar
		
		addRequirementButton = new JButton("Add Requirement");
		addRequirementButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO: Add requirement to iteration		
			}
		});
		
		removeButton = new JButton("Remove Requirement");
		removeButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO: Remove requirement from iteration
			}
		});
		removeButton.setEnabled(false);
		
		this.add(scroll, BorderLayout.CENTER); // Add scroll pane to panel
		
		reqSelector.addButton(1,addRequirementButton);
		reqSelector.addButton(0,removeButton);
		this.add(reqSelector,BorderLayout.SOUTH);
		
		requirementTable = new JTable();
		scroll.setViewportView(requirementTable);
	}
}
