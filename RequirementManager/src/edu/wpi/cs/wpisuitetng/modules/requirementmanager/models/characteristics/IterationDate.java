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
 *
 *
 */
public class IterationDate {
	private Month month;
	private int day;
	private int year;
	
	public IterationDate(Month month, int day, int year) {
		this.month = month;
		this.day = day;
		this.year = year;
	}

	/**
	 * @return the month
	 */
	public Month getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(Month month) {
		this.month = month;
	}

	/**
	 * @return the day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(int day) {
		this.day = day;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}
}
