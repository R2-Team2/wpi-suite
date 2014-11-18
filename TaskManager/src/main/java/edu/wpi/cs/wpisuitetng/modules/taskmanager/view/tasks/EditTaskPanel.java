package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;

import java.awt.BorderLayout;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.AddTaskController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;

public class EditTaskPanel extends AbstractTaskPanel {
		//protected EditTaskPanel parentPanel;

		//infoPanel = new EditTaskInformationPanel(this);
		
		/**
		 * Constructor for the NewTaskPanel
		 * @return 
		 */
		public EditTaskPanel() {
			super();
			
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
			// create a task, send to to controller
//			new AddTaskController(this);
			//AddTaskController addNewTask = new AddTaskController(this);
			//addNewTask.addTask();
			// TODO: create task card
			// TODO: put task card in proper task status
			//ViewEventController.getInstance().closeNewTaskPanel();
//			parentPanel.hideCreateNewTaskPanel();	
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
