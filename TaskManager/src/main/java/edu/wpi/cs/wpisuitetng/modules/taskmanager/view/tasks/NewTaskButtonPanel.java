
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

import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;

/**
 *
 */
@SuppressWarnings("serial")
public class NewTaskButtonPanel extends AbstractButtonPanel {
	//Class Variables

	/**
	 * Constructor for the NewTaskButtonPanel
	 * @param parentPanel
	 */
	public NewTaskButtonPanel(AbstractTaskPanel parentPanel) {
		//Set Panel Layout
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		//Set Parent Panel
		this.parentPanel = parentPanel;
		//Set Button Messages
		String createString = "Create";
		String cancelString = "Cancel";
		//Create Buttons
		buttonLeft = new JButton(createString);
		buttonCancel = new JButton(cancelString);
		buttonLeft.setEnabled(false);
		this.add(buttonLeft);
		this.add(buttonCancel);

		setupListeners();
	}

}

