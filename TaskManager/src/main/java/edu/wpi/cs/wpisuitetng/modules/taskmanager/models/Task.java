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


import edu.wpi.cs.wpisuitetng.modules.taskmanager.view.taskstatus.TaskStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import com.google.gson.Gson;

public class Task extends AbstractModel implements ITask {

	private int taskID;
	private String title;
	private String description;
	private int estimatedEffort;
	private int actualEffort;
	private TaskStatus status;
	private String requirement;
	private Date startDate;
	private Date dueDate;
	private List<User> assignedUsers;
	private List<String> activityList;
	
	

	public Task(int taskID, String title, String description, int estimatedEffort, int actualEffort, TaskStatus status,
			String requirement, Date startDate, Date dueDate, List<User> assignedUsers) {
		this.taskID = taskID;
		this.title = title;
		this.description = description;
		this.estimatedEffort = estimatedEffort;
		this.actualEffort = actualEffort;
		this.status = status;
		this.requirement = requirement;
		this.startDate = startDate;
		this.dueDate = dueDate;
		this.assignedUsers = (assignedUsers != null) ? new ArrayList<User>(assignedUsers) : null;
		this.activityList = (activityList != null) ? new ArrayList<String>(activityList) : null;
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

	public Date getStartDate() {
		return this.startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getRequirement() {
		return this.requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	public TaskStatus getStatus() {
		return this.status;
	}
	
	public ITask setStatus(String status) {
		switch (status.toLowerCase()) {
			case "new":
				this.setStatus(new TaskStatus("new"));
				break;
			case "scheduled":
				this.setStatus(new TaskStatus("scheduled"));
				break;
			case "in progress":
				this.setStatus(new TaskStatus("in progress"));
				break;
			case "complete":
				this.setStatus(new TaskStatus("complete"));
				break;
			default:
				throw new IllegalArgumentException(
						"String given is not valid TaskStatus (must be NEW, SCHEDULED, IN_PROGRESS, or DONE)");
		}
		return this;
	}
	
	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public int getTaskID() {
		return this.taskID;
	}

	public void setTaskID(int taskID) {
		this.taskID = taskID;
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

	public static Task fromJson(String json) {
		final Gson parser = new Gson();
		return parser.fromJson(json, Task.class);
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
		return new Gson().toJson(this, Task.class);
	}

	@Override
	public Boolean identify(Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(Task updatedTask) {
		this.title = updatedTask.title;
		this.description = updatedTask.description;
		this.assignedUsers = updatedTask.assignedUsers;
		this.estimatedEffort = updatedTask.estimatedEffort;
		this.actualEffort = updatedTask.actualEffort;
		this.dueDate = updatedTask.dueDate;
		this.activityList = updatedTask.activityList;
		this.requirement = updatedTask.requirement;
		this.status = updatedTask.status;
	}

}
