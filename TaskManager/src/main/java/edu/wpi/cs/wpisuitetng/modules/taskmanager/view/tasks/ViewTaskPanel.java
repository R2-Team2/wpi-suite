
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

import java.awt.BorderLayout;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview.WorkFlowSplitTabbedPanel;

public class ViewTaskPanel extends AbstractTaskPanel{

	//protected AbstractInformationPanel infoPanel;
    //protected AbstractButtonPanel buttonPanel;
	//private ViewEventController viewEventController = ViewEventController.getInstance();
	
	public ViewTaskPanel(WorkFlowSplitTabbedPanel parentPanel)
	{
		super(parentPanel);
		this.buildLayout();
	}
	
	protected void buildLayout() {
        buttonPanel = new ViewTaskButtonPanel(this);
        infoPanel = new EditTaskInformationPanel(this);
        
        this.setLayout(new BorderLayout());
        this.add(infoPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	public void editPressed() {
		// TODO Auto-generated method stub
		Task passTask = this.aTask;
		ViewEventController.getInstance().closeNewTaskPanel();
		EditTaskPanel editView = new EditTaskPanel(this.parentPanel);
		editView.openEditView(passTask);
		//editView.buildLayout();
	}
	
	/**
	 * Called when the Cancel Button is pressed Closes out the NewTask Tab.
	 */
    public void cancelPressed() {
        ViewEventController.getInstance().removeSplitTab();
        parentPanel.checkForHide();
    }
	
}
