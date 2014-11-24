/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.models;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import com.google.gson.Gson;

/**
 * The Class WorkFlow.
 *
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
public class WorkFlow extends AbstractListModel {

    /**
     *
     */
    private static final long serialVersionUID = 4436690474105127166L;

    /** The work flow id. */
    private int workFlowID;

    /** The task status list. */
    private List<Integer> taskStatusList;

    /**
     * Instantiates a new work flow.
     */
    public WorkFlow() {
        taskStatusList = new ArrayList<Integer>();
    }

    /**
     * Gets the work flow id.
     *
     * @return the work flow id
     */
    public int getWorkFlowID() {
        return workFlowID;
    }

    /**
     * Sets the work flow id.
     *
     * @param workFlowID the new work flow id
     */
    public void setWorkFlowID(int workFlowID) {
        this.workFlowID = workFlowID;
    }

    /**
     * Gets the task status list.
     *
     * @return the task status list
     */
    public List<Integer> getTaskStatusList() {
        return taskStatusList;
    }

    /**
     * Sets the task status list.
     *
     * @param taskStatusList the new task status list
     */
    public void setTaskStatusList(List<Integer> taskStatusList) {
        this.taskStatusList = taskStatusList;
    }


    /**
     * Adds a taskStatus to the task status list.
     *
     * @param task String
     */
    public void addTaskStatus(int taskStatus) {
        taskStatusList.add(taskStatus);
    }

    /**
     * Removes a taskStatus from the task status list.
     *
     * @param task String
     */
    public void remTask(int taskList) {
        taskStatusList.remove(taskList);
    }

    /**
     * (non-Javadoc)
     *
     * @see javax.swing.ListModel#getSize()
     */
    @Override
    public int getSize() {
        return taskStatusList.size();
    }

    /*
     * (non-Javadoc)
     * @see javax.swing.ListModel#getElementAt(int)
     */
    @Override
    public Integer getElementAt(int index) {
        return taskStatusList.get(index);
    }

    public static WorkFlow[] fromJsonArray(String json) {
        final Gson parser = new Gson();
        return parser.fromJson(json, WorkFlow[].class);
    }

    public String toJson() {
        // TODO Auto-generated method stub
        return new Gson().toJson(this, TaskStatus.class);
    }

}
