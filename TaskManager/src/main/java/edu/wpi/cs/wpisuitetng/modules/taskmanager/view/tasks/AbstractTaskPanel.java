/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * 	Team R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;

import java.awt.BorderLayout;
import java.util.Date;
import java.util.List;

import javax.swing.JPanel;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.AddTaskController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview.WorkFlowSplitTabbedPanel;

/**
 * The Class AbstractTaskPanel.
 */
public abstract class AbstractTaskPanel extends JPanel {
		
		protected WorkFlowSplitTabbedPanel parentPanel;
		protected AbstractInformationPanel infoPanel;
	    protected AbstractButtonPanel buttonPanel;
		private ViewEventController viewEventController = ViewEventController.getInstance();
		
		public AbstractTaskPanel() {
			
		}
		
		public AbstractTaskPanel(WorkFlowSplitTabbedPanel parentPanel) {
			this.parentPanel = parentPanel;
		}
		
		/**
		 * Creates the GUI for the NewTaskPanel
		 */
		protected void buildLayout()
		{
			this.setLayout(new BorderLayout());
			this.add(buttonPanel, BorderLayout.SOUTH);
	        this.add(infoPanel, BorderLayout.CENTER);
		}
		
		/**
		 * Called when the Create Button is pressed
		 * Creates a Task from the NewTask Info
		 */
		public abstract void createPressed();
		
		/**
		 * Called when the Cancel Button is pressed
		 * Closes out the NewTask Tab
		 */
		public void cancelPressed() {
			ViewEventController.getInstance().removeSplitTab();
			parentPanel.checkForHide();
		}
		
		/**
		 * Returns the title information from infoPanel
		 * @return String
		 */
		public String getTitle() {
			return infoPanel.getTitle().getText();
		}
		
		/**
		 * Returns the description information from infoPanel
		 * @return String
		 */
		public String getDescription() {
			return infoPanel.getDescription().getText();
		}
		
		/**
		 * Retrieves the Estimated Effort from infoPanel
		 * @return int
		 */
		public int getEstimatedEffort(){
			return (int)infoPanel.getEstimatedEffort().getValue();
		}
		
		/**
		 * Retrieves the Actual Effort from infoPanel
		 * @return int
		 */
		public int getActualEffort(){
			return (int)infoPanel.getActualEffort().getValue();
		}
		
		/**
		 * Retrieves the Status from infoPanel
		 * @return String
		 */
		public String getStatus() {
			return infoPanel.getStatus().getSelectedItem().toString();
		}
		
		/**
		 * Retrieves the Requirement from infoPanel
		 * @return String
		 */
		public String getRequirement() {
			return (String)infoPanel.getRequirement().getSelectedItem();
		}
		
		/**
		 * Retrieves the StartDate from infoPanel
		 * @return Date
		 */
		public Date getStartDate() {
			return infoPanel.getStartDate().getDate();
		}
		
		/**
		 * Retrieves the DueDate from infoPanel
		 * @return Date
		 */
		public Date getDueDate() {
			return infoPanel.getDueDate().getDate();
		}
		
		/**
		 * Retrieves the Chosen Members from infoPanel
		 * @return String[]
		 */
		public List<User> getAssignedUsers() {
			return infoPanel.getAssignedUsers();
		}
		
		public void setInfoPanel(NewTaskInformationPanel aPanel)
		{
			this.infoPanel = aPanel;
		}
		
}