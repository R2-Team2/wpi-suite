/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Team
 * R2-Team2
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.taskmanager.entitymanagers;

import java.util.ArrayList;
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
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.IDNum;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.TaskStatus;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.WorkFlow;

/**
 * The Class TaskEntityManager.
 */
public class WorkFlowEntityManager implements EntityManager<WorkFlow> {

	final private Data db;

	/**
	 * Constructor for the workflwo entity manager
	 * @param db interface to the database to task management.
	 */
	public WorkFlowEntityManager(Data db) {
		this.db = db;
	}

	@Override
	public WorkFlow makeEntity(Session s, String content)
			throws BadRequestException, ConflictException, WPISuiteException {

		final WorkFlow newWorkFlow = WorkFlow.fromJson(content);
		if(newWorkFlow.getTaskStatusList()==null){
			final WorkFlow defaultWF = newWorkFlow;
			
			//Initialize ID
			final IDNum idStore = new IDNum(db);
			db.save(idStore);

			//set default workflow id to 0.
			defaultWF.setWorkFlowID(idStore.getAndIncID());

			//Create Default Task Statuses
			final TaskStatus newStatus = new TaskStatus("New");
			newStatus.setTaskStatusID(idStore.getAndIncID());
			final TaskStatus selected = new TaskStatus("Selected for Development");
			selected.setTaskStatusID(idStore.getAndIncID());
			final TaskStatus develop = new TaskStatus("Currently in Development");
			develop.setTaskStatusID(idStore.getAndIncID());
			final TaskStatus completed = new TaskStatus("Completed");
			completed.setTaskStatusID(idStore.getAndIncID());

			//Save Default Task Statuses
			db.save(newStatus, s.getProject());
			db.save(selected, s.getProject());
			db.save(develop, s.getProject());
			db.save(completed, s.getProject());

			//Add TaskStatus ID's to WorkFlow object
			final ArrayList<Long> defaultTSid = new ArrayList<Long>();
			defaultTSid.add(newStatus.getTaskStatusID());
			defaultTSid.add(selected.getTaskStatusID());
			defaultTSid.add(develop.getTaskStatusID());
			defaultTSid.add(completed.getTaskStatusID());

			defaultWF.setTaskStatusList(defaultTSid);

			db.save(defaultWF, s.getProject());
			
			return defaultWF;
		}
		else
		{
			final List<Model> idList = db.retrieve(IDNum.class, "id", 0);
			final IDNum idObj = (IDNum) idList.get(0);

			newWorkFlow.setWorkFlowID(idObj.getAndIncID());

			if (!db.save(newWorkFlow, s.getProject())) {
				throw new WPISuiteException();
			}

			return newWorkFlow;
		}
	}

	/*
	 * Individual messages cannot be retrieved. This message always throws an exception.
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getEntity(edu.wpi.cs.wpisuitetng .Session,
	 * java.lang.String)
	 */
	@Override
	public WorkFlow[] getEntity(Session s, String id) throws NotFoundException,
	WPISuiteException {
		final List<Model> workflows =
				db.retrieve(WorkFlow.class, "id", Integer.parseInt(id), s.getProject());

		//Create default workflow if none exist:
		if (workflows.toArray(new WorkFlow[0])==null){
			final WorkFlow defaultWF = new WorkFlow();

			//Initialize ID
			final IDNum idStore = new IDNum(db);
			db.save(idStore);

			//set default workflow id to 0.
			defaultWF.setWorkFlowID(idStore.getAndIncID());

			//Create Default Task Statuses
			final TaskStatus newStatus = new TaskStatus("New");
			newStatus.setTaskStatusID(idStore.getAndIncID());
			final TaskStatus selected = new TaskStatus("Selected for Development");
			selected.setTaskStatusID(idStore.getAndIncID());
			final TaskStatus develop = new TaskStatus("Currently in Development");
			develop.setTaskStatusID(idStore.getAndIncID());
			final TaskStatus completed = new TaskStatus("Completed");
			completed.setTaskStatusID(idStore.getAndIncID());

			//Save Default Task Statuses
			db.save(newStatus, s.getProject());
			db.save(selected, s.getProject());
			db.save(develop, s.getProject());
			db.save(completed, s.getProject());

			//Add TaskStatus ID's to WorkFlow object
			final ArrayList<Long> defaultTSid = new ArrayList<Long>();
			defaultTSid.add(newStatus.getTaskStatusID());
			defaultTSid.add(selected.getTaskStatusID());
			defaultTSid.add(develop.getTaskStatusID());
			defaultTSid.add(completed.getTaskStatusID());

			defaultWF.setTaskStatusList(defaultTSid);

			db.save(defaultWF, s.getProject());

			final WorkFlow[] returnArry = new WorkFlow[1];
			returnArry[0] = defaultWF;
			System.out.println("New Workflow and Default TaskStatuses created.");
			return returnArry;
		}

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
		final List<Model> workflows = db.retrieveAll(new WorkFlow() {}, s.getProject());

		// Convert the List into an array
		return workflows.toArray(new WorkFlow[0]);
	}

	/*
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#update(edu.wpi.cs.wpisuitetng .Session,
	 * java.lang.String)
	 */
	@Override
	public WorkFlow update(Session s, String content) throws WPISuiteException {
		final WorkFlow updatedWorkFlow = WorkFlow.fromJson(content);

		// Retrieve the original WorkFlow
		final List<Model> oldWorkFlow =
				db.retrieve(WorkFlow.class, "id", updatedWorkFlow.getWorkFlowID(), s.getProject());
		if (oldWorkFlow.size() < 1 || oldWorkFlow.get(0) == null) {
			throw new BadRequestException("WorkFlow with ID does not exist.");
		}

		// Update the original WorkFlow with new values
		final WorkFlow existingWorkFlow = (WorkFlow) oldWorkFlow.get(0);
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
		final WorkFlow deletedObject = db.delete(getEntity(s, id)[0]);
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
		final User user = (User) db.retrieve(User.class, "username", session.getUsername()).get(0);
		if (!user.getRole().equals(role)) {
			throw new UnauthorizedException();
		}
	}

}
