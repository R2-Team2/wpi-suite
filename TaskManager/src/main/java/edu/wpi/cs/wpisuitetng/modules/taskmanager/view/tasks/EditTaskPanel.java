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
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.AddTaskController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.AddTaskRequestObserver;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.TaskController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.taskstatus.TaskStatus;
import edu.wpi.cs.wpisuitetng.network.Network;
import edu.wpi.cs.wpisuitetng.network.Request;
import edu.wpi.cs.wpisuitetng.network.models.HttpMethod;



// TODO: Auto-generated Javadoc
/**
 * The Class EditTaskPanel.
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
public class EditTaskPanel extends AbstractTaskPanel {

		//protected EditTaskPanel parentPanel;

		//infoPanel = new EditTaskInformationPanel(this);
		
		/**
		 * Constructor for the NewTaskPanel
		 * @return 
		 */
		public EditTaskPanel() {	
			this.buildLayout();
		}
		
		/**
		 * Creates the GUI for the NewTaskPanel
		 */
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
			int taskID = this.aTask.getTaskID();
			String title = this.getTitle();
			String description = this.getDescription();
			int estimatedEffort = this.getEstimatedEffort();
			int actualEffort = this.getActualEffort();
			String status = this.getStatus();
			String requirement = this.getRequirement();
			Date startDate = this.getStartDate();
			Date dueDate = this.getDueDate();
			List<User> assignedUsers = this.getAssignedUsers();
			System.out.println("Inside AddTaskController");
			
			Task toSave = new Task(taskID, title, description, estimatedEffort, actualEffort, new TaskStatus(status), requirement, startDate, dueDate, assignedUsers);

			TaskController saveTask = new TaskController(this);
			saveTask.updateTask(toSave);
			// TODO: create task card
			// TODO: put task card in proper task status
			ViewEventController.getInstance().closeNewTaskPanel();
		}
		
		
		/**
		 * Creates the GUI for the EditTaskPanel
		 * @param Task
		 */
		public void openEditView(Task aTask)
		{
			this.aTask = aTask;
			this.infoPanel.setTask(aTask);
		}

    /**
     * Constructor for the NewTaskPanel.
     */
    public EditTaskPanel() {
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

    /*
     * (non-Javadoc)
     * @see edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.AbstractTaskPanel#createPressed()
     */
    @Override
    public void createPressed() {
        // TODO Auto-generated method stub

    }
}
