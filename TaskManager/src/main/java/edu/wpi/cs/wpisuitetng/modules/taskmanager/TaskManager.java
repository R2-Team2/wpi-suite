/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * Chris Casola
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.taskmanager;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import edu.wpi.cs.wpisuitetng.janeway.modules.IJanewayModule;
import edu.wpi.cs.wpisuitetng.janeway.modules.JanewayTabModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.MainView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ToolbarView;

/**
 * This is a blank module that will become the Task Manager.
 * It currently contains a blank toolbar and a blank main view.
 */
public class TaskManager implements IJanewayModule {
    
    /**
     * A list of tabs owned by this module
     */
    List<JanewayTabModel> tabs;
    /**
     * The toolbar view
     */
    ToolbarView toolbarView;
    /**
     * The main view
     */
    MainView mainView;
    /**
     * Task Manager only uses one tab
     */
    JanewayTabModel tab1;
    
    /**
     * Constructs the main views for this module. Namely one tab, containing
     * a toolbar and a main content panel.
     */
    public TaskManager() {
        
        // Initialize the list of tabs (however, this module has only one tab)
        this.tabs = new ArrayList<JanewayTabModel>();
        
        // Create a JPanel to hold the toolbar for the tab
        this.toolbarView = new ToolbarView();
        
        // Constructs and adds the MainPanel
        this.mainView = new MainView();
        
        // Create a tab model that contains the toolbar panel and the main content panel
        this.tab1 = new JanewayTabModel(getName(), new ImageIcon(), this.toolbarView, this.mainView);
        
        // Add the tab to the list of tabs owned by this module
        this.tabs.add(tab1);
    }
    
    /*
     * @see edu.wpi.cs.wpisuitetng.janeway.modules.IJanewayModule#getName()
     */
    @Override
    public String getName() {
        return "Task Manager";
    }
    
    /*
     * @see edu.wpi.cs.wpisuitetng.janeway.modules.IJanewayModule#getTabs()
     */
    @Override
    public List<JanewayTabModel> getTabs() {
        return tabs;
    }
}
