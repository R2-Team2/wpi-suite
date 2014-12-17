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

/**
 * This class contains the fields and methods for the Taskstatus.
 *
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */

public class TaskStatus extends AbstractModel {

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
    public void remTask(Task task) {
        taskList.remove(task);
    }

    public int getSize() {
        return taskList.size();
    }

    /**
     * return a task from a list
     *
     * @param index the index in the list
     * @return task at that spot in the array
     */
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
    public void save() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Save() is an unsupported operation.");
    }

    @Override
    public void delete() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Delete() is an unsupported operation.");
    }

    @Override
    public String toJson() {
        return new Gson().toJson(this, TaskStatus.class);
    }

    @Override
    public Boolean identify(Object o) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Identify() is an unsupported operation.");
    }

    /**
     * convert from string to taskstatus
     *
     * @param json the string
     * @return the formed taskstatus
     */
    public static TaskStatus fromJson(String json) {
        final Gson parser = new Gson();
        return parser.fromJson(json, TaskStatus.class);
    }

    /**
     * update taskstatus objects
     *
     * @param updatedTaskStatus task status to update.
     */
    public void update(TaskStatus updatedTaskStatus) {
        name = updatedTaskStatus.name;
        taskList = updatedTaskStatus.taskList;
    }
}
