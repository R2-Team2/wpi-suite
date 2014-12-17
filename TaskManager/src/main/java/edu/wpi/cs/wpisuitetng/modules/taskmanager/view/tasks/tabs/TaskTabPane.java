/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.tabs;

import javax.swing.JTabbedPane;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.attributes.CommentList;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tasks.AbstractTaskPanel;

/**
 * Tabbed Panel to hold comments
 * @author R2-Team2
 * @version $Revision: 1.0 $
 *
 */
@SuppressWarnings("serial")
public class TaskTabPane extends JTabbedPane {

    private final Task currentTask;
    private final TaskCommentPanel commentPanel;
    
    /**
     * Constructor for TaskTabPane
     * @param currentTask
     * @param parentPanel
     */
    public TaskTabPane(Task currentTask, AbstractTaskPanel parentPanel) {
        this.currentTask = currentTask;

        commentPanel = new TaskCommentPanel(currentTask, parentPanel);
        this.addTab("Comments", commentPanel);
    }
    
    /**
     * Calls loadComments() in the commentPanel
     */
    public void loadComments() {
        commentPanel.loadComments();
    }
    
    /**
     * Returns the comments in the current task
     * @return CommenList comments inside of current task
     */
    public CommentList getComments() {
        return currentTask.getComments();
    }
}
