/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.models;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

/**
 * This class contains the fields and methods for the Taskstatus
 */
public class TaskStatus extends AbstractListModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3575239378691210918L;
	private int taskStatusID;
    private String name;
    private List<Task> taskList;

    /**
     * Constructor for the Taskstatus class
     */
    public TaskStatus(String name) {
        this.name = name;
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Gets the name of the object
     *
     * @return name String
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the object
     *
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the tasklist of the object
     *
     * @return taskList ArrayList
     */
    public List<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Sets the tasklist of the object
     *
     */
    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task to the tasklist
     * @param task String
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Removes a task from the tasklist
     * @param task String
     */
    public void remTask(String task) {
        this.taskList.remove(task);
    }

    public int getSize() {
        return this.taskList.size();
    }

    public Task getElementAt(int index) {
        return this.taskList.get(index);
    }
    
    public String toString() {
    	return this.name;
    }

	public int getTaskStatusID() {
		return taskStatusID;
	}

	public void setTaskStatusID(int taskStatusID) {
		this.taskStatusID = taskStatusID;
	}

}
