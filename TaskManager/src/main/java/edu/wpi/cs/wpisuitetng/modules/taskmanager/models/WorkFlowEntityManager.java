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
public class WorkFlowEntityManager implements EntityManager<WorkFlow> {

    /** The db. */
    private final Data db;

    /**
     * Instantiates a new task entity manager.
     *
     * @param db the db
     */
    public WorkFlowEntityManager(Data db) {
        this.db = db;
    }

    /*
     * (non-Javadoc)
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#makeEntity(edu.wpi.cs.wpisuitetng.Session,
     * java.lang.String)
     */
    @Override
    public WorkFlow makeEntity(Session s, String content) throws WPISuiteException {

        final WorkFlow newWorkFlow = WorkFlow.fromJson(content);

        if (!db.save(newWorkFlow, s.getProject())) {
            throw new WPISuiteException();
        }

        return newWorkFlow;
    }

    /*
     * Individual messages cannot be retrieved. This message always throws an exception.
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getEntity(edu.wpi.cs.wpisuitetng .Session,
     * java.lang.String)
     */
    /*
     * (non-Javadoc)
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getEntity(edu.wpi.cs.wpisuitetng.Session,
     * java.lang.String)
     */
    @Override
    public WorkFlow[] getEntity(Session s, String id) throws WPISuiteException {
        final List<Model> workFlows =
                db.retrieve(WorkFlow.class, "id", Integer.parseInt(id), s.getProject());
        return workFlows.toArray(new WorkFlow[0]);
    }

    /**
     * Retrieves all Tasks from the given session database.
     *
     * @param s Session which is querying the server
     * @return all Tasks in the session database
     */
    @Override
    public WorkFlow[] getAll(Session s) {
        // Retrieve all WorkFlows (no arguments specified)
        final List<Model> workFlows = db.retrieveAll(new WorkFlow(), s.getProject());

        // Convert the List into an array
        return workFlows.toArray(new WorkFlow[0]);
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#update(edu.wpi.cs.wpisuitetng .Session,
     * java.lang.String)
     */
    /*
     * (non-Javadoc)
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#update(edu.wpi.cs.wpisuitetng.Session,
     * java.lang.String)
     */
    @Override
    public WorkFlow update(Session s, String content) throws WPISuiteException {
        final WorkFlow updatedWorkFlow = WorkFlow.fromJson(content);

        // Retrieve the original Task
        final List<Model> oldWorkFlows =
                db.retrieve(WorkFlow.class, "id", updatedWorkFlow.getWorkFlowID(), s.getProject());
        if (oldWorkFlows.size() < 1 || oldWorkFlows.get(0) == null) {
            throw new BadRequestException("WorkFlow with ID does not exist.");
        }

        // Update the original Task with new values
        final WorkFlow existingWorkFlow = (WorkFlow) oldWorkFlows.get(0);
        existingWorkFlow.update(updatedWorkFlow);

        // Save the original Task, now updated
        if (!db.save(existingWorkFlow, s.getProject())) {
            throw new WPISuiteException();
        }
        return existingWorkFlow;
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#save(edu.wpi.cs.wpisuitetng .Session,
     * edu.wpi.cs.wpisuitetng.modules.Model)
     */
    /*
     * (non-Javadoc)
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#save(edu.wpi.cs.wpisuitetng.Session,
     * edu.wpi.cs.wpisuitetng.modules.Model)
     */
    /**
     * Method save.
     *
     * @param s Session
     * @param model WorkFlow
     */
    @Override
    public void save(Session s, WorkFlow model) {
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
        final WorkFlow deletedObject = db.delete(getEntity(s, id)[0]);
        return (deletedObject != null);
    }

    // TaskManager does not support deleting all tasks at once
    /*
     * (non-Javadoc)
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#deleteAll(edu.wpi.cs.wpisuitetng.Session)
     */
    @Override
    public void deleteAll(Session s) throws WPISuiteException {
        throw new WPISuiteException();
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#Count()
     */
    /*
     * (non-Javadoc)
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#Count()
     */
    @Override
    public int Count() {
        // Return the number of PostBoardMessages currently in the database
        return db.retrieveAll(new WorkFlow()).size();
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
    public String advancedGet(Session s, String[] args)
            throws WPISuiteException {
        final List<Model> workflows;
        if (args.length < 2) {
            throw new WPISuiteException();
        }

        switch (args[0]) {
            case "status":
                workflows = db.retrieve(WorkFlow.class, "status", "new", s.getProject());
                break;
            default:
                throw new WPISuiteException();
        }
        return new Gson().toJson(workflows.toArray(new WorkFlow[0]), WorkFlow[].class);
    }

    /*
     * (non-Javadoc)
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedPut(edu.wpi.cs.wpisuitetng.Session,
     * java.lang.String[], java.lang.String)
     */
    @Override
    public String advancedPut(Session s, String[] args, String content) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * @see
     * edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedPost(edu.wpi.cs.wpisuitetng.Session,
     * java.lang.String, java.lang.String)
     */
    @Override
    public String advancedPost(Session s, String string, String content) {
        // TODO Auto-generated method stub
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
            throw new UnauthorizedException();
        }
    }
}
