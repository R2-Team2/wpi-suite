
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

public class ViewTaskButtonPanel extends AbstractButtonPanel{
	protected ViewTaskPanel parentPanel;
	//private JButton buttonEdit;
	public ViewTaskButtonPanel(ViewTaskPanel parentPanel)
	{
		//Set Panel Layout
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		//Set Parent Panel
		this.parentPanel = parentPanel;
		
		//Set Button Messages
		String editString = "Edit";
		String cancelString = "Cancel";
		
		//Create Buttons
		buttonLeft = new JButton(editString);
		buttonCancel = new JButton(cancelString);
		this.add(buttonLeft);
		this.add(buttonCancel);
		this.setupListeners();
	}
	

	  /**
     * Sets up the listeners for the buttons in the New Task Button Panel.
     */
    protected void setupListeners() {
    	buttonLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parentPanel.editPressed();
			}
		});

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.cancelPressed();
            }
        });
    }
}