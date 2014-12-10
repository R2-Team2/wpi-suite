/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;

import java.awt.BorderLayout;
import java.util.Date;
import java.util.List;

import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.AddTaskController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.UpdateTaskStatusController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.TaskStatus;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview.WorkFlowSplitTabbedPanel;

/**
 * The Class NewTaskPanel.
 *
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
@SuppressWarnings("serial")
public class NewTaskPanel extends AbstractTaskPanel {

    /**
     * Constructor for the NewTaskPanel.
     */
    public NewTaskPanel() {


        buildLayout();

    }

    /**
     * Constructor for the NewTaskPanel.
     *
     * @param parentPanel the parent panel
     */
    public NewTaskPanel(WorkFlowSplitTabbedPanel parentPanel) {
        super(parentPanel);
        this.parentPanel = parentPanel;
        buildLayout();

    }

    /**
     * Creates the GUI for the NewTaskPanel.
     */
    @Override
    protected void buildLayout() {
        buttonPanel = new NewTaskButtonPanel(this);
        infoPanel = new NewTaskInformationPanel(this);

        setLayout(new BorderLayout());
        this.add(infoPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Called when the Create Button is pressed Creates a Task from the NewTask Info.
     *
     * @throws WPISuiteException throws exception if task status is unknown.
     */
    @Override
    public void createPressed() throws WPISuiteException {
        // create a task, send to to controller
        final AddTaskController addNewTask = new AddTaskController(this);
        addNewTask.addTask();

        updateTaskStatus(infoPanel.getTask().getStatus().getName(), infoPanel.getTask());

        ViewEventController.getInstance().removeSplitTab();
        ViewEventController.getInstance().refreshWorkFlowView();
        parentPanel.checkForHide();
    }

    /**
     * Updates TaskStatusObjects with references to new task objects
     *
     * @param name the Name of the Task Status
     * @param aTask the task being added to the taskStatus object
     */
    public void updateTaskStatus(String name, Task aTask) throws WPISuiteException {
        TaskStatus updatedTS = null;
        for (int i = 0; i < infoPanel.listOfStatuses.length; i++) {
            if (infoPanel.listOfStatuses[i] == name) {
                updatedTS = viewEventController.getWorkflow().getStatuses().get(i).addTask(aTask);
            }
        }
        if (updatedTS != null) {
            UpdateTaskStatusController tsController = new UpdateTaskStatusController();
            tsController.updateTask(updatedTS);
        } else
            throw new WPISuiteException("Unknown Task Status Selected");
    }

    /**
     * Called when the Cancel Button is pressed Closes out the NewTask Tab.
     */
    @Override
    public void cancelPressed() {
        ViewEventController.getInstance().removeSplitTab();
        parentPanel.checkForHide();
    }

    /**
     * Returns the title information from infoPanel.
     *
     * @return String
     */
    @Override
    public String getTitle() {
        return infoPanel.getTitle().getText();
    }

    /**
     * Returns the description information from infoPanel.
     *
     * @return String
     */
    @Override
    public String getDescription() {
        return infoPanel.getDescription().getText();
    }

    /**
     * Retrieves the Estimated Effort from infoPanel.
     *
     * @return int
     */
    @Override
    public int getEstimatedEffort() {
        return (int) infoPanel.getEstimatedEffort().getValue();
    }

    /**
     * Retrieves the Actual Effort from infoPanel.
     *
     * @return int
     */
    @Override
    public int getActualEffort() {
        return (int) infoPanel.getActualEffort().getValue();
    }

    /**
     * Retrieves the Status from infoPanel.
     *
     * @return String
     */
    @Override
    public String getStatus() {
        return infoPanel.getStatus().getSelectedItem().toString();
    }

    /**
     * Retrieves the Requirement from infoPanel.
     *
     * @return String
     */
    @Override
    public String getRequirement() {
        return (String) infoPanel.getRequirement().getSelectedItem();
    }

    /**
     * Retrieves the StartDate from infoPanel.
     *
     * @return Date
     */
    @Override
    public Date getStartDate() {
        return infoPanel.getStartDate();
    }

    /**
     * Retrieves the DueDate from infoPanel.
     *
     * @return Date
     */
    @Override
    public Date getDueDate() {
        return infoPanel.getDueDate();
    }

    /**
     * Retrieves the Chosen Members from infoPanel.
     *
     * @return String[]
     */
    @Override
    public List<User> getAssignedUsers() {
        return infoPanel.getAssignedUsers();
    }

    @Override
    public void setInfoPanel(NewTaskInformationPanel aPanel) {
        infoPanel = aPanel;
    }

    /**
     * Sets the butt panel.
     *
     * @param aPanel the new butt panel
     */
    public void setButtPanel(NewTaskButtonPanel aPanel) {
        buttonPanel = aPanel;
    }
}
