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
		//this.setMinimumSize(new Dimension(500, 200));
		

		this.buildLayout();
	}

	public void setTask(Task aTask)
	{
		aTask.getTaskID();
		this.boxTitle.setText(aTask.getTitle());
		this.boxDescription.setText(aTask.getDescription());
		//this.dropdownStatus.setSelectedItem(aTask.getStatus().toString());
		//requirement
		this.listChosenAssignees=(JList)aTask.getAssignedUsers();
		this.calStartDate.setDate(aTask.getStartDate());
		this.calDueDate.setDate(aTask.getDueDate());
		this.spinnerEstimatedEffort.setValue(aTask.getEstimatedEffort());
		this.spinnerActualEffort.setValue(aTask.getActualEffort());

		//aTask.getActivityList();

	}
}


