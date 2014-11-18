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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * The Class AbstractButtonPanel.
 */
public abstract class AbstractButtonPanel extends JPanel {
	//Class Variables
	protected AbstractTaskPanel parentPanel;

	protected JButton buttonSave;
	protected JButton buttonCreate;
	protected JButton buttonCancel;



	/**
	 * Sets up the listeners for the buttons in the New Task Button Panel
	 */
	protected void setupListeners() {
		buttonCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parentPanel.cancelPressed();
			}

		});
	}
	
	public void validateTaskInfo(){
		if(parentPanel.infoPanel.boxTitle.getText().length() <= 0){
			buttonLeft.setEnabled(false);
		}
		else{
			buttonLeft.setEnabled(true);
		}
	}

}
