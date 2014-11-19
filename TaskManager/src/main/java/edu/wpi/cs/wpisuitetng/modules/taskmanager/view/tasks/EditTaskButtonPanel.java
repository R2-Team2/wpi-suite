
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

import java.awt.FlowLayout;



import javax.swing.JButton;


// TODO: Auto-generated Javadoc
/**
 * The Class EditTaskButtonPanel.
 */
@SuppressWarnings("serial")
public class EditTaskButtonPanel extends AbstractButtonPanel{
	
	/**
	 * Constructor for the EditTaskButtonPanel.
	 *
	 * @param parentPanel the parent panel
	 */
	public EditTaskButtonPanel(AbstractTaskPanel parentPanel) {
		//Set Panel Layout
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		//Set Parent Panel
		this.parentPanel = parentPanel;
		//Set Button Messages
		final String saveString = "Save";
		final String cancelString = "Cancel";
		//Create Buttons
		buttonLeft = new JButton(saveString);
		buttonCancel = new JButton(cancelString);
		this.add(buttonLeft);
		this.add(buttonCancel);
//		parentPanel.createPressed();
		setupListeners();
	}
}
