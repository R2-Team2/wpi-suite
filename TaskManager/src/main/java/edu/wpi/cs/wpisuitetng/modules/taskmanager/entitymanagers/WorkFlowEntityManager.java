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
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.WorkFlow;

/**
 * The Class TaskEntityManager.
 */
public class WorkFlowEntityManager implements EntityManager<WorkFlow> {

    private Data db;

    public WorkFlowEntityManager(Data db) {
        this.db = db;
    }

    @Override
    public WorkFlow makeEntity(Session s, String content)
            throws BadRequestException, ConflictException, WPISuiteException {

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
    @Override
    public WorkFlow[] getEntity(Session s, String id) throws NotFoundException,
    WPISuiteException {
        List<Model> workflows =
                db.retrieve(WorkFlow.class, "id", Integer.parseInt(id), s.getProject());
        return workflows.toArray(new WorkFlow[0]);
    }

    /**
     * Retrieves all WorkFlows from the given session database
     *
     * @param s Session which is querying the server
     * @return all WorkFlows in the session database
     */
    @Override
    public WorkFlow[] getAll(Session s) throws WPISuiteException {
        // Retrieve all WorkFlows (no arguments specified)
        List<Model> workflows = db.retrieveAll(new WorkFlow() {}, s.getProject());

        // Convert the List into an array
        return workflows.toArray(new WorkFlow[0]);
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#update(edu.wpi.cs.wpisuitetng .Session,
     * java.lang.String)
     */
    @Override
    public WorkFlow update(Session s, String content) throws WPISuiteException {
        WorkFlow updatedWorkFlow = WorkFlow.fromJson(content);

        // Retrieve the original WorkFlow
        List<Model> oldWorkFlow =
                db.retrieve(WorkFlow.class, "id", updatedWorkFlow.getWorkFlowID(), s.getProject());
        if (oldWorkFlow.size() < 1 || oldWorkFlow.get(0) == null) {
            throw new BadRequestException("WorkFlow with ID does not exist.");
        }

        // Update the original WorkFlow with new values
        WorkFlow existingWorkFlow = (WorkFlow) oldWorkFlow.get(0);
        existingWorkFlow.update(updatedWorkFlow);

        // Save the original WorkFlow, now updated
        if (!db.save(existingWorkFlow, s.getProject())) {
            throw new WPISuiteException();
        }
        return existingWorkFlow;
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#save(edu.wpi.cs.wpisuitetng .Session,
     * edu.wpi.cs.wpisuitetng.modules.Model)
     */
    @Override
    public void save(Session s, WorkFlow model) throws WPISuiteException {
        db.save(model);
    }

    /**
     * Deletes the WorkFlow with the given id, if the session has ADMIN permissions
     *
     * @param s Session which is querying the server
     * @param id ID number of the WorkFlow to be deleted
     * @return The deleted WorkFlow
     * @throws WPISuiteException
     */
    @Override
    public boolean deleteEntity(Session s, String id) throws WPISuiteException {
        ensureRole(s, Role.ADMIN);
        WorkFlow deletedObject = db.delete(getEntity(s, id)[0]);
        return (deletedObject != null);
    }

    // TaskManager does not support deleting all tasks at once
    @Override
    public void deleteAll(Session s) throws WPISuiteException {
        throw new WPISuiteException();
    }

    /*
     * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#Count()
     */
    @Override
    public int Count() throws WPISuiteException {
        // Return the number of PostBoardMessages currently in the database
        return db.retrieveAll(new WorkFlow()).size();
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
