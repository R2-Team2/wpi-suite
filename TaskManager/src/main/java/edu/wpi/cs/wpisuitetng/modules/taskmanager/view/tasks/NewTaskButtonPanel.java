
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

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;

/**
 *
 */
@SuppressWarnings("serial")
public class NewTaskButtonPanel extends AbstractButtonPanel {
	//Class Variables
	
	protected NewTaskPanel parentPanel;
	
	/**
	 * Constructor for the NewTaskButtonPanel
	 * @param parentPanel
	 */
	public NewTaskButtonPanel(NewTaskPanel parentPanel) {
		//Set Panel Layout
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		//Set Parent Panel
		this.parentPanel = parentPanel;
		//Set Button Messages
		String createString = "Create";
		String cancelString = "Cancel";
		//Create Buttons
		buttonCreate = new JButton(createString);
		buttonCancel = new JButton(cancelString);
		this.add(buttonCreate);
		this.add(buttonCancel);
//		parentPanel.createPressed();
		super.setupListeners();
		this.setupListeners();
	}
	protected void setupListeners() {
		buttonCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parentPanel.createPressed();
			}
		});
	}
	
	//public void setTask()
	{
	}
}

