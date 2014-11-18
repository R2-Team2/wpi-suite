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

import java.awt.Dimension;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;
//import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.taskstatus.TaskStatus;

/**
 *
 */
@SuppressWarnings("serial")
public class NewTaskInformationPanel extends AbstractInformationPanel {
	/**
	 * Constructor for NewTaskInformationPanel
	 * @param parentPanel
	 */
	public NewTaskInformationPanel(AbstractTaskPanel parentPanel) {
		this.parentPanel = parentPanel;
        this.setMinimumSize(new Dimension(500, 200));
        
		this.buildLayout();
	}

}

