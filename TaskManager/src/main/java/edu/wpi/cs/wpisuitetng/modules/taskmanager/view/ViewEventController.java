/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors: Team R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.tree.DefaultMutableTreeNode;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.MainView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.NewTaskPanel;


/**
 * Provides an interface for interaction with the main GUI elements
 * All actions on GUI elements should be conducted through this controller.
 * 
 */
public class ViewEventController {
	
	 private static ViewEventController instance = null;
	 private MainView main = null;
	
    /**
     * Default constructor for ViewEventController. Is protected to prevent
     * instantiation.
     */
    protected ViewEventController() {
    }

    /**
     * Returns the singleton instance of the vieweventcontroller.
     * 
     * @return The instance of this controller.
     */
    public static ViewEventController getInstance() {
        if (instance == null) {
            instance = new ViewEventController();
        }
        return instance;
    }
	
    /**
     * Sets the main view to the given view.
     * 
     * @param mainview MainView
     */
    public void setMainView(MainView mainview) {
        main = mainview;
    }
    
    /**
     * Opens a new tab for the creation of a requirement.
     */
    public void createTask() {
    	NewTaskPanel newTask = new NewTaskPanel();
        
    	main.addTab("New Task", null, newTask, "New Task");
        main.invalidate(); //force the tabbedpane to redraw.
        main.repaint();
        main.setSelectedComponent(newTask);
    }
    
    public void removeTab() {
    	main.removeTabAt(main.getSelectedIndex());
    }
}
