/*******************************************************************************
 * Copyright (c) 2012 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    R2-Team2
 *******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.taskmanager.models;

import edu.wpi.cs.wpisuitetng.modules.AbstractModel;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.characteristics.TaskStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;


public class Task extends AbstractModel {

	private int id;
	private String title;
	private String description;
	private List<User> assignedUsers;
	private int estimatedEffort;
	private int actualEffort;
	private Date dueDate;
	private List<String> activityList;
	private int requirementID;
	private TaskStatus status;
	
	public Task(int id, String title, String description, List<User> assignedUsers, int estimatedEffort, 
			int actualEffort, Date dueDate, List<String> activityList, int requirementID, TaskStatus status) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.assignedUsers = new ArrayList<User>(assignedUsers);
		this.estimatedEffort = estimatedEffort;
		this.actualEffort = actualEffort;
		this.dueDate = dueDate;
		this.activityList = new ArrayList<String>(activityList);
		this.requirementID = requirementID;
		this.status = status;
	}
	
	
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getEstimatedEffort() {
		return this.estimatedEffort;
	}

	public void setEstimatedEffort(int estimatedEffort) {
		this.estimatedEffort = estimatedEffort;
	}

	public int getActualEffort() {
		return this.actualEffort;
	}

	public void setActualEffort(int actualEffort) {
		this.actualEffort = actualEffort;
	}

	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	public int getRequirementID() {
		return this.requirementID;
	}

	public void setRequirementID(int requirementID) {
		this.requirementID = requirementID;
	}

	public TaskStatus getStatus() {
		return this.status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public int getID() {
		return this.id;
	}

	/**
	 * Adds a user to the assigned users.
	 * Will not add a user that is already in the list
	 * 
	 * @param u user to be added
	 */
	public void addAssignedUser(User u) {
		for (User user : this.assignedUsers) {
			if (user.getIdNum() == u.getIdNum()) {
				return;
			}
		}
		this.assignedUsers.add(u);
	}
	
	/**
	 * Deletes a user, given the user's ID number
	 * 
	 * @param id ID number of user to be deleted
	 * @return user if found, null otherwise
	 */
	public User deleteUser(int id) {
		for (User user : this.assignedUsers) {
			if (user.getIdNum() == id) {
				this.assignedUsers.remove(user);
				return user;
			}
		}
		return null;
	}
	
	public List<User> getAssignedUsers() {
		return this.assignedUsers;
	}

	public void addActivity(String a) {
		this.activityList.add(a);
	}
	
	public List<String> getActivityList() {
		return this.activityList;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean identify(Object o) {
		// TODO Auto-generated method stub
		return null;
	}

}
