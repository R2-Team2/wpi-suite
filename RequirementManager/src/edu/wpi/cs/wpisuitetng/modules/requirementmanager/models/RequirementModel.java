/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.controller.AddRequirementController;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementStatus;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.Iteration;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.ViewEventController;


/**List of Requirements being pulled from the server
 * 
 *
 * @version $Revision: 1.0 $
 * @author justinhess
 */
@SuppressWarnings("serial")
public class RequirementModel extends AbstractListModel{
	
	/**
	 * The list in which all the requirements for a single project are contained
	 */
	private List<Requirement> requirements;
	private int nextID; // the next available ID number for the requirements that are added.
	
	//the static object to allow the requirement model to be 
	private static RequirementModel instance; 

	/**
	 * Constructs an empty list of requirements for the project
	 */
	private RequirementModel (){
		requirements = new ArrayList<Requirement>();
		nextID = 0;
	}
	
	/**
	
	 * @return the instance of the requirement model singleton. */
	public static RequirementModel getInstance()
	{
		if(instance == null)
		{
			instance = new RequirementModel();
		}
		
		return instance;
	}
	
	/**
	 * Adds a single requirement to the requirements of the project
	 * 
	 * @param newReq The requirement to be added to the list of requirements in the project
	 */
	public void addRequirement(Requirement newReq){
		// add the requirement
		requirements.add(newReq);
		try 
		{
			AddRequirementController.getInstance().addRequirement(newReq);
			ViewEventController.getInstance().refreshTable();
			ViewEventController.getInstance().refreshTree();
		}
		catch(Exception e)
		{
			
		}
	}
	/**
	 * Returns the Requirement with the given ID
	 * 
	 * @param id The ID number of the requirement to be returned
	
	 * @return the requirement for the id or null if the requirement is not found */
	public Requirement getRequirement(int id)
	{
		Requirement temp = null;
		// iterate through list of requirements until id is found
		for (int i=0; i < this.requirements.size(); i++){
			temp = requirements.get(i);
			if (temp.getId() == id){
				break;
			}
		}
		return temp;
	}
	/**
	 * Removes the requirement with the given ID
	 * 
	 * @param removeId The ID number of the requirement to be removed from the list of requirements in the project
	 */
	public void removeRequirement(int removeId){
		// iterate through list of requirements until id of project is found
		for (int i=0; i < this.requirements.size(); i++){
			if (requirements.get(i).getId() == removeId){
				// remove the id
				requirements.remove(i);
				break;
			}
		}
		try {
			ViewEventController.getInstance().refreshTable();
			ViewEventController.getInstance().refreshTree();
		}
		catch(Exception e) {}
	}

	/**
	 * Provides the number of elements in the list of requirements for the project. This
	 * function is called internally by the JList in NewRequirementPanel. Returns elements
	 * in reverse order, so the newest requirement is returned first.
	 * 
	
	
	
	 * @return the number of requirements in the project * @see javax.swing.ListModel#getSize() * @see javax.swing.ListModel#getSize() * @see javax.swing.ListModel#getSize()
	 */
	public int getSize() {
		return requirements.size();
	}
	
	/**
	 * 
	 * Provides the next ID number that should be used for a new requirement that is created.
	 * 
	
	 * @return the next open id number */
	public int getNextID()
	{
		
		return this.nextID++;
	}

	/**
	 * This function takes an index and finds the requirement in the list of requirements
	 * for the project. Used internally by the JList in NewRequirementModel.
	 * 
	 * @param index The index of the requirement to be returned
	
	
	
	 * @return the requirement associated with the provided index * @see javax.swing.ListModel#getElementAt(int) * @see javax.swing.ListModel#getElementAt(int) * @see javax.swing.ListModel#getElementAt(int)
	 */
	public Requirement getElementAt(int index) {
		return requirements.get(requirements.size() - 1 - index);
	}

	/**
	 * Removes all requirements from this model
	 * 
	 * NOTE: One cannot simply construct a new instance of
	 * the model, because other classes in this module have
	 * references to it. Hence, we manually remove each requirement
	 * from the model.
	 */
	public void emptyModel() {
		int oldSize = getSize();
		Iterator<Requirement> iterator = requirements.iterator();
		while (iterator.hasNext()) {
			iterator.next();
			iterator.remove();
		}
		this.fireIntervalRemoved(this, 0, Math.max(oldSize - 1, 0));
		try{
			ViewEventController.getInstance().refreshTable();
			ViewEventController.getInstance().refreshTree();
		}
		catch (Exception e) {}
	}
	
	/**
	 * Adds the given array of requirements to the list
	 * 
	 * @param requirements the array of requirements to add
	 */
	public void addRequirements(Requirement[] requirements) {
		for (int i = 0; i < requirements.length; i++) {
			this.requirements.add(requirements[i]);
			if(requirements[i].getId() >= nextID) nextID = requirements[i].getId() + 1;
		}
		this.fireIntervalAdded(this, 0, Math.max(getSize() - 1, 0));
		ViewEventController.getInstance().refreshTable();
		ViewEventController.getInstance().refreshTree();
	}

	/**
	 * Returns the list of the requirements
	
	 * @return the requirements held within the requirementmodel. */
	public List<Requirement> getRequirements() {
		return requirements;
	}

	/**
	 * Returns the list of children for the given requirement.
	 * @param requirement the parent requirement to find children for.
	
	 * @return The list of children. */
	public List<Requirement> getChildren(Requirement requirement) {
		List<Requirement> children = new ArrayList<Requirement>();
		
		for(Requirement possibleChild : requirements)
		{
			if(possibleChild.getParentID() == requirement.getId()) children.add(possibleChild);
		}
		
		return children;
	}
	
	/**
	 * Returns the possible children for the given requirement.
	 * @param req the given requirement
	
	 * @return the list model of possiblechildren */
	public ListModel<Requirement> getPossibleChildren(Requirement req)
	{
		DefaultListModel<Requirement> possibleChildren = new DefaultListModel<Requirement>();
		
		for(Requirement possChild : requirements)
		{
			if(possChild.isAncestor(req.getId()) || possChild.getParentID() != -1) continue;
			if(possChild == req) continue;
			if(possChild.getStatus() == RequirementStatus.COMPLETE || possChild.getStatus() == RequirementStatus.DELETED) continue;
			possibleChildren.addElement(possChild);
		}
		
		return possibleChildren;
	}
	
	
	/**
	 * Returns the possible parents for the given requirement.
	 * @param req the given requirement
	
	 * @return the list model of possibleParents */
	public ListModel<Requirement> getPossibleParents(Requirement req)
	{
		DefaultListModel<Requirement> possibleParents = new DefaultListModel<Requirement>();
		
		for(Requirement possParent : requirements)
		{
			if(possParent.hasAncestor(req.getId())) continue;
			if(possParent == req) continue;
			if(possParent.getStatus() == RequirementStatus.COMPLETE || possParent.getStatus() == RequirementStatus.DELETED) continue;
			possibleParents.addElement(possParent);
		}
		
		return possibleParents;
	}

	/**
	 * Returns the list of requirements that are assigned to the given iteration
	 * @param name the iteration name
	
	 * @return the list of requirements */
	public List<Requirement> getRequirementsForIteration(String name) {
		List<Requirement> reqForIteration = new LinkedList<Requirement>();
		
		boolean backlog = false;
		if(name.trim().length() == 0) backlog = true;
		
		for(Requirement req : requirements)
		{
			if(backlog)
			{
				if(req.getIteration().equals("Backlog") || req.getIteration().trim().equals(""))
				{
					reqForIteration.add(req);
				}
			}
			else
			{
				if(req.getIteration().equals(name))
				{
					reqForIteration.add(req);
				}
			}
		}
		
		return reqForIteration;
	}

	/**
	 * Gets the summed estimates of all requirements in the given iteration.
	 * @param displayIteration the iteration to get requirements for
	
	 * @return the summed estimates of the requirements. */
	public int getRequirementEstimateForIteration(Iteration displayIteration) {
		int estimate = 0;
		List<Requirement> inIteration = getRequirementsForIteration(displayIteration.getName());
		
		for(Requirement req : inIteration)
		{
			estimate += req.getEstimate();
		}
		
		return estimate;
	}
	
}
