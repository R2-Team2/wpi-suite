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


import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.buttons.ChartButtonsPanel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.buttons.EditButtonsPanel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.buttons.RequirementButtonsPanel;

/**
 * Sets up upper toolbar of RequirementManager tab
 * 
 * @author Arianna
 *
 */
public class ToolbarView extends JPanel {

	private ChartButtonsPanel chartButton = new ChartButtonsPanel();
	private EditButtonsPanel editButton = new EditButtonsPanel();
	private RequirementButtonsPanel reqButton = new RequirementButtonsPanel();
	
	/**
	 * Creates and positions option buttons in upper toolbar
	 */
	public ToolbarView(boolean visible) {

	//	this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED)); // add a border so you can see the panel
		this.setLayout(new GridLayout(1, 3));

	
		this.add("Create", reqButton);
		this.add("Edit Estimates", editButton);
		this.add("Charts", chartButton);


	}
	
	public EditButtonsPanel getEditButton(){
		return editButton;
	}
	
	public ChartButtonsPanel getChartButton() {
		return chartButton;
	}

	public RequirementButtonsPanel getReqButton() {
		return reqButton;
	}
}
