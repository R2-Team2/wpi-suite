/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
// this class has depricated through task 24- create collapsing sidebar.
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.workflowview;

import java.awt.Graphics;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.RetrieveWorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.TaskStatus;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.WorkFlow;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.ViewEventController;

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

    /**
     * Create the panel.
     */
    public WorkFlowView() {
        ViewEventController.getInstance().setWorkFlowView(this);

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
        instance = this;
        return workFlowObj;
    }

    /**
     * Sets the work flow obj. Should only be called by RetrieveWorkflowController.
     *
     * @param workFlowObj the new work flow obj
     */
    public void setWorkFlowObj(WorkFlow workFlowObj) {
        this.workFlowObj = workFlowObj;
        instance = this;
    }
    
    
}
