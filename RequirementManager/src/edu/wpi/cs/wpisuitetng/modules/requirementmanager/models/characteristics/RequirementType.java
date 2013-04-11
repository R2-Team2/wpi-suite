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
	BLANK(""),
	/** Indicates that the Requirement is an Epic */
	EPIC("Epic"),
	/** Indicates that the Requirement is a theme */
	THEME("Theme"),
	/** Indicates that the Requirement is a user story */
	USERSTORY("User Story"),
	/** Indicates that the Requirement is a non-functional requirement */
	NONFUNCTIONAL("Non-Functional"),
	/** Indicates that the Requirement is a scenario */
	SCENARIO("Scenario");
	
	private final String name;
	
	private RequirementType(String s)
	{
		name = s;
	}
	
	public String toString()
	{
		return name;
	}
}
