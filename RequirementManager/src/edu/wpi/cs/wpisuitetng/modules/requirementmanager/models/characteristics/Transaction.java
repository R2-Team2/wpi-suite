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
 * Class for a single transaction that contains the user, time stamp and message for the Transaction
 *  
 * @version $Revision: 1.0 $
 * @author justinhess
 */
public class Transaction {
	
	private String user;
	private long timestamp;
	private String message;
	
	/**
	 * Constructor for a transaction
	 * @param user User who has executed the transaction
	 * @param timestamp Time at which transaction occurred
	 * @param message Message detailing transaction that occurred
	 */
	public Transaction (String user, long timestamp, String message){
		this.user = user;
		this.timestamp = timestamp;
		this.message = message;
	}
	
	/**
	 * Getter for the transaction user
	
	 * @return Transaction user */
	public String getUser() {
		return user;
	}
	
	/**
	 * Getter for the transaction time stamp
	
	 * @return Transaction time stamp */
	public long getTS() {
		return timestamp;
	}
	
	/**
	 * Getter for the transaction message
	
	 * @return Transaction message */
	public String getMessage() {
		return message;
	}

}
