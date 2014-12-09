/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
// this class has depricated through task 24- create collapsing sidebar.
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.reports;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;


/**
 * The Class WorkFlowView.
 *
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
public class TaskListView {
	private final JScrollPane TaskList = new JScrollPane();

	public TaskListView() {

		buildLayout();
	}

	protected void buildLayout() {

		String labels[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
		ArrayList<Task> tasks = null;
		JList list = new JList(labels);
		TaskList.setViewportView(list);

		final JLabel lblAllTasks = new JLabel("<html><h1>" + "All Tasks" + "</h1></html>");
		// JLabel lblAllTasks = new JLabel("All Tasks");
		lblAllTasks.setFont(new Font("Tahoma", Font.BOLD, 14));
		TaskList.setColumnHeaderView(lblAllTasks);

	}
}
