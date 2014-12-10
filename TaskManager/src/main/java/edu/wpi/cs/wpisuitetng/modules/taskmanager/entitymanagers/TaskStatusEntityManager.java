/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.taskmanager.entitymanagers;

import java.util.List;

import edu.wpi.cs.wpisuitetng.Session;
import edu.wpi.cs.wpisuitetng.database.Data;
import edu.wpi.cs.wpisuitetng.exceptions.BadRequestException;
import edu.wpi.cs.wpisuitetng.exceptions.UnauthorizedException;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.EntityManager;
import edu.wpi.cs.wpisuitetng.modules.Model;
import edu.wpi.cs.wpisuitetng.modules.core.models.Role;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.IDNum;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.TaskStatus;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.WorkFlow;

/**
 * The Class TaskEntityManager.
 *
 * @version $Revision: 1.0 $
 * @author R2-Team2
 */
public class TaskStatusEntityManager implements EntityManager<TaskStatus> {

    private final Data db;

    /**
     * constructor for the task status entity manager
     *
     * @param db the database interface.
     */
    public TaskStatusEntityManager(Data db) {
        this.db = db;
    }

    @Override
    public TaskStatus makeEntity(Session s, String content)
            throws WPISuiteException {

        final TaskStatus newTaskStatus = TaskStatus.fromJson(content);

        final List<Model> idList = db.retrieve(IDNum.class, "id", 0);
        final IDNum idObj = (IDNum) idList.get(0);

        newTaskStatus.setTaskStatusID(idObj.getAndIncID());

        if (!db.save(newTaskStatus, s.getProject())) {
            throw new WPISuiteException("Taskstatus save to database failed!");
        }

        return newTaskStatus;
    }

    /*
     * Individual messages cannot be retrieved. This message always throws an exception.
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getEntity(edu.wpi.cs.wpisuitetng .Session,
     * java.lang.String)
     */
    @Override
    public TaskStatus[] getEntity(Session s, String id) throws WPISuiteException {
        final List<Model> taskstatuses =
                db.retrieve(WorkFlow.class, "id", Integer.parseInt(id), s.getProject());
        return taskstatuses.toArray(new TaskStatus[0]);
    }

    /**
     * Retrieves all TaskStatus from the given session database
     *
     * @param s Session which is querying the server
     * @return all TaskStatus in the session database
     */
    @Override
    public TaskStatus[] getAll(Session s) {
        // Retrieve all TaskStatus (no arguments specified)
        final List<Model> workflows = db.retrieveAll(new WorkFlow() {}, s.getProject());

        // Convert the List into an array
        return workflows.toArray(new TaskStatus[0]);
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#update(edu.wpi.cs.wpisuitetng .Session,
     * java.lang.String)
     */
    @Override
    public TaskStatus update(Session s, String content) throws WPISuiteException {
        final TaskStatus updatedTaskStatus = TaskStatus.fromJson(content);

        // Retrieve the original TaskStatus
        final List<Model> oldTaskStatus =
                db.retrieve(TaskStatus.class, "id", updatedTaskStatus.getTaskStatusID(),
                        s.getProject());
        if (oldTaskStatus.size() < 1 || oldTaskStatus.get(0) == null) {
            throw new BadRequestException("TaskStatus with ID does not exist.");
        }

        // Update the original TaskStatus with new values
        final TaskStatus existingTaskStatus = (TaskStatus) oldTaskStatus.get(0);
        existingTaskStatus.update(updatedTaskStatus);

        // Save the original TaskStatus, now updated
        if (!db.save(existingTaskStatus, s.getProject())) {
            throw new WPISuiteException("Taskstatus save to database failed!");
        }
        return existingTaskStatus;
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#save(edu.wpi.cs.wpisuitetng .Session,
     * edu.wpi.cs.wpisuitetng.modules.Model)
     */
    @Override
    public void save(Session s, TaskStatus model) {
        db.save(model);
    }

    /**
     * Deletes the TaskStatus with the given id, if the session has ADMIN permissions
     *
     * @param s Session which is querying the server
     * @param id ID number of the WorkFlow to be deleted
     * @return The deleted WorkFlow
     * @throws WPISuiteException
     */
    @Override
    public boolean deleteEntity(Session s, String id) throws WPISuiteException {
        ensureRole(s, Role.ADMIN);
        final TaskStatus deletedObject = db.delete(getEntity(s, id)[0]);
        return (deletedObject != null);
    }

    // TaskStatusManager does not support deleting all tasks at once
    @Override
    public void deleteAll(Session s) throws WPISuiteException {
        throw new WPISuiteException("Cannot delete all taskstatuses at once!");
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#Count()
     */
    @Override
    public int Count() {
        // Return the number of TaskStatuses currently in the database
        return db.retrieveAll(new TaskStatus(null)).size();
    }

    /**
     * Gets all WorkFlow where the property args[0] has the value args[1]
     *
     * @param s Session which is querying the server
     * @param args Array of arguments sent in the request
     * @return List of WorkFlow that have the desired value for the given field
     */
    @Override
    public String advancedGet(Session s, String[] args) {
        return null;
    }

    @Override
    public String advancedPut(Session s, String[] args, String content) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String advancedPost(Session s, String string, String content) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Ensures that a user is of the specified role Originally written for RequirementsManager,
     * should probably be a common library
     *
     * @param session the session
     * @param role the role being verified
     * @throws WPISuiteException user isn't authorized for the given role
     */
    private void ensureRole(Session session, Role role) throws WPISuiteException {
        final User user = (User) db.retrieve(User.class, "username", session.getUsername()).get(0);
        if (!user.getRole().equals(role)) {
            throw new UnauthorizedException("User is not authorized for the given role.");
        }
    }

}
