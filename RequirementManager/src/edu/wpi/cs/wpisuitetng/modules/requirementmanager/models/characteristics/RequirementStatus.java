/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics;

/**
 * Various status' that a Requirement can have 
 * @author david
 */
public enum RequirementStatus {
	/**
	 * Indicates that the Requirement has only been created
	 */
	NEW,
	/**
	 * Indicates that the Requirement has been assigned to an iteration
	 */
	INPROGRESS,
	/**
	 * Indicates that the Requirement has been removed from an iteration and returned to the backlog
	 */
	OPEN,
	/**
	 * Indicates that the Requirement has been accepted by the customer
	 */
	COMPLETE,
	/**
	 * Indicates that the Requirement has been deleted
	 */
	DELETED
}
