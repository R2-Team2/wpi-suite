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

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.UpdateTaskController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview.WorkFlowSplitTabbedPanel;


// TODO: Auto-generated Javadoc
/**
 * The Class EditTaskPanel.
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
public class EditTaskPanel extends AbstractTaskPanel {

    /**
     * Constructor for the NewTaskPanel.
     */
    public EditTaskPanel(WorkFlowSplitTabbedPanel parent, Task editTask) {
    	super();
		this.parentPanel = parent;
    	this.aTask = editTask;
    	this.title = editTask.getTitle();
        this.buildLayout();
    }

    /**
     * Creates the GUI for the NewTaskPanel.
     */
    @Override
    protected void buildLayout() {
        buttonPanel = new EditTaskButtonPanel(this);
        infoPanel = new EditTaskInformationPanel(this);

        this.setLayout(new BorderLayout());
        this.add(infoPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

	/**
	 * Called when the Save Button is pressed
	 * Loads data into the database in the existing Task.
	 */
	public void savePressed()
	{

        // create a task, send to to controller
        final UpdateTaskController updateTask = new UpdateTaskController(this);
        updateTask.updateTask(this.aTask);
        // RetrieveTasksController retrieveTasks = new RetrieveTasksController();
        // retrieveTasks.requestTasks();
        // TODO: create task card
        // TODO: put task card in proper task status
        ViewEventController.getInstance().removeSplitTab();
        parentPanel.checkForHide();
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
        return infoPanel.getStartDate().getDate();
    }

    /**
     * Retrieves the DueDate from infoPanel.
     *
     * @return Date
     */
    @Override
    public Date getDueDate() {
        return infoPanel.getDueDate().getDate();
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

    /*
     * (non-Javadoc)
     * @see
     * edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.AbstractTaskPanel#setInfoPanel(edu.
     * wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.NewTaskInformationPanel)
     */
    public void setInfoPanel(AbstractInformationPanel aPanel)
    {
        infoPanel = aPanel;
    }

    /**
     * Sets the butt panel.
     *
     * @param aPanel the new butt panel
     */
    public void setButtPanel(NewTaskButtonPanel aPanel)
    {
        buttonPanel = aPanel;
    }

	@Override
	public void createPressed() {
		// TODO Auto-generated method stub
		
	}
}
