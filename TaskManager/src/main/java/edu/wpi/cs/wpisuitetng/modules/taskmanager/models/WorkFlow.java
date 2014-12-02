/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.models;

import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class WorkFlow.
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
public class WorkFlow {

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


}
