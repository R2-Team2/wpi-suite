/*******************************************************************************
 * Copyright (c) 2012 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    R2-Team2
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.models;

import java.util.List;

import edu.wpi.cs.wpisuitetng.Session;
import edu.wpi.cs.wpisuitetng.exceptions.BadRequestException;
import edu.wpi.cs.wpisuitetng.exceptions.ConflictException;
import edu.wpi.cs.wpisuitetng.exceptions.NotFoundException;
import edu.wpi.cs.wpisuitetng.exceptions.WPISuiteException;
import edu.wpi.cs.wpisuitetng.modules.EntityManager;
import edu.wpi.cs.wpisuitetng.modules.Model;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.models.Task;
import edu.wpi.cs.wpisuitetng.database.Data;

public class TaskEntityManager implements EntityManager<Task> {

	private Data db;

	public TaskEntityManager(Data db) {
		this.db = db;
	}

	@Override
	public Task makeEntity(Session s, String content)
			throws BadRequestException, ConflictException, WPISuiteException {

		final Task newMessage = Task.fromJson(content);

		if (!db.save(newMessage, s.getProject())) {
			throw new WPISuiteException();
		}

		return newMessage;
	}

	/*
	 * Individual messages cannot be retrieved. This message always throws an
	 * exception.
	 * 
	 * @see
	 * edu.wpi.cs.wpisuitetng.modules.EntityManager#getEntity(edu.wpi.cs.wpisuitetng
	 * .Session, java.lang.String)
	 */
	@Override
	public Task[] getEntity(Session s, String id) throws NotFoundException,
			WPISuiteException {
		List<Model> tasks = db.retrieveAll(new Task(Integer.parseInt(id), id,
				id, null, 0, 0, null, null, 0, null), s.getProject());
		return tasks.toArray(new Task[0]);
	}

	/*
	 * Returns all of the tasks that have been stored.
	 * 
	 * @see
	 * edu.wpi.cs.wpisuitetng.modules.EntityManager#getAll(edu.wpi.cs.wpisuitetng
	 * .Session)
	 */
	@Override
	public Task[] getAll(Session s) throws WPISuiteException {
		// Ask the database to retrieve all objects of the type
		// PostBoardMessage.
		// Passing a dummy PostBoardMessage lets the db know what type of object
		// to retrieve
		// Passing the project makes it only get messages from that project
		List<Model> messages = db.retrieveAll(new Task(0, null, null, null, 0,
				0, null, null, 0, null), s.getProject());

		// Return the list of messages as an array
		return messages.toArray(new Task[0]);
	}

	/*
	 * @see
	 * edu.wpi.cs.wpisuitetng.modules.EntityManager#update(edu.wpi.cs.wpisuitetng
	 * .Session, java.lang.String)
	 */
	@Override
	public Task update(Session s, String content) throws WPISuiteException {
		// TODO
		// This module does not allow PostBoardMessages to be modified, so throw
		// an exception
		throw new WPISuiteException();
	}

	/*
	 * @see
	 * edu.wpi.cs.wpisuitetng.modules.EntityManager#save(edu.wpi.cs.wpisuitetng
	 * .Session, edu.wpi.cs.wpisuitetng.modules.Model)
	 */
	@Override
	public void save(Session s, Task model) throws WPISuiteException {
		db.save(model);
	}

	/*
	 * @see
	 * edu.wpi.cs.wpisuitetng.modules.EntityManager#deleteEntity(edu.wpi.cs.
	 * wpisuitetng.Session, java.lang.String)
	 */
	@Override
	public boolean deleteEntity(Session s, String id) throws WPISuiteException {
		// TODO
		throw new WPISuiteException();
	}

	/*
	 * @see
	 * edu.wpi.cs.wpisuitetng.modules.EntityManager#deleteAll(edu.wpi.cs.wpisuitetng
	 * .Session)
	 */
	@Override
	public void deleteAll(Session s) throws WPISuiteException {
		// TODO
		throw new WPISuiteException();
	}

	/*
	 * @see edu.wpi.cs.wpisuitetng.modules.EntityManager#Count()
	 */
	@Override
	public int Count() throws WPISuiteException {
		// Return the number of PostBoardMessages currently in the database
		return db.retrieveAll(
				new Task(0, null, null, null, 0, 0, null, null, 0, null))
				.size();
	}

	@Override
	public String advancedGet(Session s, String[] args)
			throws WPISuiteException {
		// TODO Auto-generated method stub
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

}
