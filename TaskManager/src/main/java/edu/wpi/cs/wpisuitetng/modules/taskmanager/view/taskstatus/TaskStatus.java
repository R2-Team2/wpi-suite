/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.taskstatus;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;

/**
 * This class contains the fields and methods for the Taskstatus
 */
public class TaskStatus extends AbstractListModel {

    private String name;
    private List<String> taskList;

    /**
     * Constructor for the Taskstatus class
     */
    public TaskStatus(String name) {
        this.name = name;
        this.taskList = new ArrayList<String>();
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
    public List<String> getTaskList() {
        return this.taskList;
    }

    /**
     * Sets the tasklist of the object
     *
     */
    public void setTaskList(List<String> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task to the tasklist
     * @param task String
     */
    public void addTask(String task) {
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

    public String getElementAt(int index) {
        return this.taskList.get(index);
    }

}
