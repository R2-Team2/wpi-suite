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

import edu.wpi.cs.wpisuitetng.janeway.gui.container.toolbar.DefaultToolbarView;
import edu.wpi.cs.wpisuitetng.janeway.gui.container.toolbar.ToolbarGroupView;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.buttons.ChartButtonsPanel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.buttons.EditButtonsPanel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.buttons.RequirementButtonsPanel;

/**
 * Sets up upper toolbar of RequirementManager tab
 * 
 *
 * @version $Revision: 1.0 $
 * @author justinhess
 */
public class ToolbarView  extends DefaultToolbarView {

	public ChartButtonsPanel chartButton = new ChartButtonsPanel();
	public EditButtonsPanel editButton = new EditButtonsPanel();
	public RequirementButtonsPanel reqButton = new RequirementButtonsPanel();
	
	/**
	 * Creates and positions option buttons in upper toolbar
	 * @param visible boolean
	 */
	public ToolbarView(boolean visible) {

		this.addGroup(reqButton);
		this.addGroup(chartButton);
		this.addGroup(editButton);

	}
	
	/**
	 * Method getEditButton.
	
	 * @return EditButtonsPanel */
	public EditButtonsPanel getEditButton(){
		return editButton;
	}
	
	/**
	 * Method getChartButton.
	
	 * @return ChartButtonsPanel */
	public ChartButtonsPanel getChartButton() {
		return chartButton;
	}

	/**
	 * Method getReqButton.
	
	 * @return RequirementButtonsPanel */
	public RequirementButtonsPanel getReqButton() {
		return reqButton;
	}
}
