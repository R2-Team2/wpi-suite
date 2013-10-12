/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.requirements;

/**
 * @author justinhess
 * @version $Revision: 1.0 $
 */
public interface RequirementPanelListener {
	/**
	 * Performs the necessary operations for if the requirement is deleted.
	 * @param b deleted or not
	 */
	public void fireDeleted(boolean b);

	/**
	 * Performs the necessary operations for if the requirement fields are valid
	 * @param b valid or not
	 */
	public void fireValid(boolean b);

	/**
	 * Performs the necessary operations for if there has been a chance in 
	 * the requirement
	 * @param b changes or not.
	 */
	public void fireChanges(boolean b);
	
	/**
	 * Refreshes the listener
	 */
	public void fireRefresh();
	
	/**
	
	 * @return whether the listener is ready to be removed. */
	public boolean readyToRemove();
}
