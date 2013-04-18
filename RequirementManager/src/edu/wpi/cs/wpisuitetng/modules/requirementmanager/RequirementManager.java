/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import edu.wpi.cs.wpisuitetng.janeway.modules.IJanewayModule;
import edu.wpi.cs.wpisuitetng.janeway.modules.JanewayTabModel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.MainView;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ToolbarView;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;


public class RequirementManager implements IJanewayModule {

	private List<JanewayTabModel> tabs;
	
	/** 
	 * Constructor for Requirement Manager. Creates a main view that contains a
	 *  toolbar on the top for each sub-tab. 
	 */
	public RequirementManager() {
		tabs = new ArrayList<JanewayTabModel>();

		ToolbarView toolBar = new ToolbarView();
		MainView mainPanel = new MainView();		

		ViewEventController.getInstance().setMainView(mainPanel);
		ViewEventController.getInstance().setToolBar(toolBar);

		// Create a tab model that contains the toolbar panel and the main content panel
		JanewayTabModel tab1 = new JanewayTabModel(getName(), new ImageIcon(), toolBar, mainPanel);

		// Add the tab to the list of tabs owned by this module
		tabs.add(tab1);
	}
	
	/**
	 * Returns the name of the Requirement manager tab.
	 */
	@Override
	public String getName() {
		return "Requirement Manager";
	}

	/**
	 * Returns the tabs that make up the requirement manager.
	 */
	@Override
	public List<JanewayTabModel> getTabs() {
		return tabs;
	}

}

