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
 * Various types that a Requirement can be
 * @author Gabriel McCormick
 */
public enum RequirementType {
	/** Indicates that the Requirement does not have an associated type */
	BLANK,
	/** Indicates that the Requirement is an Epic */
	EPIC,
	/** Indicates that the Requirement is a theme */
	THEME,
	/** Indicates that the Requirement is a user story */
	USERSTORY,
	/** Indicates that the Requirement is a non-functional requirement */
	NONFUNCTIONAL,
	/** Indicates that the Requirement is a scenario */
	SCENARIO
}
