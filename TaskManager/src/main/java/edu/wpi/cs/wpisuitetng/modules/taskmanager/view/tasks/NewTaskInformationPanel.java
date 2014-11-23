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

import java.awt.Dimension;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;
//import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.taskstatus.TaskStatus;


// TODO: Auto-generated Javadoc
/**
 * The Class NewTaskInformationPanel.
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
@SuppressWarnings("serial")
public class NewTaskInformationPanel extends AbstractInformationPanel {
	
	/**
	 * Constructor for NewTaskInformationPanel.
	 *
	 * @param parentPanel the parent panel
	 */
	public NewTaskInformationPanel(AbstractTaskPanel parentPanel) {
		this.parentPanel = parentPanel;
		//this.setMinimumSize(new Dimension(540, 200));
        
		this.buildLayout();
	}
	
	public void setTask(Task aTask)
	{
		
	}
}

