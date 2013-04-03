package edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.Iterations;

import java.util.Date;
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
 */
public class IterationEntityManager implements EntityManager<RequirementIteration> {

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
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#makeEntity(edu.wpi.cs.wpisuitetng.Session, java.lang.String)
	 */
	@Override
	public RequirementIteration makeEntity(Session s, String content) throws WPISuiteException {
		final RequirementIteration newIteration = RequirementIteration.fromJson(content);
		System.out.println(s.getProject());
		if(!db.save(newIteration, s.getProject())) {
			throw new WPISuiteException();
		}
		return newIteration;
	}
	
	/**
	 * Retrieves a single Iteration from the database
	 * @param s the session
	 * @param id the id number of the Iteration to retrieve
	 * @return the Iteration matching the given id
	 */
	@Override
	public RequirementIteration[] getEntity(Session s, String id) throws NotFoundException {
		final int intId = Integer.parseInt(id);
		if(intId < 1) {
			throw new NotFoundException();
		}
		RequirementIteration[] Iterations = null;
		try {
			Iterations = db.retrieve(RequirementIteration.class, "id", intId, s.getProject()).toArray(new RequirementIteration[0]);
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
	 * @return array of all stored Iterations
	 */
	@Override
	public RequirementIteration[] getAll(Session s) {
		return db.retrieveAll(new RequirementIteration(), s.getProject()).toArray(new RequirementIteration[0]);
	}

	/**
	 * Saves a data model to the database
	 * @param s the session
	 * @param model the model to be saved
	 */
	@Override
	public void save(Session s, RequirementIteration model) {
		db.save(model, s.getProject());
	}
	
	/**
	 * Ensures that a user is of the specified role
	 * @param session the session
	 * @param role the role being verified
	 * @throws WPISuiteException user isn't authorized for the given role
	 */
	private void ensureRole(Session session, Role role) throws WPISuiteException {
		User user = (User) db.retrieve(User.class, "username", session.getUsername()).get(0);
		if(!user.getRole().equals(role)) {
			throw new UnauthorizedException();
		}
	}
	
	/**
	 * Deletes a Iteration from the database
	 * @param s the session
	 * @param id the id of the Iteration to delete
	 * @return true if the deletion was successful
	 */
	@Override
	public boolean deleteEntity(Session s, String id) throws WPISuiteException {
		ensureRole(s, Role.ADMIN);
		return (db.delete(getEntity(s, id)[0]) != null) ? true : false;
	}
	
	/**
	 * Deletes all Iterations from the database
	 * @param s the session
	 */
	@Override
	public void deleteAll(Session s) throws WPISuiteException {
		ensureRole(s, Role.ADMIN);
		db.deleteAll(new RequirementIteration(), s.getProject());
	}
	
	/**
	 * Returns the number of Iterations in the database
	 * @return number of Iterations stored
	 */
	@Override
	public int Count() throws WPISuiteException {
		return db.retrieveAll(new RequirementIteration()).size();
	}

	@Override
	public RequirementIteration update(Session session, String content) throws WPISuiteException {
		
		RequirementIteration updatedIteration = RequirementIteration.fromJson(content);
		/*
		 * Because of the disconnected objects problem in db4o, we can't just save updatedDefect.
		 * We have to get the original defect from db4o, copy properties from updatedDefect,
		 * then save the original defect again.
		 */
		List<Model> oldIterations = db.retrieve(RequirementIteration.class, "id", updatedIteration.getId(), session.getProject());
		if(oldIterations.size() < 1 || oldIterations.get(0) == null) {
			throw new BadRequestException("Iteration with ID does not exist.");
		}
				
		RequirementIteration existingIteration = (RequirementIteration)oldIterations.get(0);		

		// copy values to old Iteration and fill in our changeset appropriately
		existingIteration.copyFrom(updatedIteration);
		
		if(!db.save(existingIteration, session.getProject())) {
			throw new WPISuiteException();
		}
		
		return existingIteration;
	}

	@Override
	public String advancedGet(Session arg0, String[] arg1) throws NotImplementedException {
		throw new NotImplementedException();
	}

	@Override
	public String advancedPost(Session arg0, String arg1, String arg2) throws NotImplementedException {
		throw new NotImplementedException();
	}

	@Override
	public String advancedPut(Session arg0, String[] arg1, String arg2) throws NotImplementedException {
		throw new NotImplementedException();
	}

}
