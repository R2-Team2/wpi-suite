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

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.TaskCard;

@SuppressWarnings("serial")
public class TaskStatusView extends JPanel {

	TaskStatus taskStatusObj;
	JTextPane txtpnTitle = new JTextPane();
	JPanel panel = new JPanel();
	
	/**
	 * Create the panel.
	 */
	public TaskStatusView(String title) {

		setLayout(new MigLayout("", "[236px,grow]", "[26px][200px,grow 500]"));
		this.taskStatusObj = new TaskStatus(title);
		
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
		PopulateTaskStatusViewCards();
	}
	
	
	/**
	 * Populate TaskStatusView with Cards Associated with the Status
	 */
	public void PopulateTaskStatusViewCards(){
		// TODO taskStatusObj.TaskList = GetAllTasksFromDatabaseWithThisStatus();
		for(int i = 0; i < taskStatusObj.getTaskList().size(); i++){
			TaskCard card = new TaskCard();
			card.setTaskCardName(taskStatusObj.getTaskList().get(i));
			panel.add(card, "newline");
		}
	}
}
