/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 *  Team R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.MainView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.NewTaskPanel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview.WorkFlowView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview.WorkFlowSplitTabbedPanel;

/**
 * Provides an interface for interaction with the main GUI elements
 * All actions on GUI elements should be conducted through this controller.
 */
public class ViewEventController {
	
	 private static ViewEventController instance = null;
	 private MainView main = null;
	 private WorkFlowView workflow = null;
	 private WorkFlowSplitTabbedPanel split = null;
	
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
    
    public void setSplitTabbedPanel(WorkFlowSplitTabbedPanel splitTabbedPanel) {
    	split = splitTabbedPanel;
    }
    
    /**
     * Opens a new tab for the creation of a requirement.
     */
    public void createTask() {
    	main.showCreateTaskView();
        
    }
    
    /*
     * Removes the current tab
     */
    public void closeNewTaskPanel() {
    	main.hideCreateTaskView();
    }
    

    /**
     * Removes the tab for the given JComponent
     * 
     * @param comp the component to remove
     */
    public void removeTab() {
        main.removeTabAt(main.getSelectedIndex());
    }
    
    public void removeSplitTab() {
    	split.removeTabAt(split.getSelectedIndex());
    }
    
    /**
     * The following is a temporary way to refresh the task status panes from anywhere
     */
    public void setWorkFlowView(WorkFlowView workflow) {
    	this.workflow = workflow;
    }
    public void refreshWorkFlowView() {
    	workflow.refresh();
    }
}
