/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * 	Team R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.controller;

import java.util.ArrayList;
import java.util.Date;


/**
 * The Interface ICreateNewTask.
 */
public interface ICreateNewTask {
	/**
	 * Method called by the view to create a new task
	 * @param title
	 * @param description
	 * @param estimatedEffort
	 * @param dueDate
	 * @param assigned List of Integers representing the IDs of assigned members
	 * @param associatedRequirement ID of associated requirement
	 * @return Integer representing validation (0) or error code (>0)
	 */
	public int createNewTask(String title, String description, int estimatedEffort, Date dueDate, ArrayList<Integer> assigned, int associatedRequirement);
	
}
