/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations;

import java.util.List;

import edu.wpi.cs.wpisuitetng.Session;
import edu.wpi.cs.wpisuitetng.database.Data;
import edu.wpi.cs.wpisuitetng.exceptions.BadRequestException;
import edu.wpi.cs.wpisuitetng.exceptions.NotFoundException;
import edu.wpi.cs.wpisuitetng.exceptions.NotImplementedException;
import edu.wpi.cs.wpisuitetng.exceptions.UnauthorizedException;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.EntityManager;
import edu.wpi.cs.wpisuitetng.modules.Model;
import edu.wpi.cs.wpisuitetng.modules.core.models.Role;
import edu.wpi.cs.wpisuitetng.modules.core.models.User;

/**
 * This is the entity manager for the Iteration in the
 * IterationManager module.
 *
 * @version $Revision: 1.0 $
 * @author justinhess
 */
public class IterationEntityManager implements EntityManager<Iteration> {

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
	public IterationEntityManager(Data db) {
		this.db = db; 
	}

	/**
	 * Saves a Iteration when it is received from a client
	 * 
	
	 * @param s the session
	 * @param content an iteration that has been converted to Json
	
	
	
	 * @return the iteration passed into the method converted from Json to an iteration * @throws WPISuiteException * @throws WPISuiteException * @throws WPISuiteException
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#makeEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String) */
	@Override
	public Iteration makeEntity(Session s, String content) throws WPISuiteException {
		final Iteration newIteration = Iteration.fromJson(content);
		if(!db.save(newIteration, s.getProject())) {
			throw new WPISuiteException();
		}
		return newIteration;
	}
	
	/**
	 * Retrieves a single Iteration from the database
	 * @param s the session
	 * @param id the id number of the Iteration to retrieve
	
	
	
	
	 * @return the Iteration matching the given id * @throws NotFoundException * @throws NotFoundException * @throws NotFoundException
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getEntity(Session, String) */
	@Override
	public Iteration[] getEntity(Session s, String id) throws NotFoundException {
		final int intId = Integer.parseInt(id);
		if(intId < 1) {
			throw new NotFoundException();
		}
		Iteration[] Iterations = null;
		try {
			Iterations = db.retrieve(Iteration.class, "id", intId, s.getProject()).toArray(new Iteration[0]);
		} catch (WPISuiteException e) {
			e.printStackTrace();
		}
		if(Iterations.length < 1 || Iterations[0] == null) {
			throw new NotFoundException();
		}
		return Iterations;
	}

	/**
	 * Retrieves all Iterations from the database
	 * @param s the session
	
	
	
	 * @return array of all stored Iterations * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getAll(Session) * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getAll(Session) * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#getAll(Session)
	 */
	@Override
	public Iteration[] getAll(Session s) {
		return db.retrieveAll(new Iteration(), s.getProject()).toArray(new Iteration[0]);
	}

	/**
	 * Saves a data model to the database
	 * @param s the session
	 * @param model the model to be saved
	 */
	@Override
	public void save(Session s, Iteration model) {
		db.save(model, s.getProject());
	}
	
	/**
	 * Ensures that a user is of the specified role
	 * @param session the session
	 * @param role the role being verified
	
	 * @throws WPISuiteException user isn't authorized for the given role */
	private void ensureRole(Session session, Role role) throws WPISuiteException {
		User[] userArray = new User[2];
		User user = db.retrieve(User.class, "username", session.getUsername()).toArray(userArray)[0];
		if(!user.getRole().equals(role)) {
			throw new UnauthorizedException();
		}
	}
	
	/**
	 * Deletes a Iteration from the database
	 * @param s the session
	 * @param id the id of the Iteration to delete
	
	
	
	
	 * @return true if the deletion was successful * @throws WPISuiteException * @throws WPISuiteException * @throws WPISuiteException
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#deleteEntity(Session, String) */
	@Override
	public boolean deleteEntity(Session s, String id) throws WPISuiteException {
		ensureRole(s, Role.ADMIN);
		return (db.delete(getEntity(s, id)[0]) != null) ? true : false;
	}
	
	/**
	 * Deletes all Iterations from the database
	 * @param s the session
	
	
	 * @throws WPISuiteException * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#deleteAll(Session) * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#deleteAll(Session)
	 */
	@Override
	public void deleteAll(Session s) throws WPISuiteException {
		ensureRole(s, Role.ADMIN);
		db.deleteAll(new Iteration(), s.getProject());
	}
	
	/**
	 * Returns the number of Iterations in the database
	
	
	
	
	 * @return number of Iterations stored * @throws WPISuiteException * @throws WPISuiteException * @throws WPISuiteException
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#Count() */
	@Override
	public int Count() throws WPISuiteException {
		return db.retrieveAll(new Iteration()).size();
	}

	/**
	 * Updates the given iteration in the database
	 * @param session the session the iteration to be updated is in
	 * @param content the updated iteration as a Json string
	
	
	
	
	 * @return the old iteration prior to updating * @throws WPISuiteException * @throws WPISuiteException * @throws WPISuiteException
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#update(Session, String) */
	@Override
	public Iteration update(Session session, String content) throws WPISuiteException {
		
		Iteration updatedIteration = Iteration.fromJson(content);
		/*
		 * Because of the disconnected objects problem in db4o, we can't just save updatedIteration.
		 * We have to get the original iteration from db4o, copy properties from updatedIteration,
		 * then save the original iteration again.
		 */
		List<Model> oldIterations = db.retrieve(Iteration.class, "id", updatedIteration.getId(), session.getProject());
		if(oldIterations.size() < 1 || oldIterations.get(0) == null) {
			throw new BadRequestException("Iteration with ID does not exist.");
		}
				
		Iteration existingIteration = (Iteration)oldIterations.get(0);		

		// copy values to old Iteration and fill in our changeset appropriately
		existingIteration.copyFrom(updatedIteration);
		
		if(!db.save(existingIteration, session.getProject())) {
			throw new WPISuiteException();
		}
		
		return existingIteration;
	}

	/**
	 * Method advancedGet.
	 * @param arg0 Session
	 * @param arg1 String[]
	
	
	
	 * @return String * @throws NotImplementedException * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedGet(Session, String[]) * @throws NotImplementedException
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedGet(Session, String[])
	 */
	@Override
	public String advancedGet(Session arg0, String[] arg1) throws NotImplementedException {
		throw new NotImplementedException();
	}

	/**
	 * Method advancedPost.
	 * @param arg0 Session
	 * @param arg1 String
	 * @param arg2 String
	
	
	
	 * @return String * @throws NotImplementedException * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedPost(Session, String, String) * @throws NotImplementedException
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedPost(Session, String, String)
	 */
	@Override
	public String advancedPost(Session arg0, String arg1, String arg2) throws NotImplementedException {
		throw new NotImplementedException();
	}

	/**
	 * Method advancedPut.
	 * @param arg0 Session
	 * @param arg1 String[]
	 * @param arg2 String
	
	
	
	 * @return String * @throws NotImplementedException * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedPut(Session, String[], String) * @throws NotImplementedException
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#advancedPut(Session, String[], String)
	 */
	@Override
	public String advancedPut(Session arg0, String[] arg1, String arg2) throws NotImplementedException {
		throw new NotImplementedException();
	}

}
