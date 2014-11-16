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

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.AddTaskController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.TempPanel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;

/**
 * @author R2-Team2
 *
 */
@SuppressWarnings("serial")
public class NewTaskPanel extends JPanel {

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
		viewEventController.removeTab();
	}
	
	/**
	 * Called when the Cancel Button is pressed
	 * Closes out the NewTask Tab
	 */
	public void cancelPressed() {
		viewEventController.removeTab();
	}
	
	/**
	 * Returns the JTextField holding the title information
	 * @return JTextField
	 */
	public JTextField getTitle() {
		return infoPanel.getTitle();
	}
	
	/**
	 * Returns the JTextArea holding the description information
	 * @return JTextArea
	 */
	public JTextArea getDescription() {
		return infoPanel.getDescription();
	}
	
	/**
	 * Retrieves the Estimated Effort Spinner from infoPanel
	 * @return JSpinner The Spinner for Estimated Effort
	 */
	public JSpinner getEstimatedEffort(){
		return infoPanel.getEstimatedEffort();
	}
	
	/**
	 * Retrieves the Actual Effort Spinner from infoPanel
	 * @return JSpinner The Spinner for Actual Effort
	 */
	public JSpinner getActualEffort(){
		return infoPanel.getActualEffort();
	}
	
}
