/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.entitymanagers;

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
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.IDNum;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.TaskStatus;


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
        System.out.println("begin make entity");
        final Task newMessage = Task.fromJson(content);
        final List<Model> idList = db.retrieveAll(new IDNum(db));
        // List<Model> idList = db.retrieve(IDNum.class, "db", this.db, s.getProject());

        final IDNum[] idArry = idList.toArray(new IDNum[0]);
        if (idArry.length == 0) {
            // System.out.println("Creating new IDNum object");
            // Initialize ID
            final IDNum idStore = new IDNum(db);
            db.save(idStore);

            newMessage.setTaskID(idStore.getAndIncID());
            // System.out.println("gave task new id: " + newMessage.toJson());

            if (!db.save(newMessage, s.getProject())) {
                throw new WPISuiteException("Unable to save TNG");
            }

            db.save(newMessage, s.getProject());

            return newMessage;
        } else {
            // System.out.println("retrieved id list");
            // System.out.println("id object: " + idArry[0].toJson());
            newMessage.setTaskID(idArry[0].getAndIncID());

            if (!db.save(newMessage, s.getProject())) {
                throw new WPISuiteException("Unable to save TNG");
            }

            while (!updateTaskStatus(s, newMessage)) {
            }

            // add update task status functionality
            // List<Model> tsList = db.retrieveAll(new TaskStatus(null));
            // TaskStatus[] tsArray = tsList.toArray(new TaskStatus[0]);

            // if (tsArray.length == 0 || tsArray[0] == null) {
            // System.out.println("Error: No tasks updated for new task!");
            // } else {
            /*
             * for (int i = 0; i < tsArray.length; i++) { System.out.println("tsArray.getName: -" +
             * tsArray[i].getName() + "- task.getStatus: -" + newMessage.getStatus() + "-"); if
             * (tsArray[i].getName().equals(newMessage.getStatus())) {
             * System.out.println("Found matching Task Status"); TaskStatus updatedTS =
             * tsArray[i].addTask(newMessage); tsArray[i].upd(updatedTS);
             * System.out.println(tsArray[i].toJson()); db.save(tsArray[i], s.getProject());
             * System.out.println("");
             * System.out.println("Do We Get here to end of update Task Status?");
             * System.out.println(""); } // } }
             */
            db.save(newMessage, s.getProject());
            return newMessage;
        }
    }

    /**
     * updates the task status to include a reference to this task.
     *
     * @param s is the session
     * @param aTask task to add in task status.
     * @return boolean flag to tell whether sucessfull.
     */
    public boolean updateTaskStatus(Session s, Task aTask) {
        System.out.println("Updating Task Status from New Task");
        boolean flag = false;
        List<Model> tsList = db.retrieveAll(new TaskStatus(null));
        TaskStatus[] tsArray = tsList.toArray(new TaskStatus[0]);

        if (tsArray.length == 0 || tsArray[0] == null) {
            System.out.println("Error: No tasks updated for new task!");
            return flag = false;
        } else {
            for (int i = 0; i < tsList.size(); i++) {
                if (tsArray[i].getName().equals(aTask.getStatus())) {
                    System.out.println("Found matching Task Status");
                    TaskStatus updatedTS = tsArray[i].addTask(aTask);
                    System.out.println("tsArray[i] before update: " + tsArray[i].toJson());
                    tsArray[i].update(updatedTS);
                    System.out.println("tsArray[i] after update: " + tsArray[i].toJson());

                    System.out.println("Task Update Successful");
                    db.save(tsArray[i], s.getProject());
                    return flag = true;
                }
            }
            return flag;
        }
    }

    @Override
    public Task[] getEntity(Session s, String id) throws WPISuiteException {
        final List<Model> tasks =
                db.retrieve(Task.class, "taskID", Integer.parseInt(id), s.getProject());
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
                db.retrieveAll(new Task(0, "", "", 0, 0, null, "", null, null, null, null),
                        s.getProject());

        // Convert the List into an array
        return tasks.toArray(new Task[0]);
    }

    @Override
    public Task update(Session s, String content) throws WPISuiteException {
        // System.out.println("update method called in Task Entity Manager");

        final Task updatedTask = Task.fromJson(content);
        System.out.println("updatedTask: " + updatedTask.toJson());

        // Retrieve the original Task
        final List<Model> oldTasks =
                db.retrieve(Task.class, "taskID", updatedTask.getTaskID(), s.getProject());
        if (oldTasks.size() < 1 || oldTasks.get(0) == null) {
            throw new BadRequestException("Task with ID does not exist.");
        }

        final Task existingTask = (Task) oldTasks.get(0);

        // copy values to old requirement and fill in our changeset appropriately
        existingTask.copyFrom(updatedTask);

        if (!db.save(existingTask, s.getProject())) {
            throw new WPISuiteException("Task save to database failed!");
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
        System.out.println("Task Entity Manager is Saving");
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
        return db.retrieveAll(new Task(0, null, null, 0, 0, null, null, null, null, null, null))
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
        System.out.println("Task Entity Manager is in advancedPost");

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
