/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics;

/**
 * Defines the month to be used for an Iteration
 *
 * @version $Revision: 1.0 $
 * @author Rolling Thunder
 */
public enum Month {
	/**
	 * Enumerations representing the months of a year
	 */
	JANUARY("January"),
	FEBRUARY("February"),
	MARCH("March"),
	APRIL("April"),
	MAY("May"),
	JUNE("June"),
	JULY("July"),
	AUGUST("August"),
	SEPTEMBER("September"),
	OCTOBER("October"),
	NOVEMBER("November"),
	DECEMBER("December");
	
	private final String name;
	
	/**
	 * Constructor for Month.
	 * @param month String
	 */
	private Month(String month)
	{
		name = month;
	}
	
	/**
	 * Method toString.
	
	 * @return String */
	public String toString()
	{
		return name;
	}
}
