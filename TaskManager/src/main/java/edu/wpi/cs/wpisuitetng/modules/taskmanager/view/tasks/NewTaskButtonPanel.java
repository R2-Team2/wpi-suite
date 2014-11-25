
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

import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;

/**
 * The Class NewTaskButtonPanel.
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
@SuppressWarnings("serial")
public class NewTaskButtonPanel extends AbstractButtonPanel {
	//Class Variables

	///** The button create. */
    //protected JButton buttonCreate;

	/**
	 * Constructor for the NewTaskButtonPanel.
	 *
	 * @param parentPanel the parent panel
	 */
	public NewTaskButtonPanel(NewTaskPanel parentPanel) {
		//Set Panel Layout
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		//Set Parent Panel
		this.parentPanel = parentPanel;
		//Set Button Messages
		final String createString = "Create";
		final String cancelString = "Cancel";
		//Create Buttons
		buttonCreate = new JButton(createString);
		buttonCancel = new JButton(cancelString);
		buttonCreate.setEnabled(false);
		this.add(buttonCreate);
		this.add(buttonCancel);

		super.setupListeners();
		this.setupListeners();
	}
	protected void setupListeners() {
        buttonCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.createPressed();
                ViewEventController.getInstance().refreshWorkFlowView();
            }
        });
	}

	/**
     * Validate task info.
     */
    public void validateTaskInfo() {
        if (parentPanel.infoPanel.boxTitle.getText().length() <= 0) {
            buttonCreate.setEnabled(false);
        }
        else {
            buttonCreate.setEnabled(true);
        }
    }

}

