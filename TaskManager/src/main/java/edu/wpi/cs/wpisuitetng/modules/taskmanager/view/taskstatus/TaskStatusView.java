 /*******************************************************************************
  * Copyright (c) 2013 WPI-Suite
  * All rights reserved. This program and the accompanying materials
  * are made available under the terms of the Eclipse Public License v1.0
  * which accompanies this distribution, and is available at
  * http://www.eclipse.org/legal/epl-v10.html
  * Contributors
  *  Team R2-Team2
  ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.taskstatus;

import javax.swing.JPanel;

import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.RetrieveTasksController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.TaskStatus;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.*;

import java.awt.BorderLayout;

import javax.swing.JTextPane;

import java.awt.GridLayout;

import net.miginfocom.swing.MigLayout;

import java.awt.GridBagLayout;

import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.TaskCard;

// TODO: Auto-generated Javadoc
/**
 * The Class TaskStatusView.
 */
@SuppressWarnings("serial")
public class TaskStatusView extends JPanel {

	/** The task status obj. */
	TaskStatus taskStatusObj;
	
	/** The txtpn title. */
	JTextPane txtpnTitle = new JTextPane();
	
	/** The panel. */
	JPanel panel = new JPanel();
	
	/** The TaskStatus title */
	private String title;
	
	/**
	 * Create the panel.
	 *
	 * @param title the title
	 */
	public TaskStatusView(String title) {

		this.title = title;
		setLayout(new MigLayout("", "[236px,grow]", "[26px][200px,grow 500]"));
		this.taskStatusObj = new TaskStatus(this.title);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		//Change Vertical Scroll Bar Policy to AS_NEEDED When Task Cards are developed
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBorder(new EtchedBorder());
		this.add(scrollPane, "cell 0 1,grow");
		
		// Create Format and Add Title JTextPane
		StyledDocument document = new DefaultStyledDocument();
		javax.swing.text.Style defaultStyle = document.getStyle(StyleContext.DEFAULT_STYLE);
		StyleConstants.setAlignment(defaultStyle, StyleConstants.ALIGN_CENTER);
		JTextPane txtpnTitle = new JTextPane(document);
		txtpnTitle.setBackground(UIManager.getColor("Button.background"));
		txtpnTitle.setBorder(null);
		txtpnTitle.setForeground(new Color(0, 0, 0));
		txtpnTitle.setEditable(false);
		txtpnTitle.setFont(txtpnTitle.getFont().deriveFont(20f));
		txtpnTitle.setText(this.taskStatusObj.getName());		
		this.add(txtpnTitle, "cell 0 0,alignx center,aligny center");
		panel.setBackground(Color.WHITE);
		
		scrollPane.setViewportView(panel);
		panel.setLayout(new MigLayout("", "[grow,fill]", "[]"));
		
		taskStatusObj.addTask(new Task(101, "A Title", "A Title", 100, 200, new TaskStatus("new"),
			"poop", new Date(), new Date(), new ArrayList<User> ()));
		
		requestTasksFromDb();
	}
	
	
	/**
	 * Populate TaskStatusView with Cards Associated with the Status.
	 */
	public void requestTasksFromDb() {
		// TODO taskStatusObj.TaskList = GetAllTasksFromDatabaseWithThisStatus();
		RetrieveTasksController retrieveTasks = new RetrieveTasksController(this);
		retrieveTasks.requestTasks();
	}
	
	public void fillTaskList(Task[] taskArray) {
		List<Task> taskList = Arrays.asList(taskArray);
		taskStatusObj.setTaskList(new ArrayList<Task>(taskList));
		populateTaskStatusViewCards();
	}
	
	public void populateTaskStatusViewCards() {
		// TODO taskStatusObj.TaskList = GetAllTasksFromDatabaseWithThisStatus();
		List<Task> taskList = taskStatusObj.getTaskList();
		for (int i = 0; i < taskList.size(); i++) {
			Task t = taskList.get(i);
			TaskCard card = new TaskCard(t.getTitle(), t.getDueDate().toString(), t.getUserForTaskCard());
			panel.add(card, "newline");
		}
	}
}
