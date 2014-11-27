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

/**
 * The Class WorkFlow.
 *
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
public class WorkFlow extends AbstractModel {

    /**
     *
     */
    private static final long serialVersionUID = 4436690474105127166L;

    /** The work flow id. */
    private int workFlowID;

    /** The task status list. */
    private List<TaskStatus> taskStatusList;

    /**
     * Instantiates a new work flow.
     */
    public WorkFlow() {
        taskStatusList = new ArrayList<TaskStatus>();
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
    public List<TaskStatus> getTaskStatusList() {
        return taskStatusList;
    }

    /**
     * Sets the task status list.
     *
     * @param taskStatusList the new task status list
     */
    public void setTaskStatusList(List<TaskStatus> taskStatusList) {
        this.taskStatusList = taskStatusList;
    }


    /**
     * Adds a taskStatus to the task status list.
     *
     * @param task String
     */
    public void addTaskStatus(TaskStatus taskStatus) {
        taskStatusList.add(taskStatus);
    }

    /**
     * Removes a taskStatus from the task status list.
     *
     * @param TaskStatus
     */
    public void remTaskStatus(TaskStatus taskStatus) {
        taskStatusList.remove(taskStatus);
    }

    /**
     * (non-Javadoc)
     *
     * @see javax.swing.ListModel#getSize()
     */

    public int getSize() {
        return taskStatusList.size();
    }

    /*
     * (non-Javadoc)
     * @see javax.swing.ListModel#getElementAt(int)
     */

    public TaskStatus getElementAt(int index) {
        return taskStatusList.get(index);
    }

    public static WorkFlow[] fromJsonArray(String json) {
        final Gson parser = new Gson();
        return parser.fromJson(json, WorkFlow[].class);
    }

    @Override
    public String toJson() {
        // TODO Auto-generated method stub
        return new Gson().toJson(this, WorkFlow.class);
    }

    public static WorkFlow fromJson(String json) {
        final Gson parser = new Gson();
        return parser.fromJson(json, WorkFlow.class);
    }

    public void update(WorkFlow updatedWorkFlow) {
        taskStatusList = updatedWorkFlow.taskStatusList;

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
    public Boolean identify(Object o) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Adds a task to the specified taskstatus
     *
     * @param taskStatus
     * @param task
     */
    public void addTask(Task task) {
        for (TaskStatus ts : taskStatusList) {
            if (task.getStatusID() == ts.getTaskStatusID()) {
                ts.addTask(0, task);
            }
        }
    }

    public void updateTask() {

    }

}
