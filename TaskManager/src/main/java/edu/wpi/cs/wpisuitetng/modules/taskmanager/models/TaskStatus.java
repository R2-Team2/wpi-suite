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
     * Sets the tasklist of the object.
     *
     * @param taskList the new task list
     */
    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task to the tasklist.
     *
     * @param task String
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Removes a task from the tasklist.
     *
     * @param task String
     */
    public void remTask(String task) {
        taskList.remove(task);
    }

    @Override
    public int getSize() {
        return taskList.size();
    }

    @Override
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

}
