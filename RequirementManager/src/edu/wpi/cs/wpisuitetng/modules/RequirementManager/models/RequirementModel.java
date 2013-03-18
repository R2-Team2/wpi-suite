/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Chris Casola
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.RequirementManager.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractListModel;

/**
 * This is a model for the requirements manager. It contains all of the requirements.
 * @author Dylan
 *
 */
@SuppressWarnings({"serial"})
public class RequirementModel extends AbstractListModel {
	
	/** The list of requirements */
	private List<Requirement> requirements;
	
	/**
	 * Constructs a new list with no requirements.
	 */
	public RequirementModel() {
		requirements = new ArrayList<Requirement>();
	}

	/**
	 * Adds the given requirement to the list
	 * 
	 * @param newRequirement the new requirement to add
	 */
	public void addRequirement(Requirement newRequirement) {
		// Add the requirement
		requirements.add(newRequirement);
		
		// Notify the model that it has changed so the GUI will be udpated
		this.fireIntervalAdded(this, 0, 0);
	}
	
	/**
	 * Adds the given array of requirements to the list
	 * 
	 * @param requirements the array of requirements to add
	 */
	public void addRequirements(Requirement[] requirements) {
		for (int i = 0; i < requirements.length; i++) {
			this.requirements.add(requirements[i]);
		}
		this.fireIntervalAdded(this, 0, Math.max(getSize() - 1, 0));
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
	}
	
	/**
	 * Returns the requirement at the given index. This method is called
	 * internally by the JList in **RequirementPanel**. Note this method returns
	 * elements in reverse order, so newest requirements are returned first.
	 * 
	 * @see javax.swing.ListModel#getElementAt(int)
	 */
	@Override
	public Object getElementAt(int index) {
		return requirements.get(requirements.size() - 1 - index).toString();
	}

	/**
	 * Returns the number of requirements in the model. Also used internally
	 * by the JList in **RequirementPanel**.
	 * 
	 * @see javax.swing.ListModel#getSize()
	 */
	@Override
	public int getSize() {
		return requirements.size();
	}
}
