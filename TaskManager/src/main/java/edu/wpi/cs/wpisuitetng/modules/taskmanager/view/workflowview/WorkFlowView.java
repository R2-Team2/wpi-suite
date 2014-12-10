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
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.RetrieveTaskStatusController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.RetrieveWorkflowController;
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
    private static WorkFlowView instance = null;

    /** The work flow obj. */
    private WorkFlow workFlowObj;

    /** The task status panel. */
    private JPanel taskStatusPanel;

    /** The task status views. */
    List<TaskStatusView> views;

    /** The task status objects */
    List<TaskStatus> statuses;

    /**
     * Create the panel.
     */
    public WorkFlowView() {
        ViewEventController.getInstance().setWorkFlowView(this);

        workFlowObj = new WorkFlow();
        views = new ArrayList<TaskStatusView>();
        statuses = new ArrayList<TaskStatus>();

        setLayout(new BorderLayout());

        final JScrollBar hbar = new JScrollBar(java.awt.Adjustable.HORIZONTAL, 30, 20, 0, 300);

        // refresh();
        instance = this;


    }

    public void rebuildWF() {

        // getWorkFlowFromDB();
        taskStatusPanel = null;
        taskStatusPanel = new JPanel();
        this.add(taskStatusPanel, BorderLayout.CENTER);

        taskStatusPanel
                .setLayout(new MigLayout("", "[350px:n:500px,grow,left][350px:n:500px,grow,left]"
                + "[350px:n:500px,grow,left][350px:n:500px,grow,left]", "[278px,grow 500]"));


        // retrieve task status objects
        RetrieveTaskStatusController retrieveTS = new RetrieveTaskStatusController(this);
        retrieveTS.requestTaskStatuses();
        // System.out.println("Begin Building TS Views.");
        if (statuses.size() > 0) {
            System.out.println(statuses.size());
            for (int i = 0; i < statuses.size(); i++) {
                TaskStatusView aView = new TaskStatusView(statuses.get(i));
                aView.setTaskStatusObj(statuses.get(i));
                // System.out.println("Print task status: " + i + " - " + statuses.get(i).toJson());
                taskStatusPanel.add(aView, "cell " + i + " 0,grow");
                views.add(aView);
            }
            ViewEventController.getInstance().setWorkFlowView(this);
        } else {
            rebuildWF();
        }
        instance = this;
        // System.out.println("Work Flow View Built.");
    }

    /**
     * Retrieves workflow object stored in database. Chain of sequence ends at setWorkFlowObj().
     */
    public void getWorkFlowFromDB() {
        RetrieveWorkflowController retrieveWF = new RetrieveWorkflowController(this);
        retrieveWF.requestWorkflow();
        instance = this;
    }

    /**
     * Get an instance of the work flow viewer.
     *
     * @return an instance of the workflow view
     */
    public static WorkFlowView getInstance() {
        if (instance == null) {
            instance = new WorkFlowView();
            return instance;
        } else {
            return instance;
        }

    }

    /**
     * Sets the objects list of task status objects
     *
     * @param listOfStatus the list of status objects.
     */
    public void setStatuses(List<TaskStatus> listOfStatus) {
        statuses = listOfStatus;
        instance = this;
    }

    /**
     * Returns the statuses field
     *
     * @return array of task statuses.
     */
    public List<TaskStatus> getStatuses() {
        instance = this;
        return statuses;
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
    @Override
    public WorkFlow getWorkFlowObj() {
        instance = this;
        return workFlowObj;
    }

    /**
     * Sets the work flow obj. Should only be called by RetrieveWorkflowController.
     *
     * @param workFlowObj the new work flow obj
     */
    @Override
    public void setWorkFlowObj(WorkFlow workFlowObj) {
        this.workFlowObj = workFlowObj;
        instance = this;
    }

    /**
     * Refresh.
     */
    @Override
    public void refresh() {
        getWorkFlowFromDB();
        rebuildWF();
        instance = this;
    }

}
