  /*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * 	Team R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;


import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;
//import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.CreateNewTaskPanel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.NewTaskPanel;

/**
 * The Class WorkFlowSplitView.
 */
public class WorkFlowSplitView extends JSplitPane{
	WorkFlowSplitTabbedPanel splitTabbedPanel;
	
	public WorkFlowSplitView() {
		this.splitTabbedPanel = new WorkFlowSplitTabbedPanel(this);

        ViewEventController.getInstance().setSplitTabbedPanel(splitTabbedPanel);
		
		this.setLeftComponent(new JScrollPane(new WorkFlowView()));
		this.setRightComponent(null);
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               hideCreateNewTaskPanel();
            }
        });
	}
	
	public void createNewTaskPanel() {
		this.splitTabbedPanel.addCreateTaskTab();
		this.setRightComponent(this.splitTabbedPanel);
		this.setOneTouchExpandable(true);
		this.setDividerLocation(.6);
		this.resetToPreferredSizes();
	}

	public void hideCreateNewTaskPanel(){
		this.setRightComponent(null);
		this.setOneTouchExpandable(false);
		//this.setDividerLocation(0.0);
	}
	
	public void collapse(){
		this.setOneTouchExpandable(true);
		this.setDividerLocation(1.0);
	}
}
