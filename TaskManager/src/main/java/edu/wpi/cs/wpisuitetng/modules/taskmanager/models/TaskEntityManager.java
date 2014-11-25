/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.models;

import java.util.List;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.Session;
import edu.wpi.cs.wpisuitetng.database.Data;
import edu.wpi.cs.wpisuitetng.exceptions.BadRequestException;
import edu.wpi.cs.wpisuitetng.exceptions.UnauthorizedException;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.EntityManager;
import edu.wpi.cs.wpisuitetng.modules.Model;
import edu.wpi.cs.wpisuitetng.modules.core.models.Role;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

// TODO: Auto-generated Javadoc
/**
 * The Class TaskEntityManager.
 *
 * @author R2-Team2
 * @version $Revision: 1.0 $
 */
public class TaskEntityManager implements EntityManager<Task> {

    /** The db. */
    private final Data db;

    /**
     * Instantiates a new task entity manager.
     *
     * @param db the db
     */
    public TaskEntityManager(Data db) {
        this.db = db;
    }

    @Override
    public Task makeEntity(Session s, String content) throws WPISuiteException {

        final Task newMessage = Task.fromJson(content);

        if (!db.save(newMessage, s.getProject())) {
            throw new WPISuiteException("Unable to save TNG");
        }

        return newMessage;
    }

    @Override
    public Task[] getEntity(Session s, String id) throws WPISuiteException {
        final List<Model> tasks =
                db.retrieve(Task.class, "id", Integer.parseInt(id), s.getProject());
        return tasks.toArray(new Task[0]);
    }

    /**
     * Retrieves all Tasks from the given session database.
     *
     * @param s Session which is querying the server
     * @return all Tasks in the session database
     */
    @Override
    public Task[] getAll(Session s) {
        // Retrieve all Tasks (no arguments specified)
        final List<Model> tasks =
                db.retrieveAll(new Task(0, "", "", 0, 0, new TaskStatus("new"), "", null, null,
                        null), s.getProject());

        // Convert the List into an array
        return tasks.toArray(new Task[0]);
    }

    @Override
    public Task update(Session s, String content) throws WPISuiteException {
        final Task updatedTask = Task.fromJson(content);

        // Retrieve the original Task
        final List<Model> oldTasks =
                db.retrieve(Task.class, "id", updatedTask.getTaskID(), s.getProject());
        if (oldTasks.size() < 1 || oldTasks.get(0) == null) {
            throw new BadRequestException("Task with ID does not exist.");
        }

        // Update the original Task with new values
        final Task existingTask = (Task) oldTasks.get(0);
        existingTask.update(updatedTask);

        // Save the original Task, now updated
        if (!db.save(existingTask, s.getProject())) {
            throw new WPISuiteException("Unable to save TNG");
        }
        return existingTask;
    }

    /**
     * Method save.
     *
     * @param s Session
     * @param model Task
     */
    @Override
    public void save(Session s, Task model) {
        db.save(model);
    }

    /**
     * Deletes the Task with the given id, if the session has ADMIN permissions.
     *
     * @param s Session which is querying the server
     * @param id ID number of the Task to be deleted
     * @return The deleted Task
     * @throws WPISuiteException the WPI suite exception
     */
    @Override
    public boolean deleteEntity(Session s, String id) throws WPISuiteException {
        ensureRole(s, Role.ADMIN);
        final Task deletedObject = db.delete(getEntity(s, id)[0]);
        return (deletedObject != null);
    }


    // TaskManager does not support deleting all tasks at once
    @Override
    public void deleteAll(Session s) throws WPISuiteException {
        throw new WPISuiteException("Unable to delete everything in session");
    }

    // Return the number of PostBoardMessages currently in the database
    @Override
    public int Count() {
        return db.retrieveAll(
                new Task(0, null, null, 0, 0, new TaskStatus("new"), null, null, null, null))
                .size();
    }

    /**
     * Gets all Tasks where the property args[0] has the value args[1].
     *
     * @param s Session which is querying the server
     * @param args Array of arguments sent in the request
     * @return List of Tasks that have the desired value for the given field
     * @throws WPISuiteException the WPI suite exception
     */
    @Override
    public String advancedGet(Session s, String[] args) throws WPISuiteException {
        final List<Model> tasks;
        if (args.length < 2) {
            throw new WPISuiteException("Too few arguments");
        }

        switch (args[0]) {
            case "status":
                tasks = db.retrieve(Task.class, "status", "new", s.getProject());
                break;
            default:
                throw new WPISuiteException("first argument is sobmething other than status");
        }
        return new Gson().toJson(tasks.toArray(new Task[0]), Task[].class);
    }

    @Override
    public String advancedPut(Session s, String[] args, String content) {
        return null;
    }

    @Override
    public String advancedPost(Session s, String string, String content) {
        return null;
    }

    /**
     * Ensures that a user is of the specified role Originally written for RequirementsManager,
     * should probably be a common library.
     *
     * @param session the session
     * @param role the role being verified
     * @throws WPISuiteException user isn't authorized for the given role
     */
    private void ensureRole(Session session, Role role) throws WPISuiteException {
        final User user = (User) db.retrieve(User.class, "username", session.getUsername()).get(0);
        if (!user.getRole().equals(role)) {
            throw new UnauthorizedException("User is unauthorized");
        }
    }
}
