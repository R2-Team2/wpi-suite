package edu.wpi.cs.wpisuitetng.modules.RequirementManager.models;

import java.util.List;

import edu.wpi.cs.wpisuitetng.Session;
import edu.wpi.cs.wpisuitetng.database.Data;
import edu.wpi.cs.wpisuitetng.exceptions.BadRequestException;
import edu.wpi.cs.wpisuitetng.exceptions.ConflictException;
import edu.wpi.cs.wpisuitetng.exceptions.NotFoundException;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.EntityManager;
import edu.wpi.cs.wpisuitetng.modules.Model;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.Requirement;

/**
 * This is the entity manager for the Requirements in the
 * RequirementManager module.
 * 
 * @author Dylan
 *
 */

public class RequirementEntityManager implements EntityManager<Requirement> {

	/** The database */
	Data db;
	
	/**
	 * Constructs the entity manager. This constructor is called by
	 * {@link edu.wpi.cs.wpisuitetng.ManagerLayer#ManagerLayer()}. To make sure
	 * this happens, be sure to place add this entity manager to the map in
	 * the ManagerLayer file.
	 * 
	 * @param db a reference to the persistent database
	 */
	public RequirementEntityManager(Data db) {
		this.db = db;
	}
	
	/**
	 * Saves a Requirement when it is received from a client
	 * 
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#makeEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
	 */
	@Override
	public Requirement makeEntity(Session s, String content) throws BadRequestException,
			ConflictException, WPISuiteException {
		// Parse the message from JSON
		final Requirement newMessage = Requirement.fromJson(content);

		// Save the message in the database if possible, otherwise throw an exception
		// We want the message to be associated with the project the user logged in to
		if (!db.save(newMessage, s.getProject())) {
			throw new WPISuiteException();
		}

		// Return the newly created message (this gets passed back to the client)
		return newMessage;
	}

	/**
	 * Individual requirements cannot be retrieved. This method always throws an exception.
	 * 
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
	 */
	@Override
	public Requirement[] getEntity(Session s, String id) throws NotFoundException,
			WPISuiteException {
		throw new WPISuiteException();
	}

	/** 
	 * Returns all of the requirements that have been stored.
	 * 
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getAll(edu.wpi.cs.wpisuitetng.Session)
	 */
	@Override
	public Requirement[] getAll(Session s) throws WPISuiteException {
		// Ask the database to retrieve all objects of the type Requirement.
		// Passing a dummy Requirement lets the db know what type of object to retrieve
		// Passing the project makes it only get requirements from that project
		List<Model> messages = db.retrieveAll(new Requirement(null, null, null, null, 0, 0), s.getProject());

		// Return the list of messages as an array
		return messages.toArray(new Requirement[0]);
	}

	/**
	 * Requirements cannot be updated. This method always throws an exception.
	 * 
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#update(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
	 */
	@Override
	public Requirement update(Session s, String content) throws WPISuiteException {
		throw new WPISuiteException();
	}

	/**
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#save(edu.wpi.cs.wpisuitetng.Session, edu.wpi.cs.wpisuitetng.modules.Model)
	 */
	@Override
	public void save(Session s, Requirement model) throws WPISuiteException {
		// Save the given defect in the database
		db.save(model);
	}

	/*
	 * Requirements cannot be deleted
	 * 
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#deleteEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
	 */
	@Override
	public boolean deleteEntity(Session s, String id) throws WPISuiteException {
		// This module does not allow Requirements to be deleted, so throw an exception
		throw new WPISuiteException();
	}

	@Override
	public String advancedGet(Session s, String[] args)
			throws WPISuiteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAll(Session s) throws WPISuiteException {
		// This module does not allow Requirements to be deleted, so throw an exception
		throw new WPISuiteException();
	}

	@Override
	public int Count() throws WPISuiteException {
		// Return the number of PostBoardMessages currently in the database
		return db.retrieveAll(new Requirement(null, null, null, null, 0, 0)).size();
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

}
