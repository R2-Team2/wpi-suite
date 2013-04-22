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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractListModel;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.iterationcontroller.AddIterationController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;

/**
 * List of Iterations being pulled from the server
 * 
 * @author Gabriel McCormick
 * 
 */
@SuppressWarnings({ "serial", "rawtypes" })
public class IterationModel extends AbstractListModel {

	/**
	 * The list in which all the Iterations for a single project are contained
	 */
	private ArrayList<Iteration> listOfIterations;
	private int nextID; // the next available ID number for the Iterations that
						// are added.
	
	private Iteration backlog;

	// the static object to allow the Iteration model to be
	private static IterationModel instance;

	/**
	 * Constructs an empty list of Iterations for the project
	 */
	private IterationModel() {
		backlog = null;
		listOfIterations = new ArrayList<Iteration>();
		nextID = 0;
	}

	/**
	 * @return the instance of the Iteration model singleton.
	 */
	public static IterationModel getInstance() {
		if (instance == null) {
			instance = new IterationModel();
		}

		return instance;
	}
	
	/**
	 * Sets the backlog iteration for the iteration model
	 * @param iteration to be used as the backlog
	 */
	public void setBacklog(Iteration iter)
	{
		this.backlog = iter;
	}

	/**
	 * Adds a single Iteration to the Iterations of the project
	 * 
	 * @param newReq The Iteration to be added to the list of Iterations in the
	 *        project
	 */
	public void addIteration(Iteration newIter) {
		// add the Iteration
		listOfIterations.add(newIter);
		try {
			AddIterationController.getInstance().addIteration(newIter);
		} catch (Exception e) {

		}
	}

	/**
	 * Provides the number of elements in the list of Iterations for the
	 * project. This function is called internally by the JList in
	 * NewIterationPanel. Returns elements in reverse order, so the newest
	 * Iteration is returned first.
	 * 
	 * @return the number of Iterations in the project
	 */
	public int getSize() {
		return listOfIterations.size();
	}

	/**
	 * 
	 * Provides the next ID number that should be used for a new Iteration that
	 * is created.
	 * 
	 * @return the next open id number
	 */
	private int getNextID() {

		return this.nextID++;
	}

	/**
	 * This function takes an index and finds the Iteration in the list of
	 * Iterations for the project. Used internally by the JList in
	 * NewIterationModel.
	 * 
	 * @param index
	 *            The index of the Iteration to be returned
	 * @return the Iteration associated with the provided index
	 */
	public Iteration getElementAt(int index) {
		return listOfIterations.get(listOfIterations.size() - 1 - index);
	}

	/**
	 * Removes all Iterations from this model
	 * 
	 * NOTE: One cannot simply construct a new instance of the model, because
	 * other classes in this module have references to it. Hence, we manually
	 * remove each Iteration from the model.
	 */
	public void emptyModel() {
		int oldSize = getSize();
		Iterator<Iteration> iterator = listOfIterations.iterator();
		while (iterator.hasNext()) {
			iterator.next();
			iterator.remove();
		}
		this.fireIntervalRemoved(this, 0, Math.max(oldSize - 1, 0));
		ViewEventController.getInstance().refreshTable();
	}

	/**
	 * Adds the given array of Iterations to the list
	 * 
	 * @param Iterations
	 *            the array of Iterations to add
	 */
	public void addIterations(Iteration[] Iterations) {
		System.out.println("Got iterations.." + Iterations.length);
		for (int i = 0; i < Iterations.length; i++) {
			if(Iterations[i].getName().equals("Backlog")) backlog = Iterations[i];
			this.listOfIterations.add(Iterations[i]);
			if (Iterations[i].getId() >= nextID) nextID = Iterations[i].getId() + 1;
		}
		
		if(backlog == null)
		{
			backlog = new Iteration(getNextID(), "Backlog");
			addIteration(backlog);
		}
		this.fireIntervalAdded(this, 0, Math.max(getSize() - 1, 0));
	}

	/**
	 * Returns the list of the Iterations
	 * 
	 * @return the Iterations held within the Iterationmodel.
	 */
	public List<Iteration> getIterations() {
		return listOfIterations;
	}
	
	/**
	 * Return the iteration with the specified name
	 * @param forName name of the iteration
	 * @return the iteration
	 */
	public Iteration getIteration(String forName)
	{
		if(forName == null) return backlog;
		if(forName.equals("") || forName.equals("Backlog")) return backlog;
		for(Iteration iter : listOfIterations)
		{
			if(iter.getName().equals(forName)) return iter;
		}
		
		Iteration newIteration = new Iteration(getNextID(), forName);
		
		addIteration(newIteration);

		return newIteration;
	}
}
