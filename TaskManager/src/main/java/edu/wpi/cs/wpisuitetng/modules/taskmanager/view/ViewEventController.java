/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view;


import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.settings.WorkFlowEditView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.ViewTaskPanel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.taskstatus.TaskStatusView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview.WorkFlowSplitTabbedPanel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview.WorkFlowView;

/**
 * Provides an interface for interaction with the main GUI elements All actions on GUI elements
 * should be conducted through this controller.
 *
 * @version $Revision: 1.0 $
 * @author R2-Team2
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

    /** The settings panel. */
    private final WorkFlowEditView settingsPanel = new WorkFlowEditView();

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
     * opens requirement module tab
     */
    public void openRequirementsTab() {
        final JTabbedPane mainWindow = (JTabbedPane) ((JPanel) main.getParent()).getParent();
        for (int i = 0; i < mainWindow.getTabCount(); i++) {
            if (mainWindow.getTitleAt(i).equals("Requirement Manager")) {
                mainWindow.setSelectedIndex(i);
                break;
            }
        }
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

    /**
     * Edits the work flow view.
     */
    public void editWorkFlowView() {
        main.editWorkFlowView();

    }

    /**
     * Filters task cards considering title, description, assignee, requirement and archived tasks.
     *
     * @param filterString search string
     * @param description true if should search through description
     * @param requirement true if should search through requirement
     * @param assignee true if should search through assignee
     * @param archived true if should search through archived tasks
     */
    public void filterTasksWithParameters(String filterString, boolean description,
            boolean requirement, boolean assignee, boolean archived) {
        workflow.filterWithParameters(filterString, description, requirement, assignee, archived);
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
     * Opens a new tab for viewing the given Task.
     *
     * @param task Task to be viewed
     */
    public void viewTask(Task task) {
        final WorkFlowSplitTabbedPanel viewParent = main.getWF().getWF();
        final ViewTaskPanel taskView = new ViewTaskPanel(viewParent, task);
        main.showViewTaskView(taskView);
    }

    /**
     * revalidates UI
     */
    public void revalidateAll() {
        main.resetPreferedSize();
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

    /**
     * Passes values to the current workflow's method showArchived
     * @param b Boolean
     * @param t TaskStatusView
     */
    public void showArchived(Boolean b, TaskStatusView t) {
        // TODO Auto-generated method stub
        workflow.showArchived(b, t);

    }
}
