/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors: Team R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks;

import javax.swing.JPanel;
import javax.swing.JLabel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.CreateNewTaskPanel;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;

/**
 * @author dbogatov
 *
 */
public class NewTaskPanel extends JPanel {
	private ViewEventController viewEventController = ViewEventController.getInstance();
	
	public NewTaskPanel() {
		
		CreateNewTaskPanel mainPanel = new CreateNewTaskPanel();
		this.add(mainPanel);
		//JLabel testLabel = new JLabel();
    	//testLabel.setText("We're 80% done, Your Excellence!");
    	//this.add(testLabel);
	}
	
}
