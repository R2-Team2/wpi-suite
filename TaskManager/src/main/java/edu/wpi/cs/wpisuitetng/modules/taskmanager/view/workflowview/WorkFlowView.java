/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
// this class has depricated through task 24- create collapsing sidebar.
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollBar;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.RetrieveWorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.TaskStatus;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.WorkFlow;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.AbsView;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.taskstatus.TaskStatusView;

// TODO: Auto-generated Javadoc
/**
 * The Class WorkFlowView.
 *
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
@SuppressWarnings("serial")
public class WorkFlowView extends AbsView {

    /** The work flow obj. */
    private WorkFlow workFlowObj;

    /** The task status panel. */
    private final JPanel taskStatusPanel;

    /** The task status views. */
    List<TaskStatusView> views;

    /**
     * Create the panel.
     */
    public WorkFlowView() {
        ViewEventController.getInstance().setWorkFlowView(this);

        workFlowObj = new WorkFlow();
        views = new ArrayList<TaskStatusView>();

        setLayout(new BorderLayout());

        final JScrollBar hbar = new JScrollBar(java.awt.Adjustable.HORIZONTAL, 30, 20, 0, 300);
        // this.add(hbar, BorderLayout.SOUTH);
        // JScrollPane scrollPane = new JScrollPane();
        // this.add(scrollPane, BorderLayout.SOUTH);

        taskStatusPanel = new JPanel();
        this.add(taskStatusPanel, BorderLayout.CENTER);
        final TaskStatusView taskStatusNew = new TaskStatusView("New", "new");
        final TaskStatusView taskStatusSelDev =
                new TaskStatusView("Selected for Development", "scheduled");
        final TaskStatusView taskStatusInDev =
                new TaskStatusView("Currently in Development", "in progress");
        final TaskStatusView taskStatusDone = new TaskStatusView("Completed", "complete");

        taskStatusPanel
                .setLayout(new MigLayout(
                        "",
                        "[350px:n:500px,grow,left][350px:n:500px,grow,left]"
                                + "[350px:n:500px,grow,left][350px:n:500px,grow,left]",
                        "[278px,grow 500]"));

        // Hard Coded Task Statuses, move this to database soon
        taskStatusPanel.add(taskStatusNew, "cell 0 0,grow");
        taskStatusPanel.add(taskStatusSelDev, "cell 1 0,grow");
        taskStatusPanel.add(taskStatusInDev, "cell 2 0,grow");
        taskStatusPanel.add(taskStatusDone, "cell 3 0,grow");

        views.add(taskStatusNew);
        views.add(taskStatusSelDev);
        views.add(taskStatusInDev);
        views.add(taskStatusDone);
    }

    /**
     * Retrieves workflow object stored in database. Chain of sequence ends at setWorkFlowObj().
     */
    public void getWorkFlowFromDB() {
        RetrieveWorkflowController retrieveWF = new RetrieveWorkflowController(this);
        retrieveWF.requestWorkflow();
    }

    /**
     * Generates views field from taskStatusArray
     */
    @Override
    public void utilizeTaskStatuses(TaskStatus[] taskStatusArray) {
        // Generate views field from taskStatusArray
    }

    /**
     * Gets the work flow obj.
     *
     * @return the work flow obj
     */
    public WorkFlow getWorkFlowObj() {
        return workFlowObj;
    }

    /**
     * Sets the work flow obj. Should only be called by RetrieveWorkflowController.
     *
     * @param workFlowObj the new work flow obj
     */
    public void setWorkFlowObj(WorkFlow workFlowObj) {
        this.workFlowObj = workFlowObj;
    }

    /**
     * Refresh.
     */
    public void refresh() {
        for (TaskStatusView v : views) {
            v.getTasksFromDb();
        }
    }

}
