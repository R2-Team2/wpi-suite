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
 * Various Priorities a requirement can have
 * @author david
 *
 */
public enum RequirementPriority {
	/**
	 * The user has not assigned a priority to the requirement
	 */
	BLANK,
	/**
	 * Indicates the Requirement has a high priority
	 */
	LOW,
	/**
	 * Indicates the Requirement has a medium priority
	 */
	MEDIUM,
	/**
	 * Indicates the Requirement has a high priority
	 */
	HIGH
}
