/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;

import java.awt.BorderLayout;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.TaskController;
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
		 * Constructor for the NewTaskPanel
		 * @return 
		 */
		public EditTaskPanel(WorkFlowSplitTabbedPanel theParent) {
			super(theParent);
			this.buildLayout();
			this.parentPanel = theParent;
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
	        // create a task, send to to controller
	        final TaskController updateTask = new TaskController(this);
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
	    public void cancelPressed() {
	        ViewEventController.getInstance().removeSplitTab();
	        parentPanel.checkForHide();
	    }
		
		/**
		 * Creates the GUI for the EditTaskPanel
		 * @param Task
		 */
		public void openEditView(Task aTask)
		{
			this.infoPanel.setTask(aTask);
		}
}

