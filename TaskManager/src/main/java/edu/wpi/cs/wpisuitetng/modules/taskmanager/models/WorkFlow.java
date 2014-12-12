/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.models;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.modules.AbstractModel;



// TODO: Auto-generated Javadoc
/**
 * The Class WorkFlow.
 * 
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
public class WorkFlow extends AbstractModel {

    /** The work flow id. */
    private long workFlowID;

    /** The task status list. */
    private List<Long> taskStatusList;

    /**
     * Instantiates a new work flow.
     */
    public WorkFlow() {
        taskStatusList = new ArrayList<Long>();
    }


    /**
     * Gets the work flow id.
     * 
     * @return the work flow id
     */

    public long getWorkFlowID() {
        return workFlowID;
    }


    /**
     * Sets the work flow id.
     *
     * @param l the new work flow id
     */

    public void setWorkFlowID(long l) {
        workFlowID = l;
    }


    /**
     * Gets the task status list.
     *
     * @return the task status list
     */
    public List<Long> getTaskStatusList() {
        return taskStatusList;
    }

    /**
     * Sets the task status list.
     *
     * @param taskStatusList the new task status list
     */

    public void setTaskStatusList(List<Long> taskStatusList) {
        this.taskStatusList = taskStatusList;
    }

    @Override
    public void save() {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete() {
        // TODO Auto-generated method stub

    }

    @Override
    public String toJson() {
        return new Gson().toJson(this, WorkFlow.class);
    }

    @Override
    public Boolean identify(Object o) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * convert from string to workflow object
     * 
     * @param json the string
     * @return the formed workflow object.
     */
    public static WorkFlow fromJson(String json) {
        final Gson parser = new Gson();
        return parser.fromJson(json, WorkFlow.class);
    }

    /**
     * update the workflow object in the database.
     * 
     * @param updatedWorkFlow the workflow to update
     */
    public void update(WorkFlow updatedWorkFlow) {
        taskStatusList = updatedWorkFlow.taskStatusList;
    }

}
