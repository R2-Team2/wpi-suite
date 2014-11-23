
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



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


// TODO: Auto-generated Javadoc
/**
 * The Class EditTaskButtonPanel.
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
@SuppressWarnings("serial")
public class EditTaskButtonPanel extends AbstractButtonPanel{
	
	protected EditTaskPanel parentPanel;
	JButton buttonSave;
	

	/**
	 * Constructor for the EditTaskButtonPanel.
	 *
	 * @param parentPanel the parent panel
	 */
	public EditTaskButtonPanel(EditTaskPanel parentPanel) {
		//Set Panel Layout
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		//Set Parent Panel
		this.parentPanel = parentPanel;
		//Set Button Messages
		final String saveString = "Save";
		final String cancelString = "Cancel";
		//Create Buttons
		buttonSave = new JButton(saveString);
		buttonCancel = new JButton(cancelString);
		this.add(buttonSave);
		this.add(buttonCancel);
//		parentPanel.createPressed();
		//super.setupListeners();
		this.setupListeners();
	}
	protected void setupListeners() {
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parentPanel.savePressed();
			}
		});
	}
	
}
