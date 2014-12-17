/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.tabs;

import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.JScrollPane;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;

/**
 * @author R2-Team2
 * @version $Revision: 1.0 $
 * 
 */
@SuppressWarnings("serial")
public class TaskActivitiesPanel extends JScrollPane {

    private final Task currentTask;
    
    /**
     * Constructor for TaskActivitiesPanel
     * @param currentTask
     */
    public TaskActivitiesPanel(Task currentTask) {
        this.currentTask = currentTask;

        // Create new scroll pane for notes
        this.setVerticalScrollBarPolicy(
                javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        // Always show scroll bar
        this.setMinimumSize(new Dimension(100, 300));
        
        if (currentTask != null) {
            refresh();
        }
    }
    
    /**
     * refreshes the contents of the activities JList
     */
    public void refresh() {
        final JList<String> activities = 
                new JList(currentTask.getActivityList().toArray());
        this.setViewportView(activities);
    }
    
}
