/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.ViewTaskPanel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview.WorkFlowSplitTabbedPanel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview.WorkFlowView;

// TODO: Auto-generated Javadoc
/**
 * Provides an interface for interaction with the main GUI elements All actions on GUI elements
 * should be conducted through this controller.
 *
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
public class ViewEventController {

    /** The instance. */
    private static ViewEventController instance = null;

    /** The main. */
    private MainView main = null;

    /** The workflow. */
    private WorkFlowView workflow = null;

    /** The split. */
    private WorkFlowSplitTabbedPanel split = null;

    /**
     * Default constructor for ViewEventController. Is protected to prevent instantiation.
     */
    private ViewEventController() {}

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
     * Sets the split tabbed panel.
     *
     * @param splitTabbedPanel the new split tabbed panel
     */
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
    /**
     * Close new task panel.
     */
    public void closeNewTaskPanel() {
        main.hideCreateTaskView();
    }

    /**
     * Opens a new tab for viewing the given Task
     *
     * @param task Task to be viewed
     */
    public void viewTask(Task task) {
        ViewTaskPanel taskView = new ViewTaskPanel(task);
        //taskView.
        
    }


    /**
     * Removes the tab for the given JComponent.
     */
    public void removeTab() {
        main.removeTabAt(main.getSelectedIndex());
    }

    /**
     * Removes the split tab.
     */
    public void removeSplitTab() {
        split.removeTabAt(split.getSelectedIndex());
    }

    /**
     * The following is a temporary way to refresh the task status panes from anywhere.
     *
     * @param workflow the new work flow view
     */
    public void setWorkFlowView(WorkFlowView workflow) {
        this.workflow = workflow;
    }

    /**
     * Refresh work flow view.
     */
    public void refreshWorkFlowView() {
        workflow.refresh();
    }
}
