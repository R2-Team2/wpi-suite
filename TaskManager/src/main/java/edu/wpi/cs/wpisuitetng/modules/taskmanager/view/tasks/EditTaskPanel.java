package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;

import java.awt.BorderLayout;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;

public class EditTaskPanel extends AbstractTaskPanel {
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

		@Override
		public void createPressed() {
			// TODO Auto-generated method stub
			
		}
}
