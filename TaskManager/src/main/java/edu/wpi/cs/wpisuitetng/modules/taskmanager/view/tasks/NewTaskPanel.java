/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors: Team R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;

import java.awt.BorderLayout;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.AddTaskController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.TempPanel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview.WorkFlowSplitTabbedPanel;

@SuppressWarnings("serial")
public class NewTaskPanel extends JPanel {

	private WorkFlowSplitTabbedPanel parentPanel;
	
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
	 * Constructor for the NewTaskPanel
	 */
	public NewTaskPanel(WorkFlowSplitTabbedPanel parentPanel) {
		this.parentPanel = parentPanel;
		
		this.buildLayout();
		
	}
	
	/**
	 * Creates the GUI for the NewTaskPanel
	 */
	private void buildLayout() {
        buttonPanel = new NewTaskButtonPanel(this);
        infoPanel = new NewTaskInformationPanel(this);
        
        this.setLayout(new BorderLayout());
        this.add(infoPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * Called when the Create Button is pressed
	 * Creates a Task from the NewTask Info
	 */
	public void createPressed() {
		new AddTaskController(this);
		//viewEventController.removeTab();
		//parentPanel.hideCreateNewTaskPanel();
	}
	
	/**
	 * Called when the Cancel Button is pressed
	 * Closes out the NewTask Tab
	 */
	public void cancelPressed() {
		//viewEventController.removeTab(parentPanel);
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
		return (String)infoPanel.getStatus().getSelectedItem();
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
	public String[] getAssignees() {
		return infoPanel.getAssignees();
	}
	
}
