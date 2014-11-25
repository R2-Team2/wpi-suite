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

import javax.swing.JList;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;

// TODO: Auto-generated Javadoc
/**
 * The Class EditTaskInformationPanel.
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
@SuppressWarnings("serial")
public class EditTaskInformationPanel extends AbstractInformationPanel{

	/**
	 * Constructor for NewTaskInformationPanel.
	 *
	 * @param parentPanel the parent panel
	 */
	public EditTaskInformationPanel(AbstractTaskPanel parentPanel) {
		this.parentPanel = parentPanel;
		

		this.buildLayout();
		this.setupListeners();
	}

	public void setTask() {
		parentPanel.aTask.getTaskID();
		this.boxTitle.setText(parentPanel.aTask.getTitle());
		this.boxDescription.setText(parentPanel.aTask.getDescription());
		//this.dropdownStatus.setSelectedItem(aTask.getStatus().toString());
		//requirement
		this.listChosenAssignees= parentPanel.aTask.getAssignedUsers();
		this.calStartDate.setDate(parentPanel.aTask.getStartDate());
		this.calDueDate.setDate(parentPanel.aTask.getDueDate());
		this.spinnerEstimatedEffort.setValue(parentPanel.aTask.getEstimatedEffort());
		this.spinnerActualEffort.setValue(parentPanel.aTask.getActualEffort());
	}	
	
	protected void setupListeners() {
		// Text Field Listeners
		boxTitle.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				parentPanel.buttonPanel.validateTaskInfo();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				parentPanel.buttonPanel.validateTaskInfo();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				parentPanel.buttonPanel.validateTaskInfo();
			}
		});
	}
}


