/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.taskmanager.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JList;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.janeway.config.ConfigManager;
import edu.wpi.cs.wpisuitetng.modules.AbstractModel;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.attributes.CommentList;

// TODO: Auto-generated Javadoc
/**
 * The Class Task.
 *
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
public class Task extends AbstractModel {

    /** The task id. */
    private long taskID;

    /** The title. */
    private String title;

    /** The description. */
    private String description;

    /** The estimated effort. */
    private int estimatedEffort;

    /** The actual effort. */
    private int actualEffort;

    /** The status. */
    private TaskStatus status;

    /** The requirement. */
    private String requirement;

    /** The start date. */
    private Date startDate;

    /** The due date. */
    private Date dueDate;

    /** The assigned users. */
    private List<User> assignedUsers;

    /** The activity list. */
    private List<String> activityList;

    /** The comment thread on the task. */
    private final CommentList comments;

    /**
     * Instantiates a new task.
     *
     * @param taskID the task id
     * @param title the title
     * @param description the description
     * @param estimatedEffort the estimated effort
     * @param actualEffort the actual effort
     * @param status the status
     * @param requirement the requirement
     * @param startDate the start date
     * @param dueDate the due date
     * @param assignedUsers the assigned users
     * @param activityList the activity list
     */
    public Task(long taskID, String title, String description, int estimatedEffort,
            int actualEffort, TaskStatus status, String requirement, Date startDate, Date dueDate,
            List<User> assignedUsers, List<String> activityList) {
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
        comments = new CommentList();
    }


    /*
     * @see edu.wpi.cs.wpisuitetng.modules.taskmanager.models.ITask#getTitle()
     */
    /**
     * Gets the title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title.
     *
     * @param title the new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the estimated effort.
     *
     * @return the estimated effort
     */
    public int getEstimatedEffort() {
        return estimatedEffort;
    }

    /**
     * Sets the estimated effort.
     *
     * @param estimatedEffort the new estimated effort
     */
    public void setEstimatedEffort(int estimatedEffort) {
        this.estimatedEffort = estimatedEffort;
    }

    /**
     * Gets the actual effort.
     *
     * @return the actual effort
     */
    public int getActualEffort() {
        return actualEffort;
    }

    /**
     * Sets the actual effort.
     *
     * @param actualEffort the new actual effort
     */
    public void setActualEffort(int actualEffort) {
        this.actualEffort = actualEffort;
    }

    /**
     * Gets the start date.
     *
     * @return the start date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date.
     *
     * @param startDate the new start date
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the due date.
     *
     * @return the due date
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * Sets the due date.
     *
     * @param dueDate the new due date
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Gets the requirement.
     *
     * @return the requirement
     */
    public String getRequirement() {
        return requirement;
    }

    /**
     * Sets the requirement.
     *
     * @param requirement the new requirement
     */
    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    /**
     * Gets the status.
     *
     * @return the status
     */
    public TaskStatus getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status the new status
     */
    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    /**
     * Gets the task id.
     *
     * @return the task id
     */
    public long getTaskID() {
        return taskID;
    }

    /**
     * Sets the task id.
     *
     * @param l the new task id
     */
    public void setTaskID(long l) {
        taskID = l;
    }

    /**
     * Adds a user to the assigned users. Will not add a user that is already in the list
     *
     * @param u user to be added
     */
    public void addAssignedUser(User u) {
        boolean alreadyExists = false;
        for (User user : assignedUsers) {
            if (user.getIdNum() == u.getIdNum()) {
                alreadyExists = true;
                break;
            }
        }
        if (!alreadyExists) {
            assignedUsers.add(u);
        }
    }

    /**
     * Deletes a user, given the user's ID number.
     *
     * @param id ID number of user to be deleted
     * @return deletedUser if found, null otherwise
     */
    public User deleteUser(int id) {
        User deletedUser = null;
        for (User user : assignedUsers) {
            if (user.getIdNum() == id) {
                assignedUsers.remove(user);
                deletedUser = user;
                break;
            }
        }
        return deletedUser;
    }

    /**
     * Gets the assigned users.
     *
     * @return the assigned users
     */
    public JList<User> getAssignedUsers() {
        /*
         * User[] midArry= new User[assignedUsers.size()]; for (int i=0;i< assignedUsers.size();
         * i++) { //returnUsers.addElement(assignedUsers.get(i)); midArry[i] = assignedUsers.get(i);
         * } //returnUsers=midArry;
         */
        final JList<User> returnUsers = new JList(assignedUsers.toArray());
        return returnUsers;
    }

    /**
     * Return name of first user assigned to task in correct format to be displayed on a task card.
     *
     * @return String
     */
    public String getUserForTaskCard() {
        String user;
        if (assignedUsers.size() > 1) {
            user = assignedUsers.get(0).getName() + " ...";
        } else if (assignedUsers.size() == 0) {
            user = "";
        } else {
            user = assignedUsers.get(0).getName();
        }
        return user;
    }

    /**
     * Adds the activity.
     *
     * @param a the a
     */
    public void addActivity(String a) {
        activityList.add(a);
    }

    /**
     * Gets the activity list.
     *
     * @return the activity list
     */
    public List<String> getActivityList() {
        return activityList;
    }

    public void addComment(String msg) {
        comments.add(msg);
    }

    public CommentList getComments() {
        return comments;
    }

    /**
     * From json.
     *
     * @param json the json string
     * @return task the task from the json string
     */
    public static Task fromJson(String json) {
        final Gson parser = new Gson();
        final Task task = parser.fromJson(json, Task.class);
        return task;
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.Model#save()
     */
    @Override
    public void save() {
        // TODO Auto-generated method stub

    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.Model#delete()
     */
    @Override
    public void delete() {
        // TODO Auto-generated method stub

    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.Model#toJson()
     */
    @Override
    public String toJson() {
        return new Gson().toJson(this, Task.class);
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.Model#identify(java.lang.Object)
     */
    @Override
    public Boolean identify(Object o) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Update.
     *
     * @param updatedTask the updated task
     */
    public void update(Task updatedTask) {
        title = updatedTask.title;
        description = updatedTask.description;
        assignedUsers = updatedTask.assignedUsers;
        estimatedEffort = updatedTask.estimatedEffort;
        actualEffort = updatedTask.actualEffort;
        dueDate = updatedTask.dueDate;
        activityList = updatedTask.activityList;
        // checks to see if the task changes status
        // if status has changed, create an activity
        if (!(status.equals(updatedTask.status))) {
            // Code inspired by mkyong
            final String user = ConfigManager.getConfig().getUserName();
            final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss MM/dd/yyyy");
            final Date date = new Date();
            final String statusChange = updatedTask.status.getName();
            final String updateActivity =
                    "Status changed to " + statusChange + " at " + dateFormat.format(date) + "("
                            + user + ")";
            activityList.add(updateActivity); // add activity entry to activity list
        }
        requirement = updatedTask.requirement;
        status = updatedTask.status;
    }

    /**
     * Returns an array of Tasks parsed from the given JSON-encoded string.
     *
     * @param json a string containing a JSON-encoded array of Tasks
     * @return tasks an array of Tasks deserialized from the given json string
     */
    public static Task[] fromJsonArray(String json) {
        final Gson parser = new Gson();
        final Task[] tasks = parser.fromJson(json, Task[].class);
        return tasks;

    }

    /**
     * copies old task params to this task.
     * @param toCopyFrom old task.
     */
    public void copyFrom(Task toCopyFrom) {
        //borrowed idea from requirements manager
        title = toCopyFrom.title;
        description = toCopyFrom.description;
        assignedUsers = toCopyFrom.assignedUsers;
        estimatedEffort = toCopyFrom.estimatedEffort;
        actualEffort = toCopyFrom.actualEffort;
        dueDate = toCopyFrom.dueDate;
        activityList = toCopyFrom.activityList;
        requirement = toCopyFrom.requirement;
        status = toCopyFrom.status;
    }
}
