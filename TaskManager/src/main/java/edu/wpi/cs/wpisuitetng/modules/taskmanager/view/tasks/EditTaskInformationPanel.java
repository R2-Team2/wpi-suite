/*******************************************************************************
 * Copyright (c) 2012 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    R2-Team2
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

import net.miginfocom.swing.MigLayout;

import com.toedter.calendar.JCalendar;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.taskstatus.TaskStatus;
//import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.taskstatus.TaskStatus;

/**
 *
 */
@SuppressWarnings("serial")
public class EditTaskInformationPanel extends AbstractInformationPanel{

	/**
	 * Constructor for NewTaskInformationPanel
	 * @param parentPanel
	 */
	public EditTaskInformationPanel(AbstractTaskPanel parentPanel) {
		this.parentPanel = parentPanel;
		this.setMinimumSize(new Dimension(500, 200));

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


