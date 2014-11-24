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

// TODO: Auto-generated Javadoc
/**
 * This class contains the fields and methods for the Taskstatus.
 *
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
public class TaskStatus extends AbstractListModel {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 3575239378691210918L;

    /** The task status id. */
    private int taskStatusID;

    /** The name. */
    private String name;

    /** The task list. */
    private List<Integer> taskList;

    private int workFlowID;

    /**
     * Constructor for the Taskstatus class.
     *
     * @param name the name
     */
    public TaskStatus(String name, int workFlowID) {
        this.name = name;
        taskList = new ArrayList<Integer>();
        this.setWorkFlowID(workFlowID);
    }

    /**
     * Gets the name of the object.
     *
     * @return name String
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the object.
     *
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the tasklist of the object.
     *
     * @return taskList ArrayList
     */
    public List<Integer> getTaskList() {
        return taskList;
    }

    /**
     * Sets the tasklist of the object.
     *
     * @param taskList the new task list
     */
    public void setTaskList(List<Integer> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task to the tasklist.
     *
     * @param task String
     */
    public void addTask(int task) {
        taskList.add(task);
    }

    /**
     * Removes a task from the tasklist.
     *
     * @param task String
     */
    public void remTask(int task) {
        taskList.remove(task);
    }

    /*
     * (non-Javadoc)
     * @see javax.swing.ListModel#getSize()
     */
    @Override
    public int getSize() {
        return taskList.size();
    }

    /*
     * (non-Javadoc)
     * @see javax.swing.ListModel#getElementAt(int)
     */
    @Override
    public Integer getElementAt(int index) {
        return taskList.get(index);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Gets the task status id.
     *
     * @return the task status id
     */
    public int getTaskStatusID() {
        return taskStatusID;
    }

    /**
     * Sets the task status id.
     *
     * @param taskStatusID the new task status id
     */
    public void setTaskStatusID(int taskStatusID) {
        this.taskStatusID = taskStatusID;
    }

    /**
     * Returns an array of TaskStatuses parsed from the given JSON-encoded string.
     *
     * @param json a string containing a JSON-encoded array of TaskStatuses
     * @return an array of TaskStatuses deserialized from the given json string
     */
    public static TaskStatus[] fromJsonArray(String json) {
        final Gson parser = new Gson();
        return parser.fromJson(json, TaskStatus[].class);
    }

    public String toJson() {
        // TODO Auto-generated method stub
        return new Gson().toJson(this, TaskStatus.class);
    }

    public int getWorkFlowID() {
        return workFlowID;
    }

    public void setWorkFlowID(int workFlowID) {
        this.workFlowID = workFlowID;
    }

}
