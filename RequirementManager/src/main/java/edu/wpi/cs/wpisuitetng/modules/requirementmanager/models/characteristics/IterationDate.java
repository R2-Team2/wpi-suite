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

import java.util.Date;

/**
 *The information for the Iteration's date
 *
 * @version $Revision: 1.0 $
 * @author justinhess
 */
public class IterationDate {
	private Date date;
	
	/**
	 * Constructor for IterationDate.
	 * @param curr date
	 */
	public IterationDate(Date curr) {
		this.date = curr;
	}

	/**
	
	 *  * @return the date */
	public Date getDate()
	{
		return this.date;
	}
	
	/**
	 * Sets the date
	 * @param date the new date.
	 */
	public void setDate(Date date)
	{
		this.date = date;
	}
}
