package edu.wpi.cs.wpisuitetng.modules.taskmanager.entitymanagers;

import java.util.List;

import edu.wpi.cs.wpisuitetng.Session;
import edu.wpi.cs.wpisuitetng.database.Data;
import edu.wpi.cs.wpisuitetng.exceptions.BadRequestException;
import edu.wpi.cs.wpisuitetng.exceptions.ConflictException;
import edu.wpi.cs.wpisuitetng.exceptions.NotFoundException;
import edu.wpi.cs.wpisuitetng.exceptions.UnauthorizedException;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.EntityManager;
import edu.wpi.cs.wpisuitetng.modules.Model;
import edu.wpi.cs.wpisuitetng.modules.core.models.Role;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.TaskStatus;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.WorkFlow;

/**
 * The Class TaskEntityManager.
 */
public class TaskStatusEntityManager implements EntityManager<TaskStatus> {

    private Data db;

    public TaskStatusEntityManager(Data db) {
        this.db = db;
    }

    @Override
    public TaskStatus makeEntity(Session s, String content)
            throws BadRequestException, ConflictException, WPISuiteException {

        final TaskStatus newTaskStatus = TaskStatus.fromJson(content);

        if (!db.save(newTaskStatus, s.getProject())) {
            throw new WPISuiteException();
        }

        return newTaskStatus;
    }

    /*
     * Individual messages cannot be retrieved. This message always throws an exception.
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getEntity(edu.wpi.cs.wpisuitetng .Session,
     * java.lang.String)
     */
    @Override
    public TaskStatus[] getEntity(Session s, String id) throws NotFoundException,
            WPISuiteException {
        List<Model> taskstatuses =
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
    public TaskStatus[] getAll(Session s) throws WPISuiteException {
        // Retrieve all TaskStatus (no arguments specified)
        List<Model> workflows = db.retrieveAll(new WorkFlow() {}, s.getProject());

        // Convert the List into an array
        return workflows.toArray(new TaskStatus[0]);
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#update(edu.wpi.cs.wpisuitetng .Session,
     * java.lang.String)
     */
    @Override
    public TaskStatus update(Session s, String content) throws WPISuiteException {
        TaskStatus updatedTaskStatus = TaskStatus.fromJson(content);

        // Retrieve the original TaskStatus
        List<Model> oldTaskStatus =
                db.retrieve(TaskStatus.class, "id", updatedTaskStatus.getTaskStatusID(),
                        s.getProject());
        if (oldTaskStatus.size() < 1 || oldTaskStatus.get(0) == null) {
            throw new BadRequestException("TaskStatus with ID does not exist.");
        }

        // Update the original TaskStatus with new values
        TaskStatus existingTaskStatus = (TaskStatus) oldTaskStatus.get(0);
        existingTaskStatus.update(updatedTaskStatus);

        // Save the original TaskStatus, now updated
        if (!db.save(existingTaskStatus, s.getProject())) {
            throw new WPISuiteException();
        }
        return existingTaskStatus;
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#save(edu.wpi.cs.wpisuitetng .Session,
     * edu.wpi.cs.wpisuitetng.modules.Model)
     */
    @Override
    public void save(Session s, TaskStatus model) throws WPISuiteException {
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
        TaskStatus deletedObject = db.delete(getEntity(s, id)[0]);
        return (deletedObject != null);
    }

    // TaskStatusManager does not support deleting all tasks at once
    @Override
    public void deleteAll(Session s) throws WPISuiteException {
        throw new WPISuiteException();
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#Count()
     */
    @Override
    public int Count() throws WPISuiteException {
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
    public String advancedGet(Session s, String[] args)
            throws WPISuiteException {
        return null;
    }

    @Override
    public String advancedPut(Session s, String[] args, String content)
            throws WPISuiteException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String advancedPost(Session s, String string, String content)
            throws WPISuiteException {
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
        User user = (User) db.retrieve(User.class, "username", session.getUsername()).get(0);
        if (!user.getRole().equals(role)) {
            throw new UnauthorizedException();
        }
    }

}
