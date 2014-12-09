/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.taskmanager;



import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import edu.wpi.cs.wpisuitetng.janeway.modules.IJanewayModule;
import edu.wpi.cs.wpisuitetng.janeway.modules.JanewayTabModel;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.MainView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ToolbarView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;

// TODO: Auto-generated Javadoc
/**
 * This is a blank module that will become the Task Manager. It currently contains a blank toolbar
 * and a blank main view.
 * 
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
public class TaskManager implements IJanewayModule {

    /** A list of tabs owned by this module. */
    List<JanewayTabModel> tabs;

    /** The toolbar view. */
    ToolbarView toolbarView;

    /** The main view. */
    MainView mainView;

    /** Task Manager only uses one tab. */
    JanewayTabModel tab1;

    /**
     * Constructs the main views for this module. Namely one tab, containing a toolbar and a main
     * content panel.
     */
    public TaskManager() {

        // Initialize the list of tabs (however, this module has only one tab)
        tabs = new ArrayList<JanewayTabModel>();

        // Create a JPanel to hold the toolbar for the tab
        toolbarView = new ToolbarView();

        // Constructs and adds the MainPanel
        mainView = new MainView();

        // Create a tab model that contains the toolbar panel and the main content panel
        tab1 = new JanewayTabModel(getName(), new ImageIcon(), toolbarView, mainView);

        // Add the tab to the list of tabs owned by this module
        tabs.add(tab1);
        
        ViewEventController.getInstance().setMainView(mainView);
    }
    
    @Override
    public String getName() {
        return "Task Manager";
    }

    @Override
    public List<JanewayTabModel> getTabs() {
        return tabs;
    }
}
