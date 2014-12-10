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
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.TaskStatus;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.WorkFlow;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.taskstatus.TaskStatusView;

/**
 * The Class WorkFlowView.
 *
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
@SuppressWarnings("serial")
public class WorkFlowView extends AbsWorkFlowView {

    /** The work flow obj. */
    private WorkFlow workFlowObj;

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
        final TaskStatusView taskStatusNew = new TaskStatusView(new TaskStatus("New"));
        final TaskStatusView taskStatusSelDev = new TaskStatusView(
        		new TaskStatus("Selected for Development"));
        final TaskStatusView taskStatusInDev = new TaskStatusView(
        		new TaskStatus("Currently in Development"));
        final TaskStatusView taskStatusDone = new TaskStatusView(new TaskStatus("Completed"));

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
     * Gets the work flow obj.
     *
     * @return the work flow obj
     */
    @Override
    public WorkFlow getWorkFlowObj() {
        return workFlowObj;
    }

    /**
     * Sets the work flow obj.
     *
     * @param workFlowObj the new work flow obj
     */
    @Override
    public void setWorkFlowObj(WorkFlow workFlowObj) {
        this.workFlowObj = workFlowObj;
    }

    /**
     * Refresh.
     */
    @Override
    public void refresh() {
        for (TaskStatusView v : views) {
            System.out.println("Currently in Refresh method");
            v.requestTasksFromDb();
        }
        revalidate();
        repaint();
    }

}
