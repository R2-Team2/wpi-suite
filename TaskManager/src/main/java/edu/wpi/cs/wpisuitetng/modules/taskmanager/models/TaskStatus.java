/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this <<<<<<<
 * HEAD distribution, and is available at http://www.eclipse.org/legal/epl-v10.html =======
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2 >>>>>>> develop
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.models;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.modules.AbstractModel;

// TODO: Auto-generated Javadoc
/**
 * This class contains the fields and methods for the Taskstatus.
 *
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */

public class TaskStatus extends AbstractModel {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 3575239378691210918L;

    /** The task status id. */
    private long taskStatusID;

    /** The name. */
    private String name;

    /** The task list. */
    private List<Task> taskList;

    /**
     * Constructor for the Taskstatus class.
     *
     * @param name the name
     */
    public TaskStatus(String name) {
        this.name = name;
        taskList = new ArrayList<Task>();
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
    public List<Task> getTaskList() {
        return taskList;
    }

    /**
     * Sets the tasklist of the object
     *
     * @param taskList the new task list
     */
    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task to the tasklist
     *
     * @param task String
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Removes a task from the tasklist
     *
     * @param task String
     */
    public void remTask(String task) {
        taskList.remove(task);
    }

    public int getSize() {
        return taskList.size();
    }

    public Task getElementAt(int index) {
        return taskList.get(index);
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Gets the task status id.
     *
     * @return the task status id
     */
    public long getTaskStatusID() {
        return taskStatusID;
    }

    /**
     * Sets the task status id.
     *
     * @param taskStatusID the new task status id
     */
    public void setTaskStatusID(long taskStatusID) {
        this.taskStatusID = taskStatusID;
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
        return new Gson().toJson(this, TaskStatus.class);
    }

    @Override
    public Boolean identify(Object o) {
        // TODO Auto-generated method stub
        return null;
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

    public void update(TaskStatus updatedTaskStatus) {
        name = updatedTaskStatus.name;
        taskList = updatedTaskStatus.taskList;
    }

}
