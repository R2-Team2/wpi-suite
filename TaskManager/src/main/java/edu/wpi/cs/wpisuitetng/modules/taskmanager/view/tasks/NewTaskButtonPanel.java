
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
 * The Class NewTaskButtonPanel.
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
@SuppressWarnings("serial")
public class NewTaskButtonPanel extends AbstractButtonPanel {
	//Class Variables

	/**
	 * Constructor for the NewTaskButtonPanel.
	 *
	 * @param parentPanel the parent panel
	 */
	public NewTaskButtonPanel(AbstractTaskPanel parentPanel) {
		//Set Panel Layout
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		//Set Parent Panel
		this.parentPanel = parentPanel;
		//Set Button Messages
		final String createString = "Create";
		final String cancelString = "Cancel";
		//Create Buttons
		buttonLeft = new JButton(createString);
		buttonCancel = new JButton(cancelString);
		buttonLeft.setEnabled(false);
		this.add(buttonLeft);
		this.add(buttonCancel);

		setupListeners();
	}

}

