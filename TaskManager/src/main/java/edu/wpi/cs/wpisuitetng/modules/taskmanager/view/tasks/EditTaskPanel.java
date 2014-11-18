package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;

import java.awt.BorderLayout;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;

public class EditTaskPanel {
	//private WorkFlowSplitView parentPanel;
	
		private NewTaskInformationPanel infoPanel;
	    private NewTaskButtonPanel buttonPanel;
	    
		private ViewEventController viewEventController = ViewEventController.getInstance();
		
		/**
		 * Constructor for the NewTaskPanel
		 */
		public NewTaskPanel() {
			
			this.buildLayout();
			
		}
		
		/**
		 * Creates the GUI for the NewTaskPanel
		 */
		private void buildLayout() {
	        buttonPanel = new EditTaskButtonPanel(this);
	        infoPanel = new EditTaskInformationPanel(this);
	        
	        this.setLayout(new BorderLayout());
	        this.add(infoPanel, BorderLayout.CENTER);
	        this.add(buttonPanel, BorderLayout.SOUTH);
		}
}
